package projectEuler;

public class Problem014 extends Problem{

	@Override
	protected String solve() {
		
		long longestChain = 0;
		long answer = 1;
		
		for (long i = 500000; i < 1000000;i++){
			long number = i, chain = 1;
			
			while (number != 1){
				if (number % 2 == 0) number /=2;
				else number = 3*number + 1;
				chain++;
			}

			if (chain > longestChain){
				answer = i;
				longestChain = chain;
			}
		}
		
		return answer + "";
	}
}
