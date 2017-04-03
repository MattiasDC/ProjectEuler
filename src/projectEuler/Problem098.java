package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.javatuples.Pair;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Problem098 extends Problem {

	@Override
	protected String solve() {
		long tmp, length = -1, largestAnagramSquare = 0;
		ArrayList<String> words = readData();
		ArrayList<Pair<String, String>> anagramWords = findAnagramWords(words);

		int maxLength = 0;
		for (Pair<String, String> pair : anagramWords) {
			if (pair.getValue0().length() > maxLength) {
				maxLength = pair.getValue0().length();
			}
		}

		ArrayList<ArrayList<Long>> anagramSquares = filterSingles(findSquares((int) Math.pow(10, maxLength - 1)));
		for (ArrayList<Long> squareGroup : anagramSquares) {
			Collections.sort(squareGroup, Collections.reverseOrder());
		}
		Collections.sort(anagramSquares,
				(ArrayList<Long> l1, ArrayList<Long> l2) -> -1 * Long.compare(l1.get(0), l2.get(0)));

		Collections.sort(anagramWords, (Pair<String, String> p1, Pair<String, String> p2) -> -1
				* Integer.compare(p1.getValue0().length(), p2.getValue0().length()));
		for (Pair<String, String> p : anagramWords) {
			if (p.getValue0().length() < length) {
				break;
			}
			if ((tmp = findMatches(p, anagramSquares)) > largestAnagramSquare) {
				length = p.getValue0().length();
				largestAnagramSquare = tmp;
			}
		}
		return Long.toString(largestAnagramSquare);
	}

	private long findMatches(Pair<String, String> p, ArrayList<ArrayList<Long>> anagramSquares) {
		Long secondMatchSquare, largestSquareFoundSoFar = -1L;
		for (ArrayList<Long> squaresList : anagramSquares) {
			if (getNumberOfDigits(squaresList.get(0)) != p.getValue0().length()) {
				continue;
			}
			for (HashMap<Character, Integer> mapping : getMapping(p.getValue0(), squaresList)) {
				if ((secondMatchSquare = canMatch(p.getValue1(), mapping, squaresList)) != null
						&& getNumberOfDigits(getValueFromMapping(mapping, p.getValue0())) == p.getValue0().length()
						&& getNumberOfDigits(secondMatchSquare) == p.getValue0().length()
						&& largestSquareFoundSoFar < Math.max(getValueFromMapping(mapping, p.getValue0()),
								secondMatchSquare)) {
					largestSquareFoundSoFar = Math.max(getValueFromMapping(mapping, p.getValue0()), secondMatchSquare);
				}
			}
		}
		return largestSquareFoundSoFar;
	}

	private long getValueFromMapping(HashMap<Character, Integer> mapping, String word) {
		long value = 0;
		for (int i = 0; i < word.length(); i++) {
			value = value * 10 + mapping.get(word.charAt(i));
		}
		return value;
	}

	private ArrayList<HashMap<Character, Integer>> getMapping(String word, ArrayList<Long> squaresList) {
		HashMap<Character, Integer> mapping;
		HashSet<Integer> usedDigits;
		ArrayList<Integer> digits;
		ArrayList<HashMap<Character, Integer>> solutions = new ArrayList<>();

		boolean found;
		for (Long square : squaresList) {
			digits = getDigits(square);
			mapping = new HashMap<Character, Integer>();
			usedDigits = new HashSet<Integer>();
			Collections.reverse(digits);
			found = true;
			for (int i = 0; i < word.length(); i++) {
				if (!mapping.containsKey(word.charAt(i)) && !usedDigits.contains(digits.get(i))) {
					mapping.put(word.charAt(i), digits.get(i));
					usedDigits.add(digits.get(i));
				} else if (mapping.get(word.charAt(i)) != digits.get(i)) {
					found = false;
					break;
				}
			}
			if (found) {
				solutions.add(mapping);
			}
		}
		return solutions;
	}

	private Long canMatch(String word, HashMap<Character, Integer> mapping, ArrayList<Long> squaresList) {
		ArrayList<Integer> digits;
		boolean found;
		for (Long square : squaresList) {
			found = true;
			digits = getDigits(square);
			Collections.reverse(digits);
			for (int i = 0; i < word.length(); i++) {
				if (mapping.get(word.charAt(i)) != digits.get(i)) {
					found = false;
					break;
				}
			}
			if (found) {
				return square;
			}
		}
		return null;
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

	private ArrayList<ArrayList<Long>> findSquares(long limit) {
		HashMap<Multiset<Integer>, ArrayList<Long>> squares = new HashMap<>();
		Multiset<Integer> digits;
		long square;
		for (long i = 1; (square = i * i) <= limit; i++) {
			digits = HashMultiset.create(getDigits(square));
			if (!squares.containsKey(digits)) {
				squares.put(digits, new ArrayList<Long>());
			}
			squares.get(digits).add(square);
		}
		return new ArrayList<>(squares.values());
	}

	private ArrayList<ArrayList<Long>> filterSingles(ArrayList<ArrayList<Long>> anagramSquares) {
		Iterator<ArrayList<Long>> iterator = anagramSquares.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().size() < 2) {
				iterator.remove();
			}
		}

		return anagramSquares;
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
