package com.github.assisstion.ModulePack.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A class marked as limited immutable should be
 * effectively immutable as long as its provided
 * fields are also effectively immutable.
 *
 * Also used for marking interfaces that should
 * be implemented as effectively immutable or
 * limited immutable.
 *
 * @see Immutable for more information.
 *
 * @author Markus Feng
 *
 */
@Documented
@Target(ElementType.TYPE)
@CompileVersion(JavaVersion.V1_5) // Annotation
public @interface LimitedImmutable{
	/**
	 * Returns the variables that must be effectively immutable
	 * in order for this class to be effectively immutable.
	 * @return the variables that must be effectively immutable
	 * in order for this class to be effectively immutable.
	 */
	String[] value();
}
