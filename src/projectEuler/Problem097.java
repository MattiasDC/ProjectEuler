package projectEuler;

public class Problem097 extends Problem {

	@Override
	protected String solve() {

		long last10Numbers = 1;
		for (int i = 1; i <= 7830457; i++) {
			last10Numbers = (last10Numbers * 2) % 10000000000L;
		}
		last10Numbers = (last10Numbers * 28433 + 1) % 10000000000L;
		return Long.toString(last10Numbers);
	}

}
