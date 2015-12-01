package projectEuler;

public class Problem085 extends Problem {

	@Override
	protected String solve() {
		int goal = 2000000;
		int closestToGoal = 0;
		int dimx = 0, dimy = 0; 
	
		for (int i = 1; i < 100; i++) {
			for (int j = 1; j < i; j++) {
				if (Math.abs(makeTriangle(i)*makeTriangle(j)-goal) < Math.abs(closestToGoal-goal)) {
					closestToGoal = makeTriangle(i)*makeTriangle(j);
					dimx = i;
					dimy = j;
				}
			}
		}
		return Integer.toString(dimy*dimx);
	}

}
