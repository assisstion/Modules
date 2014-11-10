package com.github.assisstion.ModulePack.collection.sort;

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
public class SelectionSortHelper{

	private SelectionSortHelper(){

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
		for(int i = begin; i < end - 1; i++){
			T smallest = array[i];
			int smallestIndex = i;
			for(int j = i + 1; j < end; j++){
				T current = array[j];
				if(comp.compare(smallest, current) > 0){
					smallest = current;
					smallestIndex = j;
				}
			}
			array[smallestIndex] = array[i];
			array[i] = smallest;
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
		for(int i = begin; i < end - 1; i++){
			T smallest = list.get(i);
			int smallestIndex = i;
			for(int j = i + 1; j < end; j++){
				T current = list.get(j);
				if(comp.compare(smallest, current) > 0){
					smallest = current;
					smallestIndex = j;
				}
			}
			list.set(smallestIndex, list.get(i));
			list.set(i, smallest);
		}
	}

	public static <T> BiConsumer<T[], Comparator<? super T>> getArraySorter(){
		return SelectionSortHelper::sort;
	}

	public static <T> BiConsumer<List<T>, Comparator<? super T>> getListSorter(){
		return SelectionSortHelper::sort;
	}
}
