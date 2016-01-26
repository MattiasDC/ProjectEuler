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

		char prevSign = 0;
		for (char c : roman.toCharArray()) {
		}
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
