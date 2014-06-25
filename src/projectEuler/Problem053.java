package projectEuler;

public class Problem053 extends Problem {

	@Override
	protected String solve() {
		int count = 0, k;
		for (int n = 1;n <= 100;n++){
			k = 1;
			if (n%2 == 0 && faculty((double)n)/(faculty((double)n/2)*faculty((double)n/2)) > 1000000)
				count--;
			while (k <= n-k){
				if (faculty((double)n)/(faculty((double)n-k)*faculty((double)k)) > 1000000)
					count+=2;
				k++;
			}
		}
		return count + "";
	}

	private double faculty(double number){
		if (number <= 1) return 1D;
		return faculty(number-1) * number;
	}
}
