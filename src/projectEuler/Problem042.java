package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Problem042 extends Problem {

	@Override
	protected String solve() {
		try {
			int answer = 0;
			readWordsFromFile();
			
			for (String word: words){
				if (isTriangle(getWordValue(word))) answer++;
			}
			
			return answer + "";
		} catch (IOException e) {
			return "There was an error while reading the file";
		}
	}
	
	private int getWordValue(String word){
		int wordValue = 0;
		for (int value : word.toLowerCase().toCharArray()){
			wordValue += value - 97 +1;
		}
		return wordValue;
	}
	
	private void readWordsFromFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("Problem42Words"));

		String line;	
		while ((line = reader.readLine()) != null){
			words = line.replace("\"","").split(",");
		}
	}
	
	private String[] words;
}
