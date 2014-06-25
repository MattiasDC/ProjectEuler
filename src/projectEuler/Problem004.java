package projectEuler;

public class Problem004 extends Problem{

	@Override
	protected String solve() {
		long largestPalindrome = 0;
		
		for (int i = 999;i > 100;i--){
			for(int j = i; j > 100;j--){
				if (i*j > largestPalindrome && isPalindrome(Integer.toString(i*j)))
					largestPalindrome = i*j;
			}
		}

		return largestPalindrome + "";
	}
}
