package com.github.assisstion.ModulePack.helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashHelper{
	private HashHelper(){
		//Do nothing
	}

	public static String getArrayHash(byte[] array) throws IOException, NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(array);
		return byteArrayToHexString(hash);
	}

	private static String byteArrayToHexString(byte[] hash){
		StringBuilder builder = new StringBuilder();
		for(byte b : hash){
			String str = Integer.toHexString(0xFF & b);
			while(str.length() < 2){
				str = '0' + str;
			}
			builder.append(str);
		}
		return builder.toString();
	}

	public static String getStreamHash(InputStream stream) throws IOException, NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		try(BufferedInputStream bis = new BufferedInputStream(stream)){
			byte[] buffer = new byte[1024];
			int i = bis.read(buffer);
			while(i > 0){
				md.update(buffer, 0, i);
				i = bis.read(buffer);
			}
			byte[] hash = md.digest();
			return byteArrayToHexString(hash);
		}
	}

	public static String getFileHash(File file) throws IOException, NoSuchAlgorithmException{
		return getStreamHash(new FileInputStream(file));
	}
}
