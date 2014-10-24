package projectEuler;


import extMath.ExtMath;


public class Problem072 extends Problem {

	@Override
	protected String solve() {
		long containing = 0;

		for (int i = 2; i <= 1000000; i++) {
			containing += ExtMath.phi(i);
		}

		return containing + "";
	}

}
