package projectEuler;

public class Problem001 extends Problem{

	@Override
	protected String solve() {

		long sum = 0;
		
		for (int i = 3; i < 1000; i++){
			if (i%3 == 0 || i%5 == 0) sum += i;
		}
		
		return sum + "";
	}
}
