package blackjack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Screen where the actual game is played. Takes a Game as an argument and updates all values as game is played.
 */

public class Gameplay extends JFrame {

	private static JPanel contentPane;
	private static JTextField tfBet;
	private static int bet;
	private static int roundsPlayed;
	private static JLabel lblUpdate,dealerVal,dealersHand,currentVal,lblDealersCards,lblYourCards,lblRemainingMoney,remainingMoneyN,lblBet,deckPic,lblBiggestWins,lblHighScore;
	private static JButton btnHit,btnStay,btnDouble,btnPlay;
	private static ArrayList<Card> dealersCards=new ArrayList<Card>(), playersCards=new ArrayList<Card>();
	private static JLabel lblUpdates;
	private static JLabel label;
	private static JLabel[] userCardImg= new JLabel[4], dealerCardImg= new JLabel[4];
	private static Game Game;
	private static Deck deck= new Deck();
	private static int answer;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gameplay frame = new Gameplay(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gameplay(Game Game) {
		this.Game=Game;
		setTitle("Game Play");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("<- Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(31, 19, 117, 29);
		contentPane.add(btnBack);

		for(int i=0;i<4;i++)
		{
			dealerCardImg[i]=new JLabel("");
			userCardImg[i]=new JLabel("");
			dealerCardImg[i].setBounds((490+110*i), 116, 100, 150);
			contentPane.add(dealerCardImg[i]);
			userCardImg[i].setBounds((490+110*i), 348, 100, 150);
			contentPane.add(userCardImg[i]);
		}
		clearImages();
		dealerCardImg[0].setIcon(new ImageIcon(Gameplay.class.getResource("/cards/purple_back.png")));
		dealerCardImg[1].setIcon(new ImageIcon(Gameplay.class.getResource("/cards/purple_back.png")));
		userCardImg[0].setIcon(new ImageIcon(Gameplay.class.getResource("/cards/purple_back.png")));
		userCardImg[1].setIcon(new ImageIcon(Gameplay.class.getResource("/cards/purple_back.png")));

		lblDealersCards = new JLabel("Dealer's Cards");
		lblDealersCards.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealersCards.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblDealersCards.setBounds(490, 56, 212, 35);
		contentPane.add(lblDealersCards);

		lblYourCards = new JLabel("Your Cards");
		lblYourCards.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourCards.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblYourCards.setBounds(490, 301, 212, 35);
		contentPane.add(lblYourCards);

		btnHit = new JButton("Hit");
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				hitQuiz();
			}
		});
		btnHit.setBounds(404, 527, 117, 29);
		contentPane.add(btnHit);

		btnStay = new JButton("Stay");
		btnStay.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				roundEnd();
			}
		});
		btnStay.setBounds(533, 527, 117, 29);
		contentPane.add(btnStay);

		btnDouble = new JButton("Double");
		btnDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				doubling();
			}
		});
		btnDouble.setBounds(670, 527, 117, 29);
		contentPane.add(btnDouble);

		lblRemainingMoney = new JLabel("Remaining Money");
		lblRemainingMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemainingMoney.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblRemainingMoney.setBounds(68, 60, 264, 35);
		contentPane.add(lblRemainingMoney);

		remainingMoneyN = new JLabel(String.valueOf(Game.getMoney()));
		remainingMoneyN.setHorizontalAlignment(SwingConstants.CENTER);
		remainingMoneyN.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		remainingMoneyN.setBounds(179, 110, 61, 16);
		contentPane.add(remainingMoneyN);

		lblBet = new JLabel("Bet");
		lblBet.setHorizontalAlignment(SwingConstants.CENTER);
		lblBet.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblBet.setBounds(81, 156, 264, 35);
		contentPane.add(lblBet);

		tfBet = new JTextField();
		tfBet.setText("1000");
		tfBet.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		tfBet.setHorizontalAlignment(SwingConstants.CENTER);
		tfBet.setBounds(91, 208, 243, 29);
		contentPane.add(tfBet);
		tfBet.setColumns(10);

		btnPlay = new JButton("Play!");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bet=Integer.valueOf(tfBet.getText());
					if(bet<=Game.getMoney() && bet>0)
					{
						clearImages();
						playRound(bet);
						deal();
					}
					else
						lblUpdate.setText("You need to enter a valid value");
				}

				catch(Exception e1)
				{
					lblUpdate.setText("That is not a number");
				}

			}});
		btnPlay.setBounds(153, 245, 117, 29);
		contentPane.add(btnPlay);

		deckPic = new JLabel("");
		deckPic.setIcon(new ImageIcon(Gameplay.class.getResource("/cards/deck.png")));
		deckPic.setBounds(1038, 23, 143, 143);
		contentPane.add(deckPic);

		lblBiggestWins = new JLabel("Biggest Wins");
		lblBiggestWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiggestWins.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblBiggestWins.setBounds(784, 33, 212, 35);
		contentPane.add(lblBiggestWins);

		lblHighScore = new JLabel(String.valueOf(Game.getHighScore()));
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblHighScore.setBounds(876, 75, 61, 16);
		contentPane.add(lblHighScore);

		JLabel lblCurrentHand = new JLabel("Current Hand:");
		lblCurrentHand.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblCurrentHand.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentHand.setBounds(1050, 334, 131, 28);
		contentPane.add(lblCurrentHand);

		currentVal = new JLabel("0");
		currentVal.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		currentVal.setHorizontalAlignment(SwingConstants.CENTER);
		currentVal.setBounds(1050, 373, 131, 28);
		contentPane.add(currentVal);

		dealersHand = new JLabel("Dealers Hand:");
		dealersHand.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		dealersHand.setHorizontalAlignment(SwingConstants.CENTER);
		dealersHand.setBounds(1050, 236, 131, 28);
		contentPane.add(dealersHand);

		dealerVal = new JLabel("0");
		dealerVal.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		dealerVal.setHorizontalAlignment(SwingConstants.CENTER);
		dealerVal.setBounds(1050, 275, 131, 28);
		contentPane.add(dealerVal);

		lblUpdates = new JLabel("Updates");
		lblUpdates.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdates.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblUpdates.setBounds(120, 317, 212, 35);
		contentPane.add(lblUpdates);

		lblUpdate = new JLabel("");
		lblUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblUpdate.setVerticalAlignment(SwingConstants.TOP);
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setBounds(81, 373, 297, 150);
		contentPane.add(lblUpdate);

		betting();
	}
	//deals and displays cards at the start of a round
	public static void deal()
	{
		dealersCards.add(deck.deal());
		dealersCards.add(deck.deal());
		playersCards.add(deck.deal());
		playersCards.add(deck.deal());

		dealerCardImg[0].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(dealersCards.get(0)))));
		userCardImg[0].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(playersCards.get(0)))));
		userCardImg[1].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(playersCards.get(1)))));
		currentValue();
		ArrayList<Card> dealerCard= new ArrayList<Card>();
		dealerCard.add(dealersCards.get(0));
		dealerVal.setText(">"+String.valueOf(calculateHand(dealerCard)));

		checkBlackjack();
	}
	//runs code for when the user answers the quiz question correctly.
	public static void hitCorrect()
	{
		playersCards.add(deck.deal());
		currentValue();
		userCardImg[playersCards.size()-1].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(playersCards.get(playersCards.size()-1)))));
		resume();
		if(calculateHand(playersCards)>21 || playersCards.size()==4 || calculateHand(playersCards)==0)
		{
			roundEnd();
		}
		
	}
	//Runs code for when the user answers the quiz question incorrectly.
	public static void hitWrong()
	{
		roundEnd();
		lblUpdate.setText("You have answer incorrectly. You lose.");
	}
	//Creates question screen for quizbowl question when hitting
	public static void hitQuiz()
	{
		Question q = Home.questionList.get((int)(Math.random()*Home.questionList.size()));
		QuizQuestion q1= new QuizQuestion(q);
		q1.setAlwaysOnTop(true);
		q1.setVisible(true);
		suspend();
	}
	//Deals the user another card,doubles their bet,and ends the round
	public static void doubling()
	{
		bet*=2;
		playersCards.add(deck.deal());
		currentValue();
		tfBet.setText(String.valueOf(bet));
		userCardImg[playersCards.size()-1].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(playersCards.get(playersCards.size()-1)))));
		roundEnd();
	}
	//Acts upon the result of each round
	public static void roundEnd()
	{
		dealerVal.setText(String.valueOf(calculateHand(dealersCards)));
		dealersMove();
		for(int i=1;i<dealersCards.size();i++)
		{
			dealerCardImg[i].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(dealersCards.get(i)))));
		}
		int result=calculateHands(dealersCards, playersCards);
		switch(result)
		{
		case 0:
			win();
			break;
		case 1:
			lose();
			break;
		case 3:
			push();
			break;
		}
		clearHands();
		Home.writetoFile();
		if(!Game.isComplete())
		{
			betting();
		}
	}
	//performs the dealers moves at the end of the round
	public static void dealersMove()
	{
		while(dealersCards.size()<4 && calculateHand(dealersCards)<=15 && calculateHand(dealersCards)>0)
		{
			dealersCards.add(deck.deal());
			dealerVal.setText(String.valueOf(calculateHand(dealersCards)));
			//dealerCardImg[dealersCards.size()-1].setIcon(new ImageIcon(gamePlay.class.getResource(getCard(dealersCards.get(dealersCards.size()-1)))));
		}
	}
	//clears the images to the defaults
	public static void clearImages()
	{
		for(int i=0;i<4;i++)
		{
			if(i<2)
			{
				dealerCardImg[i].setIcon(new ImageIcon(Gameplay.class.getResource("/cards/purple_back.png")));
				userCardImg[i].setIcon(new ImageIcon(Gameplay.class.getResource("/cards/purple_back.png")));
			}
			else {
				dealerCardImg[i].setIcon(null);
				userCardImg[i].setIcon(null);
			}
		}
	}
	//Clears the hands of the dealer and the user
	public static void clearHands()
	{
		for(int i=0;i<dealersCards.size();)
		{
			dealersCards.remove(0);
		}
		for(int i=0;i<playersCards.size();)
		{
			playersCards.remove(0);
		}
	}
	//Used to make all appropriate buttons and textFields enabled or disabled for the betting part of the game
	public static void betting()
	{
		tfBet.setEnabled(true);
		btnPlay.setEnabled(true);
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		btnDouble.setEnabled(false);
	}
	//Used to make all appropriate buttons and textFields enabled or disabled for the playing part of the game
	public static void playRound(int bet)
	{
		tfBet.setEnabled(false);
		btnPlay.setEnabled(false);
		btnHit.setEnabled(true);
		btnStay.setEnabled(true);
		if(bet<=Game.getMoney()/2)
			btnDouble.setEnabled(true);
	}
	//Used when the player runs out of money
	public static void endGameSequence()
	{
		tfBet.setEnabled(false);
		btnPlay.setEnabled(false);
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		btnDouble.setEnabled(false);
		lblUpdate.setText("Game over! You are out of money!");
	}
	//Used to update money, highscore, and bets whena player has won a round.
	public static void win()
	{
		Game.setMoney(Game.getMoney()+bet);
		remainingMoneyN.setText(String.valueOf(Game.getMoney()));
		bet=0;
		tfBet.setText("0");
		if(Game.getMoney()>Game.getHighScore())
		{
			Game.setHighScore(Game.getMoney());
			lblHighScore.setText(String.valueOf(Game.getHighScore()));
		}
		lblUpdate.setText("You win!");

	}
	//Used to update money, highscore, and bets whena player has lost a round. Also checks if they are bankrupt
	public static void lose()
	{
		Game.setMoney(Game.getMoney()-bet);
		remainingMoneyN.setText(String.valueOf(Game.getMoney()));
		bet=0;
		tfBet.setText("0");
		lblUpdate.setText("The dealer wins");
		if(Game.getMoney()<=0)
		{
			Game.setComplete(true);
			endGameSequence();

		}
	}
	//used to update bet after a push
	public static void push()
	{
		bet=0;
		tfBet.setText("0");
		lblUpdate.setText("It is a push");
	}
	//Used to calculate the winner of a particular round
	public static int calculateHands(ArrayList<Card> dealer, ArrayList<Card> player)
	{
		//0 is a win for the player, 1 is a win for the dealer, 3 is a push
		int dealerVal=calculateHand(dealer), playerVal=calculateHand(player);
		if(dealerVal>playerVal)
			return 1;
		else if(dealerVal<playerVal)
			return 0;
		return 3;
	}
	//Acts in the case of a blackjack
	public static void checkBlackjack()
	{
		int result=resultBlackjack();
		if(result==1)
		{
			currentVal.setText("Blackjack!");
			dealerVal.setText(String.valueOf(calculateHand(dealersCards)));
			bet*=2;
			for(int i=1;i<dealersCards.size();i++)
				dealerCardImg[i].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(dealersCards.get(i)))));
			win();
			lblUpdate.setText("Blackjack! You win twice your bet!");
			clearHands();
			betting();
		}
		else if(result==2)
		{
			dealerVal.setText("Blackjack!");
			for(int i=1;i<dealersCards.size();i++)
				dealerCardImg[i].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(dealersCards.get(i)))));
			lose();
			lblUpdate.setText("Dealer got a blackjack. You automatically lose.");
			clearHands();
			betting();
		}
		else if(result==3)
		{
			dealerVal.setText("Blackjack!");
			currentVal.setText("Blackjack!");
			lblUpdate.setText("You and the dealer got blackjacks! No money exchanges hands.");
			for(int i=1;i<dealersCards.size();i++)
				dealerCardImg[i].setIcon(new ImageIcon(Gameplay.class.getResource(getCard(dealersCards.get(i)))));
			push();
			clearHands();
			betting();
		}
	}
	//returns the result in case one or more hands is a blackjack.
	public static int resultBlackjack()
	{
		//return 0 for no black jack, return 1 for player blackjack, return for dealer blackjack, return 3 for double blackjack
		boolean player =isBlackjack(playersCards),dealer=isBlackjack(dealersCards);
		if(!player && !dealer)
			return 0;
		else if(player && !dealer)
			return 1;
		else if(!player && dealer)
			return 2;
		else
			return 3;
	}
	//check if a hand is a blackjack
	public static boolean isBlackjack(ArrayList<Card> hand)
	{
		if(hand.size()==2 && ((hand.get(0).getValue()==1 && hand.get(1).getValue()>10) || (hand.get(0).getValue()>10 && hand.get(1).getValue()==1)))
		{
			return true;
		}
		return false;
	}
	//Used to calculate the value of a hand
	public static int calculateHand(ArrayList<Card> hand)
	{
		int value=0,aces=0;
		for(int i =0;i<hand.size();i++)
		{
			value+=calculateCard(hand.get(i));
			if(hand.get(i).getValue()==1)
				aces++;
		}
		while(value>21&&aces>0)
		{
			value-=10;
		}
		if(value>21)
			return 0;
		return value;
	}
	//returns the value of a give card
	public static int calculateCard(Card c)
	{
		int value=c.getValue();
		if(value>=10)
			return 10;
		else if(value==1)
			return 11;
		return value;
	}
	//updates the Label representing the value of each 
	public static void currentValue()
	{
		currentVal.setText(String.valueOf(calculateHand(playersCards)));
	}
	//returns the file path of the image for a given card
	public static String getCard(Card c)
	{
		String name="/cards/";

		//Value of card
		int value =c.getValue();
		switch(value)
		{
		case 1:
			name+="A";
			break;
		case 11:
			name+="J";
			break;
		case 12:
			name+="Q";
			break;
		case 13:
			name+="K";
			break;
		default:
			name+=String.valueOf(value);
		}

		//suit of card
		int suit=c.getSuit();
		switch(suit)
		{
		case 0:
			name+="C";
			break;
		case 1:
			name+="D";
			break;
		case 2:
			name+="H";
			break;
		case 3:
			name+="S";
			break;
		default:
			System.out.println("invalid suit");
		}
		name+=".png";
		return name;
	}
	//Suspends all buttons while the user is answering the quizbowl question
	public static void suspend()
	{
		tfBet.setEnabled(false);
		btnPlay.setEnabled(false);
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		btnDouble.setEnabled(false);
	}
	//resumes all buttons after suspension
	public static void resume()
	{
		tfBet.setEnabled(false);
		btnPlay.setEnabled(false);
		btnHit.setEnabled(true);
		btnStay.setEnabled(true);
		btnDouble.setEnabled(false);
	}
}

