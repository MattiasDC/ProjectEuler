package projectEuler;

import java.util.ArrayList;

import extMath.ExtMath;

public class Problem027 extends Problem {

	@Override
	protected String solve() {

		int longestConsecutivePrimes = 0;
		int number1 = 0;
		int number2 = 0;

		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		for (int i = 3; i < 1000; i += 2){
			if (ExtMath.isPrime(i)){
				primes.add(i);
			}
		}
		
		for (int a = -999;a<1000;a++){
			for (Integer b : primes){

				int consecutivePrimes = 0;
				boolean wasPrime = true;

				for (int i = 0;wasPrime;i++){

					int temp = (int) Math.pow(i, 2);
					
					if (ExtMath.isPrime(Math.abs((temp + i*a + b)))){
						consecutivePrimes++;
					}
					else wasPrime = false;
				}
				
				if (consecutivePrimes > longestConsecutivePrimes){
					number1 = a;
					number2 = b;
					longestConsecutivePrimes = consecutivePrimes;
				}
			}
		}
		
		return number1 * number2 + "";
	}

}
