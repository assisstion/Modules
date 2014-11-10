package com.github.assisstion.ModulePack.collection.wrapper;

import java.util.ListIterator;

public class ListIteratorWrapper<T> extends AbstractWrapper<ListIterator<T>> implements ListIterator<T>{

	protected ListIterator<T> wrapped;

	public ListIteratorWrapper(ListIterator<T> listIterator){
		wrapped = listIterator;
	}

	@Override
	public boolean hasNext(){
		return get().hasNext();
	}

	@Override
	public T next(){
		return get().next();
	}

	@Override
	public boolean hasPrevious(){
		return get().hasPrevious();
	}

	@Override
	public T previous(){
		return get().previous();
	}

	@Override
	public int nextIndex(){
		return get().nextIndex();
	}

	@Override
	public int previousIndex(){
		return get().previousIndex();
	}

	@Override
	public void remove(){
		get().remove();
	}

	@Override
	public void set(T e){
		get().set(e);
	}

	@Override
	public void add(T e){
		get().add(e);
	}

	@Override
	public ListIterator<T> get(){
		return wrapped;
	}

}
