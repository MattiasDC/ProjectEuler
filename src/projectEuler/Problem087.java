package projectEuler;

import java.util.HashSet;

public class Problem087 extends Problem {

	@Override
	protected String solve() {
		int goal = 50000000;
		HashSet<Integer> solutionsNumbers = new HashSet<Integer>();

		createSieve((int) Math.sqrt(goal));
		int x, y, z = (int) (Math.pow(goal, 1.0 / 4.0) + 1), number;
		while (z != 2) {
			z = getPrimeBelow(z);
			y = (int) Math.pow(goal - (int) Math.pow(z, 4), 1.0 / 3.0) + 1;
			while (y != 2) {
				y = getPrimeBelow(y);
				x = (int) Math.sqrt(goal - Math.pow(z, 4) - Math.pow(y, 3)) + 1;
				x = getPrimeBelow(x);
				while (x != 0) {
					number = x * x + y * y * y + z * z * z * z;
					if (!solutionsNumbers.contains(number)) {
						solutionsNumbers.add(number);
					}
					x = getPrimeBelow(x);
				}
			}
		}

		return Long.toString(solutionsNumbers.size());
	}

}
