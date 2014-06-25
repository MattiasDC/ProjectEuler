package projectEuler;

public class Problem003 extends Problem {

	@Override
	protected String solve() {
		
		long number = 600851475143L;
		long answer = 0;
		
		if (number%2 == 0){
			number /= 2;
			answer = 2;
		}
		
		long i;
		for(i = 3; i <= Math.sqrt(number);i+=2){
			if (number%i == 0){
				answer = i;			
				while (number%i == 0)number /= i;
			}
		}
		if (i != 1) answer = number;
		
		return answer + "";
	}

}
