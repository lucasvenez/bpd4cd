import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;

import br.ufscar.dc.selection.Selection;
import br.ufscar.dc.utils.SparseMatrix;
import br.ufscar.dc.utils.ThreeDimensionalSparseMatrix;

public class PACSTest {

	private static final int NUMBER_OF_NODES = 14;
	private static final int NUMBER_OF_DATA = 9;

	/*
	 * Variables
	 */
	private static final int REQUEST = 0;
	private static final int RESPONSE = 1;

	private static final int IMAGE_PERSISTENCE_REQUEST = 2;
	private static final int IMAGE_PERSISTENCE_RESPONSE = 3;

	private static final int DIAGNOSTIC_PERSISTENCE_REQUEST = 4;
	private static final int DIAGNOSTIC_PERSISTENCE_RESPONSE = 5;

	private static final int ANALYSIS_REQUEST = 6;
	private static final int ANALYSIS_RESPONSE = 7;

	private static final int I = 8;

	/*
	 * Nodes
	 */
	private static final int REC = 0;
	private static final int ASSIGN_1 = 1;
	private static final int LOOP = 2;
	private static final int ASSIGN_2 = 3;
	private static final int PAR = 4;
	private static final int ACT_1 = 5;
	private static final int ACT_2 = 6;
	private static final int IF_1 = 7;
	private static final int ASSIGN_3 = 8;
	private static final int ACT_3 = 9;
	private static final int IF_2 = 10;
	private static final int ASSIGN_4 = 11;
	private static final int ASSIGN_5 = 12;
	private static final int REP = 13;

	@Test
	public void test() throws IOException {

		File file = new File(System.getProperty("user.dir")
				+ "/analyzes/data/selection_costs.csv");

		if (file.exists())
			file.delete();

		/*
		 * Nodes weight
		 */
		SparseMatrix<Double> nodesCostOnPremise = new SparseMatrix<Double>(1,
				NUMBER_OF_NODES, Double.class);

		nodesCostOnPremise.setValue(0, REC, 1.0); // rec
		nodesCostOnPremise.setValue(0, ASSIGN_1, 3.0); // assign#1
		nodesCostOnPremise.setValue(0, LOOP, 2.0); // loop
		nodesCostOnPremise.setValue(0, ASSIGN_2, 3.0); // assign#2
		nodesCostOnPremise.setValue(0, PAR, 1.0); // par
		nodesCostOnPremise.setValue(0, ACT_1, 7.0); // act1
		nodesCostOnPremise.setValue(0, ACT_2, 6.0); // act2
		nodesCostOnPremise.setValue(0, IF_1, 1.0); // if_1
		nodesCostOnPremise.setValue(0, ASSIGN_3, 3.0); // assign#4
		nodesCostOnPremise.setValue(0, ACT_3, 60.0); // act3
		nodesCostOnPremise.setValue(0, IF_2, 1.0); // if_2
		nodesCostOnPremise.setValue(0, ASSIGN_4, 3.0); // assign#5
		nodesCostOnPremise.setValue(0, ASSIGN_5, 3.0); // assign#6
		nodesCostOnPremise.setValue(0, REP, 1.0); // rep

		/*
		 * Data flow
		 */
		ThreeDimensionalSparseMatrix<Boolean> dataFlow = new ThreeDimensionalSparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_NODES, NUMBER_OF_DATA, Boolean.class);

		dataFlow.setValue(REC, LOOP, REQUEST, true);
		dataFlow.setValue(LOOP, ASSIGN_2, REQUEST, true);
		dataFlow.setValue(ASSIGN_2, ASSIGN_3, REQUEST, true);
		dataFlow.setValue(ASSIGN_3, ASSIGN_4, REQUEST, true);
		dataFlow.setValue(ASSIGN_4, ASSIGN_5, REQUEST, true);

		dataFlow.setValue(ASSIGN_1, ASSIGN_4, RESPONSE, true);
		dataFlow.setValue(ASSIGN_4, REP, RESPONSE, true);

		dataFlow.setValue(ASSIGN_1, ASSIGN_2, IMAGE_PERSISTENCE_REQUEST, true);
		dataFlow.setValue(ASSIGN_2, ACT_1, IMAGE_PERSISTENCE_REQUEST, true);

		dataFlow.setValue(ACT_1, IF_1, IMAGE_PERSISTENCE_RESPONSE, true);

		dataFlow.setValue(ASSIGN_1, ASSIGN_2, DIAGNOSTIC_PERSISTENCE_REQUEST,
				true);
		dataFlow.setValue(ASSIGN_2, ACT_2, DIAGNOSTIC_PERSISTENCE_REQUEST, true);

		dataFlow.setValue(ACT_2, IF_1, DIAGNOSTIC_PERSISTENCE_RESPONSE, true);

		dataFlow.setValue(ASSIGN_1, ASSIGN_3, ANALYSIS_REQUEST, true);
		dataFlow.setValue(ASSIGN_3, ACT_3, ANALYSIS_REQUEST, true);

		dataFlow.setValue(ACT_3, IF_2, ANALYSIS_RESPONSE, true);

		dataFlow.setValue(ASSIGN_1, LOOP, I, true);
		dataFlow.setValue(LOOP, ASSIGN_2, I, true);
		dataFlow.setValue(ASSIGN_2, ASSIGN_3, I, true);
		dataFlow.setValue(ASSIGN_3, ASSIGN_4, I, true);
		dataFlow.setValue(ASSIGN_4, ASSIGN_5, I, true);

		/*
		 * Data Constraint
		 */
		SparseMatrix<Boolean> dataConstraint = new SparseMatrix<Boolean>(1,
				NUMBER_OF_DATA, Boolean.class);
		dataConstraint.setValue(0, REQUEST, true);
		dataConstraint.setValue(0, RESPONSE, false);

		dataConstraint.setValue(0, IMAGE_PERSISTENCE_REQUEST, true);
		dataConstraint.setValue(0, IMAGE_PERSISTENCE_RESPONSE, true);

		dataConstraint.setValue(0, DIAGNOSTIC_PERSISTENCE_REQUEST, true);
		dataConstraint.setValue(0, DIAGNOSTIC_PERSISTENCE_RESPONSE, true);

		dataConstraint.setValue(0, ANALYSIS_REQUEST, false);
		dataConstraint.setValue(0, ANALYSIS_RESPONSE, false);

		dataConstraint.setValue(0, I, false);

		/*
		 * Data size
		 */
		SparseMatrix<Double> dataSize = new SparseMatrix<Double>(1,
				NUMBER_OF_DATA, Double.class);

		dataSize.setValue(0, REQUEST, 23.4 + 2.86 * Math.pow(10, -6) + 0.06);
		dataSize.setValue(0, RESPONSE, 23.4);

		dataSize.setValue(0, IMAGE_PERSISTENCE_REQUEST, 24.0);
		dataSize.setValue(0, IMAGE_PERSISTENCE_RESPONSE,
				7.63 * Math.pow(10, -6));

		dataSize.setValue(0, DIAGNOSTIC_PERSISTENCE_REQUEST,
				Math.pow(10, -6) + 0.06);
		dataSize.setValue(0, DIAGNOSTIC_PERSISTENCE_RESPONSE,
				7.63 * Math.pow(10, -6));

		dataSize.setValue(0, ANALYSIS_REQUEST, 24.0);
		dataSize.setValue(0, ANALYSIS_RESPONSE, 1.91 * Math.pow(10, -6));

		dataSize.setValue(0, I, 3.81 * Math.pow(10, -6));

		/*
		 * Data storage
		 */
		SparseMatrix<Boolean> dataStorage = new SparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_DATA, Boolean.class);

		dataStorage.setValue(ACT_1, IMAGE_PERSISTENCE_REQUEST, true);
		dataStorage.setValue(ACT_2, DIAGNOSTIC_PERSISTENCE_REQUEST, true);

		@SuppressWarnings("unchecked")
		ArrayList<Double>[] costs = new ArrayList[] { new ArrayList<Double>(),
				new ArrayList<Double>(), new ArrayList<Double>() };

		/*
		 * Cloud configurations
		 */
		final double cloudRAM[] = new double[] { 7.00, 68.40, 117.00 };

		final int cloudVCPUs[] = new int[] { 8, 8, 16 };

		final double cloudVCPUSFrequency[] = new double[] { 2.75, 3.58, 2.41 };

		double cloudStorageCost = 0.12;

		double cloudTransmissionCost = 0.01;

		double cloudInstanceCost[] = new double[] { 0.58, 1.64, 4.60 };

		/*
		 * Premise configuration
		 */
		final double premiseRAM = 1.00;

		final int premiseVCPUS = 1;

		final double premiseVCPUSFrequency = 0.80;

		SparseMatrix<Boolean> dataNodeRelation = Selection
				.calculateDataNodeRelation(dataFlow);

		double bandwidth = 125;

		double executionCostWeight = 0.09;

		double monetaryCostWeight = 0.01;

		double privacityCostWeight = 0.90;

		for (int i = 0; i < Math.pow(2, NUMBER_OF_NODES - 2); i++) {

			SparseMatrix<Boolean> tmpNodesLocation = Selection
					.generateNodesLocation("0"
							+ Selection.generateBitsLocation(i,
									NUMBER_OF_NODES - 2) + "0");

			SparseMatrix<Boolean> dataLocation = Selection
					.calculateDataLocation(dataFlow, tmpNodesLocation);

			for (short j = 0; j < 3; j++) {

				/*
				 * Nodes cost on cloud
				 */
				SparseMatrix<Double> nodesCostOnCloud = Selection
						.calculateNodesCostOnCloud(nodesCostOnPremise,
								cloudRAM[j], cloudVCPUs[j],
								cloudVCPUSFrequency[j], premiseRAM,
								premiseVCPUS, premiseVCPUSFrequency);

				/*
				 * Calculating cost.
				 */
				costs[j].add(Selection.calculateCost(tmpNodesLocation,
						nodesCostOnPremise, nodesCostOnCloud, dataConstraint,
						dataStorage, dataSize, dataLocation, dataNodeRelation,
						bandwidth, cloudStorageCost, cloudTransmissionCost,
						cloudInstanceCost[j], executionCostWeight,
						monetaryCostWeight, privacityCostWeight));

				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(file, true)));
				out.print("," + (j + 1) + "\n");
				out.close();
			}
		}
	}
}
