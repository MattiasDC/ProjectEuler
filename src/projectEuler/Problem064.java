package projectEuler;


import java.util.HashSet;

import org.javatuples.Pair;


public class Problem064 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;

		for (int i = 2; i <= 10000; i++) {
			if (Math.sqrt(i) % 1.0 == 0) {
				continue;
			}
			if (hasOddPeriod(new ContinuedFraction(new Pair<Double, Double>(Math.sqrt(i),
					-Math.floor(Math.sqrt(i))), 1.0))) {
				answer++;
			}
		}

		return answer + "";
	}

	private boolean hasOddPeriod(ContinuedFraction fraction) {
		HashSet<ContinuedFraction> fractions = new HashSet<ContinuedFraction>();

		ContinuedFraction currentFraction = fraction;
		while (!fractions.contains(currentFraction)) {
			fractions.add(currentFraction);
			currentFraction = getNextContinuedFraction(currentFraction).getValue0();
		}
		return (fractions.size()) % 2 == 1;
	}

}
