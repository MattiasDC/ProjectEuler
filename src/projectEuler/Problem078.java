package projectEuler;


import java.util.HashMap;


public class Problem078 extends Problem {

	@Override
	protected String solve() {
		int answer = 0, remainder = 1000000;

		while (partitionFunctionRemainder(++answer, remainder) != 0)
			;
		;

		return answer + "";
	}

	private long partitionFunctionRemainder(int n, int remainder) {
		if (partitionResults.containsKey(n)) {
			return partitionResults.get(n);
		}
		if (n < 0) {
			return 0L;
		}
		if (n == 0) {
			return 1L;
		}

		long partitions = 0, valueToAdd;
		for (int i = 1; makePentagonal(i) <= n; i++) {
			valueToAdd = partitionFunctionRemainder(n - makePentagonal(i), remainder)
					+ partitionFunctionRemainder(n - makePentagonal(-i), remainder);
			if (i % 2 == 0) {
				valueToAdd *= -1;
			}
			partitions += valueToAdd;
			partitions %= remainder;
		}

		partitionResults.put(n, partitions);
		return partitions;
	}

	HashMap<Integer, Long> partitionResults = new HashMap<Integer, Long>();
}