package com.github.assisstion.ModulePack.helper;

public final class CharArrayFormatHelper{

	private CharArrayFormatHelper(){
		//Do nothing
	}

	public static char[] fillCharacters(char[] ca, char c, int i){
		char[] ca2 = ca.clone();
		char[] ca3 = new char[i];
		for(int iteration = 0; iteration < i; iteration++){
			int n = ca2.length - 1 - iteration;
			if(n < 0){
				ca3[iteration] = c;
			}
			else{
				ca3[iteration] = ca2[n];
			}
		}
		return reverse(ca3);
	}

	public static char[] reverse(char[] ca){
		int length = ca.length;
		char[] out = new char[length];
		for(int i = 0; i < length; i++){
			out[length - i - 1] = ca[i];
		}
		return out;
	}
}
