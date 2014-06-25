package projectEuler;

public class Problem002 extends Problem{

	@Override
	protected String solve() {

		long answer = 0;
		int secondLast = 1;
		int temp = 0;
		
		for (int i = 1; i < 4000000; i+= temp){
			if (i%2 == 0) answer += i;
			temp = secondLast;
			secondLast = i;
		}
		
		return answer + "";
	}

}
