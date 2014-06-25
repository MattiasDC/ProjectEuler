package projectEuler;

import java.util.ArrayList;

public class Problem024 extends Problem {

	//this problem can be easily done by hand, but I wasn't smart enough.
	@Override
	protected String solve() {

		ArrayList<String> permutations = getPermutations("0123456789");
		return permutations.get(999999);
	}

}
