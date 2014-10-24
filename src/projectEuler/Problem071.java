package projectEuler;


import extMath.ExtMath;


public class Problem071 extends Problem {

	@Override
	protected String solve() {
		int answer = 0, numerator, limit = 1000000;
		double difference = Double.MAX_VALUE;

		for (int denominator = limit; denominator > limit * 0.99; denominator--) {
			numerator = (int) Math.floor((3.0 * denominator) / 7.0);
			if (ExtMath.gcd(numerator, denominator) == 1
					&& difference > (3.0 / 7.0 - (double) numerator / denominator)) {
				answer = numerator;
				difference = 3.0 / 7.0 - (double) numerator / denominator;
			}
		}

		return answer + "";
	}
}
