package projectEuler;


import java.util.ArrayList;
import java.util.Comparator;


public class Problem024 extends Problem {

	// this problem can be easily done by hand, but I wasn't smart enough.
	@Override
	protected String solve() {
		ArrayList<Character[]> permutations = getPermutations(new Character[] { '0', '1', '2', '3',
				'4', '5', '6', '7', '8', '9' }, new Comparator<Character>() {

					@Override
					public int compare(Character o1, Character o2) {
						return o1.compareTo(o2);
					}
				});
		
		StringBuilder sb = new StringBuilder();
		for (Character c : permutations.get(999999)) {
			sb.append(c);
		}
		return sb.toString();
	}

}
