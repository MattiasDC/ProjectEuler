package projectEuler;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.javatuples.Pair;

import com.google.common.collect.HashMultiset;

import extMath.BigFraction;
import extMath.Fraction;


public abstract class Problem {

	public void run() {
		long begin = System.nanoTime();

		String answer = solve();
		long elapsedTime = (System.nanoTime() - begin) / 1000000;
		System.out.println(getClass().getSimpleName() + ": " + answer + " and was solved in "
				+ elapsedTime + "ms");

	}

	protected abstract String solve();

	protected boolean isPalindrome(String candidate) {
		for (int i = 0; i <= candidate.length() / 2; i++) {
			if (candidate.charAt(i) != candidate.charAt(candidate.length() - 1 - i))
				return false;
		}
		return true;
	}

	protected boolean[] sieve;

	protected int getPrimeBelow(int number) {
		while (--number >= 1 && !sieve[number])
			;
		return number;
	}

	protected int getPrimeUp(int number) {
		while (++number < sieve.length && !sieve[number])
			;
		return number;
	}

	protected int getIthReverseNumber(int number, int place) {
		return (int) ((number % ((int) Math.pow(10, place))) / (int) Math.pow(10, place - 1));
	}

	protected int getIthReverseNumber(long number, int place) {
		return (int) ((number % ((int) Math.pow(10, place))) / (int) Math.pow(10, place - 1));
	}

	protected boolean isPandigit(String number) {
		return !hasDuplicate(number + "") && containsToHighestDigit(number + "")
				&& !number.contains("0");
	}

	protected boolean hasDuplicate(String sequence) {
		HashSet<Character> duplicateChecker = new HashSet<>();
		for (int i = 0; i < sequence.length(); i++) {
			if (duplicateChecker.contains(sequence.charAt(i))){
				return false;
			}
			duplicateChecker.add(sequence.charAt(i));
		}
		return true;
	}

	protected boolean containsToHighestDigit(String number) {
		for (int i = 1; i < getHighestDigit(Long.parseLong(number)); i++) {
			if (!(number + "").contains(i + ""))
				return false;
		}
		return true;
	}

	protected int getSumOfDigits(int number) {
		int sum = 0;
		while (number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}

	protected int getSumOfDigits(long number) {
		int sum = 0;
		while (number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}

	protected int getSumOfDigits(BigInteger number) {
		int sum = 0;
		while (number.compareTo(BigInteger.ZERO) > 0) {
			sum += number.mod(BigInteger.TEN).intValue();
			number = number.divide(BigInteger.TEN);
		}
		return sum;
	}

	protected int getHighestDigit(int number) {
		int highestDigit = 0;
		while (number >= 1) {
			if (number % 10 > highestDigit)
				highestDigit = number % 10;
			number /= 10;
		}
		return highestDigit;
	}

	protected int getHighestDigit(long number) {
		int highestDigit = 0;
		while (number >= 1) {
			if (number % 10 > highestDigit)
				highestDigit = (int) (number % 10);
			number /= 10;
		}
		return highestDigit;
	}

	protected int getNumberOfDigits(int number) {
		return (int) Math.floor(Math.log10(number) + 1);
	}

	protected int getNumberOfDigits(long number) {
		return (int) Math.floor(Math.log10(number) + 1);
	}

	protected int getNumberOfDigits(double number) {
		return (int) Math.floor(Math.log10(number) + 1);
	}

	protected ArrayList<Integer> getDigits(int number) {
		ArrayList<Integer> digits = new ArrayList<Integer>(getNumberOfDigits(number));
		int numberDigits = getNumberOfDigits(number);

		for (int i = 0; i < numberDigits; i++) {
			digits.add((number % 10));
			number /= 10;
		}
		return digits;
	}

	protected ArrayList<Integer> getDigits(long number) {
		ArrayList<Integer> digits = new ArrayList<Integer>(getNumberOfDigits(number));
		int numberDigits = getNumberOfDigits(number);
		for (int i = 0; i < numberDigits; i++) {
			digits.add((int) (number % 10));
			number /= 10;
		}
		return digits;
	}

	protected boolean isPermutationOf(long original, long candidate) {
		return HashMultiset.create(getDigits(original)).equals(
				HashMultiset.create(getDigits(candidate)));

	}

	protected boolean isPermutationOf(int original, int candidate) {
		if (getNumberOfDigits(original) != getNumberOfDigits(candidate)
				|| getHighestDigit(original) != getHighestDigit(candidate)) {
			return false;
		}
		HashSet<Integer> digits = new HashSet<Integer>();
		for (int digit : getDigits(original)) {
			digits.add(digit);
		}
		for (int digit : getDigits(candidate)) {
			if (!digits.contains(digit)) {
				return false;
			}
		}
		return true;

	}

	protected boolean isOctagonal(int x) {
		return ((1.0 / 3.0 + Math.sqrt(4 + 12 * x) / 6.0) % 1.0 == 0.0);
	}

	protected int makeOctagonal(int x) {
		return x * (3 * x - 2);
	}

	protected boolean isHeptagonal(int x) {
		return ((3.0 / 10.0 + Math.sqrt(9.0 / 4.0 + 10.0 * x) / 5.0) % 1.0 == 0.0);
	}

	protected int makeHeptagonal(int x) {
		return (x * (5 * x - 3)) / 2;
	}

	protected boolean isHexagonal(int x) {
		return ((1.0 + Math.sqrt(1.0 + 8.0 * x)) / 4.0 % 1.0 == 0.0);
	}

	protected int makeHexagonal(int x) {
		return x * (2 * x - 1);
	}

	protected boolean isPentagonal(int x) {
		return ((1.0 / 2.0 + Math.sqrt(1.0 / 4.0 + 6.0 * x)) / 3.0 % 1.0 == 0.0);
	}

	protected int makePentagonal(int x) {
		return x * (3 * x - 1) / 2;
	}

	protected boolean isSquare(int x) {
		return Math.sqrt(x) % 1.0 == 0.0;
	}

	protected int makeSquare(int x) {
		return x * x;
	}

	protected boolean isTriangle(int x) {
		return ((-1.0 / 2.0 + Math.sqrt(1.0 / 4 + 2.0 * x)) % 1.0 == 0.0);
	}

	protected int makeTriangle(int x) {
		return x * (x + 1) / 2;
	}

	protected <T> ArrayList<T[]> getPermutations(T[] dictionary, Comparator<T> comparator) {
		ArrayList<T[]> permutations = new ArrayList<T[]>();

		Arrays.sort(dictionary, comparator);

		permutations.add(dictionary);
		int k = 0, l = 0, length = dictionary.length;
		T tmp;
		if (length == 1)
			return permutations;

		while (true) {
			for (int i = length - 2; i >= 0; i--) {
				if (i == 0 && comparator.compare(dictionary[i], dictionary[i + 1]) >= 0)
					return permutations;
				if (comparator.compare(dictionary[i], dictionary[i + 1]) < 0) {
					k = i;
					break;
				}
			}
			for (int i = length - 1; i > k; i--)
				if (comparator.compare(dictionary[k], dictionary[i]) < 0) {
					l = i;
					break;
				}
			tmp = dictionary[k];
			dictionary[k] = dictionary[l];
			dictionary[l] = tmp;
			reverseOrder(dictionary, k + 1, length - 1);
			permutations.add(dictionary.clone());
		}
	}

	private <T> void reverseOrder(T[] chars, int k, int l) {
		T tmp;
		for (int i = k; i <= k + (l - k) / 2; i++) {
			tmp = chars[i];
			chars[i] = chars[l - (i - k)];
			chars[l - (i - k)] = tmp;
		}
	}

	protected Pair<ContinuedFraction, Integer> getNextContinuedFraction(ContinuedFraction fraction) {
		ContinuedFraction newFraction = fraction;
		Double newDenominator;
		Pair<Double, Double> newNumerator;

		newDenominator = (double) Math
				.round((newFraction.numerator.getValue0() + newFraction.numerator.getValue1())
						* (newFraction.numerator.getValue0() - newFraction.numerator.getValue1())
						/ newFraction.denominator);

		int extractedValue = (int) ((newFraction.numerator.getValue0() - newFraction.numerator
				.getValue1()) / newDenominator);

		newNumerator = new Pair<Double, Double>(newFraction.numerator.getValue0(),
				-newFraction.numerator.getValue1() - extractedValue * newDenominator);
		newFraction = new ContinuedFraction(newNumerator, newDenominator);

		return new Pair<ContinuedFraction, Integer>(newFraction, extractedValue);
	}

	protected Fraction evaluateFraction(List<Integer> splittedValues) {

		Fraction convergingFraction = new Fraction(splittedValues.get(splittedValues.size() - 1), 1);
		for (int i = splittedValues.size() - 2; i >= 0; i--) {
			convergingFraction = convergingFraction.inverse().sum(
					new Fraction(splittedValues.get(i), 1));
		}
		return convergingFraction;
	}

	protected BigFraction evaluateBigFraction(List<Integer> splittedValues) {

		BigFraction convergingFraction = new BigFraction(BigInteger.valueOf(splittedValues
				.get(splittedValues.size() - 1)), BigInteger.ONE);
		for (int i = splittedValues.size() - 2; i >= 0; i--) {
			convergingFraction = convergingFraction.inverse().add(
					new BigFraction(BigInteger.valueOf(splittedValues.get(i)), BigInteger.ONE));
		}
		return convergingFraction;
	}

	protected class ContinuedFraction {

		public ContinuedFraction(Pair<Double, Double> numerator, Double denominator) {
			this.numerator = numerator;
			this.denominator = denominator;
		}

		public final Pair<Double, Double> numerator;
		public final Double denominator;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
			result = prime * result + ((numerator == null) ? 0 : numerator.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ContinuedFraction other = (ContinuedFraction) obj;
			if (denominator == null) {
				if (other.denominator != null)
					return false;
			}
			else if (!denominator.equals(other.denominator))
				return false;
			if (numerator == null) {
				if (other.numerator != null)
					return false;
			}
			else if (!numerator.equals(other.numerator))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return numerator + "/" + denominator;
		}
	}
}
