package projectEuler;

import java.util.HashMap;

public class Problem087 extends Problem {

	@Override
	protected String solve() {
		int solutions = 0;
		int goal = 100;

		HashMap<Integer, Integer> primesBelow = new HashMap<Integer, Integer>();

		createSieve((int) Math.sqrt(goal));
		int x, y, z = (int) (Math.pow(goal, 1.0 / 4.0) + 1);
		while (z != 2) {
			z = getPrimeBelow(z);
			y = (int) Math.pow(goal - (int) Math.pow(z, 4), 1.0 / 3.0) + 1;
			while (y != 2) {
				y = getPrimeBelow(y);
				x = (int) Math.sqrt(goal - Math.pow(z, 4) - Math.pow(y, 3)) + 1;
				x = getPrimeBelow(x);
				int initialx = x, solutionsNow = solutions;
				while (x != 0) {
					// if (primesBelow.containsKey(x)) {
					// solutions += primesBelow.get(x);
					// break;
					// }
					System.out.println(x
							+ " "
							+ y
							+ " "
							+ z
							+ " "
							+ Integer.toString(x * x + y * y * y + z * z * z
									* z));
					solutions++;
					x = getPrimeBelow(x);
				}
				primesBelow.put(initialx, solutions - solutionsNow);
			}
		}

		return Long.toString(solutions);
	}

}
