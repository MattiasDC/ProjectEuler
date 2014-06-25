package projectEuler;

import extMath.ExtMath;

public class Problem047 extends Problem {

	@Override
	protected String solve() {
		long i = 2, jumpTo = 0;
		int length = 4;
		while(true){
			while (ExtMath.getPrimeFactors(i).size()/2 != length)
				i++;
			for (long j = i+length-1;j >= i + 1;j--){
				if (ExtMath.getPrimeFactors(j).size()/2 != length){
					jumpTo = j+1;
					break;
				}
			}
			if (jumpTo == i)
				return i + "";
			i = jumpTo;
		}
	}

}
