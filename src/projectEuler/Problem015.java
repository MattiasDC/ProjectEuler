package projectEuler;

public class Problem015 extends Problem{

	//this problem was originally bruteforced, took about a minute. Due to running time and HDD reset,
	//the bruteforce way has been changed to a simple mathematical solution
	
	@Override
	protected String solve() {
		return (getFaculty(40) / (getFaculty(20)*getFaculty(20))) + "";
	}
	
	private double getFaculty(int number){
		if (number == 1) return 1;
		else
			return number * getFaculty(number-1);
	}
}
