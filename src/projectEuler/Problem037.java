package projectEuler;

import extMath.ExtMath;

public class Problem037 extends Problem {

	@Override
	protected String solve() {
		int answer = 23;
		int truncatablesFound =1;
		
		for (long i = 11; truncatablesFound < 11; i += 2){
			if (!hasEvenNumber(i)
					&& isTruncatableFromRight(i)
					&& isTruncatableFromLeft(i)
					&& ExtMath.isPrime(i)){

					truncatablesFound++;
					answer += i;				
			}
		}
		
		return answer + "";
	}
	
	private boolean hasEvenNumber(long number){
		while (number > 0){
			if (number % 2 == 0) return true;
			number /= 10;
		}
		return false;
	}
	
	private boolean isTruncatableFromRight(long number){
		number /= 10;
		boolean allPrime = true;
		int length = getNumberOfDigits(number);
		for (int j = 1; j <= length && allPrime;j++){
			if (ExtMath.isPrime(number))
				number /= 10;
			else
				allPrime = !allPrime;
		}
		
		return allPrime;
	}
	
	private boolean isTruncatableFromLeft(long number) {
		int length = getNumberOfDigits(number);
		boolean allPrime = true;
		
		for (int j = 1; j <= length && allPrime; j++){
			if (ExtMath.isPrime(number))
				number = (long) (number % Math.pow(10, length -j));
			else
				allPrime = !allPrime;
		}
		return allPrime;
	}
}
