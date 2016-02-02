package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.javatuples.Pair;

public class Problem099 extends Problem {

	// a^x > b^y => x > log_a(b^y) => x > y/log_b(a) => x*log(a) > y*log(b)
	@Override
	protected String solve() {
		ArrayList<Pair<Integer, Integer>> exponents = readData();

		Pair<Integer, Integer> maxExpo = new Pair<Integer, Integer>(1, 1);
		int maxLineNumber = 0;
		for (int i = 0; i < exponents.size(); i++) {
			if (maxExpo.getValue1() * Math.log(maxExpo.getValue0()) < exponents.get(i).getValue1()
					* Math.log(exponents.get(i).getValue0())) {
				maxExpo = exponents.get(i);
				maxLineNumber = i;
			}
		}

		return Integer.toString(maxLineNumber + 1);
	}

	private ArrayList<Pair<Integer, Integer>> readData() {
		try {
			ArrayList<Pair<Integer, Integer>> values = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader("Problem99Exponent"));
			String line;
			String[] splittedLine;
			while ((line = reader.readLine()) != null) {
				splittedLine = line.split(",");
				values.add(
						new Pair<Integer, Integer>(Integer.valueOf(splittedLine[0]), Integer.valueOf(splittedLine[1])));
			}
			reader.close();
			return values;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
