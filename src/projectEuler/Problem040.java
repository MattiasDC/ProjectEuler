package projectEuler;

public class Problem040 extends Problem {

	@Override
	protected String solve() {
		int answer = 1;
		int multiplier = 1;
		
		int placeInSequence = 1;
		int number = 1;
		while (multiplier <= 1000000){
			if (placeInSequence >= multiplier){
				answer *= getIthReverseNumber(number, getNumberOfDigits(number) - (placeInSequence-multiplier));
				multiplier *= 10;
			}			
			placeInSequence += getNumberOfDigits(number);
			number++;
		}
		
		return answer + "";
	}
}
