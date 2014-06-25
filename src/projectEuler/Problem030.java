package projectEuler;

import java.util.HashSet;

public class Problem030 extends Problem {

	@Override
	protected String solve() {

		// 9^5 * 6 = 354k which means that any 6 number greater than 354k can't match it.
		//Since 9^5 * 7 = 413k any 7 number long number will definitely not match
		//since 9^5 * 7 is way lower
		//This wasn't my idea, I had the upper limit to 200k which was the same result.
		for (int i = 2;i <= Math.pow(9, 5) * 6;i++){
			int checker = 0;
			for (int j = 1; j <= 6;j++){
				checker += Math.pow(getIthReverseNumber(i, j), 5);
			}
			
			if (i == checker){
				addValidNumbers(i);
			}
		}
		
		int sum = 0;
		for (Integer validNumber : getValidNumbers()){
			sum += validNumber;
		}
		
		return sum + "";	
	}
	
	public HashSet<Integer> getValidNumbers() {
		return validNumbers;
	}

	public void addValidNumbers(Integer validNumber) {
		if (validNumber != null)
			validNumbers.add(validNumber);
	}

	private HashSet<Integer> validNumbers = new HashSet<Integer>();

}
//156 % 1000 / 100 = 1
//156 % 100 / 10 = 5
//156 % 10 / 1 = 6
//
//1634 % 10000 / 1000 = 1
//1634 % 1000 / 100 = 6
//1634 % 100 / 10 = 3
//1634 % 10 / 1 = 4
//
//54356 % 100000 / 10000 = 5
//54356 % 10000 / 1000 = 4
//54356 % 1000 / 100 = 3
//54356 % 100 / 10 = 5
//54356 % 10 / 1= 6
