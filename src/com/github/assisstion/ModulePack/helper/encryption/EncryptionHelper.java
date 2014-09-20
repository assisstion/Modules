package com.github.assisstion.ModulePack.helper.encryption;

import com.github.assisstion.ModulePack.annotation.Dependency;
import com.github.assisstion.ModulePack.helper.HashCodeHelper;

/**
 * Simple encryption - No warranties!
 *
 * EncryptionHelper
 * dependencies
 * @see HashCodeHelper
 * @author assisstion
 *
 */
@Dependency(HashCodeHelper.class)
public final class EncryptionHelper {
	private EncryptionHelper(){}

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
			int code = (int)(Math.sqrt(encryptor * (Math.pow(j + 1, 2) + 1)) + 1 + j + input.length() + (input.length() - j)+ encryptor * j);
			int y = -1;
			int z = -1;
			char outSingle = 1;
			String mode = "none";
			if(x > 96 && x < 123){
				mode = "lowercase";
				y = x - 96;
			}
			else if(x > 64 && x < 91){
				mode = "uppercase";
				y = x - 64;
			}
			else if(x > 47 && x < 58){
				mode = "number";
				y = x - 48;
			}
			else if(x == 32){
				mode = "space";
				y = x;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "lowercase" || mode == "uppercase"){
				z = (code + y) % 26;
			}
			else if(mode == "number"){
				z = (code + y) % 10;
			}
			if(z == 0){
				if(mode == "lowercase" || mode == "uppercase"){
					z = 26;
				}
			}
			if(mode == "lowercase"){
				outSingle = (char) (z + 96);
			}
			else if(mode == "uppercase"){
				outSingle = (char) (z + 64);
			}
			else if(mode == "number"){
				outSingle = (char) (z + 48);
			}
			else if(mode == "space" || mode == "other"){
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
			if(x > 96 && x < 123){
				mode = "lowercase";
				y = x - 96;
			}
			else if(x > 64 && x < 91){
				mode = "uppercase";
				y = x - 64;
			}
			else if(x > 47 && x < 58){
				mode = "number";
				y = x - 48;
			}
			else if(x == 32){
				mode = "space";
				y = x;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "lowercase" || mode == "uppercase"){
				z = (code + y) % 26;
			}
			else if(mode == "number"){
				z = (code + y) % 10;
			}
			if(z == 0){
				if(mode == "lowercase" || mode == "uppercase"){
					z = 26;
				}
			}
			if(mode == "lowercase"){
				outSingle = (char) (z + 96);
			}
			else if(mode == "uppercase"){
				outSingle = (char) (z + 64);
			}
			else if(mode == "number"){
				outSingle = (char) (z + 48);
			}
			else if(mode == "space" || mode == "other"){
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
			if(x > 96 && x < 123){
				mode = "lowercase";
				y = x - 96;
			}
			else if(x > 64 && x < 91){
				mode = "uppercase";
				y = x - 64;
			}
			else if(x > 47 && x < 58){
				mode = "number";
				y = x - 48;
			}
			else if(x == 32){
				mode = "space";
				y = x;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "lowercase" || mode == "uppercase"){
				z = (y - code % 26 + 676) % 26;
			}
			else if(mode == "number"){
				z = (y - code % 10 + 100) % 10;
			}
			if(z == 0){
				if(mode == "lowercase" || mode == "uppercase"){
					z = 26;
				}
			}
			if(mode == "lowercase"){
				outSingle = (char) (z + 96);
			}
			else if(mode == "uppercase"){
				outSingle = (char) (z + 64);
			}
			else if(mode == "number"){
				outSingle = (char) (z + 48);
			}
			else if(mode == "space" || mode == "other"){
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
			if(x > 96 && x < 123){
				mode = "lowercase";
				y = x - 96;
			}
			else if(x > 64 && x < 91){
				mode = "uppercase";
				y = x - 64;
			}
			else if(x > 47 && x < 58){
				mode = "number";
				y = x - 48;
			}
			else if(x == 32){
				mode = "space";
				y = x;
			}
			else{
				mode = "other";
				y = x;
			}
			if(mode == "lowercase" || mode == "uppercase"){
				z = (y - code % 26 + 676) % 26;
			}
			else if(mode == "number"){
				z = (y - code % 10 + 100) % 10;
			}
			if(z == 0){
				if(mode == "lowercase" || mode == "uppercase"){
					z = 26;
				}
			}
			if(mode == "lowercase"){
				outSingle = (char) (z + 96);
			}
			else if(mode == "uppercase"){
				outSingle = (char) (z + 64);
			}
			else if(mode == "number"){
				outSingle = (char) (z + 48);
			}
			else if(mode == "space" || mode == "other"){
				outSingle = (char) y;
			}
			out[i] = outSingle;
		}
		String n = String.copyValueOf(out);
		return n;
	}
}
