package com.github.assisstion.ModulePack.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.github.assisstion.ModulePack.Pair;
import com.github.assisstion.ModulePack.annotation.Helper;

@Helper
public final class SortHelper{

	private SortHelper(){

	}

	//Sample test
	public static void main(String[] args){
		analyzeSorts();
	}

	public static void analyzeSorts(){
		TreeMap<Long, Class<?>> map = new TreeMap<Long, Class<?>>();
		List<Class<?>> sorts = new ArrayList<Class<?>>();
		sorts.add(DynamicQuickSortHelper.class);
		sorts.add(HeapSortHelper.class);
		sorts.add(BubbleSortHelper.class);
		sorts.add(CloningMergeSortHelper.class);
		sorts.add(InPlaceMergeSortHelper.class);
		sorts.add(InsertionSortHelper.class);
		sorts.add(SelectionSortHelper.class);
		sorts.add(QuickSortHelper.class);
		for(Class<?> clazz : sorts){
			long time = sampleTest(clazz, false);
			System.out.println("--------");
			map.put(time, clazz);
		}
		for(Map.Entry<Long, Class<?>> entry : map.entrySet()){
			System.out.println(entry.getValue().getSimpleName() + " sort: "
					+ entry.getKey() + " ns");
		}
	}

	public static long sampleTest(Class<?> clazz, boolean verbose){
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
		if(verbose){
			for(int i : ia){
				System.out.println(i);
			}
		}
		System.out.println(clazz.getSimpleName() + " sort");
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
		return n2 - n1;
	}

	public static long stabilityTest(Class<?> clazz, boolean verbose){
		LeftSortedIntegerPair[] ia = getTestingIntegerPairs();
		LeftSortedIntegerPair[] backup = ia.clone();
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
		if(verbose){
			for(LeftSortedIntegerPair i : ia){
				System.out.println(i);
			}
		}
		System.out.println(clazz.getSimpleName() + " sort");
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Stable: " + LeftSortedIntegerPair.isSorted(ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
		return n2 - n1;
	}

	private static Integer[] getTestingArray(){
		Random random = new Random();
		Integer[] ia = new Integer[1024];
		for(int i = 0; i < ia.length; i++){
			ia[i] = random.nextInt();
		}
		return ia;
	}

	private static LeftSortedIntegerPair[] getTestingIntegerPairs(){
		Random random = new Random();
		LeftSortedIntegerPair[] ia = new LeftSortedIntegerPair[1024];
		for(int i = 0; i < ia.length - 1; i+=2){
			int n = random.nextInt();
			ia[i] = new LeftSortedIntegerPair(n, i);
			ia[i + 1] = new LeftSortedIntegerPair(n, i + 1);
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

	private static class LeftSortedIntegerPair extends Pair<Integer, Integer>
	implements Comparable<LeftSortedIntegerPair>{

		private static final long serialVersionUID = -2794933434914594631L;

		public LeftSortedIntegerPair(int left, int right){
			super(left, right);
		}

		private static boolean isSorted(LeftSortedIntegerPair[] array){
			Comparator<Integer> comparator = new NaturalComparator<Integer>();
			for(int i = 1; i < array.length; i++){
				LeftSortedIntegerPair last = array[i - 1];
				LeftSortedIntegerPair current = array[i];
				if(comparator.compare(last.getValueOne(), current.getValueOne()) == 0 &&
						comparator.compare(last.getValueTwo(), current.getValueTwo()) > 0){
					return false;
				}
			}
			return true;
		}

		@Override
		public int compareTo(LeftSortedIntegerPair o){
			return Integer.compare(getValueOne(), o.getValueOne());
		}

	}

}
