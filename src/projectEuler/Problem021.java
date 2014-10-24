package projectEuler;


import java.util.HashMap;
import java.util.Iterator;

import extMath.ExtMath;


public class Problem021 extends Problem {

	@Override
	protected String solve() {

		int sumAmicablePairs = 0;

		getNumbersAndDivisors().put(1L, 1L);
		for (long i = 2; i <= 10000; i++) {
			getNumbersAndDivisors().put(i, ExtMath.getSumDivisors(i) - i);
		}

		Iterator<Long> iterator = getNumbersAndDivisors().keySet().iterator();

		while (iterator.hasNext()) {
			long number = iterator.next();
			long sumDivisors = getNumbersAndDivisors().get(number);

			if (sumDivisors <= 10000 && getNumbersAndDivisors().get(sumDivisors) == number
					&& sumDivisors != number && sumDivisors != 1) {
				sumAmicablePairs += number;
			}
		}

		return sumAmicablePairs + "";
	}

	public HashMap<Long, Long> getNumbersAndDivisors() {
		return numbersAndDivisors;
	}

	private HashMap<Long, Long> numbersAndDivisors = new HashMap<Long, Long>();
}
