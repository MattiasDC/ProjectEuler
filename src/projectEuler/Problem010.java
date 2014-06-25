package projectEuler;
import extMath.ExtMath;

public class Problem010 extends Problem {

//fast but alot of memory taken (would take up to 625mb of RAM for all numbers to 10 billion
	@Override
	protected String solve() {
		boolean[] sieve = new boolean[1000000];
		
		for (int i = 0; i < 1000000; i++){
			sieve[i] = true;
		}
		
		long sum = 2;
		
		for (int i = 0; i < 1000000; i++){
			if (sieve[i] == false) continue;
			if (ExtMath.isPrime(2*i + 3)){
				sum += 2*i + 3;
				for (int j = 1; i + j*(2*i + 3) < 1000000;j++){
					sieve[i + j*(2*i + 3)] = false;
				}
			}
		}
		
		return sum + "";
	}
	
// 	Without the sieve of Erastothenes (less memory but slower),
//
//
//	@Override
//	protected String solve() {
//
//		long sum = 2;
//		
//		for (int i = 3; i < 2000000; i += 2){
//			if (isPrime(i)) sum += i;
//		}
//		
//		return sum + "";
//	}
//
}
