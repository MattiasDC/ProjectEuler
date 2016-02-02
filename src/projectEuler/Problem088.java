package projectEuler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.javatuples.Pair;

import extMath.ExtMath;

public class Problem088 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		HashSet<Integer> already = new HashSet<>();

		for (int k = 2; k <= 12000; k++) {
			for (int i = k; i < Integer.MAX_VALUE; i++) {
				if (canForm(i, k)) {
					if (!already.contains(i)) {
						answer += i;
						already.add(i);
					}
					break;
				}
			}
		}

		return Integer.toString(answer);
	}

	HashMap<Pair<Integer, Integer>, Boolean> memo = new HashMap<>();

	private boolean canForm(int n, int k) {
		Pair<Integer, Integer> p = new Pair<Integer, Integer>(n, k);
		if (memo.containsKey(p)) {
			return memo.get(p);
		}
		ArrayList<Integer> divisors = ExtMath.getDivisors(n);
		int size = divisors.size();
		int tmp;
		boolean t;
		for (int i = 1; i < Math.round(divisors.size() / 2.0); i++) {
			tmp = n - divisors.get(i) - divisors.get(size - i - 1) + 2;
			if (tmp == k) {
				memo.put(p, true);
				return true;
			} else if (tmp < k) {
				t = canForm(divisors.get(i), k - tmp + 1);
				if (t) {
					memo.put(p, true);
					return true;
				}
				t = canForm(divisors.get(size - i - 1), k - tmp + 1);
				if (t) {
					memo.put(p, true);
					return true;
				}
			}
		}
		memo.put(p, false);
		return false;
	}
}
