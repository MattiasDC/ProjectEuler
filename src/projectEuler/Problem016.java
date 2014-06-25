package projectEuler;

import java.math.BigInteger;

public class Problem016 extends Problem{

	//This was originally done by string manipulation but due to HDD reset, an easier solution was recreated
	@Override
	protected String solve() {
		
		BigInteger number = new BigInteger("2");
		
		number = number.pow(1000);
		
		int sumNumbers = 0;
		for (int i = 0; i < number.toString().length();i++){
			sumNumbers += Integer.parseInt(number.toString().substring(i,i+1));
		}
		
		return sumNumbers + "";
	}

}
