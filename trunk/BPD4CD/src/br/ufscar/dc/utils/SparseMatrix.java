package br.ufscar.dc.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Implementation of a sparse matrix.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @param <E>
 *            is the type of the values store by the matrix.
 * @since November 19th, 2013
 */
public class SparseMatrix<E> {

	private long numberOfLines = 0;
	private long numberOfColumns = 0;

	private HashMap<Long, E> values = new HashMap<Long, E>();
	private Class<E> type;

	/**
	 * @param numberOfLines
	 *            is the number of lines of the matrix.
	 * @param numberOfColumns
	 *            is the number of columns of the matrix.
	 */
	public SparseMatrix(long numberOfLines, long numberOfColumns, Class<E> type) {
		this.type = type;
		this.numberOfLines = numberOfLines;
		this.numberOfColumns = numberOfColumns;
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
	public void setValue(long line, long column, E value) {

		if (line >= 0 && line < numberOfLines && column >= 0
				&& column < numberOfColumns) {

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
			} else
				isOK = false;

			if (isOK)
				values.put(indexCalculation(line, column), value);
		} else {

			String message = new String();

			if (line < 0 || line >= numberOfLines)
				message += "The parameter \"line\" should be greater than zero and less than "
						+ numberOfLines + ". ";

			if (column < 0 || column >= numberOfColumns)
				message += "The parameter \"column\" should be greater than zero and less than "
						+ numberOfColumns + ". ";

			throw new IndexOutOfBoundsException(message);
		}
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
	public E getValue(long line, long column) {

		long index = indexCalculation(line, column);

		if (line < 0 || line >= numberOfLines)
			throw new IndexOutOfBoundsException(
					"The parameter \"line\" should be greater than zero and less than "
							+ numberOfLines + ". ");

		if (column < 0 || column >= numberOfColumns)
			throw new IndexOutOfBoundsException(
					"The parameter \"column\" should be greater than zero and less than "
							+ numberOfColumns + ". ");

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
	 * @return the number of columns.
	 */
	public long getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * @return the number of lines.
	 */
	public long getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * Calculation of the index used as key in the matrix.
	 * 
	 * @param line
	 *            is the index of the line.
	 * @param column
	 *            is the index of the column.
	 * @return the calculated index.
	 */
	private long indexCalculation(long line, long column) {

		/*
		 * Number of decimal cases of column
		 */
		long i = 0;

		/*
		 * Number of decimal cases in the column index
		 */
		for (long tmp = column; tmp > 0; i++, tmp /= 10)
			;

		/*
		 * Calculation of the index if i = 12 and j = 24 then index = 1224
		 */
		return (long) (line * Math.pow(10, i) + column);
	}

	@Override
	public String toString() {
		
		if (this.type.equals(Boolean.class)) {
			
			String response = new String();
			
			for(int i = 0; i < numberOfLines; i++)
				for (int j = 0; j < numberOfColumns; j++)
					response += Convertion.booleanToInt((Boolean)getValue(i, j));
			
			return response;
		}
		else
			return super.toString();
	}
	
	
}
