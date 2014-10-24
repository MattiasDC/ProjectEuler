package projectEuler;


import java.math.BigDecimal;

import extMath.ExtMath;


public class Problem080 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		for (int i = 1; i <= 100; i++) {
			if (Math.sqrt(i) % 1.0 != 0.0) {
				answer += getDecimalSum(ExtMath.sqrt(i, 100));
			}
		}
		return answer + "";
	}

	private int getDecimalSum(BigDecimal number) {
		return getSumOfDigits(number.movePointRight(99).toBigInteger());
	}
}
