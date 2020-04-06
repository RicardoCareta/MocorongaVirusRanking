package com.ps.mocoronavirusapi.util;

public class IntegerValidation {
	public static boolean isInt(String possibleInt) {
		try {
			Integer.parseInt(possibleInt);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
}
