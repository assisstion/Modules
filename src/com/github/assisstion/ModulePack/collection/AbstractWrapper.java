package com.github.assisstion.ModulePack.collection;

import java.util.function.Supplier;

public abstract class AbstractWrapper<T> implements Supplier<T>{

	@Override
	public abstract T get();

	@Override
	public boolean equals(Object o){
		return get().equals(o);
	}

	@Override
	public String toString(){
		return get().toString();
	}

	@Override
	public int hashCode(){
		return get().hashCode();
	}
}
