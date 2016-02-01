package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Problem089 extends Problem {

	Map<Character, Integer> signs = new ImmutableMap.Builder<Character, Integer>().put('I', 1).put('V', 5).put('X', 10)
			.put('L', 50).put('C', 100).put('D', 500).put('M', 1000).build();

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

		int prevValue = Integer.MAX_VALUE;
		int prevSignTimes = 0;
		for (char c : roman.toCharArray()) {
			if (signs.get(c) > prevValue) {
				value -= 2 * (prevValue * prevSignTimes);
			}
			value += signs.get(c);
			if (prevValue != signs.get(c)) {
				prevSignTimes = 0;
				prevValue = signs.get(c);
			}
			prevSignTimes++;
		}
		return value;
	}

	private String toRoman(int value) {
		StringBuilder roman = new StringBuilder();

		while (value >= 1000) {
			roman.append('Ḿ');
			value -= 1000;
		}
		if (value >= 900) {
			roman.append('C');
			roman.append('M');
			value -= 900;
		}
		if (value >= 500) {
			roman.append('D');
			value -= 500;
		}
		if (value >= 400) {
			roman.append('C');
			roman.append('D');
			value -= 400;
		}
		while (value >= 100) {
			roman.append('C');
			value -= 100;
		}
		if (value >= 90) {
			roman.append('X');
			roman.append('C');
			value -= 90;
		}
		if (value >= 50) {
			roman.append('L');
			value -= 50;
		}
		if (value >= 40) {
			roman.append('X');
			roman.append('L');
			value -= 40;
		}
		while (value >= 10) {
			roman.append('X');
			value -= 10;
		}
		if (value == 9) {
			roman.append('I');
			roman.append('X');
			value -= 9;
		}
		if (value >= 5) {
			roman.append('V');
			value -= 5;
		}
		if (value == 4) {
			roman.append('I');
			roman.append('V');
			value -= 4;
		}
		while (value > 0) {
			roman.append('I');
			value -= 1;
		}

		return roman.toString();
	}

	private ArrayList<String> readData() {
		try {
			ArrayList<String> romans = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader("Problem89Roman"));
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
