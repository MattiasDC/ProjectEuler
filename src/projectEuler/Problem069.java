package projectEuler;


import extMath.ExtMath;


public class Problem069 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		double max = 0, tmp;
		for (int i = 2; i < 1000000; i++) {
			if ((tmp = (double) i / ExtMath.phi(i)) > max) {
				max = tmp;
				answer = i;
			}
		}
		return answer + "";
	}
}
