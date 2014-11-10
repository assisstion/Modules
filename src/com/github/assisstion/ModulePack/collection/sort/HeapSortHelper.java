package com.github.assisstion.ModulePack.collection.sort;

import java.util.Comparator;
import java.util.List;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter(Object[].class)
@Sorter(List.class)
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
		sort(array, comp, 0, array.length);
	}

	public static <T> void sort(T[] array, Comparator<? super T> comp, int begin, int end){
		int size = end - begin;
		buildHeap(array, comp, begin, size);
		int currentSize = size;
		while(currentSize > 0){
			T last = array[currentSize - 1 + begin];
			T head = array[begin];
			array[currentSize - 1 + begin] = head;
			array[begin] = last;
			currentSize--;
			swapDown(array, comp, 0, currentSize, begin);
		}
	}

	/**
	 * Builds the array into a heap.
	 * @param array the array to be built
	 * @param comp the comparator to be used
	 */
	private static <T> void buildHeap(T[] array, Comparator<? super T> comp, int offset, int size){
		//Last parent node
		int start = (size - 2) / 2;
		while(start >= 0){
			swapDown(array, comp, start, size, offset);
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
			int begin, int end, int offset){
		int rootIndex = begin;
		while(rootIndex * 2 + 1 < end){
			int childIndex = rootIndex * 2 + 1;
			int swapIndex = rootIndex;
			T child = array[childIndex + offset];
			T swap = array[swapIndex + offset];
			if(comp.compare(swap, child) < 0){
				swapIndex = childIndex;
				swap = child;
			}
			if(childIndex + 1 < end){
				T rightChild = array[childIndex + 1 + offset];
				if(comp.compare(swap, rightChild) < 0){
					swapIndex = childIndex + 1;
					swap = rightChild;
				}
			}
			if(swapIndex != rootIndex){
				T root = array[rootIndex + offset];
				array[rootIndex + offset] = swap;
				array[swapIndex + offset] = root;
				rootIndex = swapIndex;
			}
			else{
				return;
			}
		}

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
		sort(list, comp, 0, list.size());
	}

	public static <T> void sort(List<T> list, Comparator<? super T> comp, int begin, int end){
		int size = end - begin;
		buildHeap(list, comp, begin, size);
		int currentSize = size;
		while(currentSize > 0){
			T last = list.get(currentSize - 1 + begin);
			T head = list.get(begin);
			list.set(currentSize - 1 + begin, head);
			list.set(begin, last);
			currentSize--;
			swapDown(list, comp, 0, currentSize, begin);
		}
	}

	/**
	 * Builds the array into a heap.
	 * @param array the array to be built
	 * @param comp the comparator to be used
	 */
	private static <T> void buildHeap(List<T> array, Comparator<? super T> comp,
			int offset, int size){
		//Last parent node
		int start = (size - 2) / 2;
		while(start >= 0){
			swapDown(array, comp, start, size, offset);
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
	private static <T> void swapDown(List<T> array, Comparator<? super T> comp,
			int begin, int end, int offset){
		int rootIndex = begin;
		while(rootIndex * 2 + 1 < end){
			int childIndex = rootIndex * 2 + 1;
			int swapIndex = rootIndex;
			T child = array.get(childIndex + offset);
			T swap = array.get(swapIndex + offset);
			if(comp.compare(swap, child) < 0){
				swapIndex = childIndex;
				swap = child;
			}
			if(childIndex + 1 < end){
				T rightChild = array.get(childIndex + 1 + offset);
				if(comp.compare(swap, rightChild) < 0){
					swapIndex = childIndex + 1;
					swap = rightChild;
				}
			}
			if(swapIndex != rootIndex){
				T root = array.get(rootIndex + offset);
				array.set(rootIndex + offset, swap);
				array.set(swapIndex + offset, root);
				rootIndex = swapIndex;
			}
			else{
				return;
			}
		}

	}

}
