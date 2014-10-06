package com.github.assisstion.ModulePack.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.github.assisstion.ModulePack.Pair;
import com.github.assisstion.ModulePack.annotation.Dependency;

@Dependency(Pair.class)
public final class ArrayHelper{

	private ArrayHelper(){}

	public static <T> int arrayCounter(T[] x){
		int z = 0;
		for(int i = 0; i < x.length; i++){
			T y = x[i];
			if(y != null){
				z++;
			}
		}
		return z;
	}

	public static <T> T[] arrayCopy(T[] x){
		return arrayCopy(x, true);
	}

	public static <T> T[] arrayCopy(T[] x, boolean notNull){
		int z = 0;
		T[] l = x.clone();
		for(int i = 0; i < x.length; i++){
			T y = x[i];
			l[i] = null;
			if(!notNull || y != null){
				l[z] = y;
				z++;
			}
		}
		T[] n = Arrays.copyOf(l, z);
		return n;
	}

	public static int[] arrayCopy(int[] x){
		return arrayCopy(x, false);
	}

	public static int[] arrayCopy(int[] x, boolean notZero){
		int z = 0;
		int[] l = new int[x.length + 1];
		for(int i = 0; i < x.length; i++){
			int y = x[i];
			if(!notZero || y != 0){
				l[z] = y;
				z++;
			}
		}
		int[] n = new int[z];
		System.arraycopy(l, 0, n, 0, z);
		return n;
	}

	public static byte[] arrayCopy(byte[] x){
		return arrayCopy(x, false);
	}

	public static byte[] arrayCopy(byte[] x, boolean notZero){
		int z = 0;
		byte[] l = new byte[x.length + 1];
		for(int i = 0; i < x.length; i++){
			byte y = x[i];
			if(!notZero || y != 0){
				l[z] = y;
				z++;
			}
		}
		byte[] n = new byte[z];
		System.arraycopy(l, 0, n, 0, z);
		return n;
	}

	public static char[] arrayCopy(char[] x){
		return arrayCopy(x, false);
	}

	public static char[] arrayCopy(char[] x, boolean notZero){
		int z = 0;
		char[] l = new char[x.length + 1];
		for(int i = 0; i < x.length; i++){
			char y = x[i];
			if(!notZero || y != 0){
				l[z] = y;
				z++;
			}
		}
		char[] n = new char[z];
		System.arraycopy(l, 0, n, 0, z);
		return n;
	}

	public static <T> T[] arrayCopy(T[] x, int size){
		int z = 0;
		T[] l = x.clone();
		for(int i = 0; i < x.length; i++){
			T y = x[i];
			l[i] = null;
			if(y != null){
				l[z] = y;
				z++;
			}
		}
		T[] n = Arrays.copyOf(l, size);
		return n;
	}

	public static <T> void arrayPrint(T[] x){
		arrayPrint(x, "\n", true);
	}

	public static <T> void arrayPrint(T[] x, String separator){
		arrayPrint(x, separator, false);
	}

	public static <T> void arrayPrint(T[] x, String separator, boolean finalSep){
		T[] b = x;//arrayCopy(x);
		boolean first = true;
		for(int n = 0; n < b.length; n++){
			if(first){
				first = false;
			}
			else{
				System.out.print(separator);
			}
			T w = b[n];
			System.out.print(w);
		}
		if(finalSep){
			System.out.print(separator);
		}
	}

	/*
	public static boolean contains(Object[] x, Object y){
		for(int i = 0; i < x.length; i++){
			if(x[i] == y){
				return true;
			}
		}
		return false;

	}
	 */

	public static <T, S extends T> boolean containsArray(T[] x, S[] y){
		for(int j = 0; j < y.length; j++){
			for(int i = 0; i < x.length; i++){
				if(x[i].equals(y[j])){
					return true;
				}
			}
		}
		return false;

	}

	public static <T, S extends T> int positionIn(T[] x, S y){
		return firstIndexOf(x, y);
	}

	public static void arrayPrint(int[] x) {
		int[] b = x;//arrayCopy(x);
		for(int n = 0; n < b.length; n++){
			int w = b[n];
			System.out.println(w);
		}
	}

	public static void arrayPrint(byte[] x) {
		byte[] b = x;//arrayCopy(x);
		for(int n = 0; n < b.length; n++){
			byte w = b[n];
			System.out.println(w);
		}
	}

	public static void arrayPrint(char[] x) {
		char[] b = x;//arrayCopy(x);
		for(int n = 0; n < b.length; n++){
			char w = b[n];
			System.out.println(w);
		}
	}

	public static <T, S extends T> boolean contains(T[] x, S y){
		for(int i = 0; i < x.length; i++){
			if(x[i].equals(y)){
				return true;
			}
		}
		return false;

	}

	public static <T, S extends T> Set<Integer> indexOf(T[] x, S y){
		HashSet<Integer> integers = new HashSet<Integer>();
		for(int i = 0; i < x.length; i++){
			if(x[i].equals(y)){
				integers.add(i);
			}
		}
		return integers;
	}

	public static <T, S extends T> int firstIndexOf(T[] x, S y){
		return nthIndexOf(x, y, 0);
	}

	//Index n, not numeral n
	public static <T, S extends T> int nthIndexOf(T[] x, S y, int n){
		int counter = 0;
		for(int i = 0; i < x.length; i++){
			if(x[i].equals(y)){
				if(counter >= n){
					return i;
				}
				else{
					counter++;
				}
			}
		}
		return -1;
	}

	public static <T, S extends T> int lastIndexOf(T[] x, S y, int n){
		int lastFound = -1;
		for(int i = 0; i < x.length; i++){
			if(x[i].equals(y)){
				lastFound = i;
			}
		}
		return lastFound;
	}

	public static <T, S extends T> Set<Pair<Integer, Integer>> indexOf(T[][] x, S y){
		HashSet<Pair<Integer, Integer>> integers = new HashSet<Pair<Integer, Integer>>();
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[i].length; j++){
				if(x[i][j].equals(y)){
					integers.add(new Pair<Integer, Integer>(i, j));
				}
			}
		}
		return integers;
	}

	public static <T, S extends T> Pair<Integer, Integer> firstIndexOf(T[][] x, S y){
		return nthIndexOf(x, y, 0);
	}

	public static <T, S extends T> Pair<Integer, Integer> nthIndexOf(T[][] x, S y, int n){
		int counter = 0;
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[i].length; j++){
				if(x[i][j].equals(y)){
					if(counter >= n){
						return new Pair<Integer, Integer>(i, j);
					}
					else{
						counter++;
					}
				}
			}
		}
		return null;
	}

	public static <T, S extends T> Pair<Integer, Integer> lastIndexOf(T[][] x, S y){
		Pair<Integer, Integer> lastFound = null;
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[i].length; j++){
				if(x[i][j].equals(y)){
					lastFound = new Pair<Integer, Integer>(i, j);
				}
			}
		}
		return lastFound;
	}
}
