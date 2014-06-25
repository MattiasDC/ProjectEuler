package projectEuler;

public class Problem036 extends Problem {
	
	@Override
	protected String solve() {
		int answer = 0;
		
		for (int i = 0; i < 1000000; i++){
			if (isPalindrome(Integer.toString(i)) && isPalindrome(Integer.toBinaryString(i))){
				answer +=i;
			}
		}
		
		return answer + "";
	}
}
