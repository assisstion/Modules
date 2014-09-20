package com.github.assisstion.ModulePack.helper.encryption;

import java.util.Random;

/**
 * Simple encryption - No warranties!
 * Use MappingEncryptionHelperUpdate instead
 * @author assisstion
 *
 */
@Deprecated
public final class MappingEncryptionHelper{
	private MappingEncryptionHelper(){}

	public static String encrypt(String input, String key){
		int inputLength = input.length();
		char[] out = new char[inputLength];
		int code = key.hashCode();
		Character[] mapping = randomizeArray(getMasterMapping(), code);
		for(int i = 0; i < inputLength; i++){
			char x = input.charAt(i);
			if(x < 32 && x != 9 && x != 10 || x > 255 || x > 126 && x < 160){
				throw new IllegalArgumentException("Illegal Input Character " + (int)x);
			}
			int y = convert(x);
			char z = mapping[y];
			out[i] = z;
		}
		return new String(out);
	}

	public static String decrypt(String input, String key){
		int inputLength = input.length();
		char[] out = new char[inputLength];
		int code = key.hashCode();
		Character[] mapping = antiRandomizeArray(getMasterMapping(), code);
		for(int i = 0; i < inputLength; i++){
			char x = input.charAt(i);
			if(x < 32 && x != 9 && x != 10 || x > 255 || x > 126 && x < 160){
				throw new IllegalArgumentException("Illegal Input Character " + (int)x);
			}
			int y = convert(x);
			char z = mapping[y];
			out[i] = z;
		}
		return new String(out);
	}

	private static int convert(char a){
		int output;
		switch(a){
			case 9:
				output = 0;
				break;
			case 10:
				output = 1;
				break;
			default:
				if(a < 127){
					output = a - 30;
				}
				else if (a > 159){
					output = a - 63;
				}
				else{
					output = -1;
				}
				break;
		}
		return output;
	}

	private static Character[] getMasterMapping(){
		Character[] masterMapping = new Character[193];
		masterMapping[0] = 9;
		masterMapping[1] = 10;
		for(int i = 2; i < 97; i++){
			masterMapping[i] = (char)(i + 30);
		}
		for(int i = 97; i < 193; i++){
			masterMapping[i] = (char)(i + 63);
		}
		return masterMapping;
	}

	private static <T> T[] randomizeArray(T[] array, long seed){
		int length = array.length;
		boolean[] lookup = new boolean[length];
		T[] output = array.clone();
		Random random = new Random(seed);
		for(int i = 0; i < length; i++){
			int x = random.nextInt(length);
			while(lookup[x] == true){
				x = random.nextInt(length);
			}
			lookup[x] = true;
			output[i] = array[x];
		}
		return output;
	}

	private static <T> T[] antiRandomizeArray(T[] array, long seed){
		T[] comparator = randomizeArray(array, seed);
		T[] output = array.clone();
		int length = array.length;
		for(int i = 0; i < length; i++){
			int x = getIndex(array, comparator[i]);
			output[x] = array[i];
		}
		return output;
	}

	private static <T> int getIndex(T[] array, T object){
		for(int i = 0; i < array.length; i++){
			if(array[i].equals(object)){
				return i;
			}
		}
		return -1;
	}
}
