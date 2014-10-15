package com.github.assisstion.ModulePack.annotation;

/**
 * A class marked as limited immutable should be
 * effectively immutable as long as its provided
 * fields are also effectively immutable.
 *
 * @see Immutable for more information.
 *
 * @author Markus Feng
 *
 */
public @interface LimitedImmutable{
	/**
	 * Returns the variables that must be effectively immutable
	 * in order for this class to be effectively immutable.
	 * @return the variables that must be effectively immutable
	 * in order for this class to be effectively immutable.
	 */
	String[] value();
}
