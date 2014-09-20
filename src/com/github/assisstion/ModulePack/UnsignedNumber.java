package com.github.assisstion.ModulePack;

import java.math.BigInteger;

public class UnsignedNumber<E extends Number> {
	private long value;
	private E originalValue;
	private int type;
	
	private static final int TYPE_BYTE = 0;
	private static final int TYPE_SHORT = 1;
	private static final int TYPE_INT = 2;
	private static final int TYPE_LONG = 3;
	
	public E getSignedValue(){
		return originalValue;
	}
	
	public Class<? extends Number> getValueClass(){
		return originalValue.getClass();
	}
	
	public UnsignedNumber(E value){
		if(value instanceof Byte || value instanceof Long || value instanceof Integer || value instanceof Short){
			this.value = value.longValue();
			this.originalValue = value;
			if(value instanceof Byte)
				type = TYPE_BYTE;
			if(value instanceof Short)
				type = TYPE_SHORT;
			if(value instanceof Integer)
				type = TYPE_INT;
			if(value instanceof Long)
				type = TYPE_LONG;
		}
		else
			throw new IllegalArgumentException();
	}
	
	public BigInteger getUnsignedBigIntegerValue(){
		BigInteger bi = BigInteger.valueOf(0);
		boolean[] ba = reverse(toBitArray());
		int length = ba.length;
		for(int i = 0; i < length; i++){
			bi = bi.add(ba[i]?BigInteger.valueOf(2).pow(i):BigInteger.valueOf(0));
		}
		return bi;
	}
	
	public long getUnsignedValue(){
		long bi = 0;
		boolean[] ba = reverse(toBitArray());
		int length = ba.length;
		for(int i = 0; i < length; i++){
			bi += ba[i]?Math.pow(2, i):0;
		}
		return bi;
	}
	
	public boolean[] toBitArray(){
		int typebits = typeBits(type);
		boolean[] ba = new boolean[typebits];
		for(int i = 0;i < typebits; i++){
			long value;
			if(i < 64){
				value = (long) Math.pow(2, i);
			}
			else{
				value = (long) (Math.pow(2, i) - Long.MIN_VALUE);
			}
			if((value & this.value) > 0){
				ba[i] = true;
			}
			else{
				ba[i] = false;
			}
		}
		return reverse(ba);
	}
	
	private int typeBits(int type){
		switch(type){
		case TYPE_BYTE: return 8;
		case TYPE_SHORT: return 16;
		case TYPE_INT: return 32;
		case TYPE_LONG: return 64;
		default: return 0;
		}
	}
	
	private static boolean[] reverse(boolean[] ba){
		int length = ba.length;
		boolean[] out = new boolean[length];
		for(int i = 0; i < length; i++){
			out[length - i - 1] = ba[i];
		}
		return out;
	}
}
