package projectEuler;

import extMath.*;

public class Problem033 extends Problem {

	@Override
	protected String solve() {
		int answer = 1;

		int[] product = {1,1};

		for (int i = 11; i < 100; i++){
			for (int j = i + 1;j < 100; j++){
				try{
					if ((i/10 == j%10 && ((double)i/j) == ((double)(i%10)/(j/10))) || (i%10 == j/10 && ((double)i/j) == ((double)(i/10)/(j%10)))){
						product[0] *= i;
						product[1] *= j;
					}
				}catch (ArithmeticException exc){
					continue;
				}
			}
		}

		answer = product[1] /ExtMath.gcd(product[0],product[1]);
		return answer + "";
	}
}
