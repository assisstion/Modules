package com.github.assisstion.ModulePack.tuple;

import java.util.Iterator;

import com.github.assisstion.ModulePack.ComplexNumber;
import com.github.assisstion.ModulePack.annotation.Dependency;

/**
 * The TupleComplexNumber class extends the ComplexNumber class to
 * provide additional functionality related to Tuple2's and Tuples,
 * including getting the size of the TuplePair based on its Tuple
 * contents, and getting value at a specific index of the Tuple.
 *
 * @author Markus Feng
 */
@Dependency(ComplexNumber.class)
public class TupleComplexNumber extends ComplexNumber implements Tuple2<Double, Double>{

	/**
	 * Creates a new ComplexNumber with the given values
	 * @param real the real part of the ComplexNumber
	 * @param imaginary the imaginary part of the ComplexNumber
	 */
	public TupleComplexNumber(double real, double imaginary){
		super(real, imaginary);
	}

	/**
	 * Creates a new ComplexNumber with the same values as a given ComplexNumber
	 * @param cn the ComplexNumber to get the values from
	 */
	public TupleComplexNumber(ComplexNumber cn){
		this(cn.getReal(), cn.getImaginary());
	}

	/**
	 * Creates a new ComplexNumber with the same values as a given TupleComplexNumber
	 * @param cn the TupleComplexNumber to get the values from
	 */
	public TupleComplexNumber(TupleComplexNumber cn){
		this(cn.getReal(), cn.getImaginary());
	}

	/**
	 * Creates a new ComplexNumber with the same values as a given Tuple2
	 * @param cn the Tuple2 to get the values from
	 */
	public TupleComplexNumber(Tuple2<Double, Double> cn){
		this(cn.getValueOne(), cn.getValueTwo());
	}

	/**
	 * Creates a new ComplexNumber with a real or imaginary value
	 * @param value the value of the ComplexNumber
	 * @param imaginary if the value was a real or imaginary part
	 */
	public TupleComplexNumber(double value, boolean imaginary){
		super(value, imaginary);
	}

	/**
	 * Creates an empty ComplexNumber
	 */
	protected TupleComplexNumber(){

	}

	@Override
	public Double getValueAt(int index){
		switch(index){
			case 0:
				return getValueOne();
			case 1:
				return getValueTwo();
			default:
				throw new IndexOutOfBoundsException(String.valueOf(index));
		}
	}

	@Override
	public int getSize(){
		return 2;
	}

	@Override
	public int getTupleSize(){
		return 2;
	}

	@Override
	public Iterator<Object> iterator(){
		return new TupleIterator<Object>(this);
	}

	@Override
	public Double getValueOne(){
		return getReal();
	}

	@Override
	public Double getValueTwo(){
		return getImaginary();
	}

}
