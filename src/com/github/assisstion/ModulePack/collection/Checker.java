package com.github.assisstion.ModulePack.collection;

import java.util.function.Function;

@FunctionalInterface
public interface Checker<T>{
	Function<T, Boolean> getChecker();
	default boolean check(T t){
		return getChecker().apply(t);
	}
}
