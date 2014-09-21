package com.github.assisstion.ModulePack.helper;

public final class ParsingHelper {

	private ParsingHelper(){
		//Do nothing
	}

	public static int mode = 0;
	/* 0 == custom
	 * 1 == built in
	 */

	@SuppressWarnings("unused")
	private static final char[] ACCEPTABLE_BASE10_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	@SuppressWarnings("unused")
	private static final byte[] ACCEPTABLE_BASE10_DIGITS_NUMERALS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

	private static final char[] ACCEPTABLE_DIGITS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	private static final byte[] ACCEPTABLE_DIGITS_NUMERALS = {
		0,  1,  2,  3,  4,  5,  6,  7,  8,  9,
		10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
		20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
		30, 31, 32, 33, 34, 35
	};

	public static long parseBinary(String s){
		if(mode == 1){
			return Long.parseLong(s,2);
		}
		char[] ca = reverse(s.toCharArray());
		int length = ca.length;
		long value = 0;
		for(int i = 0;i < length;i++){
			char c = ca[i];
			if(c == 49){
				if(i == 63){
					value = value - Long.MAX_VALUE;
				}
				else{
					value += Math.pow(2, i);
				}
			}
			else if (c != 48){
				throw new NumberFormatException();
			}
		}
		return value;
	}

	public static long parseLong(String s){
		if(mode == 1){
			return Long.parseLong(s,10);
		}
		boolean negative = false;
		char[] ca = s.toCharArray();
		char z = ca[0];
		if(z == 45){
			negative = true;
			ca = removeChar(ca, (char)45);
		}
		if(z == 48){
			int n = ca[1];
			if(n == 66 || n == 98){
				String real = s.substring(2);
				return parseLong(real, 2);
			}
			if(n == 88 || n == 120){
				String real = s.substring(2);
				return parseLong(real, 16);
			}
			String real = s.substring(1);
			return parseLong(real, 8);
		}
		ca = reverse(ca);
		int length = ca.length;
		long value = 0;
		for(int i = 0;i < length;i++){
			char c = ca[i];
			int n = c - 48;
			if(n < 0 || n > 9){
				throw new NumberFormatException();
			}
			long tempValue = n;
			for(int x = 0; x < i; x++){
				tempValue *= 10;
			}
			if(i > 1 && tempValue % 10 != 0){
				throw new NumberFormatException();
			}
			if(i > 18 && n == 9){
				throw new NumberFormatException();
			}
			if(i > 19){
				throw new NumberFormatException();
			}
			value += tempValue;
		}
		return negative ? -value : value;
	}

	private static char[] reverse(char[] ca){
		int length = ca.length;
		char[] out = new char[length];
		for(int i = 0; i < length; i++){
			out[length - i - 1] = ca[i];
		}
		return out;
	}

	private static char[] append(char[] ca, char c){
		int length = ca.length;
		char[] out = new char[length + 1];
		System.arraycopy(ca, 0, out, 0, length);
		out[length] = c;
		return out;
	}

	public static long parseLong(String s, int radix){
		if(mode == 1){
			return Long.parseLong(s,radix);
		}
		boolean negative = false;
		if(radix < 2 || radix > 36){
			throw new NumberFormatException();
		}
		char[] chars = s.toUpperCase().toCharArray();
		if(chars[0] == 45){
			negative = true;
			chars = removeChar(chars, (char)45);
		}
		chars = reverse(chars);
		int length = chars.length;
		long n = 0;
		for(int i = 0; i < length; i++){
			int x = chars[i];
			if(x > 47 && x < 58){
				x -= 48;
			}
			else if(x > 64 && x < 91){
				x -= 55;
			}
			else{
				throw new NumberFormatException();
			}
			if(x >= radix){
				throw new NumberFormatException();
			}
			n += x * Math.pow(radix, i);
		}
		return negative ? -n : n;
	}

	public static long parseLongWithTable(String s, int radix){
		boolean negative = false;
		if(radix < 2 || radix > 36){
			throw new NumberFormatException();
		}
		char[] chars = s.toUpperCase().toCharArray();
		if(chars[0] == 45){
			negative = true;
			chars = removeChar(chars, (char)45);
		}
		chars = reverse(chars);
		int length = chars.length;
		long n = 0;
		for(int i = 0; i < length; i++){
			char c = chars[i];
			int j = 0;
			boolean bool = false;
			for(; j < radix; j++){
				if(c == ACCEPTABLE_DIGITS[j]){
					bool = true;
					break;
				}
			}
			if(!bool){
				throw new NumberFormatException();
			}
			byte b = ACCEPTABLE_DIGITS_NUMERALS[j];
			n += b * Math.pow(radix, i);
		}
		return negative ? -n : n;
	}

	public static long[] parseArray(String text, char separator, char[] ignoredChars, int minlength, int maxlength, long minvalue, long maxvalue){
		return positiveParse(text, separator, ignoredChars, minlength, maxlength, minvalue, maxvalue);
	}

	public static long[] parseArray(String text, char separator){
		return parseArray(text, separator, null, 0, 0, 0L, 0L);
	}

	public static long[] simpleParseArray(String text, char seperator){
		return simpleParseArray(text, seperator, null, 0, 0, 0L, 0L);
	}

	public static long[] simpleParseArray(String text, char seperator, char[] ignoredChars, int minlength, int maxlength, long minvalue, long maxvalue){
		char[] chars = text.toCharArray();
		if(ignoredChars != null){
			chars = removeChars(chars, ignoredChars);
		}
		int length = chars.length;
		if(chars[length-1] != seperator){
			chars = append(chars, seperator);
			length = chars.length;
		}
		String[] words = new String[length];
		char[] tempWord = new char[length];
		int wordIteration = 0;
		int tempWordIteration = 0;
		for(int i = 0; i < length; i++){
			char c = chars[i];
			if(c > 47 && c < 58){
				tempWord[tempWordIteration] = c;
				tempWordIteration++;
			}
			else if(c == seperator){
				tempWord = removeChar(tempWord, (char)0);
				words[wordIteration] = String.copyValueOf(tempWord);
				tempWord = new char[length];
				tempWordIteration = 0;
				wordIteration++;
			}
			else{
				throw new NumberFormatException();
			}
		}
		words = removeString(words, null);
		length = words.length;
		if(minlength != 0){
			if(length < minlength){
				throw new NumberFormatException();
			}
		}
		if(maxlength != 0){
			if(length > maxlength){
				throw new NumberFormatException();
			}
		}
		long[] la = new long[length];
		for(int i = 0; i < length; i++){
			long l = parseLong(words[i]);
			if(minvalue != 0){
				if(l < minvalue){
					throw new NumberFormatException();
				}
			}
			if(maxvalue != 0){
				if(l > maxvalue){
					throw new NumberFormatException();
				}
			}
			la[i] = l;
		}
		return la;
	}

	private static long[] positiveParse(String text, char separator, char[] ignoredChars, int minlength, int maxlength, long minvalue, long maxvalue){
		final char[] ACCEPTABLE_FIRST_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		final char[] ACCEPTABLE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', separator};

		final byte[] ACCEPTABLE_FIRST_DIGIT_NUMERALS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		final byte[] ACCEPTABLE_DIGIT_NUMERALS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1};

		char[] chars = text.toCharArray();
		if(ignoredChars != null){
			for(char c : ignoredChars){
				chars = removeChar(chars, c);
			}
		}
		int length = chars.length;
		byte[] data = new byte[length];
		int parts = 0;
		for(int iteration = 0;iteration < length;parts++){
			data[iteration] = find(chars[iteration], ACCEPTABLE_FIRST_DIGITS, ACCEPTABLE_FIRST_DIGIT_NUMERALS);
			iteration++;
			for(;iteration < length;iteration++){
				data[iteration] = find(chars[iteration], ACCEPTABLE_DIGITS, ACCEPTABLE_DIGIT_NUMERALS);
				if(data[iteration] == -1){
					iteration++;
					break;
				}
			}
		}
		if(maxlength > 0){
			if(parts > maxlength){
				throw new NumberFormatException();
			}
		}
		if(parts < minlength){
			throw new NumberFormatException();
		}
		ArrayPart[] singleData = new ArrayPart[parts];
		int position = 0;
		int partialPosition = 0;
		for(int iteration = 0; iteration < parts; iteration++){
			for(;position < length;position++){
				if(data[position] == -1){
					break;
				}
			}
			byte[] array = new byte[position - partialPosition];
			System.arraycopy(data, partialPosition, array, 0, position - partialPosition);
			singleData[iteration] = new ArrayPart(array);
			position++;
			partialPosition = position;
		}
		int dataLength = singleData.length;
		long[] output = new long[parts];
		for(int iteration = 0; iteration < dataLength; iteration++){
			long n = byteArrayToInteger(singleData[iteration].part, false);
			if(minvalue != 0){
				if(n < minvalue){
					throw new NumberFormatException();
				}
			}
			if(maxvalue != 0){
				if(n > maxvalue){
					throw new NumberFormatException();
				}
			}
			output[iteration] = n;
		}
		return output;
	}



	//TODO Implement Negative Parsing
	/*
	private  static long[] negativeParse(String text, char separator, char[] ignoredChars){
		final char[] ACCEPTABLE_FIRST_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};
		final char[] ACCEPTABLE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', separator};

		final byte[] ACCEPTABLE_FIRST_DIGIT_NUMERALS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1};
		final byte[] ACCEPTABLE_DIGIT_NUMERALS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -2};

		return null;
	}
	 */

	private static char[] removeChar(char[] ca, char c){
		int x = 0;
		char[] l = new char[ca.length + 1];
		for(int i = 0; i < ca.length; i++){
			char y = ca[i];
			if(y != c){
				l[x] = y;
				x++;
			}
		}
		char[] n = new char[x];
		System.arraycopy(l, 0, n, 0, x);
		return n;
	}

	private static char[] removeChars(char[] ca, char[] ca2){
		int x = 0;
		char[] l = new char[ca.length + 1];
		for(int i = 0; i < ca.length; i++){
			char y = ca[i];
			if(contains(ca2, y)){
				l[x] = y;
				x++;
			}
		}
		char[] n = new char[x];
		System.arraycopy(l, 0, n, 0, x);
		return n;
	}

	private static boolean contains(char[] ca, char c){
		for(char c1 : ca){
			if(c == c1){
				return true;
			}
		}
		return false;
	}

	private static String[] removeString(String[] sa, String c){
		int x = 0;
		String[] l = new String[sa.length + 1];
		for(int i = 0; i < sa.length; i++){
			String y = sa[i];
			if(c == null){
				if(y != c){
					l[x] = y;
					x++;
				}
			}
			if(c != null){
				if(y.equals(c)){
					l[x] = y;
					x++;
				}
			}
		}
		String[] n = new String[x];
		System.arraycopy(l, 0, n, 0, x);
		return n;
	}

	private static byte find(char c, char[] digits, byte[] numerals){
		int i = 0;
		int j = digits.length;
		for(;i < j; i++){
			if(digits[i] == c){
				return numerals[i];
			}
		}
		throw new NumberFormatException();
	}

	private static long byteArrayToInteger(byte[] b, boolean negative){
		int i = 0;
		long n = 0;
		int l = b.length;
		for(;i < l;i++){
			if(b[i] < 0 || b[i] > 9){
				throw new NumberFormatException();
			}
			n += Math.pow(10, l-i-1) * b[i];
		}
		if(negative){
			return n * -1;
		}
		else{
			return n;
		}
	}

	private static class ArrayPart{
		private byte[] part;
		private ArrayPart(byte[] part){
			this.part = part;
		}
	}

	public static long[] builtInParseArray(String text, char separator){
		String[] sa = text.split(String.valueOf(separator));
		int length = sa.length;
		long[] la = new long[length];
		for(int i = 0; i < length; i++){
			la[i] = parseLong(sa[i]);
		}
		return la;
	}
}
