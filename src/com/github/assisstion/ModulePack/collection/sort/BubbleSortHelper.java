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
public final class BubbleSortHelper{

	private BubbleSortHelper(){

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
		boolean swapped = true;
		while(swapped){
			swapped = false;
			for(int i = 1; i < array.length; i++){
				T current = array[i];
				T last = array[i - 1];
				if(comp.compare(last, current) > 0){
					swapped = true;
					array[i] = last;
					array[i-1] = current;
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
		boolean swapped = true;
		while(swapped){
			swapped = false;
			for(int i = 1; i < list.size(); i++){
				T current = list.get(i);
				T last = list.get(i - 1);
				if(comp.compare(last, current) > 0){
					swapped = true;
					list.set(i, last);
					list.set(i - 1, current);
				}
			}
		}
	}

	public static <T> BiConsumer<T[], Comparator<? super T>> getArraySorter(){
		return BubbleSortHelper::sort;
	}

	public static <T> BiConsumer<List<T>, Comparator<? super T>> getListSorter(){
		return BubbleSortHelper::sort;
	}
}
