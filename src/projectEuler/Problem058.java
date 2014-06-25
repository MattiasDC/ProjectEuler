package projectEuler;

import extMath.ExtMath;

public class Problem058 extends Problem {

	@Override
	protected String solve() {
		//initialization
		double percentage = 1/10.0;
		int numberOfPrimes = 8;
		int diag = 13;
		int length = 7;
		
		while (numberOfPrimes/1.0/diag > percentage){
			length += 2;
			diag = 2*length -1;
			for (int i = 1; i <= 4;i++)
				if (ExtMath.isPrime((length*length)-i*(length-1)))
					numberOfPrimes++;
		}
		return length +"";
	}

}
