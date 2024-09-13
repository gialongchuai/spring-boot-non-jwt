package com.javaweb.utils;

public class NumberUtil {
	public static boolean checkNumber(String s) {
		try {
			Long x = Long.parseLong(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	} 
}
