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
		Class<?> clazz = HeapSortHelper.class;
		Integer[] ia = getTestingArray();
		Integer[] backup = ia.clone();
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
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
	}

	private static Integer[] getTestingArray(){
		Random random = new Random();
		Integer[] ia = new Integer[1024];
		for(int i = 0; i < ia.length; i++){
			ia[i] = random.nextInt();
		}
		return ia;
	}

	public static <T extends Comparable<? super T>> boolean isSorted(T[] array){
		return isSorted(array, new NaturalComparator<T>());
	}

	public static <T> boolean verify(T[] arrayA, T[] arrayB){
		int bl = arrayB.length;
		if(arrayA.length != bl){
			return false;
		}
		boolean[] bIndex = new boolean[bl];
		outer: for(int i = 0; i < arrayA.length; i++){
			for(int j = 0; j < arrayB.length; j++){
				if(bIndex[j] == true){
					continue;
				}
				if(arrayB[j].equals(arrayA[i])){
					bIndex[j] = true;
					continue outer;
				}
			}
			return false;
		}
		return true;
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
		if(fastSort(clazz, array, comparator)){
			return;
		}
		if(clazz.isAnnotationPresent(Sorter.class)){
			Method sorter = clazz.getMethod("sort", Object[].class, Comparator.class);
			sorter.invoke(null, array, comparator);
		}
		else{
			throw new ClassCastException("Missing annotation: @Sorter");
		}
	}

	public static <T> boolean fastSort(Class<?> clazz, T[] array, Comparator<? super T> comparator){
		if(clazz.equals(DynamicQuickSortHelper.class)){
			DynamicQuickSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(HeapSortHelper.class)){
			DynamicQuickSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(BubbleSortHelper.class)){
			BubbleSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(CloningMergeSortHelper.class)){
			CloningMergeSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(InPlaceMergeSortHelper.class)){
			InPlaceMergeSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(InsertionSortHelper.class)){
			InsertionSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(SelectionSortHelper.class)){
			SelectionSortHelper.sort(array, comparator);
		}
		else if(clazz.equals(QuickSortHelper.class)){
			QuickSortHelper.sort(array, comparator);
		}
		else{
			return false;
		}
		return true;
	}


}
