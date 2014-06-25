package projectEuler;

import java.util.HashSet;

public class Problem029 extends Problem{

	@Override
	protected String solve() {
		
		for (int i = 2; i <= 100; i++){
			for (int j = i; j <= 100; j++){
				addDistinctTerms(Math.pow(i, j));
				addDistinctTerms(Math.pow(j, i));
			}
		}
		
		return getDistinctTerms().size() + "";
		
	}
	
	public HashSet<Double> getDistinctTerms() {
		return distinctTerms;
	}

	public void addDistinctTerms(Double term) {
		if (term != null)
		distinctTerms.add(term);
	}
	
	private HashSet<Double> distinctTerms = new HashSet<Double>();

}
