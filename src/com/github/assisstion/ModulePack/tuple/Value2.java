package com.github.assisstion.ModulePack.tuple;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable({})
public interface Value2<S>{
	/**
	 * Returns the value of the second item
	 * @return the value of the second item
	 */
	S getValueTwo();
}
