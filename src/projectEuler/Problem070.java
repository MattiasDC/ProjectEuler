package projectEuler;


public class Problem070 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		double min = Double.MAX_VALUE, tmp;

		for (int i = 9999999; i > 1; i -= 2) {
			tmp = Math.round(i * validCandidate(i, min));
			if (min > i / tmp && isPermutationOf(i, (long) tmp)) {
				min = i / tmp;
				answer = i;
			}
		}

		return answer + "";
	}

	private double validCandidate(int number, double min) {
		double restantFactor = 1.0;

		for (int i = 3; number != 1 && i * i < number; i += 2) {
			if (min < 1.0 / restantFactor) {
				return restantFactor;
			}
			if (number % i == 0) {
				number /= i;
				while (number % i == 0) {
					number /= i;
				}
				restantFactor *= (1.0 - 1.0 / i);
			}

		}

		if (number != 1) {
			restantFactor *= (1.0 - 1.0 / number);
		}
		return restantFactor;
	}

}
