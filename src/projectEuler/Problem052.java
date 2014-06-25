package projectEuler;

public class Problem052 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		boolean check;
		
		for (int i = 10; answer == 0;i++){
			check = true;
			if (i > Math.pow(10, getNumberOfDigits(i))/6){
				i = (int) Math.pow(10, getNumberOfDigits(i));
				continue;
			}
			for (int j = 2; j <= 6;j++){
				if (!contains(i+"",(i*j+"")))
					check = false;
			}
			if (check)
				answer = i;
		}
		
		return answer + "";
	}
	
	private boolean contains(String number, String OtherNumber){
		int length = OtherNumber.length();
		char[] chars = OtherNumber.toCharArray();
		for (int i = 0; i < length;i++){
			if (!number.contains(chars[i] +""))
				return false;
		}
		return true;
	}

}
