package projectEuler;
import extMath.ExtMath;

public class Problem051 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		sieve = ExtMath.sieve(1000000);
		int[] increasers = initiateIncreasers(999999);
		int groupCount = 0, goal = 8,digits = 0;

		for (int i = 11;i < 1000000;i = getPrimeUp(i)){

			digits = getNumberOfDigits(i);
			groupCount = 0;
			for (int value : increasers){
				for (int j = 1; j < 10;j++){
					//if the number has gained a digit, it can't be a solution
					if (getNumberOfDigits(i + value*j) != digits)
						break;
					if (sieve[i+value*j])
						groupCount++;
				}
				//groupcount +1 == goal => possible solution
				//and check if the all digits are the same who are changed (this is done by the evaluate function)
				if (groupCount +1 == goal && evaluate(i,value,goal))
					break;
				groupCount = 0;
			}
			if (groupCount +1 == goal){
				answer = i;
				break;
			}
		}
		return answer + "";
	}

	private boolean evaluate(int number, int value, int goal) {
		int changingDigit = -1;
		for (int i = 1; i <= getNumberOfDigits(value);i++){
			if (getIthReverseNumber(value, i) == 1 && getIthReverseNumber(number, i) >= 10-goal)
				return false;
			if (getIthReverseNumber(value, i) == 1){
				if (changingDigit == -1)
					changingDigit = getIthReverseNumber(number, i);
				else
					if (changingDigit != getIthReverseNumber(number, i))
						return false;
			}
		}
		return true;
	}

	//initiate all possible values who change digits
	//10  100 110 1000 1010 1100 1110 ...
	private int[] initiateIncreasers(int number){
		int size = (int) Math.pow(2,getNumberOfDigits(number)-1)-1;
		int[] increasers = new int[size];

		for (int i = 0; i < getNumberOfDigits(number)-1;i++){
			increasers[(int) Math.pow(2, i)-1] = (int) Math.pow(10, i+1);
			for (int j = 1; j <= Math.pow(2, i)-1;j++)
				increasers[(int) (Math.pow(2,i)+j-1)] = (int)Math.pow(10, i+1)+increasers[j-1];
		}

		return increasers;
	}
}
