package br.ufscar.dc.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SparseMatrixTest {

	@Test
	public void test() {
		SparseMatrix<Boolean> booleanMatrix = new SparseMatrix<Boolean>(2, 1, Boolean.class);

		booleanMatrix.setValue(0, 0, true);

		assertEquals(true, booleanMatrix.getValue(0, 0));
		assertEquals(false, booleanMatrix.getValue(1, 0));

		try {
			booleanMatrix.getValue(1, 2);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

		try {
			assertEquals(booleanMatrix.getValue(1, 1), false);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

		try {
			booleanMatrix.setValue(1, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		SparseMatrix<Integer> integerMatrix = new SparseMatrix<Integer>(2, 1, Integer.class);

		integerMatrix.setValue(0, 0, 1);

		assertEquals(new Integer(1), integerMatrix.getValue(0, 0));
		assertEquals(new Integer(0), integerMatrix.getValue(1, 0));

		try {
			integerMatrix.getValue(1, 2);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

		try {
			assertEquals(new Integer(0), integerMatrix.getValue(2, 2));
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

		try {
			integerMatrix.setValue(1, 0, null);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}
