package com.github.assisstion.ModulePack.helper.encryption;

import com.github.assisstion.ModulePack.annotation.Dependency;
import com.github.assisstion.ModulePack.annotation.Helper;
import com.github.assisstion.ModulePack.helper.HashCodeHelper;

/**
 * Simple encryption - No warranties!
 * @author mfeng17
 *
 */
@Dependency(HashCodeHelper.class)
@Helper
public final class SymbolicEncryptionHelper {
	private SymbolicEncryptionHelper(){}

	public static String encrypt(String input, String key){
		System.out.println();
		long encryptor = 1;
		if(key.length() < 14){
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, 16 - key.length()))) + HashCodeHelper.hashCode(key, 16 - key.length()) % 65535);
		}
		else{
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, 2))) + HashCodeHelper.hashCode(key, 2) % 65535);
		}
		char[] out = new char[input.length()];
		for(int i = 0; i < input.length(); i++){
			int j = i + 2;
			int x = input.charAt(i);
			int code = (int)(Math.sqrt(encryptor * (Math.pow(j + 1, 2) + 1)) + 1 + j + input.length() + (input.length() - j) + encryptor * j);
			int y = -1;
			int z = -1;
			char outSingle = 1;
			String mode = "none";
			if(x > 32 && x < 127){
				mode = "symbol";
				y = x - 33;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "symbol"){
				z = (code + y) % 95;
			}
			if(mode == "symbol"){
				outSingle = (char) (z + 33);
			}
			else if(mode == "other"){
				outSingle = (char) y;
			}
			out[i] = outSingle;
		}
		String n = String.copyValueOf(out);
		return n;
	}

	public static String encrypt(String input, String key, int modulation, int maxPower, int outputByLengthModification){
		System.out.println();
		long encryptor = 1;
		if(key.length() < maxPower - 2){
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, maxPower - key.length()))) + HashCodeHelper.hashCode(key, maxPower - key.length()) % modulation);
		}
		else{
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, 2))) + HashCodeHelper.hashCode(key, 2) % modulation);
		}
		char[] out = new char[input.length()];
		for(int i = 0; i < input.length(); i++){
			int j = i + 2 + outputByLengthModification;
			int x = input.charAt(i);
			int code = (int)(Math.sqrt(encryptor * (Math.pow(j + 1, 2) + 1)) + 1 + j + input.length() + (input.length() - j)+ encryptor * j);
			int y = -1;
			int z = -1;
			char outSingle = 1;
			String mode = "none";
			if(x > 32 && x < 127){
				mode = "symbol";
				y = x - 33;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "symbol"){
				z = (code + y) % 95;
			}
			if(mode == "symbol"){
				outSingle = (char) (z + 33);
			}
			else if(mode == "other"){
				outSingle = (char) y;
			}
			out[i] = outSingle;
		}
		String n = String.copyValueOf(out);
		return n;
	}

	public static String decrypt(String input, String key){
		System.out.println();
		char[] out = new char[input.length()];
		long encryptor = 1;
		if(key.length() < 14){
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, 16 - key.length()))) + HashCodeHelper.hashCode(key, 16 - key.length()) % 65535);
		}
		else{
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, 2))) + HashCodeHelper.hashCode(key, 2) % 65535);
		}
		for(int i = 0; i < input.length(); i++){
			int j = i + 2;
			int x = input.charAt(i);
			int code = (int)(Math.sqrt(encryptor * (Math.pow(j + 1, 2) + 1)) + 1 + j + input.length() + (input.length() - j)+ encryptor * j);
			int y = -1;
			int z = -1;
			char outSingle = 1;
			String mode = "none";
			if(x > 32 && x < 127){
				mode = "symbol";
				y = x - 33;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "symbol"){
				z = (y - code % 95 + 9025) % 95;
			}
			if(mode == "symbol"){
				outSingle = (char) (z + 33);
			}
			else if(mode == "other"){
				outSingle = (char) y;
			}
			out[i] = outSingle;
		}
		String n = String.copyValueOf(out);
		return n;
	}

	public static String decrypt(String input, String key, int modulation, int maxPower, int outputByLengthModification){
		System.out.println();
		char[] out = new char[input.length()];
		long encryptor = 1;
		if(key.length() < maxPower - 2){
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, maxPower - key.length()))) + HashCodeHelper.hashCode(key, maxPower - key.length()) % modulation);
		}
		else{
			encryptor = (long) (Math.sqrt(Math.sqrt(HashCodeHelper.hashCode(key, 2))) + HashCodeHelper.hashCode(key, 2) % modulation);
		}
		for(int i = 0; i < input.length(); i++){
			int j = i + 2 + outputByLengthModification;
			int x = input.charAt(i);
			int code = (int)(Math.sqrt(encryptor * (Math.pow(j + 1, 2) + 1)) + 1 + j + input.length() + (input.length() - j)+ encryptor * j);
			int y = -1;
			int z = -1;
			char outSingle = 1;
			String mode = "none";
			if(x > 32 && x < 127){
				mode = "symbol";
				y = x - 33;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "symbol"){
				z = (y - code % 95 + 9025) % 95;
			}
			if(mode == "symbol"){
				outSingle = (char) (z + 33);
			}
			else if(mode == "other"){
				outSingle = (char) y;
			}
			out[i] = outSingle;
		}
		String n = String.copyValueOf(out);
		return n;
	}
}
