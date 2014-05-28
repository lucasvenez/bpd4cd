package br.ufscar.dc.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

public class ThreeDimensionalSparseMatrix<E> {

	private int numberOfLines = 0;
	private int numberOfColumns = 0;
	private int numberOfWidth = 0;

	private HashMap<Long, E> values = new HashMap<Long, E>();
	private Class<E> type;

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public ThreeDimensionalSparseMatrix(int x, int y, int z, Class<E> type) {
		this.numberOfLines = x;
		this.numberOfColumns = y;
		this.numberOfWidth = z;
		this.type = type;
	}

	/**
	 * Method used to get values in the matrix.
	 * 
	 * @param line
	 *            is the index of the line.
	 * @param column
	 *            is the index of the column.
	 * @return the values in the (index, column) cell.
	 */
	@SuppressWarnings("unchecked")
	public E getValue(int x, int y, int z) {

		long index = indexCalculation(x, y, z);

		if (x < 0 || x >= this.numberOfLines)
			throw new IndexOutOfBoundsException(
					"The parameter \"x\" should be greater than or equals zero and less than "
							+ numberOfLines + ". ");

		if (y < 0 || y >= this.numberOfColumns)
			throw new IndexOutOfBoundsException(
					"The parameter \"y\" should be greater than or equals zero and less than "
							+ numberOfColumns + ". ");

		if (z < 0 || z >= this.numberOfWidth)
			throw new IndexOutOfBoundsException(
					"The parameter \"z\" should be greater than or equals zero and less than "
							+ numberOfWidth + ". ");
		
		if (!values.containsKey(index)) {

			if (type.getName().indexOf(".String") != -1) {
				return (E) new String("");
			} else if (type.getName().indexOf(".StringBuffer") != -1) {
				return (E) new StringBuffer("");
			} else if (type.getName().indexOf(".Byte") != -1) {
				return (E) new Byte((byte) 0);
			} else if (type.getName().indexOf(".Character") != -1) {
				return (E) new Character((char) 0);
			} else if (type.getName().indexOf(".Short") != -1) {
				return (E) new Short((short) 0);
			} else if (type.getName().indexOf(".Boolean") != -1) {
				return (E) new Boolean(false);
			} else if (type.getName().indexOf(".Integer") != -1) {
				return (E) new Integer(0);
			} else if (type.getName().indexOf(".Double") != -1) {
				return (E) new Double(0.0);
			} else if (type.getName().indexOf(".Float") != -1) {
				return (E) new Float(0);
			} else if (type.getName().indexOf(".BigInteger") != -1) {
				return (E) new BigInteger("0");
			} else if (type.getName().indexOf(".BigDecimal") != -1) {
				return (E) new BigDecimal("0");
			}

			return null;
		}

		return values.get(index);
	}

	/**
	 * Method used to set values in the matrix.
	 * 
	 * @param line
	 *            is the index of the line.
	 * @param column
	 *            is the index of the column.
	 * @param value
	 *            is the value to be stored on the (index, column) cell.
	 */
	public void setValue(int x, int y, int z, E value) {

		if (x >= 0 && x < this.numberOfLines && y >= 0 && y < this.numberOfColumns && z >= 0
				&& z < this.numberOfWidth) {

			boolean isOK = true;
			/*
			 * Checking if the value is valid to put into structure.
			 */
			if (value != null) {
				if (type.getName().indexOf(".String") != -1) {
					if (value.equals(new String("")))
						isOK = false;
				} else if (type.getName().indexOf(".StringBuffer") != -1) {
					if (value.equals(new StringBuffer("")))
						isOK = false;
				} else if (type.getName().indexOf(".Byte") != -1) {
					if (value.equals(new Byte((byte) 0)))
						isOK = false;
				} else if (type.getName().indexOf(".Character") != -1) {
					if (value.equals(new Character((char) 0)))
						isOK = false;
				} else if (type.getName().indexOf(".Short") != -1) {
					if (value.equals(new Short((short) 0)))
						isOK = false;
				} else if (type.getName().indexOf(".Boolean") != -1) {
					if (value.equals(new Boolean(false)))
						isOK = false;
				} else if (type.getName().indexOf(".Integer") != -1) {
					if (value.equals(new Integer(0)))
						isOK = false;
				} else if (type.getName().indexOf(".Double") != -1) {
					if (value.equals(new Double(0.0)))
						isOK = false;
				} else if (type.getName().indexOf(".Float") != -1) {
					if (value.equals(new Float(0.0)))
						isOK = false;
				} else if (type.getName().indexOf(".BigInteger") != -1) {
					if (value.equals(new BigInteger("0")))
						isOK = false;
				} else if (type.getName().indexOf(".BigDecimal") != -1) {
					if (value.equals(new BigDecimal("0")))
						isOK = false;
				}
			} else if (value == null)
				isOK = false;
			
			if (isOK)
				values.put(indexCalculation(x, y, z), value);
			
		} else {
			String message = new String();

			if (x <= 0 || x > this.numberOfLines)
				message += "The parameter \"x\" should be greater than or equals zero and less than "
						+ this.numberOfLines + ". ";

			if (y <= 0 || y > this.numberOfColumns)
				message += "The parameter \"y\" should be greater than or equals zero and less than "
						+ y + ". ";

			if (z <= 0 || z > this.numberOfColumns)
				message += "The parameter \"z\" should be greater than or equals zero and less than "
						+ z + ". ";

			throw new IndexOutOfBoundsException(message);
		}
	}

	public int getNumberOfLines() {
		return this.numberOfLines;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public int getNumberOfColumns() {
		return this.numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public int getNumberOfWidth() {
		return this.numberOfWidth;
	}

	public void setNumberOfWidth(int numberOfWidth) {
		this.numberOfWidth = numberOfWidth;
	}

	private Long indexCalculation(int x, int y, int z) {
		return Long.parseLong(String.valueOf(x) + String.valueOf(y) + String.valueOf(z));
	}
}
