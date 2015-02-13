package com.github.assisstion.ModulePack.collection.sort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import com.github.assisstion.ModulePack.Pair;
import com.github.assisstion.ModulePack.annotation.Helper;

@Helper
public final class SortHelper{

	private SortHelper(){

	}

	//Sample test
	public static void main(String[] args){
		analyzeListSorts();
	}

	public static List<Class<?>> getSorts(){
		List<Class<?>> sorts = new ArrayList<Class<?>>();
		sorts.add(DynamicQuickSortHelper.class);
		sorts.add(QuickSortHelper.class);
		sorts.add(HeapSortHelper.class);
		sorts.add(DynamicMergeSortHelper.class);
		sorts.add(InPlaceMergeSortHelper.class);
		sorts.add(CloningMergeSortHelper.class);
		sorts.add(InsertionSortHelper.class);
		sorts.add(SelectionSortHelper.class);
		sorts.add(BubbleSortHelper.class);
		return sorts;
	}

	public static void analyzeSorts(){
		TreeMap<Long, Class<?>> map = new TreeMap<Long, Class<?>>();
		List<Class<?>> sorts = getSorts();
		for(Class<?> clazz : sorts){
			sampleTest(clazz, false);
			System.out.println("--------");
		}
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

	public static void analyzeListSorts(){
		TreeMap<Long, Class<?>> map = new TreeMap<Long, Class<?>>();
		List<Class<?>> sorts = getSorts();
		for(Class<?> clazz : sorts){
			sampleListTest(clazz, false);
			System.out.println("--------");
		}
		for(Class<?> clazz : sorts){
			long time = sampleListTest(clazz, false);
			System.out.println("--------");
			map.put(time, clazz);
		}
		for(Map.Entry<Long, Class<?>> entry : map.entrySet()){
			System.out.println(entry.getValue().getSimpleName() + " sort: "
					+ entry.getKey() + " ns");
		}
	}

	public static void analyzeListStability(){
		TreeMap<Long, Class<?>> map = new TreeMap<Long, Class<?>>();
		List<Class<?>> sorts = getSorts();
		for(Class<?> clazz : sorts){
			sampleListTest(clazz, false);
			System.out.println("--------");
		}
		for(Class<?> clazz : sorts){
			long time = listStabilityTest(clazz, false);
			System.out.println("--------");
			map.put(time, clazz);
		}
		for(Map.Entry<Long, Class<?>> entry : map.entrySet()){
			System.out.println(entry.getValue().getSimpleName() + " sort: "
					+ entry.getKey() + " ns");
		}
	}

	public static long sampleTest(Class<?> clazz, boolean verbose){
		System.out.println(clazz.getSimpleName() + " sort");
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
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
		return n2 - n1;
	}

	public static long sampleListTest(Class<?> clazz, boolean verbose){
		System.out.println(clazz.getSimpleName() + " sort");
		List<Integer> ia = getTestingList();
		List<Integer> backup = new ArrayList<Integer>(ia);
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
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
		return n2 - n1;
	}

	public static long stabilityTest(Class<?> clazz, boolean verbose){
		System.out.println(clazz.getSimpleName() + " sort");
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
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Stable: " + LeftSortedIntegerPair.isSorted(ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
		return n2 - n1;
	}

	public static long listStabilityTest(Class<?> clazz, boolean verbose){
		System.out.println(clazz.getSimpleName() + " sort");
		List<LeftSortedIntegerPair> ia = getTestingIntegerPairList();
		List<LeftSortedIntegerPair> backup = new ArrayList<LeftSortedIntegerPair>(ia);
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
		System.out.println("Sorted: " + SortHelper.isSorted(ia));
		System.out.println("Verified: " + SortHelper.verify(backup, ia));
		System.out.println("Stable: " + LeftSortedIntegerPair.isSorted(ia));
		System.out.println("Time elapsed (ns): " + (n2 - n1));
		return n2 - n1;
	}

	private static final int TEST_LENGTH = 1024;

	private static Integer[] getTestingArray(){
		Random random = new Random();
		Integer[] ia = new Integer[TEST_LENGTH];
		for(int i = 0; i < ia.length; i++){
			ia[i] = random.nextInt();
		}
		return ia;
	}

	private static List<Integer> getTestingList(){
		Random random = new Random();
		int length = TEST_LENGTH;
		List<Integer> ia = new ArrayList<Integer>(length);
		for(int i = 0; i < length; i++){
			ia.add(random.nextInt());
		}
		return ia;
	}

	private static LeftSortedIntegerPair[] getTestingIntegerPairs(){
		Random random = new Random();
		LeftSortedIntegerPair[] ia = new LeftSortedIntegerPair[TEST_LENGTH];
		for(int i = 0; i < ia.length - 1; i+=2){
			int n = random.nextInt();
			ia[i] = new LeftSortedIntegerPair(n, i);
			ia[i + 1] = new LeftSortedIntegerPair(n, i + 1);
		}
		return ia;
	}

	private static List<LeftSortedIntegerPair> getTestingIntegerPairList(){
		Random random = new Random();
		int length = TEST_LENGTH;
		List<LeftSortedIntegerPair> ia = new ArrayList<LeftSortedIntegerPair>(length);
		for(int i = 0; i < length - 1; i+=2){
			int n = random.nextInt();
			ia.add(new LeftSortedIntegerPair(n, i));
			ia.add(new LeftSortedIntegerPair(n, i + 1));
		}
		return ia;
	}

	public static <T extends Comparable<? super T>> boolean isSorted(T[] array){
		return isSorted(array, new NaturalComparator<T>());
	}

	public static <T extends Comparable<? super T>> boolean isSorted(List<T> list){
		return isSorted(list, new NaturalComparator<T>());
	}

	public static <T extends Comparable<? super T>> boolean verify(T[] arrayA, T[] arrayB){
		Comparator<T> comp = new NaturalComparator<T>();
		int bl = arrayB.length;
		if(arrayA.length != bl){
			return false;
		}
		Arrays.sort(arrayA);
		Arrays.sort(arrayB);
		for(int i = 0; i < bl; i++){
			if(comp.compare(arrayA[i], arrayB[i]) != 0){
				return false;
			}
		}
		return true;
	}

	public static <T extends Comparable<? super T>> boolean verify(List<T> arrayA, List<T> arrayB){
		Comparator<T> comp = new NaturalComparator<T>();
		int bl = arrayB.size();;
		if(arrayA.size() != bl){
			return false;
		}
		Collections.sort(arrayA);
		Collections.sort(arrayB);
		for(int i = 0; i < bl; i++){
			if(comp.compare(arrayA.get(i), arrayB.get(i)) != 0){
				return false;
			}
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

	public static <T> boolean isSorted(List<T> list, Comparator<? super T> comparator){
		for(int i = 1; i < list.size(); i++){
			T last = list.get(i - 1);
			T current = list.get(i);
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

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T extends Comparable<? super T>> void sort(Class<?> clazz,
			Class<?> containerType, Object container)
					throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		sort(clazz, containerType, container, new NaturalComparator());
	}

	@SuppressWarnings("unchecked")
	public static <T> void sort(Class<?> clazz, Class<?> containerType,
			Object container, Comparator<? super T> comparator)
					throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(containerType.equals(Object[].class)){
			sort(clazz, (T[]) container, comparator);
		}
		else if(containerType.equals(List.class)){
			sort(clazz, (List<T>) container, comparator);
		}
		else if(
				contains(clazz.getAnnotationsByType(Sorter.class), containerType)){
			Method sorter = clazz.getMethod("sort", containerType, Comparator.class);
			sorter.invoke(null, container, comparator);
		}
		else{
			throw new ClassCastException("Missing annotation: @Sorter");
		}
	}

	public static <T> void sort(Class<?> clazz, T[] array, Comparator<? super T> comparator)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(fastSort(clazz, array, comparator)){
			return;
		}
		if(contains(clazz.getAnnotationsByType(Sorter.class), Object[].class)){
			Method sorter = clazz.getMethod("sort", Object[].class, Comparator.class);
			sorter.invoke(null, array, comparator);
		}
		else{
			throw new ClassCastException("Missing annotation: @Sorter");
		}
	}

	public static <T extends Comparable<? super T>> void sort(Class<?> clazz, List<T> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		sort(clazz, list, new NaturalComparator<T>());
	}

	public static <T> void sort(Class<?> clazz, List<T> list, Comparator<? super T> comparator)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(fastSort(clazz, list, comparator)){
			return;
		}
		if(contains(clazz.getAnnotationsByType(Sorter.class), List.class)){
			Method sorter = clazz.getMethod("sort", List.class, Comparator.class);
			sorter.invoke(null, list, comparator);
		}
		else{
			throw new ClassCastException("Missing annotation: @Sorter");
		}
	}

	private static boolean contains(Sorter[] ta, Class<?> matcher){
		for(Sorter t : ta){
			if(matcher.equals(t.value())){
				return true;
			}
		}
		return false;
	}

	public static <T> boolean fastSort(Class<?> clazz, T[] array, Comparator<? super T> comparator){
		Map<Class<?>, BiConsumer<T[], Comparator<? super T>>> map = arraySorts(array);
		if(!map.containsKey(clazz)){
			return false;
		}
		map.get(clazz).accept(array, comparator);
		return true;
	}

	public static <T> boolean fastSort(Class<?> clazz, List<T> list, Comparator<? super T> comparator){
		Map<Class<?>, BiConsumer<List<T>, Comparator<? super T>>> map = listSorts(list);
		if(!map.containsKey(clazz)){
			return false;
		}
		map.get(clazz).accept(list, comparator);
		return true;
	}

	protected static <T> Map<Class<?>, BiConsumer<List<T>, Comparator<? super T>>> listSorts(List<T> list){
		Map<Class<?>, BiConsumer<List<T>, Comparator<? super T>>> map =
				new HashMap<Class<?>, BiConsumer<List<T>, Comparator<? super T>>>();
		map.put(DynamicMergeSortHelper.class, DynamicMergeSortHelper.getListSorter());
		map.put(DynamicQuickSortHelper.class, DynamicQuickSortHelper.getListSorter());
		map.put(HeapSortHelper.class, HeapSortHelper.getListSorter());
		map.put(BubbleSortHelper.class, BubbleSortHelper.getListSorter());
		map.put(CloningMergeSortHelper.class, CloningMergeSortHelper.getListSorter());
		map.put(InPlaceMergeSortHelper.class, InPlaceMergeSortHelper.getListSorter());
		map.put(InsertionSortHelper.class, InsertionSortHelper.getListSorter());
		map.put(SelectionSortHelper.class, SelectionSortHelper.getListSorter());
		map.put(QuickSortHelper.class, QuickSortHelper.getListSorter());
		return map;
	}

	protected static <T> Map<Class<?>, BiConsumer<T[], Comparator<? super T>>> arraySorts(T[] array){
		Map<Class<?>, BiConsumer<T[], Comparator<? super T>>> map =
				new HashMap<Class<?>, BiConsumer<T[], Comparator<? super T>>>();
		map.put(DynamicMergeSortHelper.class, DynamicMergeSortHelper.getArraySorter());
		map.put(DynamicQuickSortHelper.class, DynamicQuickSortHelper.getArraySorter());
		map.put(HeapSortHelper.class, HeapSortHelper.getArraySorter());
		map.put(BubbleSortHelper.class, BubbleSortHelper.getArraySorter());
		map.put(CloningMergeSortHelper.class, CloningMergeSortHelper.getArraySorter());
		map.put(InPlaceMergeSortHelper.class, InPlaceMergeSortHelper.getArraySorter());
		map.put(InsertionSortHelper.class, InsertionSortHelper.getArraySorter());
		map.put(SelectionSortHelper.class, SelectionSortHelper.getArraySorter());
		map.put(QuickSortHelper.class, QuickSortHelper.getArraySorter());
		return map;
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

		private static boolean isSorted(List<LeftSortedIntegerPair> list){
			Comparator<Integer> comparator = new NaturalComparator<Integer>();
			for(int i = 1; i < list.size(); i++){
				LeftSortedIntegerPair last = list.get(i - 1);
				LeftSortedIntegerPair current = list.get(i);
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

	public static <T extends Comparable<? super T>, S> void sort(Class<?> clazz, S list){
		sort(clazz, list, new NaturalComparator<T>());
	}

	public static <T, S> void sort(Class<?> clazz, S list, Comparator<? super T> comp){
		try{
			for(Sorter sorter : clazz.getAnnotationsByType(Sorter.class)){
				if(sorter.value().isInstance(list)){
					Method m = clazz.getMethod(
							"sort", sorter.value(), Comparator.class);
					m.invoke(list, comp);
				}
			}
		}
		catch(Exception e){
			throw new RuntimeException("Invocation exception during sorting", e);
		}
	}

	public static <T, S> BiConsumer<S, Comparator<? super T>> getSorter(Class<?> clazz){
		return (t, s) -> sort(clazz, t, s);
	}

}
