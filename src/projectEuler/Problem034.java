package projectEuler;

import java.util.HashMap;
import extMath.ExtMath;

public class Problem034 extends Problem {

	@Override
	protected String solve() {
		initializeFaculties();
		long answer = 0;
		
		//the normal upper bound would be 9999999 since 7* 9! is smaller than this number,
		//the distance will only increase for each extra number
		for (int i = 3; i < 50000;i++){
			int sumFaculties = 0;
			int numberOfDigits = getNumberOfDigits(i);
			for (int j = 1; j <= numberOfDigits && sumFaculties <= i;j++){
				sumFaculties += getFacultyValues().get(getIthReverseNumber(i, j));
			}
			if (sumFaculties == i) answer += i;
		}

		return answer + "";
	}

	private void initializeFaculties(){
		setFacultyValues(new HashMap<Integer,Long>());
		for (int i = 0; i < 10;i++){
			getFacultyValues().put(i, (long) ExtMath.getFaculty(i));
		}
	}
	
	
	private HashMap<Integer, Long> getFacultyValues() {
		return facultyValues;
	}

	private void setFacultyValues(HashMap<Integer, Long> facultyValues) {
		this.facultyValues = facultyValues;
	}

	private HashMap<Integer,Long> facultyValues;
}
