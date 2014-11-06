package com.github.assisstion.ModulePack.collection;

import java.util.Comparator;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;
import com.github.assisstion.ModulePack.annotation.Helper;

@CompileVersion(SourceVersion.RELEASE_5) //Generics
@Helper
@Sorter
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
		for(int i = 0; i < array.length - 1; i++){
			T smallest = array[i];
			int smallestIndex = i;
			for(int j = i + 1; j < array.length; j++){
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

}