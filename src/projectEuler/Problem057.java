package projectEuler;
import java.math.BigInteger;
import extMath.*;

public class Problem057 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		BigFraction previousFraction = new BigFraction(new BigInteger(3+""),new BigInteger(2+""));
		BigInteger two = new BigInteger(2+"");
		for (int i = 2; i <= 1000;i++){
			//IMPORTANT: THERE ARE TWO POSSIBLE WAYS TO CALCULATE THE NEXT ITERATION, I USED THE
			//COMMENTED ONE. IT RUNS IN 800MS. THE OTHER ONE IS MUCH QUICKER AND RUNS IN 100MS AND
			//USES THE FACT THAT THE NEW NUMERATOR IS TWICE THE PREVIOUS DENOMINATOR PLUS
			//THE PREVIOUS NUMERATOR. THE NEW DENOMINATOR IS THE SUM OF THE PREVIOUS DENOMINATOR AND
			//THE PREVIOUS NUMERATOR
			//previousFraction = new Fraction(BigInteger.ONE,BigInteger.ONE).sum(previousFraction.sum(new Fraction(BigInteger.ONE,BigInteger.ONE)).inverse());
			previousFraction = new BigFraction(previousFraction.denominator.multiply(two).add(previousFraction.numerator),previousFraction.denominator.add(previousFraction.numerator));
			if (previousFraction.numerator.toString().length() > previousFraction.denominator.toString().length())
					answer++;
		}
		
		return answer + "";
	}

}
