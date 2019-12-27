package blackjack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Displays all rules of the game.
 */

public class Rules extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rules frame = new Rules();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Rules() {
		setTitle("Rules");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("<- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.setVisible(true);
				dispose();
			}
		});
		button.setBounds(31, 19, 117, 29);
		contentPane.add(button);
		
		JLabel lblThisIsWhere = new JLabel("<html> For every round, the player and dealer each receive two cards. The player can see both of their cards and only 1 of the dealers. <br />-\nAn ace is worth either 1 or 11, cards 2-9 are their respective value, and all face cards are worth 10. <br />-\nEach hand is worth the sum of all the cards in it, except for an ace and a face card, which is a “BlackJack” and outweighs all other hands. <br />-\nIf the dealer or the player has a blackjack, they will turnover their cards immediately and the respective party wins. If both have a blackjack, it is a push. If they user has a  blackjack, they win two times their bet. <br />-\nIf no one has a BlackJack, then the player has 3 options: <br />-\nStand: The player stands pat with their cards <br />-\nHit: A player draws another card. However, to do so, they must answer a question. If they are wrong, they lose the round. <br />If their new value exceeds twenty-one, they go bust and lose their money. <br />-\nDouble: The player doubles their bet, if possible, and get only one more card. Only available on the first turn. <br />-\nOnce they player finishes their turn, the dealer turns over his last card. If the dealer's hand is worth 16 points or below, they will draw another card. <br />-\nIf the dealer goes over 21 and the player has not, the player wins. <br />-\nIf no one exceeds 21 points, then the party with the highest score will win the hand.\n<html />");
		lblThisIsWhere.setHorizontalAlignment(SwingConstants.CENTER);
		lblThisIsWhere.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblThisIsWhere.setBounds(31, 92, 1163, 437);
		contentPane.add(lblThisIsWhere);
		
		JLabel lblRules = new JLabel("Rules");
		lblRules.setHorizontalAlignment(SwingConstants.CENTER);
		lblRules.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblRules.setBounds(449, 24, 319, 87);
		contentPane.add(lblRules);
	}
}
