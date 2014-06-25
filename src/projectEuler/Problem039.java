package projectEuler;

public class Problem039 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		int highestCount = 0;

		//using 2 formulas: a + b + c = i and a² + b² = c²
		//solve this system with a as parameter.
		for (int i = 12; i <= 1000; i++){
			int count = 0;
			for (int a = 1; a < i; a++){
				int b = (i*i-2*i*a)/(2*(i-a));
				int c = i -a - b;

				if (c*c == a*a + b*b){
					count++;
				}
			}
			if (count > highestCount){
				answer = i;
				highestCount = count;
			}
		}

		return answer + "";
	}

}
