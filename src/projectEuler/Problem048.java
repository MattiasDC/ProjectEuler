package projectEuler;

public class Problem048 extends Problem {

	@Override
	protected String solve() {
		long answer = 0;
		for (int i = 1; i <= 1000; i++){
			long power = 1;
			for (int j = 1;j <= i;j++)
				power = i*power%10000000000L;
			answer = (answer +power)%10000000000L;
			power = 1;
		}
		return answer +"";
	}
}
