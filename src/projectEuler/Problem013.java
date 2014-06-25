package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

public class Problem013 extends Problem{
	
	//this can be done with string manipulation (done it before hard disk was wiped),
	//but is much easier with number classes

	@Override
	protected String solve() {
		try{
			getNumbersFromFile();
			
			BigInteger sum = BigInteger.ZERO;
			Iterator<BigInteger> iterator = getNumbers().iterator();
			
			while (iterator.hasNext()){
				sum = sum.add(iterator.next());
			}
			
			return sum.toString().substring(0, 10);
		} catch(IOException exc){
			return "There was an error while trying to read from a file.";
		}
	}
	
	private void getNumbersFromFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("Problem13Numbers"));
		
		String line;	
		while ((line = reader.readLine()) != null){
			getNumbers().add(new BigInteger(line));
		}
	}
	
	public HashSet<BigInteger> getNumbers() {
		return numbers;
	}

	public void setNumbers(HashSet<BigInteger> numbers) {
		this.numbers = numbers;
	}


	private HashSet<BigInteger> numbers = new HashSet<BigInteger>();
}

