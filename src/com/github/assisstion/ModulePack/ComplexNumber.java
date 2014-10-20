package com.github.assisstion.ModulePack;

import java.io.Serializable;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Immutable;

/**
 * Complex numbers represented with two doubles, one real
 * and one imaginary. Common operations with complex numbers
 * are included in the implementation.
 *
 * @author Markus Feng
 */
@Immutable
@CompileVersion(SourceVersion.RELEASE_0)
public class ComplexNumber implements Serializable{

	private static final long serialVersionUID = -7777073479850468489L;

	/**
	 * The real part
	 */
	protected double real = 0;

	/**
	 * The imaginary part
	 */
	protected double imaginary = 0;

	/**
	 * Creates a new ComplexNumber with the given values
	 * @param real the real part of the ComplexNumber
	 * @param imaginary the imaginary part of the ComplexNumber
	 */
	public ComplexNumber(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * Creates a new ComplexNumber with the same values as a given ComplexNumber
	 * @param cn the ComplexNumber to get the values from
	 */
	public ComplexNumber(ComplexNumber cn){
		real = cn.getReal();
		imaginary = cn.getImaginary();
	}

	/**
	 * Creates a new ComplexNumber with a real or imaginary value
	 * @param value the value of the ComplexNumber
	 * @param imaginary if the value was a real or imaginary part
	 */
	public ComplexNumber(double value, boolean imaginary){
		if(imaginary){
			this.imaginary = value;
		}
		else{
			real = value;
		}
	}

	/**
	 * Creates an empty ComplexNumber
	 */
	protected ComplexNumber(){

	}

	/**
	 * Returns a new ComplexNumber equal to the sum of this and another ComplexNumber
	 * @param c the ComplexNumber to add this to
	 * @return a new ComplexNumber equal to the sum of this and another ComplexNumber
	 */
	public ComplexNumber add(ComplexNumber c){
		double nReal = real + c.real;
		double nImaginary = imaginary + c.imaginary;
		return new ComplexNumber(nReal, nImaginary);
	}

	/**
	 * Returns a new ComplexNumber equal to the product of this and another ComplexNumber
	 * @param c the ComplexNumber to multiply this with
	 * @return a new ComplexNumber equal to the product of this and another ComplexNumber
	 */
	public ComplexNumber multiply(ComplexNumber c){
		double nReal = real * c.real - imaginary * c.imaginary;
		double nImaginary = imaginary * c.real + real * c.imaginary;
		return new ComplexNumber(nReal, nImaginary);
	}

	/**
	 * Returns a new ComplexNumber equal to the difference of this and another ComplexNumber
	 * @param c the ComplexNumber to subtract this by
	 * @return a new ComplexNumber equal to the difference of this and another ComplexNumber
	 */
	public ComplexNumber subtract(ComplexNumber c){
		double nReal = real - c.real;
		double nImaginary = imaginary - c.imaginary;
		return new ComplexNumber(nReal, nImaginary);
	}

	/**
	 * Returns a new ComplexNumber equal to the quotient of this and another ComplexNumber
	 * @param c the ComplexNumber to divide this by
	 * @return a new ComplexNumber equal to the quotient of this and another ComplexNumber
	 */
	public ComplexNumber divide(ComplexNumber c){
		double nReal = (real * c.real + imaginary * c.imaginary) / (c.real * c.real + c.imaginary * c.imaginary);
		double nImaginary = (imaginary * c.real - real * c.imaginary) / (c.real * c.real + c.imaginary * c.imaginary);
		return new ComplexNumber(nReal, nImaginary);
	}

	/**
	 * Returns a new ComplexNumber equal to the square root of this ComplexNumber
	 * @return a new ComplexNumber equal to the square root of this ComplexNumber
	 */
	public ComplexNumber sqrt(){
		double nReal = Math.sqrt((real + Math.sqrt(real * real + imaginary * imaginary)) / 2);
		double d = Math.sqrt((-real + Math.sqrt(real * real + imaginary * imaginary)) / 2);
		double nImaginary = imaginary > 0 ? d : -d;
		return new ComplexNumber(nReal, nImaginary);
	}

	/**
	 * Returns if the ComplexNumber is infinite
	 * @return if the ComplexNumber is infinite
	 */
	public boolean isInfinite(){
		if(Double.isInfinite(real) || Double.isInfinite(imaginary)){
			return true;
		}
		return false;
	}

	/**
	 * Returns if the ComplexNumber is NaN
	 * @return if the ComplexNumber is NaN
	 */
	public boolean isNaN(){
		if(Double.isNaN(real) || Double.isNaN(imaginary)){
			return true;
		}
		return false;
	}

	/**
	 * Returns the real part of the ComplexNumber
	 * @return the real part of the ComplexNumber
	 */
	public double getReal(){
		return real;
	}

	/**
	 * Returns the imaginary part of the ComplexNumber
	 * @return the imaginary part of the ComplexNumber
	 */
	public double getImaginary(){
		return imaginary;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof ComplexNumber){
			ComplexNumber c = (ComplexNumber) o;
			if(real == c.real && imaginary == c.imaginary){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode(){
		return new Double(real).hashCode() ^ new Double(imaginary).hashCode() << 32 ^ new Double(imaginary).hashCode() >> 32;
	}

	@Override
	public String toString(){
		return "r: " + real + " i: " + imaginary;
	}

	/**
	 * Creates a new ComplexNumber with the given values
	 * @param real the real value
	 * @param imaginary the imaginary value
	 * @return a new ComplexNumber with the given values
	 */
	public static ComplexNumber make(double real, double imaginary){
		return new ComplexNumber(real, imaginary);
	}

	/**
	 * Creates a new ComplexNumber with the same values as a given ComplexNumber
	 * @param cn the ComplexNumber to get the values from
	 * @return a new ComplexNumber with the same values as a given ComplexNumber
	 */
	public static ComplexNumber make(ComplexNumber cn){
		return new ComplexNumber(cn);
	}
}
