package com.github.assisstion.ModulePack.annotation;

public enum Version{
	V1_0 (1,0),
	V1_1 (1,1),
	V1_2 (1,2),
	V1_3 (1,3),
	V1_4 (1,4),
	V1_5 (1,5),
	V1_6 (1,6),
	V1_7 (1,7),
	V1_8 (1,8);

	protected final int major;
	protected final int minor;

	private Version(int major, int minor){
		this.major = major;
		this.minor = minor;
	}

	public int getMajor(){
		return major;
	}

	public int getMinor(){
		return minor;
	}
}
