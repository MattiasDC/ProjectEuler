package projectEuler;

import java.math.BigInteger;

public class Problem025 extends Problem {

	@Override
	protected String solve() {

		BigInteger secondLast = BigInteger.ONE;
		BigInteger temp = BigInteger.ZERO;
		BigInteger i = BigInteger.ONE;
		int j = 2;
		
		while (i.toString().length() < 1000){
			temp = new BigInteger(secondLast.toByteArray());
			secondLast = new BigInteger(i.toByteArray());
			i = i.add(temp);
			j++;
		}
		
		return j + "";
	}

}
