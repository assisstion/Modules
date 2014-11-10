package com.github.assisstion.ModulePack.collection;

import java.util.function.Predicate;


@FunctionalInterface
public interface Checker<T>{
	Predicate<T> getChecker();
	default boolean check(T t){
		return getChecker().test(t);
	}
}
