package projectEuler;
import extMath.ExtMath;

public class Problem012 extends Problem{

	@Override
	protected String solve() {

		long number = 1;
		long prev =  1;
		
		while (ExtMath.getNumberOfDivisors(number) <= N){
			number += prev + 1;
			prev++;
		}
		return number + "";
	}

	private int N = 500;
}
