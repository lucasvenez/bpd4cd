package br.ufscar.dc.selection;

import static br.ufscar.dc.utils.Convertion.booleanToInt;
import static br.ufscar.dc.utils.Math.longToBooleanMatrix;
import static br.ufscar.dc.utils.Math.min;
import static java.lang.Math.abs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.utils.SparseMatrix;
import br.ufscar.dc.utils.ThreeDimensionalSparseMatrix;

public class Selection {

	private Graph monolithicProcess;
	
	private PremisseServerConfiguration serverOnPremise;
	
	private CloudInstanceConfiguration cloudInstance;
	
	private ThreeDimensionalSparseMatrix<Boolean> dataFlow;
	
	private SparseMatrix<Double>  executionTimeOnPremise; // n_i
	
	private SparseMatrix<Double>  executionTimeOnCloud;   // k_i
	
	private SparseMatrix<Boolean> dataConstraint;         // c_j
	
	private SparseMatrix<Boolean> dataStorage;            // P(i,j)
	
	private SparseMatrix<Double>  dataSize;               // d_j
	
	private SparseMatrix<Boolean> dataLocation;           // Q(i,j)
	
	private SparseMatrix<Boolean> dataNodeRelation;       // R(i,j)
	
	private CostsWeight weights;

	public Selection() {
	
	}
	
	public Selection(Graph monolithicProcess) {
		this.monolithicProcess = monolithicProcess;
	}
	
	public Selection(Graph monolithicProcess,
			PremisseServerConfiguration serverOnPremise,
			CloudInstanceConfiguration cloudInstance,
			SparseMatrix<Double> nodesCostOnPremise,
			SparseMatrix<Boolean> dataConstraint,
			SparseMatrix<Boolean> dataStorage, 
			SparseMatrix<Double> dataSize,
			CostsWeight weights) {

		this.monolithicProcess      = monolithicProcess;
		this.serverOnPremise        = serverOnPremise;
		this.cloudInstance          = cloudInstance;
		this.executionTimeOnPremise = nodesCostOnPremise;
		this.dataConstraint         = dataConstraint;
		this.dataStorage            = dataStorage;
		this.dataSize               = dataSize;
		this.weights                = weights;
	}

	public Graph performLocationSelection() {
		
		calculateExecutionTimeOnCloud();
		calculateDataNodeRelation();
		
		Map<Double, SparseMatrix<Boolean>> costs = new HashMap<Double, SparseMatrix<Boolean>>();
		
		for(long i = 0; i <= monolithicProcess.getNumberOfNodes(); i++) {
			
			SparseMatrix<Boolean> distribution = 
					longToBooleanMatrix(i, monolithicProcess.getNumberOfNodes());
			
			if (checkNodeLocationRestriction(distribution)) {
				continue;
			} else {
				calculateDataLocationMatrix(distribution);
				costs.put(performCostCalculation(distribution), distribution);
			}
		}
		
		Double min = null;
		
		for(Iterator<Double> i = costs.keySet().iterator(); i.hasNext(); ) {
			min = min(min, i.next());
		}
				
		monolithicProcess.setDistributionLocation(costs.get(min));
		
		return monolithicProcess;
	}
	
	private boolean checkNodeLocationRestriction(SparseMatrix<Boolean> distribution) {
		return false;
	}
	
	/**
	 * Calculation of the cost of the Workflow distribution.
	 * 
	 * @param nodesLocation
	 *            is a sparse matrix that indicates where a node is located
	 *            (cloud or on-premise).
	 * 
	 * @return
	 */
	private double performCostCalculation(SparseMatrix<Boolean> nodesLocation) {

		/* Checking parameters */
		if (attributesAreValid()) {

			throw new InvalidParameterException(
					"Check the length of matrix and variables values.");
		}

		double executionCost = 0.0, monetaryCost = 0.0, privacityCost = 0.0;

		/* summing the nodes weight */{

			for (long i = 0; i < monolithicProcess.getNumberOfDataItems(); i++) {

				executionCost += executionTimeOnCloud.getValue(0, i)
						* booleanToInt(nodesLocation.getValue(0, i))
						+ executionTimeOnPremise.getValue(0, i)
						* (1 - booleanToInt(nodesLocation.getValue(0, i)));

				monetaryCost += cloudInstance.getPricePerHour()
						* executionTimeOnCloud.getValue(0, i)
						* booleanToInt(nodesLocation.getValue(0, 1));

				for (long j = 0; j < monolithicProcess.getNumberOfDataItems(); j++) {

					executionCost += (dataSize.getValue(0, j) / serverOnPremise.getBandwidth())
							* booleanToInt(dataNodeRelation.getValue(i, j))
							* abs(booleanToInt(nodesLocation.getValue(0, i))
									- booleanToInt(dataLocation.getValue(i, j)));

					monetaryCost += (cloudInstance.getPriceForTransfering()
							* dataSize.getValue(0, j)
							* abs(booleanToInt(nodesLocation.getValue(0, i))
									- booleanToInt(dataLocation.getValue(i, j))) * (1 - booleanToInt(dataLocation
							.getValue(i, j))))
							+ (cloudInstance.getPriceForStoring()
									* dataSize.getValue(0, j)
									* booleanToInt(nodesLocation.getValue(0, i)) * booleanToInt(dataStorage
										.getValue(i, j)));

					privacityCost += booleanToInt(dataConstraint.getValue(0, j))
							* booleanToInt(dataNodeRelation.getValue(i, j))
							* booleanToInt(nodesLocation.getValue(0, i));
				}
			}
		}

		double result = weights.getExecutionCostWeight() * executionCost + 
				        weights.getMonetaryCostWeight()  * monetaryCost  + 
				        weights.getPrivacityCostWeight() * privacityCost;

		/* Code for debugging*/ {

			StringBuffer buffer = new StringBuffer();
			
			buffer.append(executionCost)
			      .append(",")
			      .append(monetaryCost)
				  .append(",")
				  .append(privacityCost)
				  .append(",")
				  .append(result)
				  .append(",")
				  .append("\"" + nodesLocation.toString() + "\"");
			
			File file = new File(System.getProperty("user.dir") + "/analyzes/data/selection_costs.csv");
	
			try {
	
				if (!file.exists())
					file.createNewFile();
	
				 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				 out.print(buffer.toString());
				 out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	private void calculateExecutionTimeOnCloud() {

		if (cloudInstance.getRam() <= 0 
				|| cloudInstance.getvCPUs() <= 0 
				|| cloudInstance.getFrequency() <= 0
				|| serverOnPremise.getRam() <= 0 
				|| serverOnPremise.getvCPUs() <= 0
				|| serverOnPremise.getFrequency() <= 0) {
			
			throw new InvalidParameterException(
					"Invalid parameter for the function \"executionTimeOnCloudCalculation\".");
		}

		SparseMatrix<Double> result = new SparseMatrix<Double>(1,
				executionTimeOnPremise.getNumberOfColumns(), Double.class);

		double a = (serverOnPremise.getvCPUs() * serverOnPremise.getFrequency())
				/ (cloudInstance.getvCPUs() * cloudInstance.getFrequency());

		double b = serverOnPremise.getRam() / cloudInstance.getRam();

		for (int i = 0; i < executionTimeOnPremise.getNumberOfColumns(); i++)
			result.setValue(0, i,
					((a + b) / 2) * executionTimeOnPremise.getValue(0, i));

		this.executionTimeOnCloud = result;
	}

	private void calculateDataLocationMatrix(SparseMatrix<Boolean> nodesLocation) {

		if (dataFlow == null
				|| nodesLocation == null
				|| (dataFlow.getNumberOfLines() & dataFlow.getNumberOfColumns()) != nodesLocation
						.getNumberOfColumns())
			throw new InvalidParameterException("Invalid paramters");

		SparseMatrix<Boolean> result = new SparseMatrix<Boolean>(
				monolithicProcess.getNumberOfNodes(), monolithicProcess.getNumberOfDataItems(), Boolean.class);

		for (int o = 0; o < monolithicProcess.getNumberOfNodes(); o++)
			for (int i = 0; i < monolithicProcess.getNumberOfNodes(); i++)
				for (int p = 0; p < monolithicProcess.getNumberOfDataItems(); p++)
					result.setValue(o, p, true && nodesLocation.getValue(0, i)
							|| result.getValue(o, p));

		this.dataLocation = result;
	}

	private void calculateDataNodeRelation() {

		if (dataFlow == null)
			throw new InvalidParameterException(
					"dataFlow parameter should not be null.");

		SparseMatrix<Boolean> result = new SparseMatrix<Boolean>(
				monolithicProcess.getNumberOfNodes(), monolithicProcess.getNumberOfDataItems(), Boolean.class);

		for (int i = 0; i < monolithicProcess.getNumberOfNodes(); i++)
			for (int j = i + 1; j < monolithicProcess.getNumberOfNodes(); j++)
				for (int k = 0; k < monolithicProcess.getNumberOfDataItems(); k++) {
					if (dataFlow.getValue(i, j, k)) {
						result.setValue(i, k, true);
						result.setValue(j, k, true);
					}
				}

		this.dataNodeRelation = result;
	}

	private boolean attributesAreValid() {
		
		return executionTimeOnPremise.getNumberOfColumns()   == monolithicProcess.getNumberOfNodes()
			   && executionTimeOnPremise.getNumberOfLines()  == 1
			   && executionTimeOnCloud.getNumberOfColumns()  == monolithicProcess.getNumberOfNodes()
			   && executionTimeOnCloud.getNumberOfLines()    == 1
			   && dataConstraint.getNumberOfColumns()        == monolithicProcess.getNumberOfDataItems()
			   && dataConstraint.getNumberOfLines()          == 1
			   && dataStorage.getNumberOfColumns()           == monolithicProcess.getNumberOfDataItems()
			   && dataStorage.getNumberOfLines()             == monolithicProcess.getNumberOfNodes()
			   && dataLocation.getNumberOfColumns()          == monolithicProcess.getNumberOfDataItems()
			   && dataLocation.getNumberOfLines()            == monolithicProcess.getNumberOfNodes()
			   && dataNodeRelation.getNumberOfColumns()      == monolithicProcess.getNumberOfDataItems()
			   && dataNodeRelation.getNumberOfLines()        == monolithicProcess.getNumberOfNodes()
			   && serverOnPremise.getBandwidth()             > 0 
			   && cloudInstance.getPriceForStoring()         > 0
			   && cloudInstance.getPriceForTransfering()     > 0 
			   && cloudInstance.getPricePerHour()            > 0
			   && weights.getExecutionCostWeight()           > 0 
			   && weights.getMonetaryCostWeight()            > 0
			   && weights.getPrivacityCostWeight()           > 0;
	}
}
