package projectEuler;

import java.util.ArrayList;
import java.util.HashMap;
import extMath.ExtMath;

public class Problem060 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		ArrayList<Integer> activeComplements = new ArrayList<Integer>();
		initializeComplements();
		for (int prime : complements.keySet()){
			ArrayList<Integer> usingPrimes = new ArrayList<Integer>();
			usingPrimes.add(prime);
			activeComplements.clear();
			activeComplements.addAll(complements.get(prime));
			
			if ((usingPrimes = chooseComplements(activeComplements,usingPrimes)) != null){
				for (int usingPrime : usingPrimes)
					answer += usingPrime;
				return answer + "";
			}
		}
		
		
		return answer + "";
	}

	private void initializeComplements() {
		for (int i = 0; i < 10000;i++){
			if (!sieve[i])
				continue;
			AddComplements(i);
		}
	}
	
	private void AddComplements(int number) {
		int mergedNumber, reverseNumber;
		int digitsNumber, digitsI;
		digitsNumber = getNumberOfDigits(number);
		for (int i = 0; i < number;i++){			
			digitsI = getNumberOfDigits(i);
			if (!sieve[i])
				continue;
			mergedNumber = (int) (number*Math.pow(10, digitsI)+i);
			reverseNumber = (int) (i*Math.pow(10, digitsNumber)+number);
			if (sieve[mergedNumber] && sieve[reverseNumber]){
				if (complements.get(number) == null){
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(i);
					complements.put(number,tmp);
				}
				else
					if (!complements.get(number).contains(i))
						complements.get(number).add(i);
				if (complements.get(i) == null){
					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.add(number);
					complements.put(i,tmp);
				}
				else
					if (!complements.get(i).contains(number))
						complements.get(i).add(number);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Integer> chooseComplements(ArrayList<Integer> activeComplements,
			ArrayList<Integer> usingPrimes) {
		ArrayList<Integer> nowUsingPrimes;
		ArrayList<Integer> remainingComplements;
		for (int complement : activeComplements){
			remainingComplements = filter(activeComplements,complement);
			nowUsingPrimes = (ArrayList<Integer>) usingPrimes.clone();
			nowUsingPrimes.add(complement);
			if (remainingComplements.size() == 0)
				continue;
			if (nowUsingPrimes.size() == depth-1){
				nowUsingPrimes.add(remainingComplements.get(0));
				return nowUsingPrimes;
			}
			ArrayList<Integer> tmp = chooseComplements(remainingComplements,nowUsingPrimes);
			if (tmp != null)
				return tmp;
		}
		
		return null;
	}
	
	private ArrayList<Integer> filter(ArrayList<Integer> activeComplements,
			int complement) {
		ArrayList<Integer> remainingComplements = new ArrayList<Integer>();
		for (int activeComplement : activeComplements)
			if (complements.get(complement).contains(activeComplement))
				remainingComplements.add(activeComplement);
		return remainingComplements;
	}

	boolean sieve[] = ExtMath.sieve(100000000);
	private final int depth = 5;
	private HashMap<Integer,ArrayList<Integer>> complements = new HashMap<Integer,ArrayList<Integer>>();
}
