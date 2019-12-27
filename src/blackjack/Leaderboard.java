package blackjack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Creates leaderboard Screen. Calculates leaders from gameList and then updates the label with the leaders.
 */

public class Leaderboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Leaderboard frame = new Leaderboard();
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
	public Leaderboard() 
	{
		setTitle("Leaderboard\n");
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
		
		
		String leaders1 =calculateLeaders();

		JLabel lblLeaders = new JLabel(leaders1);
		lblLeaders.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaders.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblLeaders.setBounds(404, 24, 413, 535);
		contentPane.add(lblLeaders);
	}
	
	//Returns string with all leaders in order of highest score.
	public static String calculateLeaders()
	{
		String leaders="";
		ArrayList<Game> finishedGames= new ArrayList<Game>();
		for(int i=0;i<Home.gameList.size();i++)
		{
			if(Home.gameList.get(i).isComplete())
				finishedGames.add(Home.gameList.get(i));
		}
		if(finishedGames.size()>0)
		{
		    //currentState(home.gameList);
			sortGames(finishedGames);
			//currentState(finishedGames);
			leaders="<html>";
			for(int i=0; i<Math.min(10, finishedGames.size());i++)
			{
				leaders+=String.valueOf(i+1)+". "+finishedGames.get(i).getName()+" – "+finishedGames.get(i).getHighScore()+"<br />";
			}
			leaders+="<html />";
		}
		else
		{
			leaders="There are no games completed as of yet";
		}
		return leaders;
	}
	//Sorts a game list in terms of highest score 
	public static void sortGames(ArrayList<Game> list)
	{
		int maxIndex;
		for(int i=0;i<list.size();i++)
		{
			maxIndex=i;
			for(int j=i+1;j<list.size();j++)
			{
				if(list.get(j).getHighScore()>list.get(maxIndex).getHighScore())
					maxIndex=j;
			}
			Collections.swap(list, i, maxIndex);
		}
	}
	//DEBUGGING: print out all elements of a Game array
	public static void currentState(ArrayList<Game> games)
	{
		for(int i=0;i<games.size();i++)
		{
			System.out.println(games.get(i).getName()+" "+ games.get(i).getHighScore()+" "+games.get(i).isComplete);
		}
	}

}
