package com.github.assisstion.ModulePack.collection;

import java.util.Comparator;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;

/**
 * A natural comparator implementation. Assumes the type implements
 * Comparable and uses the comparable implementation.
 *
 * @author Markus Feng
 *
 * @param <T> the type of the natural comparator
 */
@CompileVersion(SourceVersion.RELEASE_5) //Generics
public class NaturalComparator<T extends Comparable<? super T>>
implements Comparator<T>{

	@Override
	public int compare(T o1, T o2){
		return o1.compareTo(o2);
	}

}