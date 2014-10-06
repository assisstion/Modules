package com.github.assisstion.ModulePack.helper;

import com.github.assisstion.ModulePack.annotation.Helper;

@Helper
public final class HashCodeHelper {
	private HashCodeHelper(){}

	public static long hashCode(String s){
		long returnValue = hashCode(s, 31);
		return returnValue;

	}
	public static long hashCode(String s, int i){
		int returnValue = 0;
		char[] charArray = new char[s.length()];
		s.getChars(0, s.length(), charArray, 0);
		for(int n = 0; n < s.length(); n++){
			char charTemp = charArray[n];
			returnValue += charTemp * Math.pow(i,s.length() - (n + 1));
		}
		return returnValue;
	}
}
