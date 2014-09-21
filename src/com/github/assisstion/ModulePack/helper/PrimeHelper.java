package com.github.assisstion.ModulePack.helper;

public final class PrimeHelper {

	public static int relativelyPrime(int n1, int n2){
		for(int i = 2; i >= n1 || i >= n2; i++){
			if(n1 % i == 0 && n2 % i == 0){
				return i;
			}
		}
		return 0;
	}

	public static int[] primeFactorization(int i){
		if(i < 0){
			throw new ArithmeticException("Cannot determine if a negative value is prime");
		}
		else if(i == 0){
			throw new ArithmeticException("Cannot determine if zero is prime");
		}
		else if(i == 1){
			throw new ArithmeticException("Cannot determine if one is prime");
		}
		else{
			int[] returnValue = new int[i];
			int j = i;
			int x = 0;
			int n = 2;
			while(n <= j){
				if(test(n)){
					if(j % n == 0){
						returnValue[x] = n;
						x++;
						j = j/n;
						n = 1;
					}
				}
				n++;
			}
			return arrayCopy(returnValue);
		}
	}

	//External
	private static int[] arrayCopy(int[] x){
		int z = 0;
		int[] l = new int[x.length + 1];
		for(int i = 0; i < x.length; i++){
			int y = x[i];
			if(y != 0){
				l[z] = y;
				z++;
			}
		}
		int[] n = new int[z];
		System.arraycopy(l, 0, n, 0, z);
		return n;
	}

	/*public int gcf(int n1, int n2){
		int[] x1 = primeFactorization(n1);
		int[] x2 = primeFactorization(n2);
		return 0;
	}*/

	public static boolean test(int i){
		if(i < 0){
			throw new ArithmeticException("Cannot determine if a negative value is prime");
		}
		else if(i == 0){
			throw new ArithmeticException("Cannot determine if zero is prime");
		}
		else if(i == 1){
			throw new ArithmeticException("Cannot determine if one is prime");
		}
		else if(i == 2){
			return true;
		}
		else{
			return unprotectedTest(i);
		}
	}

	public static boolean test(long l){
		if(l < 0){
			throw new ArithmeticException("Cannot determine if a negative value is prime");
		}
		else if(l == 0){
			throw new ArithmeticException("Cannot determine if zero is prime");
		}
		else if(l == 1){
			throw new ArithmeticException("Cannot determine if one is prime");
		}
		else if(l == 2){
			return true;
		}
		else{
			return unprotectedTest(l);
		}
	}

	public static int[] test(int[] intArray){
		int[] returnArrayTemp = new int[intArray.length];
		for(int n = 0, m = intArray.length; n < m; n++){
			int i = intArray[n];
			if(i < 0){
				throw new ArithmeticException("Cannot determine if a negative value is prime");
			}
			else if(i == 0){
				throw new ArithmeticException("Cannot determine if zero is prime");
			}
			else if(i == 1){
				throw new ArithmeticException("Cannot determine if one is prime");
			}
			else if(i == 2){
				returnArrayTemp[n] = i;
			}
			else{
				if(unprotectedTest(i) == true){
					returnArrayTemp[n] = i;
				}
			}
		}
		int[] returnArray = arrayCopy(returnArrayTemp);
		return returnArray;
	}

	public static int[] generator(int max){
		int[] returnArrayTemp = new int[max];
		for(int n = 2, m = max; n <= m; n++){
			if(n < 0){
				throw new ArithmeticException("Cannot determine if a negative value is prime");
			}
			else if(n == 0){
				throw new ArithmeticException("Cannot determine if zero is prime");
			}
			else if(n == 1){
				throw new ArithmeticException("Cannot determine if one is prime");
			}
			else if(n == 2){
				returnArrayTemp[n] = n;
			}
			else{
				if(unprotectedTest(n) == true){
					returnArrayTemp[n] = n;
				}
			}
		}
		int[] returnArray = arrayCopy(returnArrayTemp);
		return returnArray;
	}

	private static boolean unprotectedTest(int i){
		for(int n = 2, max = (int) Math.ceil(Math.sqrt(i)); n <= max; n++){
			if(i % n == 0){
				return false;
			}
		}
		return true;
	}

	private static boolean unprotectedTest(long l){
		for(int n = 2, max = (int) Math.ceil(Math.sqrt(l)); n <= max; n++){
			if(l % n == 0){
				return false;
			}
		}
		return true;
	}

	private PrimeHelper(){}
}
