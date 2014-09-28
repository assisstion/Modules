package com.github.assisstion.ModulePack;

/**
 * Complex numbers represented with two doubles, one real
 * and one imaginary. Common operations with complex numbers
 * are included in the implementation.
 *
 * @author Markus Feng
 */
public class ComplexNumber{
	protected double real = 0;
	protected double imaginary = 0;
	public ComplexNumber(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}
	public ComplexNumber(double value, boolean imaginary){
		if(imaginary){
			this.imaginary = value;
		}
		else{
			real = value;
		}
	}
	protected ComplexNumber(){}
	public ComplexNumber add(ComplexNumber c){
		ComplexNumber c2 = new ComplexNumber();
		c2.real = real + c.real;
		c2.imaginary = imaginary + c.imaginary;
		return c2;
	}
	public ComplexNumber multiply(ComplexNumber c){
		ComplexNumber c2 = new ComplexNumber();
		c2.real = real * c.real - imaginary * c.imaginary;
		c2.imaginary = imaginary * c.real + real * c.imaginary;
		return c2;
	}
	public ComplexNumber subtract(ComplexNumber c){
		ComplexNumber c2 = new ComplexNumber();
		c2.real = real - c.real;
		c2.imaginary = imaginary - c.imaginary;
		return c2;
	}
	public ComplexNumber divide(ComplexNumber c){
		ComplexNumber c2 = new ComplexNumber();
		c2.real = (real * c.real + imaginary * c.imaginary) / (c.real * c.real + c.imaginary * c.imaginary);
		c2.imaginary = (imaginary * c.real - real * c.imaginary) / (c.real * c.real + c.imaginary * c.imaginary);
		return c2;
	}
	public ComplexNumber sqrt(){
		ComplexNumber c2 = new ComplexNumber();
		c2.real = Math.sqrt((real + Math.sqrt(real * real + imaginary * imaginary)) / 2);
		double d = Math.sqrt((-real + Math.sqrt(real * real + imaginary * imaginary)) / 2);
		c2.imaginary = imaginary > 0 ? d : -d;
		return c2;
	}
	public boolean isInfinite(){
		if(Double.isInfinite(real) || Double.isInfinite(imaginary)){
			return true;
		}
		return false;
	}
	public boolean isNaN(){
		if(Double.isNaN(real) || Double.isNaN(imaginary)){
			return true;
		}
		return false;
	}
	public double getReal(){
		return real;
	}
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
}
