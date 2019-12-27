package blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Screen that takes a the name and creates a game from it. Leads to gameplay class.
 */

public class NewGame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newGame frame = new newGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public NewGame() {
		setTitle("New Game");
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
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textField.setBounds(454, 251, 304, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your name:");
		lblPleaseEnterYour.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPleaseEnterYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterYour.setBounds(490, 191, 228, 36);
		contentPane.add(lblPleaseEnterYour);
		
		JLabel lblPleaseEnterA = new JLabel("Please enter a valid name");
		lblPleaseEnterA.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPleaseEnterA.setForeground(Color.RED);
		lblPleaseEnterA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterA.setBounds(414, 489, 390, 36);
		lblPleaseEnterA.setVisible(false);
		contentPane.add(lblPleaseEnterA);
		
		JButton btnPlay = new JButton("Play!");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(textField.getText() == null)
			{
				//System.out.println("0");
				lblPleaseEnterA.setVisible(true);
			}
			else
			{
				Game newGame = new Game(textField.getText(),1000);
				//System.out.println("New Game "+ newGame.getName()+" "+newGame.getHighScore());
				Home.gameList.add(0,newGame);
				//System.out.println("Gamelist");
				//leaderboard.currentState(home.gameList);
				Gameplay f= new Gameplay(newGame);
				f.setVisible(true);
				dispose();
			}
			}
			
		});
		btnPlay.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnPlay.setBounds(559, 319, 117, 41);
		contentPane.add(btnPlay);
		
		
	}
}
