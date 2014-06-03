package br.ufscar.dc.selection;

import static br.ufscar.dc.utils.Convertion.booleanToInt;
import static br.ufscar.dc.utils.Convertion.charToBoolean;
import static java.lang.Math.abs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.utils.SparseMatrix;
import br.ufscar.dc.utils.ThreeDimensionalSparseMatrix;

public class Selection {

	public static Graph selectLocation(Graph graph) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Calculation of the cost of the Workflow distribution.
	 * 
	 * @param nodesLocation
	 *            is a sparse matrix that indicates where a node is located
	 *            (cloud or on-premise).
	 * @param nodesCostOnPremise
	 *            is an array that indicates each node cost on user premise
	 *            (values between 0 and 10).
	 * @param nodesCostOnCloud
	 *            is an array that indicates each node cost on cloud (values
	 *            between 0 and 10).
	 * @param dataNodeRelation
	 *            is an sparse matrix that indicates the relation between data
	 *            elements and each node.
	 * @param dataRestriction
	 *            is an array that indicates the restriction of each data
	 *            element.
	 * @param dataStorage
	 *            is an array that indicates if the data is persisted or not.
	 * 
	 * @param bandwidth
	 * 
	 * @param cloudStorageCost
	 * 
	 * @param cloudTranmissionCost
	 * 
	 * @param cloudInstanceCost
	 * 
	 * @param executionCostWeight
	 * 
	 * @param monetaryCostWeight
	 * 
	 * @param privacityCostWeight
	 * 
	 * @return
	 */
	public static double calculateCost(SparseMatrix<Boolean> nodesLocation, // s_i
			SparseMatrix<Double> nodesCostOnPremise, // n_i
			SparseMatrix<Double> nodesCostOnCloud, // k_i
			SparseMatrix<Boolean> dataConstraint, // c_j
			SparseMatrix<Boolean> dataStorage, // P(i,j)
			SparseMatrix<Double> dataSize, // d_j
			SparseMatrix<Boolean> dataLocation, // Q(i,j)
			SparseMatrix<Boolean> dataNodeRelation, // R(i,j)
			double bandwidth, // b
			double cloudStorageCost, // cost_s
			double cloudTransmissionCost, // cost_t
			double cloudInstanceCost, // cost_h
			double executionCostWeight, // w_e
			double monetaryCostWeight, // w_m
			double privacityCostWeight // w_p
	) {

		long numberOfNodes = nodesLocation.getNumberOfColumns();
		long numberOfData = dataSize.getNumberOfColumns();

		/* Checking parameters */
		if (nodesCostOnPremise.getNumberOfColumns() != numberOfNodes
				|| nodesCostOnPremise.getNumberOfLines() != 1
				|| nodesCostOnCloud.getNumberOfColumns() != numberOfNodes
				|| nodesCostOnCloud.getNumberOfLines() != 1
				|| dataConstraint.getNumberOfColumns() != numberOfData
				|| dataConstraint.getNumberOfLines() != 1
				|| dataStorage.getNumberOfColumns() != numberOfData
				|| dataStorage.getNumberOfLines() != numberOfNodes
				|| dataLocation.getNumberOfColumns() != numberOfData
				|| dataLocation.getNumberOfLines() != numberOfNodes
				|| dataNodeRelation.getNumberOfColumns() != numberOfData
				|| dataNodeRelation.getNumberOfLines() != numberOfNodes
				|| bandwidth <= 0.00 || cloudStorageCost < 0.00
				|| cloudTransmissionCost < 0.00 || cloudInstanceCost < 0.00
				|| executionCostWeight < 0.0 || monetaryCostWeight < 0.00
				|| privacityCostWeight < 0.00) {

			throw new InvalidParameterException(
					"Check the length of matrix and variables values.");
		}

		double executionCost = 0.0, monetaryCost = 0.0, privacityCost = 0.0;

		/* summing the nodes weight */{

			for (long i = 0; i < numberOfNodes; i++) {

				executionCost += nodesCostOnCloud.getValue(0, i)
						* booleanToInt(nodesLocation.getValue(0, i))
						+ nodesCostOnPremise.getValue(0, i)
						* (1 - booleanToInt(nodesLocation.getValue(0, i)));

				monetaryCost += cloudInstanceCost
						* nodesCostOnCloud.getValue(0, i)
						* booleanToInt(nodesLocation.getValue(0, 1));

				for (long j = 0; j < numberOfData; j++) {

					executionCost += (dataSize.getValue(0, j) / bandwidth)
							* booleanToInt(dataNodeRelation.getValue(i, j))
							* abs(booleanToInt(nodesLocation.getValue(0, i))
									- booleanToInt(dataLocation.getValue(i, j)));

					monetaryCost += (cloudTransmissionCost
							* dataSize.getValue(0, j)
							* abs(booleanToInt(nodesLocation.getValue(0, i))
									- booleanToInt(dataLocation.getValue(i, j))) * (1 - booleanToInt(dataLocation
							.getValue(i, j))))
							+ (cloudStorageCost
									* dataSize.getValue(0, j)
									* booleanToInt(nodesLocation.getValue(0, i)) * booleanToInt(dataStorage
										.getValue(i, j)));

					privacityCost += booleanToInt(dataConstraint.getValue(0, j))
							* booleanToInt(dataNodeRelation.getValue(i, j))
							* booleanToInt(nodesLocation.getValue(0, i));
				}
			}
		}

		StringBuffer buffer = new StringBuffer();

		double result = executionCostWeight * executionCost
				+ monetaryCostWeight * monetaryCost + privacityCostWeight
				* privacityCost;

		buffer.append(executionCost).append(",").append(monetaryCost)
				.append(",").append(privacityCost).append(",").append(result)
				.append(",").append("\"" + nodesLocation.toString() + "\"");

		File file = new File(System.getProperty("user.dir")
				+ "/analyzes/data/selection_costs.csv");

		try {

			if (!file.exists())
				file.createNewFile();

			 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			 out.print(buffer.toString());
			 out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static SparseMatrix<Double> calculateNodesCostOnCloud(
			SparseMatrix<Double> nodesCostOnPremise, double cloudRAM,
			int cloudVCPUs, double cloudVCPUSFrequency, double premiseRAM,
			int premiseVCPUS, double premiseVCPUSFrequency) {

		if (cloudRAM <= 0 || cloudVCPUs <= 0 || cloudVCPUSFrequency <= 0
				|| premiseRAM <= 0 || premiseVCPUS <= 0
				|| premiseVCPUSFrequency <= 0)
			throw new InvalidParameterException(
					"Invalid parameter for the function \"nodesCostOnCloudCalculation\".");

		SparseMatrix<Double> result = new SparseMatrix<Double>(1,
				nodesCostOnPremise.getNumberOfColumns(), Double.class);

		double a = (premiseVCPUS * premiseVCPUSFrequency)
				/ (cloudVCPUs * cloudVCPUSFrequency);

		double b = premiseRAM / cloudRAM;

		for (int i = 0; i < nodesCostOnPremise.getNumberOfColumns(); i++)
			result.setValue(0, i,
					((a + b) / 2) * nodesCostOnPremise.getValue(0, i));

		return result;
	}

	public static String generateBitsLocation(int configuration,
			int numberOfNodes) {
		if (configuration < 0 || numberOfNodes < 1)
			throw new InvalidParameterException(
					"\"configuration\" should be greater than or equals to 0 and \"numberOfNodes\" should be greater than 0.");

		String bin = Integer.toBinaryString(configuration);

		int limit = Integer.toBinaryString((int) Math.pow(2, numberOfNodes))
				.length() - bin.length() - 1;

		for (int j = 0; j < limit; j++)
			bin = ("0" + bin);

		return bin;
	}

	public static SparseMatrix<Boolean> generateNodesLocation(
			int configuration, int numberOfNodes) {

		if (configuration < 0 || numberOfNodes < 1)
			throw new InvalidParameterException(
					"\"configuration\" should be greater than or equals to 0 and \"numberOfNodes\" should be greater than 0.");

		String bin = Integer.toBinaryString(configuration);

		int limit = Integer.toBinaryString((int) Math.pow(2, numberOfNodes))
				.length() - bin.length() - 1;

		for (int j = 0; j < limit; j++)
			bin = ("0" + bin);

		SparseMatrix<Boolean> result = new SparseMatrix<Boolean>(1,
				numberOfNodes, Boolean.class);

		for (int i = 0; i < bin.length(); i++)
			result.setValue(0, i, charToBoolean(bin.charAt(i)));

		return result;
	}

	public static SparseMatrix<Boolean> generateNodesLocation(String bin) {

		int numberOfNodes = bin.length();

		int limit = Integer.toBinaryString((int) Math.pow(2, numberOfNodes))
				.length() - bin.length() - 1;

		for (int j = 0; j < limit; j++)
			bin = ("0" + bin);

		SparseMatrix<Boolean> result = new SparseMatrix<Boolean>(1,
				numberOfNodes, Boolean.class);

		for (int i = 0; i < bin.length(); i++)
			result.setValue(0, i, charToBoolean(bin.charAt(i)));

		return result;
	}

	public static SparseMatrix<Boolean> calculateDataLocation(
			ThreeDimensionalSparseMatrix<Boolean> dataFlow,
			SparseMatrix<Boolean> nodesLocation) {

		if (dataFlow == null
				|| nodesLocation == null
				|| (dataFlow.getNumberOfLines() & dataFlow.getNumberOfColumns()) != nodesLocation
						.getNumberOfColumns())
			throw new InvalidParameterException("Invalid paramters");

		final int NUMBER_OF_NODES = dataFlow.getNumberOfLines();
		final int NUMBER_OF_DATA = dataFlow.getNumberOfWidth();

		SparseMatrix<Boolean> result = new SparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_DATA, Boolean.class);

		for (int o = 0; o < NUMBER_OF_NODES; o++)
			for (int i = 0; i < NUMBER_OF_NODES; i++)
				for (int p = 0; p < NUMBER_OF_DATA; p++)
					result.setValue(o, p, true && nodesLocation.getValue(0, i)
							|| result.getValue(o, p));

		return result;
	}

	public static SparseMatrix<Boolean> calculateDataNodeRelation(
			ThreeDimensionalSparseMatrix<Boolean> dataFlow) {

		if (dataFlow == null)
			throw new InvalidParameterException(
					"dataFlow parameter should not be null.");

		final int NUMBER_OF_NODES = dataFlow.getNumberOfLines();
		final int NUMBER_OF_DATA = dataFlow.getNumberOfWidth();

		SparseMatrix<Boolean> result = new SparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_DATA, Boolean.class);

		for (int i = 0; i < NUMBER_OF_NODES; i++)
			for (int j = i + 1; j < NUMBER_OF_NODES; j++)
				for (int k = 0; k < NUMBER_OF_DATA; k++) {
					if (dataFlow.getValue(i, j, k)) {
						result.setValue(i, k, true);
						result.setValue(j, k, true);
					}
				}

		return result;
	}
}
