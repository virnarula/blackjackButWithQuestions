package blackjack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Load games that have not been completed. Loads most recent on the sides and all can be search for. When selected, resumes game.
 */

public class LoadGame extends JFrame {

	private JPanel contentPane;
	private  ArrayList<Game> savedGames= new ArrayList<Game>();
	private  ArrayList<JButton> gameButtons= new ArrayList<JButton>();
	private JTextField textField;

	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadGame frame = new LoadGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoadGame() {
		setTitle("Load Game");
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

		JLabel lblPleaseChooseYour = new JLabel("Please choose your game:");
		lblPleaseChooseYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseChooseYour.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPleaseChooseYour.setBounds(469, 40, 272, 38);
		contentPane.add(lblPleaseChooseYour);

		createSavedGames();

		JButton zero = new JButton("Name + Remaining Money");
		zero.setBounds(71, 112, 208, 61);
		contentPane.add(zero);
		gameButtons.add(zero);

		JButton one = new JButton("Name + Remaining Money");
		one.setBounds(71, 201, 208, 61);
		contentPane.add(one);
		gameButtons.add(one);

		JButton two = new JButton("Name + Remaining Money");
		two.setBounds(71, 294, 208, 61);
		contentPane.add(two);
		gameButtons.add(two);

		JButton three = new JButton("Name + Remaining Money");
		three.setBounds(71, 390, 208, 61);
		contentPane.add(three);
		gameButtons.add(three);

		JButton four = new JButton("Name + Remaining Money");
		four.setBounds(858, 112, 208, 61);
		contentPane.add(four);
		gameButtons.add(four);

		JButton five = new JButton("Name + Remaining Money");
		five.setBounds(858, 201, 208, 61);
		contentPane.add(five);
		gameButtons.add(five);

		JButton six = new JButton("Name + Remaining Money");
		six.setBounds(858, 294, 208, 61);
		contentPane.add(six);
		gameButtons.add(six);

		JButton seven = new JButton("Name + Remaining Money");
		seven.setBounds(858, 390, 208, 61);
		contentPane.add(seven);
		gameButtons.add(seven);

		JLabel lblNewLabel = new JLabel("If you don’t see your game here, search for it below with your name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(355, 133, 438, 16);
		contentPane.add(lblNewLabel);


		JLabel lblNotFound= new JLabel("A game with that name has not been found.");
		lblNotFound.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotFound.setBounds(366, 315, 428, 16);
		contentPane.add(lblNotFound);
		lblNotFound.setVisible(false);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(355, 181, 438, 29);
		contentPane.add(textField);
		textField.setColumns(10);


		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String gameName=textField.getText();
					for(int i=0;i<savedGames.size();i++)
					{
						if(savedGames.get(i).getName().equals(gameName))
						{
							Game newGame=savedGames.get(i);
							Gameplay g= new Gameplay(newGame);
							g.setVisible(true);
							dispose();
						}
							
					}
					lblNotFound.setVisible(true);

				}
				catch(Exception e1)
				{
					lblNotFound.setText("Please enter a name");
				}
			}
		});
		btnSearch.setBounds(517, 233, 117, 29);
		contentPane.add(btnSearch);

		if(savedGames.size()==0)
		{
			textField.setEnabled(false);
			btnSearch.setEnabled(false);
		}


		for(int i=Math.min(savedGames.size(),8);i<gameButtons.size();i++)
		{
			gameButtons.get(i).setEnabled(false);
			gameButtons.get(i).setText("Not created");
		}
		for(int i=0;i<savedGames.size();i++)
		{
			gameButtons.get(i).setText(savedGames.get(i).getName()+" – "+savedGames.get(i).getMoney());
			gameButtons.get(i).setEnabled(true);
			int k=i;
			gameButtons.get(i).addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
					Game newGame=savedGames.get(k);
					//clearList();
					Gameplay g = new Gameplay(newGame);
					g.setVisible(true);
					dispose();
				}
			});

		}
	}
	//Creates list of all games that are not finished yet
	public void createSavedGames()
	{
		for(int i =0;i<Home.gameList.size();i++)
		{
			if(!Home.gameList.get(i).isComplete())
				savedGames.add(Home.gameList.get(i));
		}
	}
	//Clears list of saved games
	public void clearList()
	{
		for(int i=0;i<savedGames.size();i++)
		{
			savedGames.remove(0);
		}
	}
}
