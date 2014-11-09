package com.github.assisstion.ModulePack.collection;

import java.util.Iterator;

@Wrapper
public class IteratorWrapper<T> extends AbstractWrapper<Iterator<T>>
implements Iterator<T>{

	protected Iterator<T> wrapped;

	public IteratorWrapper(Iterator<T> iterator){
		wrapped = iterator;
	}

	public static <T> IteratorWrapper<T> wrap(Iterator<T> iterator){
		return new IteratorWrapper<T>(iterator);
	}

	@Override
	public Iterator<T> get(){
		return wrapped;
	}

	@Override
	public void remove(){
		get().remove();
	}

	@Override
	public boolean hasNext(){
		return get().hasNext();
	}

	@Override
	public T next(){
		return get().next();
	}

}
