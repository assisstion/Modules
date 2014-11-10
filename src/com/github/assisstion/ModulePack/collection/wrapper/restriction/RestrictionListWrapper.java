package com.github.assisstion.ModulePack.collection.wrapper.restriction;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.github.assisstion.ModulePack.collection.wrapper.ListWrapper;

public class RestrictionListWrapper<T> extends ListWrapper<T> implements Checker<T>{

	protected Predicate<T> checker;

	public RestrictionListWrapper(List<T> list, Predicate<T> condition){
		super(list);
		checker = condition;
	}

	@Override
	public boolean add(T t){
		if(check(t)){
			super.add(t);
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> coll){
		List<T> newList = coll.stream().filter((t) -> check(t))
				.collect(Collectors.toList());
		return super.addAll(newList);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> coll){
		List<T> newList = coll.stream().filter((t) -> check(t))
				.collect(Collectors.toList());
		return super.addAll(index, newList);
	}

	@Override
	public T set(int index, T element){
		if(check(element)){
			super.set(index, element);
		}
		return null;
	}

	@Override
	public void add(int index, T element){
		if(check(element)){
			super.add(index, element);
		}
	}

	@Override
	public ListIterator<T> listIterator(){
		return new RestrictionListIterator<T>(get().listIterator(), getChecker());
	}

	@Override
	public ListIterator<T> listIterator(int index){
		return new RestrictionListIterator<T>(get().listIterator(index), getChecker());
	}

	@Override
	public Predicate<T> getChecker(){
		return checker;
	}
}
