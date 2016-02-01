package projectEuler;

import java.util.ArrayList;
import java.util.HashSet;

import extMath.ExtMath;

public class Problem094 extends Problem {

	// Triangle has two equal length, take the leg that is different from the
	// other and draw a height line. This line divides the line in two and form
	// two triangles with a right-angle. Since the hypothenuse and the base are
	// integer values, the height must be too. The right-angle triangle thus is
	// a pythagorean triplet. These are generated and for each m value,
	// equations are solved to find n using the knowledge that the base is half
	// the size plus or minus 1 with respect to the hypothenuse.
	@Override
	protected String solve() {
		long limit = 1000000000;
		long a, b, c, tmp;
		long answer = 0;
		for (int m = 1; m < 100000; m++) {
			for (int n : calculatePossibleN(m)) {
				if (n == -1) {
					continue;
				}
				a = m * m - n * n;
				b = 2 * m * n;
				c = m * m + n * n;
				tmp = Math.max(a, b);
				a = Math.min(a, b);
				b = tmp;
				if ((c == 2 * a - 1 || c == 2 * a + 1) && a * 2 + c * 2 <= limit) {
					answer += a * 2 + c * 2;
				}
			}
		}
		return Long.toString(answer);
	}

	private ArrayList<Integer> calculatePossibleN(int m) {
		HashSet<Integer> solutions = new HashSet<>();
		double testN;

		// Equation used: c = 2*a +- 1
		// Where c = m^2 + n^2 and a = m^2-n^2 or a = 2*m*n
		// first possibility, n is found via shortest side = m^2-n^2
		testN = m * m / 3.0;
		if ((testN * 3) % 1.0 == 0.0) {
			if ((int) Math.sqrt(testN + 1.0 / 3) > 0 && Math.sqrt(testN + 1.0 / 3) < m
					&& ExtMath.gcd(m, (int) Math.sqrt(testN + 1.0 / 3)) == 1) {
				solutions.add((int) Math.sqrt(testN + 1.0 / 3));
			} else if ((int) Math.sqrt(testN - 1.0 / 3) > 0 && Math.sqrt(testN - 1.0 / 3) < m
					&& ExtMath.gcd(m, (int) Math.sqrt(testN - 1.0 / 3)) == 1) {
				solutions.add((int) Math.sqrt(testN - 1.0 / 3));
			}
		}

		// second possibility, n is found via shortest side = 2*m*n (solving
		// equation n^2 -2*m*n+m^2 +- 1 = 0)
		double helper = 3 * m * m;
		if (Math.sqrt(helper + 1) % 1.0 == 0) {
			testN = 2 * m + Math.sqrt(helper + 1);
			if ((int) testN > 0 && testN < m && ExtMath.gcd(m, (int) testN) == 1) {
				solutions.add((int) testN);
			}
			testN = 2 * m + Math.sqrt(helper - 1);
			if ((int) testN > 0 && testN < m && ExtMath.gcd(m, (int) testN) == 1) {
				solutions.add((int) testN);
			}
			testN = 2 * m - Math.sqrt(helper + 1);
			if ((int) testN > 0 && testN < m && ExtMath.gcd(m, (int) testN) == 1) {
				solutions.add((int) testN);
			}
			testN = 2 * m - Math.sqrt(helper - 1);
			if ((int) testN > 0 && testN < m && ExtMath.gcd(m, (int) testN) == 1) {
				solutions.add((int) testN);
			}
		}

		return new ArrayList<Integer>(solutions);
	}
}