package projectEuler;

public class Problem063 extends Problem {

	/* Information on the 1-digit bound
	 * 
	 * It is possible to calculate a lower and upper bound on the digits of a power.
	 * Take (abcd)^t where abcd is a 4-digit number and t is a random integer.
	 * 
	 * The lower bound of digits of this power is found by the following formula:
	 * amountOfDigits + (amountOfDigits-1)*(t-1)
	 * 
	 * The upper bound of digits of this power is found by the  following formula:
	 * amountOfDigits*t
	 * 
	 * Let's first see why only 1-digit bases can have the property we seek.
	 * If we have a 2-digit number ab with the power t we can use the lower bound formula:
	 * 2 + 1*(t-1) = 2 + t -1 = t + 1
	 * Thus for 2-digit bases the lower bound will always be higher than t and thus can never be equal to it.
	 * We can use the same logic for any higher than 1-digit base to see
	 * that >1-digit numbers can never have the property we seek.
	 * 
	 * Now let's see why there is a solution less than infinity for 1-digit numbers.
	 * The lower bound of digits of a 1-digit base is equal to 1.
	 * The upper bound of digits of a 1-digit base is equal to t.
	 * 
	 * We need to prove that there is always a value of t where the solution has less digits than t.
	 * This is easy since we know that the number 10 multiplied with a number
	 * ALWAYS increases the number of digits by 1.
	 * We also know that this is not the case numbers smaller than 10.
	 * So eventually there will be a value t where the number of digits does not increase,
	 * since we have proven that only 1-digit numbers can have the property we seek and with each
	 * increasing power we multiply with a 1-digit number.
	 * 
	 */
	@Override
	protected String solve() {
		int answer = 1, cachedDigits = 1;
		double previousPower = 0;
		
		//We skip the number one since we already incremented answer for it.
		for (int i = 2; i < 10;i++){
			for (int j = 1; (j-1) <= cachedDigits;j++){
				previousPower = Math.pow(i, j);				
				if ((cachedDigits = getNumberOfDigits(previousPower)) == j)
					answer++;
			}
			cachedDigits = 1;
		}
		
		return answer + "";
	}

}
