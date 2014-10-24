package projectEuler;


import java.util.HashMap;

import org.javatuples.Pair;


public class Problem076 extends Problem {

	@Override
	protected String solve() {
		long answer = 0;
		int sequence = 100;

		answer = getPossibilities(sequence, 1) - 1;
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
			possibilities = getPossibilities(sequence - i, i);
			answer += possibilities;
			numberPossibilities.put(new Pair<Integer, Integer>(sequence - i, i), possibilities);
		}
		return answer;
	}

	HashMap<Pair<Integer, Integer>, Long> numberPossibilities = new HashMap<Pair<Integer, Integer>, Long>();
}