package com.github.assisstion.ModulePack.collection.sort;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

/**
 * Standard insertion sort implementation.
 * Creates no new arrays. All operations done to the
 * input array are flip/exchange operations.
 *
 * This sort is in-place and stable.
 *
 * @author Markus Feng
 */
@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter(Object[].class)
@Sorter(List.class)
public final class InsertionSortHelper{

	private InsertionSortHelper(){

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
		for(int i = begin + 1; i < end; i++){
			T current = array[i];
			for(int swap = i; swap > begin; swap--){
				T compare = array[swap - 1];
				if(comp.compare(current, compare) < 0){
					array[swap] = compare;
					array[swap - 1] = current;
				}
				else{
					break;
				}
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

	/**
	 * Sorts a part of an list using a specified comparator.
	 * @param list the list to be sorted
	 * @param comp the comparator to be used
	 * @param begin the begin index of the sorted elements (inclusive)
	 * @param end the end index of the sorted elements (exclusive)
	 */
	public static <T> void sort(List<T> list, Comparator<? super T> comp, int begin, int end){
		for(int i = begin + 1; i < end; i++){
			T current = list.get(i);
			for(int swap = i; swap > begin; swap--){
				T compare = list.get(swap - 1);
				if(comp.compare(current, compare) < 0){
					list.set(swap, compare);
					list.set(swap - 1, current);
				}
				else{
					break;
				}
			}
		}
	}

	public static <T> BiConsumer<T[], Comparator<? super T>> getArraySorter(){
		return InsertionSortHelper::sort;
	}

	public static <T> BiConsumer<List<T>, Comparator<? super T>> getListSorter(){
		return InsertionSortHelper::sort;
	}
}
