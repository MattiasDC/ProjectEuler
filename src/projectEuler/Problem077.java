package projectEuler;


import java.util.HashMap;

import org.javatuples.Pair;

import extMath.ExtMath;


public class Problem077 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		while (getPossibilities(++answer, 1) <= 5000)
			;

		return answer + "";
	}

	private long getPossibilities(int sequence, int last) {
		long answer = 0, possibilities;
		if (numberPossibilities.containsKey(new Pair<Integer, Integer>(sequence, last))) {
			return numberPossibilities.get(new Pair<Integer, Integer>(sequence, last));
		}
		if (sequence == 0)
			return 1;
		for (int i = sequence; i >= last; i--) {
			if (!sieve[i]) {
				continue;
			}
			possibilities = getPossibilities(sequence - i, i);
			answer += possibilities;
			numberPossibilities.put(new Pair<Integer, Integer>(sequence - i, i), possibilities);
		}
		return answer;
	}

	HashMap<Pair<Integer, Integer>, Long> numberPossibilities = new HashMap<Pair<Integer, Integer>, Long>();
	boolean[] sieve = ExtMath.sieve(10000);

}
