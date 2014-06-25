package projectEuler;
import extMath.ExtMath;

public class Problem007 extends Problem{

	@Override
	protected String solve() {
		
		int primesFound = 1;
		long lastPrime = 2;

		for (long number = 3; primesFound < 10001; number += 2){
			if (ExtMath.isPrime(number)){
				primesFound++;
				lastPrime = number;
			}
		}
		
		return lastPrime + "";
	}
}
