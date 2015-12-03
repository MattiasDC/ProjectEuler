package projectEuler;

import java.util.HashSet;
import java.util.Iterator;

public class Problem032 extends Problem {

	@Override
	protected String solve() {
		long answer = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < 10;i++){
			for (int j = 1234; j < 9876; j++){
				builder.append(i);
				builder.append(j);
				builder.append(i*j);
				if (isPandigit(builder.toString())){
					getProducts().add(i*j);
				}
				builder.setLength(0);
			}
		}		
		for (int i = 12; i<100;i++){
			for (int j = 123; j < 987;j++){
				builder.append(i);
				builder.append(j);
				builder.append(i*j);
				if (isPandigit(builder.toString())){
					getProducts().add(i*j);
				}
				builder.setLength(0);
			}
		}
		
		Iterator<Integer> iterator = getProducts().iterator();
		
		while (iterator.hasNext()){
			answer += iterator.next();
		}
		return answer + "";
	}
	
	private HashSet<Integer> getProducts(){
		return products;
	}
	
	private HashSet<Integer> products = new HashSet<Integer>();
}