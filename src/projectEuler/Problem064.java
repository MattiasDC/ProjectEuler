package projectEuler;

import java.util.ArrayList;

public class Problem064 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		
		for (int i = 2; i <= 10000;i++)
			if (Math.sqrt(i)%1.0 != 0 && getPeriodLength(i) % 2 != 0)
				answer++;
		
		return answer + "";
	}

	private int getPeriodLength(int number) {
		ArrayList<Integer> period = new ArrayList<Integer>();
		double leftOver = Math.sqrt(number) % 1.0;
		while (!hasPeriod(period)){
			period.add((int)(1.0/leftOver));
			leftOver = (1.0/leftOver)%1.0;
		}
		System.out.println(number + "length: " + period);
		return period.size()/2;
	}

	private boolean hasPeriod(ArrayList<Integer> period) {
		int mid = period.size()/2;
		if (period.size() < 10)
			return false;
		for (int i = 0; i < mid;i++)
			if (period.get(i) != period.get(mid+i))
				return false;
		return true;
	}

}
