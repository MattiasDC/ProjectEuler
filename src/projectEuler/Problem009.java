package projectEuler;

public class Problem009 extends Problem{

	@Override
	protected String solve() {
		
		//using 2 formulas: a + b + c = 1000 and a² + b² = c²
		//solve this system with a as parameter.
		for (int a = 1; a <= 998; a++){
				int b = (1000000-2000*a)/(2000-2*a);
				int c = 1000 -a - b;
				
				if (c*c == a*a + b*b){
					return a * b * c + "";
				}
			
		}
		
		return null;
	}

}