package com.github.assisstion.ModulePack.collection;

import java.util.Comparator;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter
public final class CloningMergeSortHelper{

	private CloningMergeSortHelper(){

	}

	/**
	 * Sorts the array using a natural comparator. Array type must implement
	 * Comparable<? super T>, where T is the array type.
	 * @param array the array to be sorted
	 */
	public static <T extends Comparable<? super T>> void sort(T[] array){
		sort(array, new NaturalComparator<T>());
	}

	/**
	 * Sorts the array using a specified comparator.
	 * @param array the array to be sorted
	 * @param comp the comparator to be used
	 */
	public static <T> void sort(T[] array, Comparator<? super T> comp){
		sortRecursive(array, comp, 0, array.length);
	}

	/**
	 * Sorts a part of an array using a specified comparator.
	 * @param array the array to be sorted
	 * @param comp the comparator to be used
	 * @param begin the begin index of the sorted elements (inclusive)
	 * @param end the end index of the sorted elements (exclusive)
	 */
	public static <T> void sort(T[] array, Comparator<? super T> comp, int begin, int end){
		//Begin index cannot be larger than end index
		if(begin > end){
			throw new IllegalArgumentException("Begin index must be less" +
					"than end index");
		}
		//Begin index cannot be out of bounds
		if(begin > array.length - 1){
			throw new ArrayIndexOutOfBoundsException("For begin index: " + begin);
		}
		//End index cannot be out of bounds
		if(end > array.length){
			throw new ArrayIndexOutOfBoundsException("For end index: " + end);
		}
		//An array with length of 0 or 1 needs not be sorted
		if(array.length == 0 || array.length == 1){
			return;
		}
		sortRecursive(array, comp, begin, end);
	}

	public static <T> void sortRecursive(T[] array, Comparator<T> comp, int begin, int end){
		//A sorting section with no elements or one element needs not be sorted
		if(begin == end || begin == end - 1){
			return;
		}
		//A sorting section with two elements can be sorted trivially
		//Swap if the larger element precedes the smaller
		if(begin == end - 2){
			T a = array[begin];
			T b = array[begin + 1];
			if(comp.compare(a, b) > 0){
				array[begin] = b;
				array[begin + 1] = a;
			}
			return;
		}
		//Split the array into half
		int split = (end + begin) / 2;
		//Sort each half of the array
		sortRecursive(array, comp, begin, split);
		sortRecursive(array, comp, split, end);
		//Creates an unsorted array as a clone
		T[] unsorted = array.clone();
		int indexCounter = 0;
		int counterLeft = begin;
		int counterRight = split;
		boolean complete = false;
		//Merging the arrays
		//Fills in the indexes; one plots the final position of
		//the element to the element's index; the other plots the
		//element at the given index to its final position
		while(!complete){
			T a = unsorted[counterLeft];
			T b = unsorted[counterRight];
			//Compares values, then adds the smaller
			//one to the indexes
			if(comp.compare(a, b) > 0){
				array[begin + indexCounter++] = b;
				counterRight++;
				if(counterRight >= end){
					//Copy the remaining values
					System.arraycopy(unsorted, counterLeft, array,
							begin + indexCounter, end - begin - indexCounter);
					complete = true;
				}
			}
			else{
				array[begin + indexCounter++] = a;
				counterLeft++;
				if(counterLeft >= split){
					//Copy the remaining values
					System.arraycopy(unsorted, counterRight, array,
							begin + indexCounter, end - begin - indexCounter);
					complete = true;
				}
			}
		}
		//The array should now be sorted
	}
}
