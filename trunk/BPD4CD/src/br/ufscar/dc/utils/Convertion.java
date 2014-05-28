package br.ufscar.dc.utils;

public class Convertion {

	public static int booleanToInt(boolean value) {
		return value ? 1 : 0;
	}
	
	public static boolean charToBoolean(char bool) {
		return bool == '0' ? false : true;
	}
}
