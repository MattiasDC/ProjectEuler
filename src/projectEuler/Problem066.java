package projectEuler;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import extMath.BigFraction;


public class Problem066 extends Problem {

	@Override
	protected String solve() {
		BigInteger max = BigInteger.ZERO, tmp;
		int answer = 0;

		for (int i = 2; i <= 1000; i++) {
			if (Math.sqrt(i) % 1.0 == 0.0) {
				continue;
			}
			if ((tmp = solutionX(i)).compareTo(max) > 0) {
				max = tmp;
				answer = i;
			}
		}

		return answer + "";
	}

	private BigInteger solutionX(int n) {
		List<Integer> splittedValues = new ArrayList<Integer>();
		ContinuedFraction continuedFraction = new ContinuedFraction(new Pair<Double, Double>(
				Math.sqrt(n), -Math.floor(Math.sqrt(n))), 1.0);
		splittedValues.add((int) Math.floor(Math.sqrt(n)));

		BigFraction fraction;
		Pair<ContinuedFraction, Integer> pair;
		while (!pellSolution((fraction = evaluateBigFraction(splittedValues)).numerator, n,
				fraction.denominator)) {
			pair = getNextContinuedFraction(continuedFraction);
			continuedFraction = pair.getValue0();
			splittedValues.add(pair.getValue1());
		}
		return evaluateBigFraction(splittedValues).numerator;
	}

	private boolean pellSolution(BigInteger x, long n, BigInteger y) {
		return x.multiply(x).subtract(BigInteger.valueOf(n).multiply(y).multiply(y))
				.equals(BigInteger.ONE);
	}

}
