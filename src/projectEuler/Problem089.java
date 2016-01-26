package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Problem089 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;

		for (String roman : readData()) {
			answer += roman.length() - toRoman(romanValue(roman)).length();
		}

		return Integer.toString(answer);
	}

	private int romanValue(String roman) {
		int value = 0;

		return value;
	}

	private String toRoman(int value) {
		StringBuilder roman = new StringBuilder();

		return roman.toString();
	}

	private ArrayList<String> readData() {
		try {
			ArrayList<String> romans = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader("Problem89Roman.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				romans.add(line);
			}
			return romans;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
