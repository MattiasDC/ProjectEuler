package projectEuler;

public class Problem017 extends Problem {

	@Override
	protected String solve() {

		int length = 11; //one thousand
		
		for (int i = 1; i < 1000;i++){
			int number = i;
			if (number >= 100){
				if (number % 100 != 0) length +=3; //and
				length += getLength(100) + getLength(number/100);
				number = number % 100;
			}
			if (number >19){
				length += getLength(number-number%10) + getLength(number%10);
			}
			else length += getLength(number);
			
		}
		
		return length + "";
	}

	private int getLength(int number){
		switch (number){
			case 1: return 3;
			case 2: return 3;
			case 3: return 5;
			case 4: return 4;
			case 5: return 4;
			case 6: return 3;
			case 7: return 5;
			case 8: return 5;
			case 9: return 4;
			case 10: return 3;
			case 11: return 6;
			case 12: return 6;
			case 13: return 8;
			case 14: return 8;
			case 15: return 7;
			case 16: return 7;
			case 17: return 9;
			case 18: return 8;
			case 19: return 8;
			case 20: return 6;
			case 30: return 6;
			case 40: return 5;
			case 50: return 5;
			case 60: return 5;
			case 70: return 7;
			case 80: return 6;
			case 90: return 6;
			case 100: return 7;
			default: return 0;
		}		
	}
}
