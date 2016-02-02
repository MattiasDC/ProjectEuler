package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import org.javatuples.Triplet;

import com.google.common.collect.Sets;

public class Problem096 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		ArrayList<ArrayList<ArrayList<Set<Integer>>>> sudokus = readData();
		sudokus = deducePossibilitiesAll(sudokus);

		for (ArrayList<ArrayList<Set<Integer>>> sudoku : sudokus) {
			answer += extractTopLeftCorner(solveSudoku(sudoku));
		}
		return Integer.toString(answer);
	}

	private ArrayList<ArrayList<ArrayList<Set<Integer>>>> deducePossibilitiesAll(
			ArrayList<ArrayList<ArrayList<Set<Integer>>>> sudokus) {

		for (ArrayList<ArrayList<Set<Integer>>> sudoku : sudokus) {
			sudoku = deducePossibilities(sudoku);
		}
		return sudokus;
	}

	private ArrayList<ArrayList<Set<Integer>>> deducePossibilities(ArrayList<ArrayList<Set<Integer>>> sudoku) {
		Set<Integer> allPos = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Set<Integer> remainingPos;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				remainingPos = sudoku.get(i).get(j);
				if (remainingPos.size() == 1) {
					continue;
				} else if (remainingPos.size() == 0) {
					sudoku.get(i).set(j, allPos);
					remainingPos = allPos;
				}
				remainingPos = Sets.difference(remainingPos, getRowFilledIn(sudoku, i));
				remainingPos = Sets.difference(remainingPos, getColumnFilledIn(sudoku, j));
				remainingPos = Sets.difference(remainingPos, getBlockFilledIn(i, j, sudoku));
				sudoku.get(i).set(j, remainingPos);
			}
		}
		return sudoku;
	}

	private Set<Integer> getRowFilledIn(ArrayList<ArrayList<Set<Integer>>> sudoku, int i) {
		Set<Integer> filledIn = new HashSet<Integer>();
		for (Set<Integer> e : sudoku.get(i)) {
			if (e.size() == 1) {
				filledIn.add(e.iterator().next());
			}
		}
		return filledIn;
	}

	private Set<Integer> getColumnFilledIn(ArrayList<ArrayList<Set<Integer>>> sudoku, int j) {
		Set<Integer> filledIn = new HashSet<Integer>();
		for (int i = 0; i < 9; i++) {
			if (sudoku.get(i).get(j).size() == 1) {
				filledIn.add(sudoku.get(i).get(j).iterator().next());
			}
		}
		return filledIn;
	}

	private Set<Integer> getBlockFilledIn(int i, int j, ArrayList<ArrayList<Set<Integer>>> sudoku) {
		int beginRow = (i / 3) * 3, beginColumn = (j / 3) * 3;
		Set<Integer> filledIn = new HashSet<Integer>();
		for (int k = beginRow; k < beginRow + 3; k++) {
			for (int l = beginColumn; l < beginColumn + 3; l++) {
				if (sudoku.get(k).get(l).size() == 1) {
					filledIn.add(sudoku.get(k).get(l).iterator().next());
				}
			}
		}
		return filledIn;
	}

	private ArrayList<ArrayList<Set<Integer>>> solveSudoku(ArrayList<ArrayList<Set<Integer>>> sudoku) {
		if (isSolved(sudoku)) {
			return sudoku;
		}
		if (isFilled(sudoku)) {
			return null;
		}
		PriorityQueue<Triplet<Integer, Integer, Set<Integer>>> queue = new PriorityQueue<Triplet<Integer, Integer, Set<Integer>>>(
				new Comparator<Triplet<Integer, Integer, Set<Integer>>>() {

					@Override
					public int compare(Triplet<Integer, Integer, Set<Integer>> o1,
							Triplet<Integer, Integer, Set<Integer>> o2) {
						return Integer.compare(o1.getValue2().size(), o2.getValue2().size());
					}
				});

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku.get(i).get(j).size() != 1) {
					queue.add(new Triplet<Integer, Integer, Set<Integer>>(i, j, sudoku.get(i).get(j)));
				}
			}
		}
		Triplet<Integer, Integer, Set<Integer>> place;
		place = queue.poll();
		if (place.getValue2().size() == 0) {
			return null;
		} else {
			ArrayList<ArrayList<Set<Integer>>> tmp;
			for (int p : place.getValue2()) {
				sudoku.get(place.getValue0()).set(place.getValue1(), Sets.newHashSet(p));
				tmp = deducePossibilities(deepCopy(sudoku));
				tmp = solveSudoku(tmp);
				if (tmp != null) {
					return tmp;
				}
			}
		}
		return null;
	}

	private ArrayList<ArrayList<Set<Integer>>> deepCopy(ArrayList<ArrayList<Set<Integer>>> sudoku) {
		ArrayList<ArrayList<Set<Integer>>> copySudoku = new ArrayList<>();
		ArrayList<Set<Integer>> copyRow;
		for (ArrayList<Set<Integer>> row : sudoku) {
			copyRow = new ArrayList<>();
			for (Set<Integer> pos : row) {
				copyRow.add(new HashSet<Integer>(pos));
			}
			copySudoku.add(copyRow);
		}
		return copySudoku;
	}

	private boolean isSolved(ArrayList<ArrayList<Set<Integer>>> sudoku) {
		if (!isFilled(sudoku)) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			if (getRowFilledIn(sudoku, i).size() != 9 || getColumnFilledIn(sudoku, i).size() != 9
					|| getBlockFilledIn((i / 3) * 3, (i % 3) * 3, sudoku).size() != 9) {
				return false;
			}
		}
		return true;
	}

	private boolean isFilled(ArrayList<ArrayList<Set<Integer>>> sudoku) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku.get(i).get(j).size() > 1) {
					return false;
				}
			}
		}
		return true;
	}

	private int extractTopLeftCorner(ArrayList<ArrayList<Set<Integer>>> sudoku) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum = sum * 10 + sudoku.get(0).get(i).iterator().next();
		}
		return sum;
	}

	private ArrayList<ArrayList<ArrayList<Set<Integer>>>> readData() {
		try {
			ArrayList<ArrayList<ArrayList<Set<Integer>>>> sudokus = new ArrayList<>();
			ArrayList<ArrayList<Set<Integer>>> sudoku;
			ArrayList<Set<Integer>> row;
			BufferedReader reader = new BufferedReader(new FileReader("Problem96Sudoku"));
			String line;
			String[] splittedLine;
			while ((line = reader.readLine()) != null) {
				sudoku = new ArrayList<>();
				for (int i = 0; i < 9; i++) {
					row = new ArrayList<>();
					line = reader.readLine();
					splittedLine = line.split("");
					for (int j = 0; j < 9; j++) {
						row.add(new HashSet<Integer>());
						if (Integer.valueOf(splittedLine[j]) != 0) {
							row.get(j).add(Integer.valueOf(splittedLine[j]));
						}
					}
					sudoku.add(row);
				}
				sudokus.add(sudoku);
			}
			reader.close();
			return sudokus;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
