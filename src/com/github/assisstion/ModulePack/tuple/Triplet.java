package com.github.assisstion.ModulePack.tuple;

import java.io.Serializable;
import java.util.Iterator;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.JavaVersion;

/**
 * The Triplet class represents three typed Objects together. It is
 * analogous to a 3-tuple in storing typed Objects. Applications
 * include putting multiple Objects in an index of a Collection,
 * or returning more than one value from a method. Multiple
 * Triplets can be, along with Pairs nested to simulate a 4-tuple or more. Note that
 * the triplet syntax is limited by the java generics system.
 *
 * @author Markus Feng
 *
 * @param <T> the type of the first value
 * @param <S> the type of the second value
 * @param <R> the type of the third value
 */
@CompileVersion(JavaVersion.V1_5) // Generics
public class Triplet<T, S, R> implements Tuple3<T, S, R>, Serializable{

	private static final long serialVersionUID = 8379061245525375406L;

	private static final int LOW_MASK = 0x00000FFF;
	private static final int MED_MASK = 0x00FFF000;
	private static final int HIGH_MASK = 0xFF000000;

	/**
	 * The first item
	 */
	protected T valueOne;

	/**
	 * The second item
	 */
	protected S valueTwo;

	/**
	 * The third item
	 */
	protected R valueThree;

	@Override
	public Object getValueAt(int index){
		T v1 = getValueOne();
		S v2 = getValueTwo();
		R v3 = getValueThree();
		int s1 = valueSize(v1);
		int s2 = valueSize(v2);
		int s3 = valueSize(v3);
		if(index < 0 || index >= s1 + s2 + s3){
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
		else if (index < s1 + s2){
			if(v2 instanceof Tuple<?>){
				Tuple<?> tuple = (Tuple<?>) v2;
				return tuple.getValueAt(index - s1);
			}
			else{
				return v2;
			}
		}
		else{
			if(v3 instanceof Tuple<?>){
				Tuple<?> tuple = (Tuple<?>) v3;
				return tuple.getValueAt(index - (s1 + s2));
			}
			else{
				return v3;
			}
		}
	}

	@Override
	public int getSize(){
		return valueSize(getValueOne()) + valueSize(getValueTwo()) + valueSize(getValueThree());
	}

	@Override
	public int getTupleSize(){
		return 3;
	}

	@Override
	public Iterator<Object> iterator(){
		return new TupleIterator<Object>(this);
	}

	@Override
	public T getValueOne(){
		return valueOne;
	}

	@Override
	public S getValueTwo(){
		return valueTwo;
	}

	@Override
	public R getValueThree(){
		return valueThree;
	}

	@Override
	public String toString(){
		T v1 = getValueOne();
		S v2 = getValueTwo();
		R v3 = getValueThree();
		String valueOneString = v1 == null ? "null" : v1.toString();
		String valueTwoString = v2 == null ? "null" : v2.toString();
		String valueThreeString = v3 == null ? "null" : v3.toString();
		return "Triplet: [" + valueOneString + "," + valueTwoString +
				"," + valueThreeString + "]";
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Triplet){
			Triplet<?, ?, ?> p = (Triplet<?, ?, ?>) o;
			if(getValueOne().equals(p.getValueOne()) && getValueTwo().equals(p.getValueTwo())
					&& getValueThree().equals(p.getValueThree())){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode(){
		return getValueOne().hashCode() & LOW_MASK ^
				getValueTwo().hashCode() << 6 & MED_MASK ^
				getValueThree().hashCode() << 12 & HIGH_MASK;
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

}
