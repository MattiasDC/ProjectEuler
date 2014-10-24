package projectEuler;


import java.util.ArrayList;
import java.util.List;

import extMath.BigFraction;


public class Problem065 extends Problem {

	@Override
	protected String solve() {

		List<Integer> sequence = getESequence(100);
		BigFraction convergingFraction = evaluateBigFraction(sequence);

		return getSumOfDigits(convergingFraction.numerator) + "";
	}

	private List<Integer> getESequence(int length) {
		List<Integer> sequence = new ArrayList<Integer>(length);
		sequence.add(2);

		for (int i = 1; i < length; i++) {
			if ((i + 1) % 3 == 0) {
				sequence.add((i + 1) * 2 / 3);
			}
			else {
				sequence.add(1);
			}
		}

		return sequence;
	}

}
