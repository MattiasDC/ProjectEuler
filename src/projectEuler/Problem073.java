package projectEuler;


import extMath.ExtMath;
import extMath.Fraction;


public class Problem073 extends Problem {

	@Override
	protected String solve() {
		Fraction lowerBound = new Fraction(1, 3), upperBound = new Fraction(1, 2);
		int amount = 0, numeratorBegin, numeratorEnd;
		boolean[] sieve;

		for (int i = 12000; i > lowerBound.denominator; i--) {
			numeratorBegin = (int) Math.ceil((double) (i * lowerBound.numerator)
					/ lowerBound.denominator);
			if (new Fraction(numeratorBegin, i).compareTo(lowerBound) == 0) {
				numeratorBegin++;
			}
			numeratorEnd = (int) Math.floor((double) (i * upperBound.numerator)
					/ upperBound.denominator);
			if (new Fraction(numeratorEnd, i).compareTo(upperBound) == 0) {
				numeratorEnd--;
			}

			sieve = ExtMath.coPrimeSieve(i);

			for (int j = numeratorBegin; j <= numeratorEnd; j++) {
				if (sieve[j]) {
					amount++;
				}
			}

		}

		return amount + "";
	}
}
