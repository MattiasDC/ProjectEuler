package projectEuler;

import java.math.BigInteger;

public class Problem020 extends Problem {

	@Override
	protected String solve() {

		BigInteger facultyHundred = new BigInteger("100");
		
		facultyHundred = faculty(facultyHundred);
		
		int sumNumbers = 0;
		for (int i = 0; i < facultyHundred.toString().length();i++){
			sumNumbers += Integer.parseInt(facultyHundred.toString().substring(i,i+1));
		}
		
		return sumNumbers + "";
	}

	private BigInteger faculty(BigInteger number) {

		if (number.equals(BigInteger.ONE)) return BigInteger.ONE;
		return number.multiply(faculty(number.subtract(BigInteger.ONE)));
		
	}

}
