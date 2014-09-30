package com.github.assisstion.ModulePack.tuple;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Version;

/**
 * The Tuple2 interface represents two typed Objects together. It is
 * analogous to a 2-tuple in storing typed Objects. See Pair for a
 * concrete implementation of this interface.
 *
 * @author Markus Feng
 *
 * @param <T> the type of the first value
 * @param <S> the type of the second value
 */
@CompileVersion(Version.V1_5) // Generics
public interface Tuple2<T, S> extends Tuple<Object>{
	/**
	 * Returns the value of the first item
	 * @return the value of the first item
	 */
	T getValueOne();

	/**
	 * Returns the value of the second item
	 * @return the value of the second item
	 */
	S getValueTwo();
}
