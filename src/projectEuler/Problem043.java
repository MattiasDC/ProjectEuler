package projectEuler;

public class Problem043 extends Problem {

	@Override
	protected String solve() {
		long answer = 0;		
		initialize();

		int[] toUse = null;
		boolean[] usedValues = new boolean[10];
		for (int i = 1; i < 10;i++){
			boolean[] clone = usedValues.clone();
			clone[i] = true;
			answer += getValues(0,clone,toUse,i+"");
		}
		return answer + "";
	}

	int[] primes;
	private void initialize(){
		primes = new int[7];
		primes[0] = 2;
		primes[1] = 3;
		primes[2] = 5;
		primes[3] = 7;
		primes[4] = 11;
		primes[5] = 13;
		primes[6] = 17;
	}

	private long getValues(int depth,boolean[] usedValues,int[] toUse, String partialNumber){
		int prime = primes[depth];
		String part = partialNumber;
		long sum = 0;
		int maxValue,startValue,number;
		if (toUse == null){
			maxValue = 987;
			startValue = 1;
		}
		else{
			maxValue = 100*toUse[0] + 10*toUse[1]+10;
			startValue = (maxValue-20)/prime;
		}
		for (int i = startValue; (number = i*prime) < maxValue;i++){
			if (number < 10)
				continue;
			if (usedValues[getIthReverseNumber(number, 1)] || usedValues[getIthReverseNumber(number, 2)])
				continue;
			if (number >= 100 && usedValues[getIthReverseNumber(number, 3)])
				continue;
			if (toUse != null && ((number/100)%10 != toUse[0] || (number/10)%10 != toUse[1]))
				continue;
			if (depth != primes.length-1){
				boolean[] newUsedValues = usedValues.clone();
				int[] newToUse = {(number/10)%10, number%10};
				if (number >= 100)
					newUsedValues[number/100] = true;
				else if (newUsedValues[0])
					continue;
				else
					newUsedValues[0] = true;
				if (depth == 0)
					partialNumber = part + (number/100)%10 + "" + (number/10)%10;
				sum += getValues(depth+1, newUsedValues, newToUse,partialNumber + number%10);
			}
			else{
				if (number >= 100)
					usedValues[number/100] = true;
				else
					usedValues[0] = true;
				usedValues[number%10] = true;
				usedValues[number/10%10] = true;
				boolean value = true;
				for (int j = 0; j < 10;j++){
					if (usedValues[j] == false)
						value = false;
				}
				if (value)
					sum += Long.parseLong(partialNumber+number%10);
			}
		}


		return sum;
	}
}
