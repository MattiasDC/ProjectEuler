package projectEuler;

import extMath.ExtMath;

public class Problem046 extends Problem {

	@Override
	protected String solve() {
		long i = 7;
		while (true){
			i += 2;
			if (ExtMath.getNumberOfDivisors(i) == 2)
				continue;
			
			long square = (long) Math.sqrt(i);
			
			while (square != 1){
				if (ExtMath.isPrime(i - 2*square*square))
					break;
				
				square--;
			}
			if (square == 1 && !ExtMath.isPrime(i-2*square))
				return i +"";
		}
	}

}
