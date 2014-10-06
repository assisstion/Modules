package com.github.assisstion.ModulePack;

import java.io.Serializable;
import java.math.BigInteger;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.JavaVersion;

/**
 * Represents an unsigned number. Includes built-in support for byte,
 * short, int and long.
 *
 * @author Markus feng
 *
 * @param <E> The original type of the number
 */
@CompileVersion(JavaVersion.V1_5) // Generics
public class UnsignedNumber<E extends Number> implements Serializable{

	private static final long serialVersionUID = 7799997681922766654L;

	/**
	 * The the number stored in a long
	 */
	protected long value;

	/**
	 * The original value of the number
	 */
	protected E originalValue;
	private int type;

	private static final int TYPE_BYTE = 0;
	private static final int TYPE_SHORT = 1;
	private static final int TYPE_INT = 2;
	private static final int TYPE_LONG = 3;

	/**
	 * Returns the signed value of the unsigned number
	 * @return the signed value of the unsigned number
	 */
	public E getSignedValue(){
		return originalValue;
	}

	/**
	 * Returns the original class of the unsigned number
	 * @return the original class of the unsigned number
	 */
	public Class<? extends Number> getValueClass(){
		return originalValue.getClass();
	}

	/**
	 * Creates an UnsignedNumber with the same value as a given number
	 * @param number the UnsignedNumber to copy from
	 */
	public UnsignedNumber(UnsignedNumber<? extends E> number){
		this(number.getSignedValue());
	}

	/**
	 * Creates an UnsignedNumber with the given signed value
	 * @param value the value of the UnsignedNumber
	 */
	public UnsignedNumber(E value){
		if(value instanceof Byte || value instanceof Long || value instanceof Integer || value instanceof Short){
			this.value = value.longValue();
			this.originalValue = value;
			if(value instanceof Byte){
				type = TYPE_BYTE;
			}
			if(value instanceof Short){
				type = TYPE_SHORT;
			}
			if(value instanceof Integer){
				type = TYPE_INT;
			}
			if(value instanceof Long){
				type = TYPE_LONG;
			}
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the unsigned BigInteger value of the signed number
	 * @return the unsigned BigInteger value of the signed number
	 */
	public BigInteger getUnsignedBigIntegerValue(){
		BigInteger bi = BigInteger.valueOf(0);
		boolean[] ba = reverse(toBitArray());
		int length = ba.length;
		for(int i = 0; i < length; i++){
			bi = bi.add(ba[i]?BigInteger.valueOf(2).pow(i):BigInteger.valueOf(0));
		}
		return bi;
	}

	/**
	 * Returns the unsigned value of the signed number
	 * @return the unsigned value of the signed number
	 */
	public long getUnsignedValue(){
		long bi = 0;
		boolean[] ba = reverse(toBitArray());
		int length = ba.length;
		for(int i = 0; i < length; i++){
			bi += ba[i] ? Math.pow(2, i) : 0;
		}
		return bi;
	}

	/**
	 * Returns the value represented as an array of bits
	 * @return the value represented as an array of bits
	 */
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

	/**
	 * Returns a bit array that is the reverse of a given bit array
	 * @param ba the bit array to reverse
	 * @return a bit array that is the reverse of the given bit array
	 */
	protected static boolean[] reverse(boolean[] ba){
		int length = ba.length;
		boolean[] out = new boolean[length];
		for(int i = 0; i < length; i++){
			out[length - i - 1] = ba[i];
		}
		return out;
	}

	/**
	 * Creates a new UnsignedNumber with the given value
	 * @param v1 the signed value
	 * @return a new UnsignedNumber with the given value
	 */
	public static <T extends Number> UnsignedNumber<T> make(T v1){
		return new UnsignedNumber<T>(v1);
	}

	/**
	 * Creates a new UnsignedNumber with the given UnsignedNumber's value
	 * @param v1 the signed value
	 * @return a new UnsignedNumber with the given UnsignedNumber's value
	 */
	public static <T extends Number> UnsignedNumber<T> make(
			UnsignedNumber<? extends T> v1){
		return new UnsignedNumber<T>(v1);
	}
}
