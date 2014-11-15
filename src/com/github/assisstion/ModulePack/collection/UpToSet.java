package com.github.assisstion.ModulePack.collection;

import java.util.AbstractSet;
import java.util.Iterator;

public class UpToSet extends AbstractSet<Integer>{

	protected int start = 0;
	protected int end = 0;

	public UpToSet(int size){
		this(1, size + 1);
	}

	public UpToSet(int start, int end){
		if(end < start){
			throw new IllegalArgumentException("end must be greater than start");
		}
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean contains(Object o){
		if(o instanceof Number){
			Number n = (Number) o;
			int i = n.intValue();
			if(i >= start  && i < end){
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Integer> iterator(){
		return this.new UptoIterator();
	}

	@Override
	public int size(){
		return end - start;
	}

	protected class UptoIterator implements Iterator<Integer>{

		protected int index = start;

		@Override
		public boolean hasNext(){
			if(index < end){
				return true;
			}
			return false;
		}

		@Override
		public Integer next(){
			if(!hasNext()){
				return null;
			}
			return index++;
		}


	}

}
