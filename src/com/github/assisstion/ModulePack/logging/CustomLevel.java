package com.github.assisstion.ModulePack.logging;

import java.util.logging.Level;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;

@CompileVersion(SourceVersion.RELEASE_4) // Logging API
public class CustomLevel extends Level{

	private static final long serialVersionUID = -5351557309080563213L;
	public static final Level NOMESSAGE = new CustomLevel("NOMESSAGE", 1200);

	protected CustomLevel(String name, int value){
		super(name, value);
	}

}
