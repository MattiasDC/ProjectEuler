package projectEuler;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.javatuples.Pair;


public class Problem083 extends Problem {

	@Override
	protected String solve() {
		try {
			int[][] matrix = loadMatrix();

			int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

			ArrayList<Pair<Integer, Point>> startingPositions = new ArrayList<>();
			startingPositions.add(new Pair<Integer, Point>(matrix[0][0], new Point(0, 0)));

			HashSet<Point> endPositions = new HashSet<>();
			endPositions.add(new Point(matrix[0].length - 1, matrix.length - 1));

			return new Problem081().searchBestPathCost(matrix, directions, startingPositions,
					endPositions) + "";
		}
		catch (IOException exc) {
			return "An error has occured while reader from the file to load the matrix";
		}
	}

	private int[][] loadMatrix() throws IOException {
		int[][] matrix = new int[80][80];
		BufferedReader reader = new BufferedReader(new FileReader("Problem83Matrix"));
		String line;

		int i = 0, j = 0;
		while ((line = reader.readLine()) != null) {
			j = 0;
			for (String value : line.split(",")) {
				matrix[i][j] = Integer.parseInt(value);
				j++;
			}
			i++;
		}
		reader.close();
		return matrix;
	}

}
