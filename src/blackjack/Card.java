package blackjack;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Card Object. Stores value and suit.
 */

public class Card 
{
	//0 is clubs, 1 is diamonds, 2 is hearts, 3 is spades
	int value,suit;
	String name;
	public Card(int value, int suit) {
		this.value = value;
		this.suit = suit;
		switch (suit)
		{
		case 0:
			name="clubs";
		case 1:
			name="diamonds";
		case 2:
			name="hearts";
		case 3:
			name="spades";
		}
		
	}
	public int getValue() {
		return value;
	}
	public int getSuit() {
		return suit;
	}
	public String getName() {
		return name;
	}
	
}