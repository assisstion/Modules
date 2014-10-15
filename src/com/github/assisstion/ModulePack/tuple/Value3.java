package com.github.assisstion.ModulePack.tuple;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable({})
public interface Value3<R>{
	/**
	 * Returns the value of the third item
	 * @return the value of the third item
	 */
	R getValueThree();
}
