package com.github.assisstion.ModulePack.tuple;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Version;

/**
 * An iterator that iterates over a Tuple
 *
 * @author Markus Feng
 *
 * @param <T> the type of the Tuple to iterate
 */
@CompileVersion(Version.V1_5) // Generics
public class TupleIterator<T> implements Iterator<T>{

	protected Tuple<T> iterable;
	protected int index = 0;
	protected int size = 0;

	/**
	 * Creates a TupleIterator with the given Tuple
	 * @param tuple the Tuple to iterate over
	 */
	public TupleIterator(Tuple<T> tuple){
		iterable = tuple;
		size = tuple.getSize();
	}

	@Override
	public boolean hasNext(){
		return index < size;
	}

	@Override
	public T next(){
		if(!hasNext()){
			throw new NoSuchElementException("No element exists");
		}
		return iterable.getValueAt(index++);
	}

}
