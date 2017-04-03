package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import extMath.ExtMath;

public class Problem102 extends Problem {

	@Override
	protected String solve() {
		// solved using barycentric coordinates
		int containingOrigin = 0;
		ArrayList<Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>>> triangles = readData();

		double[] xCoordinates, yCoordinates, ones = new double[] { 1, 1, 1 };
		double[][] a;
		double[][] b = new double[][] { new double[] { 0 }, new double[] { 0 }, new double[] { 1 } };
		double[][] sol;
		for (Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>> triangle : triangles) {
			xCoordinates = new double[3];
			xCoordinates[0] = triangle.getValue0().getValue0();
			xCoordinates[1] = triangle.getValue1().getValue0();
			xCoordinates[2] = triangle.getValue2().getValue0();
			yCoordinates = new double[3];
			yCoordinates[0] = triangle.getValue0().getValue1();
			yCoordinates[1] = triangle.getValue1().getValue1();
			yCoordinates[2] = triangle.getValue2().getValue1();
			a = new double[][] { xCoordinates, yCoordinates, ones };
			sol = ExtMath.solveSystem(a, b);
			if (sol[0][0] > 0 && sol[0][0] < 1 && sol[1][0] > 0 && sol[1][0] < 1 && sol[2][0] > 0 && sol[2][0] < 1) {
				containingOrigin++;
			}
		}

		return Integer.toString(containingOrigin);
	}

	private ArrayList<Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>>> readData() {
		try {
			ArrayList<Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>>> values = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader("Problem102Triangles"));
			String line;
			String[] splittedLine;
			while ((line = reader.readLine()) != null) {
				splittedLine = line.split(",");
				values.add(new Triplet<Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>>(
						new Pair<Integer, Integer>(Integer.valueOf(splittedLine[0]), Integer.valueOf(splittedLine[1])),
						new Pair<Integer, Integer>(Integer.valueOf(splittedLine[2]), Integer.valueOf(splittedLine[3])),
						new Pair<Integer, Integer>(Integer.valueOf(splittedLine[4]),
								Integer.valueOf(splittedLine[5]))));
			}
			reader.close();
			return values;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
