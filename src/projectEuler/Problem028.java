package projectEuler;

public class Problem028 extends Problem {

	@Override
	protected String solve() {

		long answer = 1;
		for (int i = 3; i <= 1001; i +=2){
			int rightUpCorner = (int)Math.pow(i, 2);
			answer += 4 * rightUpCorner + 6*(-i + 1);
		}
		
		return answer + "";
	}

}
