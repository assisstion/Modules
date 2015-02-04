package com.github.assisstion.ModulePack.collection.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter(Object[].class)
@Sorter(List.class)
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
		//Creates an unsorted array as a clone
		T[] unsorted = array.clone();
		sortRecursive(array, unsorted, comp, 0, array.length);
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
		//Creates an unsorted array as a clone
		T[] unsorted = array.clone();
		sortRecursive(array, unsorted, comp, begin, end);
	}

	private static <T> void sortRecursive(T[] array, T[] unsorted, Comparator<? super T> comp, int begin, int end){
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
		sortRecursive(array, unsorted, comp, begin, split);
		sortRecursive(array, unsorted, comp, split, end);
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

	/**
	 * Sorts the list using a natural comparator. List type must implement
	 * Comparable<? super T>, where T is the list type.
	 * @param list the list to be sorted
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> list){
		sort(list, new NaturalComparator<T>());
	}

	/**
	 * Sorts the list using a specified comparator.
	 * @param list the list to be sorted
	 * @param comp the comparator to be used
	 */
	public static <T> void sort(List<T> list, Comparator<? super T> comp){
		//Creates an unsorted array as a clone
		List<T> unsorted = new ArrayList<T>(list);
		sortRecursive(list, unsorted, comp, 0, list.size());
	}

	/**
	 * Sorts a part of an array using a specified comparator.
	 * @param array the array to be sorted
	 * @param comp the comparator to be used
	 * @param begin the begin index of the sorted elements (inclusive)
	 * @param end the end index of the sorted elements (exclusive)
	 */
	public static <T> void sort(List<T> list, Comparator<? super T> comp, int begin, int end){
		//Begin index cannot be larger than end index
		if(begin > end){
			throw new IllegalArgumentException("Begin index must be less" +
					"than end index");
		}
		//Begin index cannot be out of bounds
		if(begin > list.size() - 1){
			throw new ArrayIndexOutOfBoundsException("For begin index: " + begin);
		}
		//End index cannot be out of bounds
		if(end > list.size()){
			throw new ArrayIndexOutOfBoundsException("For end index: " + end);
		}
		//An array with length of 0 or 1 needs not be sorted
		if(list.size() == 0 || list.size() == 1){
			return;
		}
		//Creates an unsorted array as a clone
		List<T> unsorted = new ArrayList<T>(list);
		sortRecursive(list, unsorted, comp, begin, end);
	}

	private static <T> void sortRecursive(List<T> list, List<T> unsorted, Comparator<? super T> comp, int begin, int end){
		//A sorting section with no elements or one element needs not be sorted
		if(begin == end || begin == end - 1){
			return;
		}
		//A sorting section with two elements can be sorted trivially
		//Swap if the larger element precedes the smaller
		if(begin == end - 2){
			T a = list.get(begin);
			T b = list.get(begin + 1);
			if(comp.compare(a, b) > 0){
				list.set(begin, b);
				list.set(begin + 1, a);
			}
			return;
		}
		//Split the array into half
		int split = (end + begin) / 2;
		//Sort each half of the array
		sortRecursive(list, unsorted, comp, begin, split);
		sortRecursive(list, unsorted, comp, split, end);
		int indexCounter = 0;
		int counterLeft = begin;
		int counterRight = split;
		boolean complete = false;
		//Merging the arrays
		//Fills in the indexes; one plots the final position of
		//the element to the element's index; the other plots the
		//element at the given index to its final position
		while(!complete){
			T a = unsorted.get(counterLeft);
			T b = unsorted.get(counterRight);
			//Compares values, then adds the smaller
			//one to the indexes
			if(comp.compare(a, b) > 0){
				list.set(begin + indexCounter++, b);
				counterRight++;
				if(counterRight >= end){
					//Copy the remaining values
					while(counterLeft < split){
						list.set(begin + indexCounter++, unsorted.get(counterLeft++));
					}
					complete = true;
				}
			}
			else{
				list.set(begin + indexCounter++, a);
				counterLeft++;
				if(counterLeft >= split){
					//Copy the remaining values
					while(counterRight < end){
						list.set(begin + indexCounter++, unsorted.get(counterRight++));
					}
					complete = true;
				}
			}
		}
		//The list should now be sorted
	}

	public static <T> BiConsumer<T[], Comparator<? super T>> getArraySorter(){
		return CloningMergeSortHelper::sort;
	}

	public static <T> BiConsumer<List<T>, Comparator<? super T>> getListSorter(){
		return CloningMergeSortHelper::sort;
	}
}
