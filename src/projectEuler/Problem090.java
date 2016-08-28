package projectEuler;

import java.util.ArrayList;

public class Problem090 extends Problem {

	@Override
	protected String solve() {
		ArrayList<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();

		int answer = findSolutions(l1, l2) / 2;
		return Integer.toString(answer);
	}

	private int findSolutions(ArrayList<Integer> l1, ArrayList<Integer> l2) {
		if (l1.size() == 6 && l2.size() == 6) {
			if (constraintsSatisfied(l1, l2)) {
				return 1;
			} else {
				return 0;
			}
		}
		int solutions = 0;
		if (l1.size() < 6) {
			for (int i = 0; i < 10; i++) {
				if (l1.contains(i) || (l1.size() > 0 && l1.get(l1.size() - 1) > i)) {
					continue;
				}
				l1.add(i);
				solutions += findSolutions(l1, l2);
				l1.remove(l1.size() - 1);
			}
		}
		if (l1.size() == 6 && l2.size() < 6) {
			for (int i = 0; i < 10; i++) {
				if (l2.contains(i) || (l2.size() > 0 && l2.get(l2.size() - 1) > i)) {
					continue;
				}
				l2.add(i);
				solutions += findSolutions(l1, l2);
				l2.remove(l2.size() - 1);
			}
		}

		return solutions;

	}

	private boolean constraintsSatisfied(ArrayList<Integer> l1, ArrayList<Integer> l2) {
		return (l1.contains(0) && l2.contains(1) || l1.contains(1) && l2.contains(0))
				&& (l1.contains(0) && l2.contains(4) || l1.contains(4) && l2.contains(0))
				&& (l1.contains(0) && (l2.contains(9) || l2.contains(6))
						|| (l1.contains(9) || l1.contains(6)) && l2.contains(0))
				&& (l1.contains(1) && (l2.contains(9) || l2.contains(6))
						|| (l1.contains(9) || l1.contains(6)) && l2.contains(1))
				&& (l1.contains(2) && l2.contains(5) || l1.contains(5) && l2.contains(2))
				&& (l1.contains(3) && (l2.contains(9) || l2.contains(6))
						|| (l1.contains(9) || l1.contains(6)) && l2.contains(3))
				&& (l1.contains(4) && (l2.contains(9) || l2.contains(6))
						|| (l1.contains(9) || l1.contains(6)) && l2.contains(4))
				&& (l1.contains(8) && l2.contains(1) || l1.contains(1) && l2.contains(8));
	}

}
