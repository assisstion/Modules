package com.github.assisstion.ModulePack.helper;

import java.io.File;
import java.io.IOException;

import com.github.assisstion.ModulePack.annotation.Dependency;
import com.github.assisstion.ModulePack.helper.encryption.SymbolicEncryptionHelper;


@Dependency({FileHelper.class, SymbolicEncryptionHelper.class})
public final class FileEncryptionHelper{

	private FileEncryptionHelper(){
		//Do nothing
	}

	public static void encryptBytes(File file, byte key) throws IOException{
		byte[] input = FileHelper.readBytes(file);
		int length = input.length;
		byte[] output = new byte[length];
		for(int i = 0; i < length; i++){
			output[i] = (byte)((input[i] + key) % 256 - 128);
		}
		FileHelper.writeBytes(file, output);
	}

	public static void decryptBytes(File file, byte key) throws IOException{
		byte[] input = FileHelper.readBytes(file);
		int length = input.length;
		byte[] output = new byte[length];
		for(int i = 0; i < length; i++){
			output[i] = (byte)(0 - (key % 256 - (input[i] + 128)));
		}
		FileHelper.writeBytes(file, output);
	}

	public static void encryptFile(File file, String key) throws IOException{
		String input = FileHelper.readFile(file);
		String output = SymbolicEncryptionHelper.encrypt(input, key);
		FileHelper.writeFile(file, output);
	}

	public static void decryptFile(File file, String key) throws IOException{
		String input = FileHelper.readFile(file);
		String output = SymbolicEncryptionHelper.decrypt(input, key);
		FileHelper.writeFile(file, output);
	}
}
