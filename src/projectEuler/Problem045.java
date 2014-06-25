package projectEuler;

public class Problem045 extends Problem {

	@Override
	protected String solve() {	
		for (int i = 144;true;i++){
			int a = makeHexagonal(i);
			if (isPentagonal(a) && isTriangle(a))
				return a + "";
		}
	}

}
