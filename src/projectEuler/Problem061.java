package projectEuler;

import java.util.ArrayList;
import java.util.HashMap;

public class Problem061 extends Problem {

	@Override
	protected String solve() {
		int answer = 0, i = 1, octagonalNumber;
		ArrayList<Integer> cyclicNumbers = null;
		boolean[] usedPolygons = new boolean[6];
		usedPolygons[5] = true;

		while ((octagonalNumber = makeOctagonal(i)) < 10000 && cyclicNumbers == null){
			while (getNumberOfDigits(octagonalNumber) != 4)
				octagonalNumber = makeOctagonal(++i);
			cyclicNumbers = new ArrayList<Integer>();
			cyclicNumbers.add(octagonalNumber);
			cyclicNumbers = getCyclicNumbers(octagonalNumber,cyclicNumbers, usedPolygons);

			i++;
		}

		for (int value : cyclicNumbers)
			answer += value;
		return answer + "";
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Integer> getCyclicNumbers(int x, ArrayList<Integer> cyclicNumbers, boolean[] usedPolygons){
		int lastTwoDigits = x % 100, tmp_nextNumber = lastTwoDigits*100 -1;
		if (lastTwoDigits < 10)
			return null;
		HashMap<Integer,ArrayList<Integer>> polygonAndType = new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<Integer> cloneNumbers;
		boolean[] cloneUsed;

		for (int i = 0; i < 6;i++)
			polygonAndType.put(i, new ArrayList<Integer>());

		//possible triangles
		while (!usedPolygons[0] && (tmp_nextNumber = getNextTriangle(tmp_nextNumber))/100 == lastTwoDigits)
			polygonAndType.get(0).add(tmp_nextNumber++);
		tmp_nextNumber = lastTwoDigits*100 -1;
		//possible squares
		while (!usedPolygons[1] && (tmp_nextNumber = getNextSquare(tmp_nextNumber))/100 == lastTwoDigits)
			polygonAndType.get(1).add(tmp_nextNumber++);
		tmp_nextNumber = lastTwoDigits*100 -1;
		//possible pentagons
		while (!usedPolygons[2] && (tmp_nextNumber = getNextPentagonal(tmp_nextNumber))/100 == lastTwoDigits)
			polygonAndType.get(2).add(tmp_nextNumber++);
		tmp_nextNumber = lastTwoDigits*100 -1;
		//possible hexagons
		while (!usedPolygons[3] && (tmp_nextNumber = getNextHexagonal(tmp_nextNumber))/100 == lastTwoDigits)
			polygonAndType.get(3).add(tmp_nextNumber++);
		tmp_nextNumber = lastTwoDigits*100 -1;
		//possible heptagons
		while (!usedPolygons[4] && (tmp_nextNumber = getNextHeptagonal(tmp_nextNumber))/100 == lastTwoDigits)
			polygonAndType.get(4).add(tmp_nextNumber++);
		tmp_nextNumber = lastTwoDigits*100 -1;

		ArrayList<Integer> tmp;

		for (int i = 0; i < 6; i++){
			for (int value : polygonAndType.get(i)){
				cloneNumbers = (ArrayList<Integer>) cyclicNumbers.clone();
				cloneUsed = usedPolygons.clone();
				cloneNumbers.add(value);
				cloneUsed[i] = true;

				boolean test = true;
				for (boolean b : cloneUsed)
					if (!b){
						test = false;
						break;
					}


				if (test && cloneNumbers.get(0)/100 == cloneNumbers.get(5) % 100)
					return cloneNumbers;
				else{
					if ((tmp = getCyclicNumbers(value, cloneNumbers, cloneUsed)) != null)
						return tmp;
				}
			}
		}

		return null;		
	}

	private int getNextTriangle(int x){
		return makeTriangle((int) (-1.0/2.0 + Math.sqrt(1.0/4 + 2.0*x)) +1);
	}

	private int getNextSquare(int x){
		return makeSquare((int) (Math.sqrt(x) + 1));
	}

	private int getNextPentagonal(int x){
		return makePentagonal((int) (1.0/2.0 + Math.sqrt(1.0/4.0 + 6.0*x)/3.0) +1);
	}

	private int getNextHexagonal(int x){
		return makeHexagonal((int) ((1.0 + Math.sqrt(1.0 + 8.0*x))/4.0) +1);
	}

	private int getNextHeptagonal(int x){
		return makeHeptagonal((int) ((3.0/10.0 + Math.sqrt(9.0/4.0 + 10.0*x)/5.0)) +1);
	}
}
