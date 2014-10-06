package com.github.assisstion.ModulePack.tuple;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.JavaVersion;

/**
 * The Tuple interface represents a sequence of Objects together. It is
 * analogous to a tuple in storing Objects. For specific sizes of Tuples,
 * see their respective interfaces.
 *
 * @author Markus Feng
 *
 * @param <T> the type of each object in the tuple
 */
@CompileVersion(JavaVersion.V1_5) // Generics
public interface Tuple<T> extends Iterable<T>{

	/**
	 * Returns the value at the given index of the tuple
	 * @param index the index to find the value
	 * @return the value at the given index of the tuple
	 * @throws IndexOutOfBoundsException
	 */
	T getValueAt(int index);

	/**
	 * Returns the number of values of the tuple
	 * @return the number of values of the tuple
	 */
	int getSize();

	/**
	 * Returns the size of the tuples, if it were filled with Objects
	 * @return the size of the tuples, if it were filled with Objects
	 */
	int getTupleSize();
}
