package projectEuler;

import java.math.BigInteger;
//import java.util.ArrayList;

//IMPORTANT: This problem contains 2 solutions,
//the quickest one uses the BigInteger class and runs in ~200ms.
//The second solutions was an experiment.
//It is programmed such that it can handle infinite large numbers, just like the BigInteger class.
//The parameter MAX_SIZE determines how many digits each element in the arraylist
//(which represents the number) can have. The experiment runs in ~2seconds
public class Problem056 extends Problem {

	@Override
	protected String solve() {
		int maxSum = 0;
		int cachedSum = 0;
		BigInteger number;
		for (int i = 1; i < 100; i++){
			for (int j = 1; j < 100;j++){
				number = new BigInteger(i+"");
				number = number.pow(j);
				for (char s : number.toString().toCharArray())
					cachedSum += Integer.parseInt(String.valueOf(s));
				if (cachedSum > maxSum)
					maxSum = cachedSum;
				cachedSum = 0;
			}
		}
//		for (int a = 1;a < 100;a++)
//			for (int b = 1; b < 100;b++)
//				if (maxSum < (cachedSum =getSum(a,b)))
//					maxSum = cachedSum;
		
		return maxSum + "";
	}

//	private int getSum(int a, int b) {
//		ArrayList<Long> fullInteger = new ArrayList<Long>();
//		long previousOverflow = 0, newValue;
//		int sum = 0;
//		fullInteger.add((long) a);
//		
//		for (int i = 1; i < b;i++){
//			for (int j = 0; j < fullInteger.size();j++){
//				newValue = previousOverflow + a*fullInteger.get(j);
//				fullInteger.set(j, (long) (newValue%(Math.pow(10, MAX_SIZE))));
//				previousOverflow = (long) (newValue/(Math.pow(10, MAX_SIZE)));
//			}
//			if (previousOverflow != 0)
//				fullInteger.add(previousOverflow);
//			previousOverflow = 0;
//		}
//		
//		for (Long value : fullInteger)
//			while (value > 0){
//				sum += value%10;
//				value /=10;
//			}
//		return sum;
//	}
//
//	private final int MAX_SIZE = 13;
}
