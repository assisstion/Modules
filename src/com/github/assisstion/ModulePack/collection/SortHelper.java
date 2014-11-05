package com.github.assisstion.ModulePack.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Random;

import com.github.assisstion.ModulePack.annotation.Helper;

@Helper
public final class SortHelper{

	private SortHelper(){

	}

	//Sample test
	public static void main(String[] args){
		Class<?> clazz = MergeSortHelper.class;
		Random random = new Random();
		Integer[] ia = new Integer[1024];
		for(int i = 0; i < ia.length; i++){
			ia[i] = random.nextInt();
		}
		long n1 = System.nanoTime();
		try{
			sort(clazz, ia);
		}
		catch(IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long n2 = System.nanoTime();
		for(int i : ia){
			System.out.println(i);
		}
		System.out.println(clazz.getSimpleName() + " sort");
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
	}

	public static <T extends Comparable<? super T>> boolean isSorted(T[] array){
		return isSorted(array, new NaturalComparator<T>());
	}

	public static <T> boolean isSorted(T[] array, Comparator<? super T> comparator){
		for(int i = 1; i < array.length; i++){
			T last = array[i - 1];
			T current = array[i];
			if(comparator.compare(last, current) > 0){
				return false;
			}
		}
		return true;
	}

	public static <T extends Comparable<? super T>> void sort(Class<?> clazz, T[] array)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		sort(clazz, array, new NaturalComparator<T>());
	}

	public static <T> void sort(Class<?> clazz, T[] array, Comparator<? super T> comparator)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(clazz.isAnnotationPresent(Sorter.class)){
			Method sorter = clazz.getMethod("sort", Object[].class, Comparator.class);
			sorter.invoke(null, array, comparator);
		}
		else{
			throw new ClassCastException("Missing annotation: @Sorter");
		}
	}

}
