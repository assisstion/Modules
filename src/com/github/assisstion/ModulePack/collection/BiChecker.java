package com.github.assisstion.ModulePack.collection;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface BiChecker<T, S>{
	BiPredicate<T, S> getChecker();
	default boolean check(T t, S s){
		return getChecker().test(t, s);
	}
}
