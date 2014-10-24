package projectEuler;


import java.util.HashSet;

import extMath.ExtMath;


public class Problem075 extends Problem {

	@Override
	protected String solve() {
		HashSet<Integer> currentLengths = new HashSet<Integer>();
		HashSet<Integer> deletedLengths = new HashSet<Integer>();
		boolean[] coPrimeSieve;
		int length;

		for (int n = 1; 2 * n * n + 3 * n + 1 < 750000; n++) {
			coPrimeSieve = ExtMath.coPrimeSieve(n,
					(int) (-2 * n + Math.sqrt(2 * n * n + 120000000) / 4) + 1);
			for (int m = n + 1; m * m + m * n < 750000; m += 2) {
				length = 2 * m * m + 2 * m * n;
				for (int k = 1; k * length < 1500000; k++) {
					if (coPrimeSieve[m] && !deletedLengths.contains(k * length)) {
						if (currentLengths.contains(k * length)) {
							currentLengths.remove(k * length);
							deletedLengths.add(k * length);
						}
						else {
							currentLengths.add(k * length);
						}
					}
				}
			}
		}
		return currentLengths.size() + "";
	}
}
