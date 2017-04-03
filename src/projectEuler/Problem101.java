package projectEuler;

import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;

public class Problem101 extends Problem {

	@Override
	protected String solve() {
		double sum = 0;

		int[] coefficients = new int[] { 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1 };
		double[] values = new double[10];
		for (int i = 0; i < 10; i++) {
			values[i] = evaluatePolynomial(coefficients, i + 1);
		}

		sum += 1;
		DividedDifferenceInterpolator interpolator = new DividedDifferenceInterpolator();
		double[] x, y;
		for (int i = 2; i <= 10; i++) {
			x = new double[i];
			y = new double[i];
			for (int j = 0; j < i; j++) {
				x[j] = j + 1;
				y[j] = values[j];
			}
			PolynomialFunctionNewtonForm polynomial = interpolator.interpolate(x, y);
			sum += polynomial.value(i + 1);
		}
		return Double.toString(sum);
	}

	private double evaluatePolynomial(int[] coefficients, int n) {
		double value = 0;
		for (int i = 0; i < coefficients.length; i++) {
			value += coefficients[i] * Math.pow(n, i);
		}
		return value;
	}
}
