package com.github.assisstion.ModulePack.collection.wrapper.restriction;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.github.assisstion.ModulePack.collection.sort.InPlaceMergeSortHelper;
import com.github.assisstion.ModulePack.collection.wrapper.ListWrapper;

public class SortedListWrapper<T> extends ListWrapper<T>{

	protected Comparator<? super T> comp;

	public SortedListWrapper(List<T> list, Comparator<? super T> comp){
		super(list);
		this.comp = comp;
		InPlaceMergeSortHelper.sort(list, comp);
	}

	@Override
	public boolean add(T t){
		int i = 0;
		for(T item : get()){
			if(comp.compare(t, item) > 0){
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
}
