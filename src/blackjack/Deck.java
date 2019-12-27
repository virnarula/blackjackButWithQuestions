package blackjack;


/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Deck object. Contains 52 card objects and deal and shuffle methods.
 */
public class Deck 
{
	private Card[] deckC= new Card[52];
	private int currentCard;
	public Deck()
	{
		currentCard=0;
		Card temp;
		int index;
		for(int i=1;i<=13;i++)
		{
			for(int j=0;j<4;j++) 
			{
				temp= new Card(i,j);
				index=(int) (Math.random()*52);
				while(deckC[index]!=null)
				{
					index=(int) (Math.random()*52);
				}
				deckC[index]=temp;
			}
		}
		shuffle();
	}
	
	//Shuffles the deck by swapping all positions with another randomly generated position
	public void shuffle()
	{
		int ran;
		Card temp;
		for(int i=0;i<deckC.length;i++)
		{
			ran=(int)Math.random()*52;
			temp=deckC[i];
			deckC[i]=deckC[ran];
			deckC[ran]=temp;
		}
	}
	//Returns and card increases index value being returned as though cards were being delt. Automatically shuffles deck if too many cards have been delt.
	public Card deal()
	{
		Card c= deckC[currentCard];
		currentCard++;
		if(currentCard>45)
		{
			shuffle();
			currentCard=0;
		}
		return c;
	}
}
