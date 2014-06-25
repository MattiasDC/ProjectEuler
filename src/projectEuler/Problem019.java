package projectEuler;

public class Problem019 extends Problem{

	@Override
	protected String solve() {

		int day = 0;
		int sundays = 0;
		for (int i = 1900; i <= 2000;i++){
			for (int j = 0; j < 12; j++){			
				if (day == 6 && i > 1900) sundays++;
				
				day = (day + months[j]%7)%7;
				if (months[j] == 1 && i%4 == 0 && (i%100 != 100 || i%400 == 0)) day = (day+1)%7;
			}
		}
		
		return sundays + "";
	}
	
	int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};

}
