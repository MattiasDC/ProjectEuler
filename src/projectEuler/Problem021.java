package projectEuler;
import extMath.ExtMath;
import java.util.HashMap;
import java.util.Iterator;

public class Problem021 extends Problem{

	@Override
	protected String solve() {

		int sumAmicablePairs = 0;

		for (int i = 1; i <= 10000;i++){
			getNumbersAndDivisors().put(i, ExtMath.getSumDivisors(i));
		}

		Iterator<Integer> iterator = getNumbersAndDivisors().keySet().iterator();

		while (iterator.hasNext()){
			long number = iterator.next();
			long sumDivisors = getNumbersAndDivisors().get(number);
			
			if (sumDivisors <= 10000 && getNumbersAndDivisors().get(sumDivisors) == number && sumDivisors != number && sumDivisors != 1){
				sumAmicablePairs += number;
			}
		}

		return sumAmicablePairs + "";
	}

	public HashMap<Integer, Long> getNumbersAndDivisors() {
		return numbersAndDivisors;
	}

	private HashMap<Integer,Long> numbersAndDivisors = new HashMap<Integer,Long>();
}
