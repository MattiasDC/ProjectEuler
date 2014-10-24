package projectEuler;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Problem079 extends Problem {

	@Override
	protected String solve() {
		try {
			loadKeyLog();
			return searchMinimalPasscodeBruteForce() + "";
		}
		catch (IOException e) {
			return "There was an error while reading from the file";
		}
	}

	/*
	 * Bruteforce way, works with duplicate values (can be solved quicker if non
	 * duplicates is assumed (see forum))
	 */
	private long searchMinimalPasscodeBruteForce() {
		int[] passcode = new int[10];
		passcode[9] = 1;

		while (!isKeylogSatisfied(passcode)) {
			passcode = increasePassCode(passcode);
		}

		return mergeValues(passcode);
	}

	private int[] increasePassCode(int[] passcode) {
		for (int i = passcode.length - 1; i >= 0; i--) {
			if (passcode[i] < 9) {
				passcode[i] = passcode[i] + 1;
				break;
			}
			else {
				passcode[i] = 0;
			}
		}
		return passcode;
	}

	private boolean isKeylogSatisfied(int[] splittedPasscode) {
		int currentPointer;
		for (Integer[] key : keylog) {
			currentPointer = 0;
			for (int digit : key) {
				while (true) {
					if (currentPointer == splittedPasscode.length) {
						return false;
					}
					if (splittedPasscode[currentPointer] == digit) {
						currentPointer++;
						break;
					}
					currentPointer++;
				}
			}
		}
		return true;
	}

	private void loadKeyLog() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Problem79Keylog"));
		String line;

		Integer[] wrapper;
		int[] splittedValue;
		while ((line = reader.readLine()) != null) {
			splittedValue = splitValues(Integer.parseInt(line));
			wrapper = new Integer[splittedValue.length];
			for (int i = 0; i < splittedValue.length; i++) {
				wrapper[i] = splittedValue[i];
			}
			keylog.add(wrapper);
		}
		reader.close();
	}

	private int[] splitValues(long number) {
		int amountDigits = getNumberOfDigits(number);
		int[] splittedValues = new int[amountDigits];
		for (int i = 0; i < amountDigits; i++) {
			splittedValues[amountDigits - i - 1] = (int) (number % 10);
			number /= 10;
		}
		return splittedValues;
	}

	private long mergeValues(int[] splittedNumber) {
		long number = 0;
		for (int digit : splittedNumber) {
			number *= 10;
			number += digit;
		}
		return number;
	}

	private ArrayList<Integer[]> keylog = new ArrayList<Integer[]>();
	long splitTime = 0;

}
