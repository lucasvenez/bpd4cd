package br.ufscar.dc.utils;

import static br.ufscar.dc.utils.Convertion.charToBoolean;
import static java.lang.Math.pow;

import java.security.InvalidParameterException;

public class Math {

	
	public static Double min(Double n1, Double n2) {
		
		if (n1 == null && n2 != null)
			return n2;
		
		else if (n2 == null && n1 != null) 
			return n1;
		
		else if (n2 == null && n1 == null)
			return null;
		
		else if (n1 < n2)
			return n1;
		
		return n2;
	}
	
	public static SparseMatrix<Boolean> longToBooleanMatrix(long distributionIndex, long limit) {

		if (distributionIndex < 0)
			throw new InvalidParameterException(
					"\"configuration\" should be greater than or equals to 0 and \"numberOfNodes\" should be greater than 0.");

		String bin = Integer.toBinaryString((int)distributionIndex);

		int diff = Long.toBinaryString((long)pow(2, limit)).length() - bin.length() - 1;

		for (int j = 0; j < diff; j++)
			bin = ("0" + bin);

		SparseMatrix<Boolean> result = 
			new SparseMatrix<Boolean>(1, limit, Boolean.class);

		for (int i = 0; i < bin.length(); i++)
			result.setValue(0, i, charToBoolean(bin.charAt(i)));

		return result;
	}
}
