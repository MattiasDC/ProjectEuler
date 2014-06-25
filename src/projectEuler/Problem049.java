package projectEuler;

import java.util.Arrays;
import extMath.ExtMath;

public class Problem049 extends Problem {

	@Override
	protected String solve() {
		int start = 10000;
		String xS;

		while (true){
			boolean[] sieve = ExtMath.sieve(start);
			start *= 10;
			for (int i = 1000; i < sieve.length-1;i++){
				if (!sieve[i])
					continue;
				if (i == 1487)
					continue;
				for (int t = 2;t < (10000-i)/2; t+= 2){
					char[] charsx = (i+"").toCharArray();
					Arrays.sort(charsx);
					xS = String.valueOf(charsx);
					char[] charsxt = (i+ t + "").toCharArray();
					Arrays.sort(charsxt);
					char[] charsxtt = (i+2*t + "").toCharArray();
					Arrays.sort(charsxtt);
					if (xS.equals(String.valueOf(charsxt)) && xS.equals(String.valueOf(charsxtt))
							&& ExtMath.isPrime(i+2*t) && ExtMath.isPrime(i+t))
						return i + "" + (i+t) + "" + (i+2*t);
				}
			}
		}
	}
}
