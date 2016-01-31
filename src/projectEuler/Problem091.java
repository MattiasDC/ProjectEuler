package projectEuler;

import java.util.Arrays;

public class Problem091 extends Problem {

	@Override
	protected String solve() {
		long answer = 0;
		double fuzzy = Math.pow(10, -6);
		int dim = 50;

		double dist[] = new double[3];
		for (int x1 = 0; x1 <= dim; x1++) {
			for (int y1 = 0; y1 <= dim; y1++) {
				if (x1 == 0 && y1 == 0) {
					continue;
				}
				for (int x2 = 0; x2 <= dim; x2++) {
					for (int y2 = 0; y2 <= dim; y2++) {
						if (x1 == x2 && y1 == y2 || x2 == 0 && y2 == 0) {
							continue;
						}
						dist[0] = distanceSQ(0, 0, x1, y1);
						dist[1] = distanceSQ(0, 0, x2, y2);
						dist[2] = distanceSQ(x1, y1, x2, y2);
						Arrays.sort(dist);
						if (Math.abs(dist[2] - (dist[0] + dist[1])) <= fuzzy) {
							answer++;
						}
					}
				}
			}
		}

		return Long.toString(answer / 2);
	}

	private double distanceSQ(int x1, int y1, int x2, int y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}

}
