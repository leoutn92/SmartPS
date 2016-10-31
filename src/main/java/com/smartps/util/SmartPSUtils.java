package com.smartps.util;

public class SmartPSUtils {
	public static String invertBar(String string) {
		String newString="";
		for (int i=0;i<string.length();i++) {
			int letra=string.indexOf(i);
			if (letra==47) {
				newString+=(char)92;
			} else {
				newString+=string.indexOf(i);
			}
		}
		return newString;
	}
}
