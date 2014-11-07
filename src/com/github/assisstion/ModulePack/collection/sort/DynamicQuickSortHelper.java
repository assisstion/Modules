package com.github.assisstion.ModulePack.collection.sort;

import java.util.Comparator;
import java.util.Random;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter
public final class DynamicQuickSortHelper{

	private static final int DEFAULT_INSERTION_CONST = 10;
	//private static int insertionConst = DEFAULT_INSERTION_CONST;

	//Basically a quicksort, insertion sort hybrid
	private DynamicQuickSortHelper(){

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
		sort(array, comp, 0, array.length);
	}

	/**
	 * Sorts a part of an array using a specified comparator.
	 * @param array the array to be sorted
	 * @param comp the comparator to be used
	 * @param begin the begin index of the sorted elements (inclusive)
	 * @param end the end index of the sorted elements (exclusive)
	 */
	public static <T> void sort(T[] array, Comparator<? super T> comp, int begin, int end){
		if(end - begin < DEFAULT_INSERTION_CONST){
			InsertionSortHelper.sort(array, comp, begin, end);
			return;
		}
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
		//Choosing the pivot
		int pivotC = begin / 2 + end / 2;
		T init = array[begin];
		T mid = array[pivotC];
		T last = array[end - 1];
		boolean initHigh = comp.compare(init, mid) > 0 ? true : false;
		int pivotI;
		{
			pivotI = comp.compare(initHigh ? init : mid, last) > 0 ?
					comp.compare(initHigh ? mid : init, last) > 0 ?
							initHigh ? 1 : 0 : 2	: initHigh ? 0 : 1;
		}
		T pivot = pivotI <= 0 ? init : pivotI >= 2 ? last: mid;
		int pivotIndex = pivotI <= 0 ? begin : pivotI >= 2 ? end - 1: pivotC;
		array[end - 1] = pivot;
		array[pivotIndex] = last;
		int indexCounter = begin;
		for(int i = begin; i < end - 1; i++){
			T current = array[i];
			if(comp.compare(current, pivot) <= 0){
				T store = array[indexCounter];
				array[i] = store;
				array[indexCounter++] = current;
			}
		}
		array[end - 1] = array[indexCounter];
		array[indexCounter] = pivot;
		if(indexCounter < pivotIndex){
			sortRecursive(array, comp, begin, indexCounter);
			sortRecursive(array, comp, indexCounter, end);
		}
		else{
			sortRecursive(array, comp, indexCounter, end);
			sortRecursive(array, comp, begin, indexCounter);
		}
	}


	public static void main(String[] args){
		for(int i = 2; i <= 1024; i *= 2){
			System.out.println(i + ": " + findConstant(i, 1000));
		}
	}

	private static Integer[] getTestingArray(int length){
		Random random = new Random();
		Integer[] ia = new Integer[length];
		for(int i = 0; i < ia.length; i++){
			ia[i] = random.nextInt();
		}
		return ia;
	}

	public static int findConstant(int length, int trials){
		Integer[][] iaa = new Integer[trials][length];
		for(int i = 0; i < trials; i++){
			iaa[i] = getTestingArray(length);
		}
		long minTotalTime = Long.MAX_VALUE;
		int constant = 0;
		for(int n = 4; n < 16; n++){
			//insertionConst = n;
			long totalTime = 0;
			for(int i = 0; i < trials; i++){
				Integer[] i1 = iaa[i];
				long begin = System.nanoTime();
				sort(i1);
				long end = System.nanoTime();
				totalTime += end - begin;
			}
			if(totalTime < minTotalTime){
				minTotalTime = totalTime;
				constant = n;
			}
		}
		//insertionConst = DEFAULT_INSERTION_CONST;
		return constant;
	}

}
