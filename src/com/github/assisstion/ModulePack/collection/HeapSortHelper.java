package com.github.assisstion.ModulePack.collection;

import java.util.Comparator;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter
public final class HeapSortHelper{

	private HeapSortHelper(){

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
		buildHeap(array, comp);
		int end = array.length;
		while(end > 0){
			T last = array[end - 1];
			T head = array[0];
			array[end - 1] = head;
			array[0] = last;
			end--;
			swapDown(array, comp, 0, end);
		}
	}

	/**
	 * Builds the array into a heap.
	 * @param array the array to be built
	 * @param comp the comparator to be used
	 */
	private static <T> void buildHeap(T[] array, Comparator<? super T> comp){
		//Last parent node
		int start = (array.length - 2) / 2;
		while(start >= 0){
			swapDown(array, comp, start, array.length);
			start--;
		}
	}

	/**
	 * Swaps the largest index downwards, then repeats it with each smaller index.
	 * @param array the array to swap with
	 * @param comp the comparator to be used
	 * @param begin the begin index of the swapping (inclusive)
	 * @param end the end index of the swapping (exclusive)
	 */
	private static <T> void swapDown(T[] array, Comparator<? super T> comp,
			int begin, int end){
		int rootIndex = begin;
		while(rootIndex * 2 + 1 < end){
			int childIndex = rootIndex * 2 + 1;
			int swapIndex = rootIndex;
			T child = array[childIndex];
			T swap = array[swapIndex];
			if(comp.compare(swap, child) < 0){
				swapIndex = childIndex;
				swap = child;
			}
			T rightChild = array[childIndex + 1];
			if(childIndex + 1 < end && comp.compare(swap, rightChild) < 0){
				swapIndex = childIndex + 1;
				swap = rightChild;
			}
			if(swapIndex != rootIndex){
				T root = array[rootIndex];
				array[rootIndex] = swap;
				array[swapIndex] = root;
				rootIndex = swapIndex;
			}
		}
	}
}
