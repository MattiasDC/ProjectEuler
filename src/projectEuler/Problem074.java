package projectEuler;


import java.util.ArrayList;
import java.util.HashMap;

import extMath.ExtMath;


public class Problem074 extends Problem {

	@Override
	protected String solve() {
		int amount = 0;

		for (int i = 1; i < 1000000; i++) {
			if (amntNRepeatingTerms(i) == 60) {
				amount++;
			}
		}

		return amount + "";
	}

	private int amntNRepeatingTerms(int number) {
		ArrayList<Integer> foundTerms = new ArrayList<Integer>();
		int foundTerm = number, amntTerms = 0;

		while (!foundTerms.contains(foundTerm) && !dynamicInfo.containsKey(foundTerm)) {
			foundTerms.add(foundTerm);
			foundTerm = getNextNumber(foundTerm);
		}

		if (dynamicInfo.containsKey(foundTerm)) {
			amntTerms = dynamicInfo.get(foundTerm);
		}

		amntTerms += foundTerms.size();

		for (int term : foundTerms) {
			if (!dynamicInfo.containsKey(term)) {
				dynamicInfo.put(term, amntTerms);
			}
			amntTerms--;
		}
		return dynamicInfo.get(number);
	}

	private int getNextNumber(int number) {
		ArrayList<Integer> digits = getDigits(number);
		int nextNumber = 0;

		for (int digit : digits) {
			nextNumber += ExtMath.getFaculty(digit);
		}
		return nextNumber;
	}

	HashMap<Integer, Integer> dynamicInfo = new HashMap<Integer, Integer>();
}
