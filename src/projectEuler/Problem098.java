package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Problem098 extends Problem {

	@Override
	protected String solve() {
		long largestSquare = 0, tmp;
		ArrayList<String> words = readData();
		ArrayList<Pair<String, String>> anagramWords = findAnagramWords(words);
		HashMap<Multiset<Integer>, ArrayList<Long>> anagramSquares = findSquares(10000000000L);

		for (Pair<String, String> p : anagramWords) {
			if ((tmp = findMatches(p, anagramSquares)) > largestSquare) {
				largestSquare = tmp;
			}
		}

		return Long.toString(largestSquare);
	}

	private long findMatches(Pair<String, String> p, HashMap<Multiset<Integer>, ArrayList<Long>> anagramSquares) {
		// TODO Auto-generated method stub
		return 0;
	}

	private ArrayList<Pair<String, String>> findAnagramWords(ArrayList<String> words) {
		ArrayList<Pair<String, String>> anagrams = new ArrayList<>();
		ArrayList<Multiset<Character>> characterSets = new ArrayList<>();
		Multiset<Character> characterSet;
		for (String word : words) {
			characterSet = HashMultiset.create();
			for (char c : word.toCharArray()) {
				characterSet.add(new Character(c));
			}
			characterSets.add(characterSet);
		}

		for (int i = 0; i < characterSets.size(); i++) {
			characterSet = characterSets.get(i);
			for (int j = i + 1; j < characterSets.size(); j++) {
				if (characterSet.equals(characterSets.get(j))) {
					anagrams.add(new Pair<String, String>(words.get(i), words.get(j)));
				}
			}
		}

		return anagrams;
	}

	private HashMap<Multiset<Integer>, ArrayList<Long>> findSquares(long limit) {
		HashMap<Multiset<Integer>, ArrayList<Long>> squares = new HashMap<>();
		Multiset<Integer> digits;
		long square;
		for (long i = 1; (square = i * i) < limit; i++) {
			digits = HashMultiset.create(getDigits(square));
			if (!squares.containsKey(digits)) {
				squares.put(digits, new ArrayList<Long>());
			}
			squares.get(digits).add(square);
		}
		return squares;
	}

	private ArrayList<String> readData() {
		try {
			ArrayList<String> words = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader("Problem98Words"));
			String line = reader.readLine();
			for (String word : line.split("\",\"")) {
				words.add(word);
			}
			words.set(0, words.get(0).substring(1));
			words.set(words.size() - 1,
					words.get(words.size() - 1).substring(0, words.get(words.size() - 1).length() - 1));
			reader.close();
			return words;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
