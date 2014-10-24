package projectEuler;


import java.util.ArrayList;


public class Problem068 extends Problem {

	// Solved by pen and paper: 6531031914842725
	// Below is a simple brute force example
	@Override
	protected String solve() {
		ArrayList<Integer[]> permutations = getPermutations(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8,
				9, 10 }, Integer::compareTo);

		double rowTotal;
		Integer[] sequence;
		for (int i = permutations.size() - 1; i >= 0; i--) {
			sequence = permutations.get(i);

			boolean lowestFirst = true;
			for (int j = 1; j < sequence.length / 2; j++) {
				if (sequence[0] > sequence[j]) {
					lowestFirst = false;
				}
			}
			if (!lowestFirst) {
				continue;
			}

			rowTotal = getRowTotal(sequence);
			if (rowTotal % 1.0 != 0.0) {
				continue;
			}

			boolean ruleViolation = false;
			for (int j = 0; j < sequence.length / 2; j++) {
				int secondIndex = sequence.length / 2 + 1 + j;
				if (secondIndex == sequence.length) {
					secondIndex = sequence.length / 2;
				}
				if (sequence[j] + sequence[sequence.length / 2 + j] + sequence[secondIndex] != rowTotal) {
					ruleViolation = true;
				}
			}
			if (ruleViolation) {
				continue;
			}
			return formString(sequence);

		}

		return "";

	}

	private String formString(Integer[] sequence) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sequence.length / 2; i++) {
			int secondIndex = sequence.length / 2 + 1 + i;
			if (secondIndex == sequence.length) {
				secondIndex = sequence.length / 2;
			}
			sb.append(sequence[i].toString() + sequence[sequence.length / 2 + i].toString()
					+ sequence[secondIndex].toString());
		}
		return sb.toString();
	}

	private double getRowTotal(Integer[] sequence) {
		int total = 0;
		for (int i = 0; i < sequence.length; i++) {
			if (i >= sequence.length / 2) {
				total += sequence[i];
			}
			total += sequence[i];
		}
		return (double) total / (sequence.length / 2.0);
	}
}
