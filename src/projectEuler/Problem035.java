package projectEuler;

import extMath.ExtMath;

public class Problem035 extends Problem {

	@Override
	protected String solve() {
		long answer = 1;
		
		for (int i = 3; i < 1000000; i += 2){
			int number = i;
			
			if (!hasEvenNumber(number) && ExtMath.isPrime(number)){
				int circulatedNumber = circulateNumber(number);
				boolean allPrime = true, lowest = true;
				int length = 1;
				while (circulatedNumber != number && allPrime && lowest){
					if (circulatedNumber > number){
						lowest = false;
					}
					if (!ExtMath.isPrime(circulatedNumber)) allPrime = false;
					circulatedNumber = circulateNumber(circulatedNumber);
					length++;
				}
				if (allPrime && lowest) answer += length;
			}
		}
		
		return answer + "";
	}
	
	private boolean hasEvenNumber(int number){
		while (number > 0){
			if (number % 2 == 0) return true;
			number /= 10;
		}
		return false;
	}
	
	private int circulateNumber(int number){
		int circulatedNumber = 0;
		int multiplier = 10;
		if (number < 10) return number;
		
		while (number > 0){
			int toAdd = (number % 10)*multiplier;
			if (toAdd == 0) return number;
			circulatedNumber += toAdd;
			number /= 10;
			multiplier *= 10;
		}
		
		circulatedNumber = circulatedNumber % (multiplier/10) + circulatedNumber % (multiplier) / (multiplier/10);
		return circulatedNumber;
	}
}
