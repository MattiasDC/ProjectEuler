package projectEuler;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Problem {

	public void run(){
		long begin = System.nanoTime();
		
		String answer = solve();
		long elapsedTime = (System.nanoTime() - begin)/1000000;
		System.out.println(getClass().getSimpleName() +  ": " + answer + " and was solved in " + elapsedTime +"ms");

	}

	protected abstract String solve();

	protected boolean isPalindrome(String candidate) {
		for (int i = 0; i <= candidate.length()/2;i++){
			if (candidate.charAt(i) != candidate.charAt(candidate.length()-1-i)) return false;
		}
		return true;
	}

	protected boolean[] sieve;
	
	protected int getPrimeBelow(int number){
		while ( --number >= 1 && !sieve[number]);
		return number;
	}

	protected int getPrimeUp(int number){
		while ( ++number < sieve.length && !sieve[number]);
		return number;
	}

	protected int getIthReverseNumber(int number, int place){	
		return (int) ((number % ((int)Math.pow(10, place))) / (int)Math.pow(10, place-1));
	}

	protected int getIthReverseNumber(long number, int place){	
		return (int) ((number % ((int)Math.pow(10, place))) / (int)Math.pow(10, place-1));
	}

	protected boolean isPandigit(String number){
		return !hasDuplicate(number + "") && containsToHighestDigit(number+"") && !number.contains("0");
	}

	protected boolean hasDuplicate(String sequence){
		for (int i = 0; i < sequence.length() - 1;i++){
			for (int j = 0; j < sequence.length();j++){
				if (i == j) continue;
				if (sequence.charAt(i) == sequence.charAt(j)) return true;
			}
		}
		return false;
	}

	protected boolean containsToHighestDigit(String number) {
		for (int i = 1; i < getHighestDigit(Long.parseLong(number));i++){
			if (!(number + "").contains(i + "")) return false;
		}
		return true;
	}

	protected long getHighestDigit(int number) {
		long highestDigit = 0;
		while (number >= 1){
			if (number % 10 > highestDigit) highestDigit = number % 10;
			number /= 10;
		}
		return highestDigit;
	}

	protected long getHighestDigit(long number) {
		long highestDigit = 0;
		while (number >= 1){
			if (number % 10 > highestDigit) highestDigit = number % 10;
			number /= 10;
		}
		return highestDigit;
	}

	protected int getNumberOfDigits(int number){
		int numberOfDigits = 0;		
		while (number >= 1){
			number /= 10;
			numberOfDigits++;
		}
		return numberOfDigits;
	}

	protected int getNumberOfDigits(long number){
		int numberOfDigits = 0;		
		while (number >= 1){
			number /= 10;
			numberOfDigits++;
		}
		return numberOfDigits;
	}
	
	protected int getNumberOfDigits(double number) {
		int numberOfDigits = 0;		
		while (number >= 1){
			number /= 10.0;
			numberOfDigits++;
		}
		return numberOfDigits;
	}

	protected boolean isOctagonal(int x){
		return ((1.0/3.0 + Math.sqrt(4+12*x)/6.0) % 1.0 == 0.0);
	}
	
	protected int makeOctagonal(int x){
		return x*(3*x -2);
	}
	
	protected boolean isHeptagonal(int x){
		 return ((3.0/10.0 + Math.sqrt(9.0/4.0 + 10.0*x)/5.0) % 1.0 == 0.0);
	}
	
	protected int makeHeptagonal(int x){
		return (x*(5*x-3))/2;
	}

	protected boolean isHexagonal(int x){
		return ((1.0 + Math.sqrt(1.0 + 8.0*x))/4.0 % 1.0 == 0.0);
	}

	protected int makeHexagonal(int x){
		return x*(2*x-1);
	}
	
	protected boolean isPentagonal(int x){
		return ((1.0/2.0 + Math.sqrt(1.0/4.0 + 6.0*x))/3.0 % 1.0 == 0.0);
	}

	protected int makePentagonal(int x){
		return x*(3*x-1)/2;
	}
	
	protected boolean isSquare(int x){
		return Math.sqrt(x) % 1.0 == 0.0;
	}
	
	protected int makeSquare(int x){
		return x*x;
	}

	protected boolean isTriangle(int x){
		return ((-1.0/2.0 + Math.sqrt(1.0/4 + 2.0*x)) % 1.0 == 0.0);
	}

	protected int makeTriangle(int x){
		return x*(x+1)/2;
	}

	protected ArrayList<String> getPermutations(String dictionary){
		ArrayList<String> permutations = new ArrayList<String>();
		char[] chars = dictionary.toCharArray();
		Arrays.sort(chars);
		permutations.add(String.valueOf(chars));
		int k = 0,l = 0, length = dictionary.length();
		char tmp;
		if (length == 1)
			return permutations;
		
		while (true){
			for (int i = length-2;i >= 0;i--){
				if (i == 0 && chars[i] >= chars[i+1])
					return permutations;
				if (chars[i] < chars[i+1]){
					k = i;
					break;
				}
			}
			for (int i = length-1;i > k;i--)
				if (chars[k] < chars[i]){
					l = i;
					break;
				}
			tmp = chars[k];
			chars[k] = chars[l];
			chars[l] = tmp;
			reverseOrder(chars,k+1, length-1);
			permutations.add(String.valueOf(chars));
		}
	}

	private void reverseOrder(char[] chars, int k, int l) {
		char tmp;
		for (int i = k; i <= k + (l-k)/2;i++){
			tmp = chars[i];
			chars[i] = chars[l-(i-k)];
			chars[l-(i-k)] = tmp;
		}
	}
}
