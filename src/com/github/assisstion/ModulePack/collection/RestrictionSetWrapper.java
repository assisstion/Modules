package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class RestrictionSetWrapper<T> extends SetWrapper<T> implements Checker<T>{

	protected Predicate<T> checker = getDefaultChecker();

	public RestrictionSetWrapper(Set<T> set){
		super(set);
		purge();
	}

	public RestrictionSetWrapper(Set<T> set, Predicate<T> checker){
		this(set);
		this.checker = checker;
	}

	@Override
	public Predicate<T> getChecker(){
		return checker;
	}

	protected static <T> Predicate<T> getDefaultChecker(){
		return (t) -> true;
	}

	//True if set is good
	//False if purge happened
	public boolean purge(){
		Set<T> itemsToPurge = new HashSet<T>();
		for(T t : get()){
			if(!check(t)){
				itemsToPurge.add(t);
			}
		}
		for(T t : itemsToPurge){
			get().remove(t);
		}
		return itemsToPurge.size() == 0;
	}

	@Override
	public boolean add(T t){
		if(check(t)){
			return get().add(t);
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c){
		Set<T> set = new HashSet<T>();
		set.forEach((t) -> {
			if(check(t)){
				set.add(t);
			}
		});
		return super.addAll(set);
	}
}
