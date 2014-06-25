package projectEuler;

public class Problem044 extends Problem{

	@Override
	protected String solve() {
		int answer = 0;
		
		for (int i = 1;true;i++){
			for (int j = i-1;j > 0;j--){
				int a = makePentagonal(i);
				int b = makePentagonal(j);
				if (isPentagonal(a-b) && isPentagonal(a+b)){
					answer = a-b;
					return answer + "";
				}
			}
		}
	}
	
	/*
	 * double D_r = 0,D_q,value;
		boolean n_jump = false;
		boolean k_jump = false;
		boolean found = false;
		
		for (int i = 5;!found;i += 6){
			D_r = i*i;
			n_jump = false;
			for (int n = 2;!n_jump;n++){
				k_jump = false;
				for (int k = n-1;k > 0 && !k_jump;k--){
					if ((value = 1+36*(n*n-k*k)-12*(n-k)) > D_r){
						k_jump = true;
						if (k == n-1)
							n_jump = true;
					}
					else if (value == D_r){
						D_q = 1+36*(n*n+k*k)-12*(n+k);
						if ((1+Math.sqrt(D_q))/6 % 1 == 0){
							answer = (int) (((Math.sqrt(D_r)+1)/6)*(((Math.sqrt(D_r)+1)/2) -1)/2);
							System.out.println(n + "  " + k);
							found = true;
							k_jump = true;
							n_jump = true;
						}
					}
				}
			}
		}
	 */
}
