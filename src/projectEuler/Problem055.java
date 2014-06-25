package projectEuler;
import java.math.BigInteger;

public class Problem055 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		
		for (int i = 0; i < 10000;i++)
			if (isLychrell(i))
				answer++;

		return answer + "";
	}

	private boolean isLychrell(int number){
		int tester;
		BigInteger sum;
		sum = new BigInteger(number+"");
		tester = 1;
		sum = sum.add(new BigInteger((new StringBuilder(sum.toString()).reverse().toString())));
		while (tester < 50 && !isPalindrome(sum.toString())){
			sum = sum.add(new BigInteger((new StringBuilder(sum.toString()).reverse().toString())));
			tester++;
		}
		if (tester == 50)
			return true;
		return false;
	}

}
