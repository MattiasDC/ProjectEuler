package projectEuler;


public class Problem031 extends Problem {

	@Override
	protected String solve() {
		long answer = 1;	
		int sequence = 200;
		
		answer += getPossibilities(sequence, 1);
		return answer + ""; 
	}
	
	private long getPossibilities(int sequence, int last){
		long answer = 0;
		for (int coin : coins){
			if (sequence >= coin && last <= coin){
				answer++;
				answer += getPossibilities(sequence-coin, coin);
			}
		}
		return answer;
	}

	private final Integer[] coins = {2,5,10,20,50,100,200};
}