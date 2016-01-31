package projectEuler;

public class Problem092 extends Problem {

	@Override
	protected String solve() {
		int answer = 0, n, tmp;

		for (int i = 2; i < 10000000; i++) {
			n = i;
			while (n != 1 && n != 89) {
				tmp = 0;
				while (n > 0) {
					tmp += (n % 10) * (n % 10);
					n /= 10;
				}
				n = tmp;
			}
			if (n == 89) {
				answer++;
			}
		}

		return Integer.toString(answer);
	}
}
