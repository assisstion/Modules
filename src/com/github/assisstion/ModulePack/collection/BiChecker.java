package com.github.assisstion.ModulePack.collection;

import java.util.function.BiFunction;

public interface BiChecker<T, S>{
	BiFunction<T, S, Boolean> getChecker();
	default boolean check(T t, S s){
		return getChecker().apply(t, s);
	}
}
