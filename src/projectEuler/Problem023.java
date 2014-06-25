package projectEuler;

import java.util.HashSet;
import java.util.Iterator;
import extMath.ExtMath;

public class Problem023 extends Problem {

	@Override
	protected String solve() {
		
		for (int i = 1; i <= 28123;i++){
			if (i < getSumDivisors(i)) getAbundantNumbers().add(i);
		}

		int sum = 0;
		for (int i = 1; i <= 28123;i++){		
			if (isValidNumber(i)) sum += i;
		}
		
		return sum + "";
		
	}
	
	private int getSumDivisors(int number){
		int sumDivisors = 0;
	
		for (long divisor : ExtMath.getDivisors(number)){
			if (divisor != number)
				sumDivisors += divisor;
		}
		return sumDivisors;
	}
	
	private boolean isValidNumber(int number){
		Iterator<Integer> iterator = getAbundantNumbers().iterator();
		while (iterator.hasNext()){
			int abundantNumber = iterator.next();
			if (getAbundantNumbers().contains(number-abundantNumber)) return false;
		}
		return true;
	}
	
	public HashSet<Integer> getAbundantNumbers() {
		return abundantNumbers;
	}

	private HashSet<Integer> abundantNumbers = new HashSet<Integer>();
}
