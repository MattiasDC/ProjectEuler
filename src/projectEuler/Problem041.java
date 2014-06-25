package projectEuler;

import extMath.ExtMath;

public class Problem041 extends Problem {

	//upperlimit: 9999999 since 123456789 is divisible by 3 since 45 is divisible by three
	//likewise for 12345678 max length can thus be 7 long
	@Override
	protected String solve() {
		int answer = 0;
		for (int i = 9876543; i > 1;i-=2){
			if (isPandigit(i +"") && ExtMath.isPrime(i)){
				return i + "";
			}
		}
		return answer + "";
	}
}
