package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Problem018 extends Problem {

	@Override
	protected String solve() {
		try {
			getNumbersFromFile();
			
			for (int i = getTriangle().size()-2; i >= 0; i--){
				for (int j = 0; j < getTriangle().get(i).size();j++){
					int sum = getTriangle().get(i).get(j);
					if (getTriangle().get(i+1).get(j) > getTriangle().get(i+1).get(j+1))
						sum += getTriangle().get(i+1).get(j);
					else sum += getTriangle().get(i+1).get(j+1);

					getTriangle().get(i).set(j, sum);
				}
			}

			return getTriangle().get(0).get(0) + "";
		} catch (IOException e) {
			return "There was a problem with reading the file";
		}
	}

	private void getNumbersFromFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("Problem18Triangle"));

		String line;	
		while ((line = reader.readLine()) != null){
			String[] numbers = line.split(" ");
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int i = 0; i < numbers.length; i++){
				row.add(Integer.parseInt(numbers[i]));
			}
			getTriangle().add(row);
		}
	}

	private ArrayList<ArrayList<Integer>> getTriangle() {
		return triangle;
	}

	private ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
}
