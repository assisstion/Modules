package com.github.assisstion.ModulePack.collection.wrapper.restriction;

import java.util.ListIterator;
import java.util.function.Predicate;

import com.github.assisstion.ModulePack.collection.wrapper.ListIteratorWrapper;

public class RestrictionListIteratorWrapper<T> extends ListIteratorWrapper<T> implements Checker<T>{

	protected Predicate<T> checker;

	public RestrictionListIteratorWrapper(ListIterator<T> listIterator, Predicate<T> condition){
		super(listIterator);
		checker = condition;
	}

	@Override
	public Predicate<T> getChecker(){
		return checker;
	}


	@Override
	public void set(T e){
		if(check(e)){
			super.set(e);
		}
	}

	@Override
	public void add(T e){
		if(check(e)){
			super.add(e);
		}
	}


}
