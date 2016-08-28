package projectEuler;

import java.util.ArrayList;
import java.util.HashSet;

import extMath.ExtMath;

public class Problem095 extends Problem {

	HashSet<Long> memo = new HashSet<Long>();
	ArrayList<Long> currentChain;

	@Override
	protected String solve() {
		int longestChain = 0, currentChainLength;
		long smallestChainNumber = 0, currentChainNumber, currentMinimum;

		for (int i = 2; i < 1000000; i++) {
			currentChainNumber = i;
			currentMinimum = Long.MAX_VALUE;
			currentChain = new ArrayList<>();

			while (!currentChain.contains(currentChainNumber)) {
				if (memo.contains(currentChainNumber) || currentChainNumber == 1 || currentChainNumber > 1000000) {
					currentChain.clear();
					break;
				}
				currentChain.add(currentChainNumber);
				memo.add(currentChainNumber);
				// main bottleneck, can be improved by using a sieve up to
				// 1000000 (from thread)
				currentChainNumber = ExtMath.getSumDivisors(currentChainNumber) - currentChainNumber;
			}
			if (currentChain.size() > 0) {
				currentChainLength = currentChain.size() - currentChain.indexOf(currentChainNumber);
				if (currentChainLength > longestChain) {
					longestChain = currentChainLength;
					currentMinimum = Long.MAX_VALUE;
					for (int j = currentChain.indexOf(currentChainNumber); j < currentChain.size(); j++) {
						if (currentChain.get(j) < currentMinimum) {
							currentMinimum = currentChain.get(j);
						}
					}
					smallestChainNumber = currentMinimum;
				}
			}
		}
		return Long.toString(smallestChainNumber);
	}

}
