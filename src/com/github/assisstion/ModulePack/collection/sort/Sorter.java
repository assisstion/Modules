package com.github.assisstion.ModulePack.collection.sort;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a sorting implementation.
 * Must have a static method with the following signature
 *
 * public static <T> void sort(T[] array, Comparator<? super T> comp);
 *
 * Allows for runtime sort selection invocation
 *
 * @author Markus Feng
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sorter{
	Class<?> value();
}
