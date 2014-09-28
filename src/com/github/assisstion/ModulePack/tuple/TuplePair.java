package com.github.assisstion.ModulePack.tuple;

import java.util.Iterator;

import com.github.assisstion.ModulePack.Pair;

/**
 * The TuplePair class extends the Pair class to provide additional
 * functionality related to Tuple2's and Tuples, including getting
 * the size of the TuplePair based on its Tuple contents, and getting
 * value at a specific index of the Tuple. Also, self reference to the
 * TuplePair in its values or tuples in its values are not allowed,
 * as they do not allow a proper size of the tuple  to be calculated.
 *
 * @author Markus Feng
 *
 * @param <T> the type of the first value
 * @param <S> the type of the second value
 */
public class TuplePair<T, S> extends Pair<T, S> implements Tuple2<T, S>{

	private static final long serialVersionUID = 5697357297873395851L;

	/**
	 * Creates an empty pair
	 */
	protected TuplePair(){

	}

	/**
	 * Creates a new TuplePair with the given values
	 * @param valueOne the value of the first item
	 * @param valueTwo the value of the second item
	 */
	public TuplePair(T valueOne, S valueTwo){
		super(valueOne, valueTwo);
	}

	/**
	 * Creates a new TuplePair with the same values as the given Pair
	 * @param original the Pair to obtain values from
	 */
	public TuplePair(Pair<? extends T, ? extends S> original){
		this(original.getValueOne(), original.getValueTwo());
	}

	/**
	 * Creates a new TuplePair with the same values as the given Tuple2
	 * @param original the Tuple2 to obtain values from
	 */
	public TuplePair(Tuple2<? extends T, ? extends S> original){
		this(original.getValueOne(), original.getValueTwo());
	}

	/**
	 * Creates a new TuplePair with the same values as the given TuplePair
	 * @param original the TuplePair to obtain values from
	 */
	public TuplePair(TuplePair<? extends T, ? extends S> original){
		this(original.getValueOne(), original.getValueTwo());
	}

	@Override
	public Object getValueAt(int index){
		T v1 = getValueOne();
		S v2 = getValueTwo();
		int s1 = valueSize(v1);
		int s2 = valueSize(v2);
		if(index < 0 || index >= s1 + s2){
			throw new IndexOutOfBoundsException(String.valueOf(index));
		}
		if(index < s1){
			if(v1 instanceof Tuple<?>){
				Tuple<?> tuple = (Tuple<?>) v1;
				return tuple.getValueAt(index);
			}
			else{
				return v1;
			}
		}
		else{
			if(v2 instanceof Tuple<?>){
				Tuple<?> tuple = (Tuple<?>) v2;
				return tuple.getValueAt(index - s1);
			}
			else{
				return v2;
			}
		}
	}

	@Override
	public int getSize(){
		return valueSize(getValueOne()) + valueSize(getValueTwo());
	}

	@Override
	public int getTupleSize(){
		return 2;
	}

	/**
	 * Returns the tuple size of a given value
	 * @param value the value to check
	 * @return the tuple size of the given value
	 */
	protected static int valueSize(Object value){
		if(value instanceof Tuple){
			Tuple<?> tuple = (Tuple<?>) value;
			return tuple.getSize();
		}
		else{
			return 1;
		}
	}

	@Override
	public Iterator<Object> iterator(){
		return new TupleIterator<Object>(this);
	}

}
