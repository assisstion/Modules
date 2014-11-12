package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.github.assisstion.ModulePack.collection.sort.InPlaceMergeSortHelper;
import com.github.assisstion.ModulePack.collection.wrapper.ListWrapper;

public class SortedList<T> extends ListWrapper<T>{

	protected Comparator<? super T> comp;

	public SortedList(List<T> list, Comparator<? super T> comp){
		super(list);
		this.comp = comp;
		sort();
	}

	public SortedList(Comparator<? super T> comp){
		super(new LinkedList<T>());
		this.comp = comp;
		sort();
	}

	public void sort(){
		InPlaceMergeSortHelper.sort(get(), getComparator());
	}

	@Override
	public boolean add(T t){
		int i = 0;
		for(T item : get()){
			if(comp.compare(t, item) < 0){
				break;
			}
			i++;
		}
		super.add(i, t);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> coll){
		boolean changed = false;
		for(T t : coll){
			if(add(t)){
				changed = true;
			}
		}
		return changed;
	}

	//Ignores index
	@Override
	public boolean addAll(int index, Collection<? extends T> coll){
		return addAll(coll);
		//throw new UnsupportedOperationException();
	}

	//Ignores index
	@Override
	public T set(int index, T element){
		add(element);
		return null;
		//throw new UnsupportedOperationException();
	}

	//Ignores index
	@Override
	public void add(int index, T element){
		add(element);
		//throw new UnsupportedOperationException();
	}

	public Comparator<? super T> getComparator(){
		return comp;
	}
}
