package com.github.assisstion.ModulePack.tuple;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable({})
public interface Value1<T>{
	/**
	 * Returns the value of the first item
	 * @return the value of the first item
	 */
	T getValueOne();
}
