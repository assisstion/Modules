package com.github.assisstion.ModulePack.collection.sort;

import java.util.Comparator;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

/**
 * Standard merge sort implementation.
 * Creates no new arrays of input type. However,
 * new int arrays are created as indexes to help
 * sort the arrays. The total size of all created
 * int arrays is less than four times the size of the
 * initial array. All operations done to the
 * input array are flip/exchange operations.
 *
 * This sort is in-place and stable.
 *
 * @author Markus Feng
 */
@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter
public final class InPlaceMergeSortHelper{

	private InPlaceMergeSortHelper(){

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

	private static <T> void sortRecursive(T[] array, Comparator<T> comp, int begin, int end){
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
		//Create indexes for storing sorting data
		int[] indexA = new int[end - begin];
		int[] indexB = new int[end - begin];
		int indexCounter = 0;
		int counterLeft = begin;
		int counterRight = split;
		boolean complete = false;
		//Merging the arrays
		//Fills in the indexes; one plots the final position of
		//the element to the element's index; the other plots the
		//element at the given index to its final position
		while(!complete){
			T a = array[counterLeft];
			T b = array[counterRight];
			//Compares values, then adds the smaller
			//one to the indexes
			if(comp.compare(a, b) > 0){
				indexA[indexCounter] = counterRight - begin;
				indexB[counterRight++ - begin] = indexCounter++;
				if(counterRight >= end){
					//When one array is complete,
					//add the remainder of the other to the index
					while(counterLeft < split){
						indexA[indexCounter] = counterLeft - begin;
						indexB[counterLeft++ - begin] = indexCounter++;
					}
					complete = true;
				}
			}
			else{
				indexA[indexCounter] = counterLeft - begin;
				indexB[counterLeft++ - begin] = indexCounter++;
				if(counterLeft >= split){
					//When one array is complete,
					//add the remainder of the other to the index
					while(counterRight < end){
						indexA[indexCounter] = counterRight - begin;
						indexB[counterRight++ - begin] = indexCounter++;
					}
					complete = true;
				}
			}
		}
		//Swaps the elements in the array such that the array is sorted
		for(int i = 0; i < end - begin; i++){
			int target = indexA[i];
			T a = array[begin + target];
			T b = array[begin + i];
			array[begin + target] = b;
			array[begin + i] = a;
			int ai = indexB[target];
			int bi = indexB[i];
			indexB[target] = bi;
			indexB[i] = ai;
			indexA[bi] = target;
		}
		//The array should now be sorted
	}
}
