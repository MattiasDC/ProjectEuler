package projectEuler;

import java.util.HashSet;

import org.javatuples.Pair;

import extMath.ExtMath;

public class Problem086 extends Problem {

	@Override
	protected String solve() {
		int goal = 1000000, left = 0, right = 10000;
		long itl;
		while (Math.abs(left - right) > 1) {
			int middle = (right + left) / 2;
			itl = amount(middle);
			if (itl > goal) {
				right = middle;
			} else if (itl < goal) {
				left = middle;
			}
		}

		return Integer.toString(right);
	}

	private long amount(int it) {
		long M = 0, bc, h, nbSolutions = 0;
		boolean deepening = false;
		HashSet<Pair<Long, Long>> pyth_trips = new HashSet<>();

		for (int m = 2; !deepening; m++) {
			for (int n = 1; m > n; n++) {
				if ((m - n) % 2 == 0 && ExtMath.gcd(m, n) != 1) {
					continue;
				}
				M = m * m - n * n;
				bc = 2 * m * n;
				h = m * m + n * n;
				if (Math.min(M, bc) > it) {
					if (n == 1) {
						continue;
					} else if (n == m - 1) {
						deepening = true;
						break;
					}
				}
				int k = 1;
				while (Math.max(M * k, bc * k) / 2 <= it
						&& Math.min(M * k, bc * k) <= it) {
					long max = Math.max(M * k, bc * k);
					long min = Math.min(M * k, bc * k);
					int amnt = 0;
					amnt += Math.max(0, max / 2 - (max - min) + 1);
					if (max <= it) {
						amnt += min / 2;
					}
					if (!pyth_trips.contains(new Pair<Long, Long>(min, h * k))) {
						nbSolutions += amnt;
						pyth_trips.add(new Pair<Long, Long>(min, h * k));
					}
					k++;
				}
			}
		}
		return nbSolutions;
	}
}
