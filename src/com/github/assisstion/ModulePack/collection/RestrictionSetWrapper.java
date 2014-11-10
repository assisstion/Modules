package com.github.assisstion.ModulePack.collection;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		return t -> true;
	}

	//True if set is good
	//False if purge happened
	public boolean purge(){
		Set<T> set = get();
		Set<T> itemsToPurge = set.stream().filter(t -> !check(t))
				.collect(Collectors.toSet());
		itemsToPurge.forEach(t -> set.remove(t));
		return itemsToPurge.size() == 0;
	}

	@Override
	public boolean add(T t){
		if(check(t)){
			return super.add(t);
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c){
		Set<T> set = c.stream().filter(t -> check(t))
				.collect(Collectors.toSet());
		return super.addAll(set);
	}
}
