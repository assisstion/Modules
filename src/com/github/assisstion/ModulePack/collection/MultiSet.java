package com.github.assisstion.ModulePack.collection;

import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import com.github.assisstion.ModulePack.collection.wrapper.restriction.RestrictionMapWrapper;

public class MultiSet<T> extends AbstractCollection<T>{

	protected Map<T, Integer> map;

	public MultiSet(){
		map = new HashMap<T, Integer>();
	}

	@Override
	public boolean contains(Object o){
		return map.containsKey(o);
	}

	@Override
	public boolean add(T t) {
		if(map.containsKey(t)){
			map.put(t, map.get(t) + 1);
			return true;
		}
		else{
			map.put(t, 1);
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o){
		T t;
		try{
			t = (T) o;
		}
		catch(ClassCastException e){
			//Cannot remove a non-typed object
			return false;
		}
		if(map.containsKey(t)){
			int i = map.get(t);
			if(i <= 1){
				map.remove(t);
			}
			else{
				map.put(t, i - 1);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty(){
		return map.isEmpty();
	}

	@Override
	public void clear(){
		map.clear();
	}

	/*
	@Override
	public Object[] toArray(){
		try{
			Object[] array = new Object[size()];
			int index = 0;
			for(Map.Entry<T, Integer> entry : map.entrySet()){
				for(int i = 0; i < entry.getValue(); i++){
					array[index++] = entry.getKey();
				}
			}
			return array;
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new ConcurrentModificationException("Failed size check");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(T[] in){
		try{
			T[] array = (T[]) Array.newInstance(in.getClass().
					getComponentType(), size());
			int index = 0;
			for(Map.Entry<T, Integer> entry : map.entrySet()){
				for(int i = 0; i < entry.getValue(); i++){
					array[index++] = entry.getKey();
				}
			}
			return array;
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new ConcurrentModificationException("Failed size check");
		}
	}
	 */

	@Override
	public Iterator<T> iterator(){
		return this.new MultiSetIterator();
	}

	@Override
	public int size(){
		int i = 0;
		for(int n : map.values()){
			i += n;
		}
		return i;
	}

	protected class MultiSetIterator implements Iterator<T>{

		protected Iterator<Map.Entry<T, Integer>> mapIterator;
		protected Map.Entry<T, Integer> currentEntry;
		protected int currentIndex;
		protected boolean allowRemove = false;

		public MultiSetIterator(){
			mapIterator = map.entrySet().iterator();
		}

		@Override
		public void remove(){
			if(!allowRemove){
				throw new IllegalStateException("Cannot perform remove");
			}
			allowRemove = false;
			currentIndex++;
			int i = currentEntry.getValue();
			if(i <= 1){
				mapIterator.remove();
			}
			else{
				currentEntry.setValue(currentEntry.getValue() - 1);
			}
		}

		@Override
		public boolean hasNext(){
			if(currentEntry != null && currentIndex < currentEntry.getValue()){
				return true;
			}
			return mapIterator.hasNext();
		}

		@Override
		public T next(){
			if(currentEntry != null){
				if(currentIndex >= currentEntry.getValue()){
					currentEntry = mapIterator.next();
					currentIndex = 0;
				}
			}
			else{
				currentEntry = mapIterator.next();
				currentIndex = 0;
			}
			currentIndex++;
			allowRemove = true;
			return currentEntry.getKey();
		}

	}

	public Map<T, Integer> mapView(){
		return new RestrictionMapWrapper<T, Integer>(map,
				(o, value) -> {
					Objects.requireNonNull(value);
					if(value < 1){
						throw new IllegalArgumentException("Must have at least 1 element");
					}
					return true;
				});
	}

}