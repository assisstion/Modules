package com.github.assisstion.ModulePack.collection;

import java.util.Comparator;

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
@Sorter
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
		for(int i = 1; i < array.length; i++){
			T current = array[i];
			for(int swap = i; swap > 0; swap--){
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
}
