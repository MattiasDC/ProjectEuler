package projectEuler;

import java.math.BigInteger;

public class Problem026 extends Problem {

	@Override
	protected String solve() {

		String numberS = "1";
		for (int i = 1; i < 1500; i++){
			numberS += "0";
		}

		BigInteger number = new BigInteger(numberS);

		int longestRepitivePart = 0;
		int answer = 1;

		for (int i = 2; i < 1000; i++){
			String sequence = number.divide(new BigInteger(i + "")).toString();
			boolean found = false;
			int startRepetitive = 0;
			int repetitivePartLength = 0;
			
			while (!found){
				found = true;
				String characters = sequence.substring(startRepetitive, startRepetitive + 3);
				String[] splittedSequence = sequence.split(characters);
				
				//had some problem with repitive parts with only 1 length. this solves that problem
				if (splittedSequence.length == 1 || splittedSequence.length == 0){
					found = true;
					repetitivePartLength = 1;
					continue;
				}
				
				repetitivePartLength = splittedSequence[1].length();
				
				for (int j = 2; j < splittedSequence.length-1;j++){
					if (splittedSequence[j].length() != repetitivePartLength && splittedSequence[j] != ""){
						found = false;
						break;
					}
				}
				if (splittedSequence.length == 2) found = false;
				startRepetitive++;
			}
			if (repetitivePartLength > longestRepitivePart){
				longestRepitivePart = repetitivePartLength;
				answer = i;
			}
		}

		return answer + "";
	}

}
