package projectEuler;


import java.util.ArrayList;


public class Problem024 extends Problem {

	// this problem can be easily done by hand, but I wasn't smart enough.
	@Override
	protected String solve() {
		ArrayList<Character[]> permutations = getPermutations(new Character[] { '0', '1', '2', '3',
				'4', '5', '6', '7', '8', '9' }, Character::compareTo);
		StringBuilder sb = new StringBuilder();
		for (Character c : permutations.get(999999)) {
			sb.append(c);
		}
		return sb.toString();
	}

}
