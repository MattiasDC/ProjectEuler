package projectEuler;

import java.util.Arrays;
import java.util.HashMap;

public class Problem062 extends Problem {

	@Override
	protected String solve() {
		long cube;
		char[] chars;
		String charsS;
		HashMap<String, long[]> occurences = new HashMap<String,long[]>();
		
		for (int i = 1; true;i++){
			cube = (long) Math.pow(i, 3);
			chars = (cube+"").toCharArray();
			Arrays.sort(chars);
			charsS = new String(chars);
			if (occurences.get(charsS) == null){
				occurences.put(charsS, new long[2]);
				occurences.get(charsS)[0] = cube;
			}
			occurences.get(charsS)[1]++;
			if (occurences.get(charsS)[1] == 5)
				return occurences.get(charsS)[0]+"";
			
		}
	}
}
