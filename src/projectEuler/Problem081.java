package projectEuler;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.javatuples.Pair;


public class Problem081 extends Problem {

	@Override
	protected String solve() {
		try {
			int[][] matrix = loadMatrix();

			int[][] directions = new int[][] { { 0, 1 }, { 1, 0 } };

			ArrayList<Pair<Integer, Point>> startingPositions = new ArrayList<>();
			startingPositions.add(new Pair<Integer, Point>(matrix[0][0], new Point(0, 0)));

			HashSet<Point> endPositions = new HashSet<>();
			endPositions.add(new Point(matrix[0].length - 1, matrix.length - 1));

			return searchBestPathCost(matrix, directions, startingPositions, endPositions) + "";
		}
		catch (IOException e) {
			return "An error has occured while reader from the file to load the matrix";
		}
	}

	public int searchBestPathCost(int[][] matrix, int[][] directionsPermitted,
			Collection<Pair<Integer, Point>> startingPositions, Set<Point> endPositions) {

		Comparator<Pair<Integer, Point>> heuristicalComparator = new Comparator<Pair<Integer, Point>>(){

			@Override
			public int compare(Pair<Integer, Point> o1, Pair<Integer, Point> o2) {
				return o1.getValue0().compareTo(o2.getValue0());
			}
			
		};
		PriorityQueue<Pair<Integer, Point>> queue = new PriorityQueue<Pair<Integer, Point>>(1, heuristicalComparator);

		for (Pair<Integer, Point> startingPosition : startingPositions) {
			queue.add(startingPosition);
		}
		Pair<Integer, Point> parent;
		while (!endPositions.contains(queue.peek().getValue1())) {
			parent = queue.poll();
			queue.addAll(createChildren(parent.getValue0(), parent.getValue1(), matrix,
					directionsPermitted));
		}

		return queue.poll().getValue0();
	}

	private Collection<? extends Pair<Integer, Point>> createChildren(int cost, Point position,
			int[][] matrix, int[][] directions) {
		Collection<Pair<Integer, Point>> children = new ArrayList<>();
		Point newPosition;
		Pair<Integer, Point> child;
		for (int[] direction : directions) {
			newPosition = new Point((int) position.getX() + direction[1], (int) position.getY()
					+ direction[0]);
			if (newPosition.getX() < matrix[0].length && newPosition.getY() < matrix.length
					&& newPosition.getX() >= 0 && newPosition.getY() >= 0) {
				child = new Pair<Integer, Point>(cost
						+ matrix[(int) newPosition.getY()][(int) newPosition.getX()], newPosition);
				if (!visitedNodes.containsKey(child.getValue1())
						|| visitedNodes.get(child.getValue1()) > child.getValue0()) {
					children.add(child);
					visitedNodes.put(child.getValue1(), child.getValue0());
				}
			}
		}
		return children;
	}

	private int[][] loadMatrix() throws IOException {
		int[][] matrix = new int[80][80];
		BufferedReader reader = new BufferedReader(new FileReader("Problem81Matrix"));
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

	private HashMap<Point, Integer> visitedNodes = new HashMap<Point, Integer>();

}
