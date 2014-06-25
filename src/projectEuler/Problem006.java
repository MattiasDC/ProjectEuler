package projectEuler;

import java.math.BigInteger;

public class Problem006 extends Problem {

	@Override
	protected String solve() {
		
		
		BigInteger squareOfSum = new BigInteger((1+100)*50 + "");
		BigInteger sumOfSquare = new BigInteger("0");
		
		squareOfSum = squareOfSum.pow(2);
		
		for (int i = 1; i <= 100; i++){
			sumOfSquare = sumOfSquare.add(new BigInteger((int)Math.pow(i, 2) + ""));
		}
		
		return squareOfSum.subtract(sumOfSquare) + "";
	}

}
