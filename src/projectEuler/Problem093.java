package projectEuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Problem093 extends Problem {

	HashMap<HashSet<Integer>, HashSet<Double>> possibilities = new HashMap<HashSet<Integer>, HashSet<Double>>();

	@Override
	protected String solve() {
		HashSet<Integer> startTuple;
		HashSet<Double> startArray;

		for (int i = 1; i < 10; i++) {
			startTuple = new HashSet<>();
			startTuple.add(i);
			startArray = new HashSet<>();
			startArray.add((double) i);
			possibilities.put(startTuple, startArray);
			findPossibilities();
		}

		int maxCount = 0, currentCount;
		HashSet<Integer> bestSet = null;
		for (HashSet<Integer> set : possibilities.keySet()) {
			currentCount = findConsecutiveLength(possibilities.get(set));
			if (maxCount < currentCount) {
				maxCount = currentCount;
				bestSet = set;
			}
		}
		ArrayList<Integer> bestArray = new ArrayList<>(bestSet);
		Collections.sort(bestArray);
		StringBuilder answer = new StringBuilder();
		for (int v : bestArray) {
			answer.append(v);
		}
		return answer.toString();
	}

	// DP solution, starting with possible outcomes of tuples with 1 element
	// (number itself), expanding to tuples with 2 elements, ...
	private void findPossibilities() {
		boolean changed = true;
		ArrayList<HashSet<Integer>> iterator1, iterator2;
		HashSet<Integer> ctuple, t1, t2;
		int prevSize;
		while (changed) {
			changed = false;
			iterator1 = new ArrayList<HashSet<Integer>>(this.possibilities.keySet());
			for (int i = 0; i < iterator1.size(); i++) {
				t1 = iterator1.get(i);
				iterator2 = new ArrayList<HashSet<Integer>>(this.possibilities.keySet());
				for (int j = i + 1; j < iterator2.size(); j++) {
					t2 = iterator2.get(j);
					if (t1.size() + t2.size() > 4 || containsADuplicate(t1, t2)) {
						continue;
					}
					ctuple = combineTuples(t1, t2);
					if (!this.possibilities.containsKey(ctuple)) {
						this.possibilities.put(ctuple, new HashSet<Double>());
					}
					prevSize = this.possibilities.get(ctuple).size();
					for (Double v1 : this.possibilities.get(t1)) {
						for (Double v2 : this.possibilities.get(t2)) {
							this.possibilities.get(ctuple).add(v1 + v2);
							this.possibilities.get(ctuple).add(v1 - v2);
							this.possibilities.get(ctuple).add(v2 - v1);
							this.possibilities.get(ctuple).add(v1 * v2);
							if (v2 != 0)
								this.possibilities.get(ctuple).add(v1 / (double) v2);
							if (v1 != 0)
								this.possibilities.get(ctuple).add(v2 / (double) v1);
						}
					}
					if (prevSize < this.possibilities.get(ctuple).size()) {
						changed = true;
					}
				}
			}
		}
	}

	private boolean containsADuplicate(HashSet<Integer> t1, HashSet<Integer> t2) {
		for (Integer v : t1) {
			if (t2.contains(v)) {
				return true;
			}
		}
		return false;
	}

	private HashSet<Integer> combineTuples(HashSet<Integer> t1, HashSet<Integer> t2) {
		HashSet<Integer> combined = new HashSet<Integer>(t1);
		combined.addAll(t2);
		return combined;
	}

	private int findConsecutiveLength(HashSet<Double> set) {
		ArrayList<Double> sortedPossibilities = new ArrayList<>(set);
		Collections.sort(sortedPossibilities);

		sortedPossibilities = deleteDoubles(sortedPossibilities);

		int index = 0;
		while (sortedPossibilities.get(index) <= 0) {
			index++;
		}

		int sequenceCount = 0;
		int shouldOccurNow = 1;
		while (index < sortedPossibilities.size()) {
			if (shouldOccurNow == sortedPossibilities.get(index)) {
				sequenceCount++;
				shouldOccurNow++;
			} else {
				break;
			}
			index++;
		}
		return sequenceCount;
	}

	private ArrayList<Double> deleteDoubles(ArrayList<Double> sortedPossibilities) {
		ArrayList<Double> newArray = new ArrayList<>();
		for (Double v : sortedPossibilities) {
			if (v % 1.0 == 0) {
				newArray.add(v);
			}
		}
		return newArray;
	}
}
