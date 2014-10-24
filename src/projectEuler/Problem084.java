package projectEuler;


import java.util.HashMap;
import java.util.TreeSet;

import extMath.ExtMath;


public class Problem084 extends Problem {

	@Override
	protected String solve() {
		double[][] A = new double[40][40], b = new double[40][1];

		// The seventh square equation is not part of the system (it is deducted
		// from the other 40 equations) (1)
		for (int i = 0; i < A[0].length; i++) {
			A[6][i] = 1.0;
		}
		b[6][0] = 1.0;

		// Initialise
		int modValue;
		for (int i = 0; i < A[0].length; i++) {
			// see (1)
			if (i != 6) {
				// 2..8 are possible dice outcomes
				for (int j = 2; j <= 8; j++) {
					// calculates the distribution of the chances to come on
					// square i, (chance of previous square times the chance of
					// dicing
					// the right number of eyes)
					modValue = (i - j) % 40;
					if (i - j < 0) {
						modValue += 40;
					}
					if (j >= 5) {
						A[i][modValue] = (4.0 - j % 5.0) / 16.0;
					}
					else {
						A[i][modValue] = (j - 1.0) / 16.0;
					}
				}

				A[i][i] = -1.0;
				b[i][0] = 0.0;
			}
		}

		// G2J tile rule
		for (int i = 0; i < A[0].length; i++) {
			if (i != 30) {
				A[10][i] += A[30][i];
				A[30][i] = 0;
			}
		}

		// CC rule
		int[] cc = new int[] { 2, 17, 33 };

		for (int ccSquare : cc) {
			A[ccSquare][ccSquare] *= (16.0 / 14.0);
			A[0][ccSquare] += (1.0 / 14.0);
			A[10][ccSquare] += (1.0 / 14.0);
		}

		// Chance rule
		int[] chance = new int[] { 7, 22, 36 };

		for (int chanceSquare : chance) {
			A[chanceSquare][chanceSquare] *= 8.0 / 3.0;
			A[0][chanceSquare] += (1.0 / 6.0);
			A[10][chanceSquare] += (1.0 / 6.0);
			A[11][chanceSquare] += (1.0 / 6.0);
			A[24][chanceSquare] += (1.0 / 6.0);
			A[38][chanceSquare] += (1.0 / 6.0);
			A[5][chanceSquare] += (1.0 / 6.0);
			switch (chanceSquare) {
				case 7:
					A[15][chanceSquare] += (1.0 / 3.0);
					A[12][chanceSquare] += (1.0 / 6.0);
					A[4][chanceSquare] += (1.0 / 6.0);
					break;
				case 22:
					A[25][chanceSquare] += (1.0 / 3.0);
					A[28][chanceSquare] += (1.0 / 6.0);
					A[19][chanceSquare] += (1.0 / 6.0);
					break;
				case 36:
					A[5][chanceSquare] += (1.0 / 3.0);
					A[12][chanceSquare] += (1.0 / 6.0);
					A[33][chanceSquare] += (1.0 / 6.0);
					break;
			}
		}

		// Triple double rule
		b[10][0] = -Math.pow(1.0 / 4.0, 3);

		for (int i = 0; i < A.length; i++) {
			// see (1)
			if (i != 6 && i != 10) {
				A[i][i] *= 1.0 / (1 - Math.pow(1.0 / 4.0, 3));
			}
		}

		double[][] t = ExtMath.solveSystem(A, b);

		// Extraction of result
		HashMap<Double, String> chanceToPlace = new HashMap<>();
		TreeSet<Double> chances = new TreeSet<Double>();
		StringBuilder digitPlace;
		for (int i = 0; i < t.length; i++) {
			chances.add(t[i][0]);
			digitPlace = new StringBuilder();
			if (i < 10) {
				digitPlace.append(0);
			}
			digitPlace.append(i);
			chanceToPlace.put(t[i][0], digitPlace.toString());
		}

		return chanceToPlace.get(chances.pollLast()) + "" + chanceToPlace.get(chances.pollLast())
				+ "" + chanceToPlace.get(chances.pollLast());
	}

}
