package projectEuler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Problem100 extends Problem {

	@Override
	protected String solve() {
		BigDecimal y = new BigDecimal(new BigInteger("2"));
		BigDecimal x;
		BigDecimal last = BigDecimal.ONE;
		BigDecimal newCoef = BigDecimal.ONE;
		while (true) {
			x = sqrt(y.multiply(y).negate().add(y).multiply(NEIGHT).add(FOUR), 100).add(TWO).divide(FOUR);
			if (x.remainder(BigDecimal.ONE).compareTo(TENETWENTY) <= 0) {
				if (y.compareTo(new BigDecimal(Math.pow(10, 12))) > 0) {
					x = x.setScale(0);
					System.out.println(newCoef);
					break;
				}
				newCoef = y.divide(last, new MathContext(20));
				last = y;
				y = y.multiply(newCoef).setScale(0, BigDecimal.ROUND_FLOOR);
			}
			y = y.add(BigDecimal.ONE);
		}
		return x.toString();
	}

	private static BigDecimal TENETWENTY = new BigDecimal(Math.pow(10, -10));
	private static BigDecimal TWO = new BigDecimal("2");
	private static BigDecimal FOUR = new BigDecimal("4");
	private static BigDecimal NEIGHT = new BigDecimal("8").negate();

	public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
		BigDecimal x0 = new BigDecimal("0");
		BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
		while (!x0.equals(x1)) {
			x0 = x1;
			x1 = A.divide(x0, SCALE, BigDecimal.ROUND_HALF_UP);
			x1 = x1.add(x0);
			x1 = x1.divide(TWO, SCALE, BigDecimal.ROUND_HALF_UP);

		}
		return x1;
	}

}
