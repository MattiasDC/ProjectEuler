package projectEuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Problem054 extends Problem {

	@Override
	protected String solve() {
		int answer = 0;
		try{
			getHandsFromFile();
		}catch (IOException e) {
			return "There was an error reading the file";
		}
		Cart[] carts, cart1 = new Cart[5], cart2 = new Cart[5];
		int score1,score2;
		while (!hands.isEmpty()){
			currentSubValueOfHand = -1;
			currentSubValueOfHandPrev = -1;
			carts = getCarts(hands.pop());
			for (int i = 0; i <= 4;i++)
				cart1[i] = carts[i];
			for (int i = 5; i <= 9;i++)
				cart2[i-5] = carts[i];
			Arrays.sort(cart1);
			Arrays.sort(cart2);
			if ((score1 = evaluateHand(cart1)) > (score2 = evaluateHand(cart2)))
				answer++;
			else if (score1 == score2){
				if (currentSubValueOfHandPrev > currentSubValueOfHand)
					answer++;
				else if (currentSubValueOfHandPrev == currentSubValueOfHand && isOverallHigher(cart1, cart2))
						answer++;
			}
		}

		return answer + "";
	}

	private Cart[] getCarts(String hands){
		Cart[] carts = new Cart[10];		
		int i = 0;
		for (String cart : hands.split(" ")){
			carts[i] = new Cart(cart);
			i++;
		}
		return carts;
	}

	private int evaluateHand(Cart[] carts){
		if (isRoyalFlush(carts))
			return 10;
		else if (isStraightFlush(carts))
			return 9;
		else if (isFourOfAKind(carts))
			return 8;
		else if (isFullHouse(carts))
			return 7;
		else if (isFlush(carts))
			return 6;
		else if (isStraight(carts))
			return 5;
		else if (isThreeOfAKind(carts))
			return 4;
		else if (isTwoPairs(carts))
			return 3;
		else if (isOnePair(carts))
			return 2;
		return 1;
	}

	private boolean isRoyalFlush(Cart[] carts){
		if (carts[0].value != 14 || !isFlush(carts) || !isStraight(carts))
			return false;
		return true;
	}

	private boolean isStraightFlush(Cart[] carts){
		if (isFlush(carts) && isStraight(carts))
			return true;
		return false;
	}

	private boolean isFourOfAKind(Cart[] carts){
		return isXPair(carts, 4);
	}

	private boolean isFullHouse(Cart[] carts){
		if (isThreeOfAKind(carts) && isTwoPairs(carts))
			return true;
		return false;
	}

	private boolean isFlush(Cart[] carts){
		Suit suit = carts[0].suit;
		for (int i = 1; i < carts.length;i++)
			if (carts[i].suit != suit)
				return false;
		return true;
	}

	private boolean isStraight(Cart[] carts){
		int value = carts[0].value;
		for (int i = 1; i < carts.length; i++)
			if (carts[i].value != value-i)
				return false;
		currentSubValueOfHandPrev = currentSubValueOfHand;
		currentSubValueOfHand = value;
		return true;
	}

	private boolean isThreeOfAKind(Cart[] carts){
		return isXPair(carts, 3);
	}

	private boolean isTwoPairs(Cart[] carts){
		int currentValue = carts[0].value, count = 1, pairs = 0, previousValue = -1;
		for (int i = 1; i < carts.length;i++){
			if (currentValue == carts[i].value && currentValue != previousValue)
				count++;
			else
				currentValue = carts[i].value;
			if (count == 2){
				pairs++;
				count = 1;
				if (pairs == 2){
					currentSubValueOfHandPrev = currentSubValueOfHand;
					currentSubValueOfHand = previousValue;
					return true;
				}
				previousValue = currentValue;
			}
		}
		return false;
	}

	private boolean isOnePair(Cart[] carts){
		return isXPair(carts, 2);
	}
	
	private boolean isXPair(Cart[] carts, int x){
		int currentValue = carts[0].value;
		int count = 1;
		for (int i = 1; i < carts.length;i++){
			if (currentValue == carts[i].value)
				count++;
			else{
				currentValue = carts[i].value;
				count = 1;
			}
			if (count == x){
				currentSubValueOfHandPrev = currentSubValueOfHand;
				currentSubValueOfHand = currentValue;
				return true;
			}
		}
		return false;
	}
	
	private boolean isOverallHigher(Cart[] cart1, Cart[] cart2) {
		int i = 0;
		while (cart1[i] == cart2[i])
			i++;
		if (cart1[i].compareTo(cart2[i]) < 1)
			return true;
		return false;
	}

	private void getHandsFromFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("Problem54Poker"));		
		String line;	
		while ((line = reader.readLine()) != null)
			hands.push(line);
	}

	private int currentSubValueOfHandPrev, currentSubValueOfHand;
	private Stack<String> hands = new Stack<String>();

	private class Cart implements Comparable<Cart>{
		public Cart(String cart){
			String number = cart.substring(0,1);
			if (number.equals("T"))
				this.value = 10;
			else if (number.equals("J"))
				this.value = 11;
			else if (number.equals("Q"))
				this.value = 12;
			else if (number.equals("K"))
				this.value = 13;
			else if (number.equals("A"))
				this.value = 14;
			else
				this.value = Integer.parseInt(number);
			suit = Suit.getSuit(cart.substring(1));
		}

		final int value;
		final Suit suit;
		
		@Override
		public int compareTo(Cart otherCart) {
			return otherCart.value-this.value;
		}
		
		public String toString(){
			return value+suit.toString();
		}
		
	}
	
	private enum Suit {
		DIAMONDS("D"), HEARTS("H"), SPADES("S"), CLUBS("C");
		
		String suit;
		
		private Suit(String suit){
			this.suit = suit;
		}
		
		public static Suit getSuit(String suit){
			if (suit.equals("D"))
				return Suit.DIAMONDS;
			else if (suit.equals("H"))
				return Suit.HEARTS;
			else if (suit.equals("S"))
				return Suit.SPADES;
			else if (suit.equals("C"))
				return Suit.CLUBS;
			return null;
		}
		
		public String toString(){
			return suit;
		}
	}
}
