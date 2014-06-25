package projectEuler;

public class Problem038 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		
		String productsMerged = "";
		for (int i = 1; i < 10000;i++){
			int multiplier = 1;
			while (productsMerged.length() <= 9){
				productsMerged = productsMerged + (i*multiplier);
				if (multiplier > 1 && productsMerged.length() == 9 && containsAllDigits(productsMerged)){
					if (answer < Integer.parseInt(productsMerged)) answer = Integer.parseInt(productsMerged);
				}
				multiplier++;
			}
			productsMerged = "";
		}
		return answer + "";
	}
	
	private boolean containsAllDigits(String productsMerged) {
		for (int i = 1; i < 10;i++){
			if (!productsMerged.contains(i + "")) return false;
		}
		return true;
	}

}
