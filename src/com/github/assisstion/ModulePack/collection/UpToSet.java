package com.github.assisstion.ModulePack.collection;

import java.util.AbstractSet;
import java.util.Iterator;

public class UpToSet extends AbstractSet<Integer>{

	protected int size = 0;

	public UpToSet(int size){
		this.size = size;
	}

	@Override
	public boolean contains(Object o){
		if(o instanceof Number){
			Number n = (Number) o;
			int i = n.intValue();
			if(i > 0 && i <= size){
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
		return size;
	}

	protected class UptoIterator implements Iterator<Integer>{

		protected int index = 1;

		@Override
		public boolean hasNext(){
			if(index <= size){
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
