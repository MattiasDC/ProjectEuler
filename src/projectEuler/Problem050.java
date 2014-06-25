package projectEuler;
import extMath.ExtMath;

public class Problem050 extends Problem {


	@Override
	protected String solve(){
		int answer = 0, currentMaxLength = 2, sum = 0,prime = 0,
				currentLength, leastSignificantPrime = 2;

		sieve = ExtMath.sieve(1000000);
		for (int i = 1000000; i > 2;i = getPrimeBelow(i)){
			sum = 2;
			prime = 2;
			currentLength = 1;
			leastSignificantPrime = 2;
			while (prime < i){
				prime = getPrimeUp(prime);
				sum += prime;
				currentLength++;
				if (sum > i){
					while (sum > i){
						sum -= leastSignificantPrime;
						currentLength--;
						leastSignificantPrime = getPrimeUp(leastSignificantPrime);
					}
					if (leastSignificantPrime > i/currentMaxLength)
						break;
				}
				if (sum == i && currentLength > currentMaxLength){
					currentMaxLength = currentLength;
					answer = i;
				}

			}
		}
		return answer + "";
	}

	/*
	 * 	BEST LEGACY CRAP CODE WITH FIRST TABLE LOOKUP EVER
	 * 
	 * 
	 * 
	@Override
	protected String solve() {
		int answer = 0, length = 0;

		sieve = sieve(1000);
		for (int i = 2; i < 1000;i = getPrimeUp(i)){
			length = maxLength(i,i,0);
			if (length > maxLength){
				System.out.println(i);
				answer = i;
				this.maxLength = length;
			}
		}
		System.out.println(count);
		return answer + "  length: " + maxLength;
	}

	private boolean[] sieve;
	private int maxLength = 0;

	private int getPrimeBelow(int number){
		while ( --number >= 1 && !sieve[number]);
		return number;
	}

	private int getPrimeUp(int number){
		while ( ++number < sieve.length && !sieve[number]);
		return number;
	}

	private int maxLength(int leftOver, int previousPrime, int depth){
		int currentPrime = previousPrime;
		int length = 0, maxLength = 0, upperBound = -1;

		test t = new test(leftOver,previousPrime);
		if (best.get(t) != null){
			count++;
			return best.get(t);
		}

		if (depth == 0 && this.maxLength >= 1){
			upperBound = getPrimeUp(previousPrime/(this.maxLength));
			for (int i = 1; i <= (this.maxLength+1)/2;i++){
				upperBound = getPrimeUp(upperBound);
			}
		}

		if (upperBound != -1)
			currentPrime = upperBound+1;
		if (leftOver < getPrimeBelow(previousPrime))
			return -1;
		if (leftOver == getPrimeBelow(previousPrime))
			return depth++;

		while ((currentPrime = getPrimeBelow(currentPrime)) > 1){
			if (leftOver - currentPrime < currentPrime){
				if (depth+1 < this.maxLength)
					currentPrime = getPrimeBelow((previousPrime+1)/2);
				if (getPrimeBelow(currentPrime) != leftOver-currentPrime)
					continue;
				else{
					best.put(new test(leftOver,previousPrime), 1);
					return 1;
				}
			}
			else{
				if (currentPrime != getPrimeBelow(previousPrime) && depth != 0){
					continue;
				}
				if (leftOver - currentPrime > (currentPrime*(currentPrime+1)/2)-((currentPrime/2)*((currentPrime/2)+1)/2)){
					continue;
				}

				length = maxLength(leftOver-currentPrime, currentPrime, depth+1)+1;
				if (length != 1){
					best.put(new test(leftOver,previousPrime), length);
					if (length > maxLength){
						maxLength = length;
					}
				}
				else
					best.put(new test(leftOver,previousPrime), 0);
				}
			}
			return maxLength;
		}

		private HashMap<test,Integer> best = new HashMap<test,Integer>();
		int count = 0;
	 */
}
