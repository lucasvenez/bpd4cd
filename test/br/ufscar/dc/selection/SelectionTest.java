package br.ufscar.dc.selection;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufscar.dc.utils.SparseMatrix;

public class SelectionTest {

	@Test
	public void testNodesCostOnCloudCalculation() {

		SparseMatrix<Double> nodesCostOnPremise = new SparseMatrix<Double>(1,
				4, Double.class);

		nodesCostOnPremise.setValue(0, 0, 0.0);
		nodesCostOnPremise.setValue(0, 1, 3.0);
		nodesCostOnPremise.setValue(0, 2, 10.0);
		nodesCostOnPremise.setValue(0, 3, 1.0);

		SparseMatrix<Double> nodesCostInCloud = Selection
				.calculateNodesCostOnCloud(nodesCostOnPremise, 16, 8, 2.2, 1,
						1, 1.8);

		assertEquals(4, nodesCostInCloud.getNumberOfColumns());
		assertEquals(1, nodesCostInCloud.getNumberOfLines());
		assertEquals(new Double(0.0), nodesCostInCloud.getValue(0, 0));
	}
	
	@Test
	public void testCostCalculation() {

		final int NUMBER_OF_NODES = 4;
		final int NUMBER_OF_VARS = 4;

		/*
		 * 
		 */
		SparseMatrix<Double> nodesCostOnPremise = new SparseMatrix<Double>(1,
				NUMBER_OF_NODES, Double.class); // n_i

		nodesCostOnPremise.setValue(0, 0, 01.00);
		nodesCostOnPremise.setValue(0, 1, 05.00);
		nodesCostOnPremise.setValue(0, 2, 15.00);
		nodesCostOnPremise.setValue(0, 3, 01.00);

		/*
		 * 
		 */
		SparseMatrix<Double> nodesCostOnCloud = Selection
				.calculateNodesCostOnCloud(nodesCostOnPremise, 117, 16, 2.41,
						1, 1, .8); // k_i

		/*
		 * 
		 */
		SparseMatrix<Boolean> dataConstraint = new SparseMatrix<Boolean>(1,
				NUMBER_OF_VARS, Boolean.class); // c_j
		
		dataConstraint.setValue(0, 0, true);
		dataConstraint.setValue(0, 1, false);
		dataConstraint.setValue(0, 2, false);
		dataConstraint.setValue(0, 3, false);

		/*
		 * 
		 */
		SparseMatrix<Boolean> dataStorage = new SparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_VARS, Boolean.class); // P(i;j)
		
		dataStorage.setValue(0, 0, false);
		dataStorage.setValue(1, 0, false);
		dataStorage.setValue(2, 0, false);
		dataStorage.setValue(3, 0, false);
		
		dataStorage.setValue(0, 1, false);
		dataStorage.setValue(1, 1, false);
		dataStorage.setValue(2, 1, true);
		dataStorage.setValue(3, 1, false);
		
		dataStorage.setValue(0, 2, false);
		dataStorage.setValue(1, 2, false);
		dataStorage.setValue(2, 2, false);
		dataStorage.setValue(3, 2, false);
		
		dataStorage.setValue(0, 3, false);
		dataStorage.setValue(1, 3, false);
		dataStorage.setValue(2, 3, false);
		dataStorage.setValue(3, 3, false);

		/*
		 * 
		 */
		SparseMatrix<Double> dataSize = new SparseMatrix<Double>(1,
				NUMBER_OF_VARS, Double.class); // d_j
		
		dataSize.setValue(0, 0, 01.0);
		dataSize.setValue(0, 1, 04.0);
		dataSize.setValue(0, 2, 10.0);
		dataSize.setValue(0, 3, 02.0);

		/*
		 * 
		 */
		SparseMatrix<Boolean> dataLocation = new SparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_VARS, Boolean.class); // Q(i;j)

		dataLocation.setValue(0, 0, false);
		dataLocation.setValue(1, 0, false);
		dataLocation.setValue(2, 0, false);
		dataLocation.setValue(3, 0, false);
		
		dataLocation.setValue(0, 1, false);
		dataLocation.setValue(1, 1, false);
		dataLocation.setValue(2, 1, true);
		dataLocation.setValue(3, 1, false);
		
		dataLocation.setValue(0, 2, false);
		dataLocation.setValue(1, 2, false);
		dataLocation.setValue(2, 2, false);
		dataLocation.setValue(3, 2, false);
		
		dataLocation.setValue(0, 3, false);
		dataLocation.setValue(1, 3, false);
		dataLocation.setValue(2, 3, false);
		dataLocation.setValue(3, 3, false);
		
		/*
		 * 
		 */
		SparseMatrix<Boolean> dataNodeRelation = new SparseMatrix<Boolean>(
				NUMBER_OF_NODES, NUMBER_OF_VARS, Boolean.class); // R(i;j)

		dataNodeRelation.setValue(0, 0, true);
		dataNodeRelation.setValue(1, 0, true);
		dataNodeRelation.setValue(2, 0, false);
		dataNodeRelation.setValue(3, 0, false);
		
		dataNodeRelation.setValue(0, 1, true);
		dataNodeRelation.setValue(1, 1, false);
		dataNodeRelation.setValue(2, 1, true);
		dataNodeRelation.setValue(3, 1, false);
		
		dataNodeRelation.setValue(0, 2, false);
		dataNodeRelation.setValue(1, 2, false);
		dataNodeRelation.setValue(2, 2, true);
		dataNodeRelation.setValue(3, 2, false);
		
		dataNodeRelation.setValue(0, 3, false);
		dataNodeRelation.setValue(1, 3, false);
		dataNodeRelation.setValue(2, 3, true);
		dataNodeRelation.setValue(3, 3, true);
		
		double bandwidth = 40; // b

		double cloudStorageCost = 0.01; // cost_s

		double cloudTransmissionCost = 0.12; // cost_t

		double cloudInstanceCost = 4.60; // cost_h

		double executionCostWeight = .25; // w_e

		double monetaryCostWeight = .25; // w_m

		double privacityCostWeight = 50; // w_p

		for (int i = 0; i < Math.pow(2,NUMBER_OF_NODES); i++) {

			SparseMatrix<Boolean> nodesLocation = Selection
					.generateNodesLocation(i, NUMBER_OF_NODES);

			double cost = Selection.calculateCost(nodesLocation,
					nodesCostOnPremise, //
					nodesCostOnCloud,//
					dataConstraint, //
					dataStorage, //
					dataSize, //
					dataLocation, //
					dataNodeRelation, //
					bandwidth, //
					cloudStorageCost, //
					cloudTransmissionCost, //
					cloudInstanceCost, //
					executionCostWeight, //
					monetaryCostWeight, //
					privacityCostWeight //
					);
			
			assertFalse(cost < 0.0);
		}
	}
}
