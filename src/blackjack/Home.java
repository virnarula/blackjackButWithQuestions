package blackjack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Creates the home screen. Also saves and loads the arraylists.
 */

public class Home extends JFrame {

	private JPanel contentPane;
	public static ArrayList<Game> gameList = new ArrayList<Game>();
	public static ArrayList<Question> questionList= new ArrayList<Question>();
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home() {
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		readFromFile();

		if(!loadQuestionList())
		{
			questionList=new ArrayList<Question>();
		}
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGame n = new NewGame();
				n.setVisible(true);
				dispose();
			}
		});
		btnNewGame.setBounds(525, 203, 168, 50);
		contentPane.add(btnNewGame);

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadGame l = new LoadGame();
				l.setVisible(true);
				dispose();
			}
		});
		btnLoadGame.setBounds(525, 267, 168, 50);
		contentPane.add(btnLoadGame);

		JButton btnLeaderboard = new JButton("Leaderboard\n");
		btnLeaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Leaderboard l= new Leaderboard();
				l.setVisible(true);
				dispose();
			}
		});
		btnLeaderboard.setBounds(525, 329, 168, 50);
		contentPane.add(btnLeaderboard);

		JButton btnRules = new JButton("Rules");
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rules r = new Rules();
				r.setVisible(true);
				dispose();
			}
		});
		btnRules.setBounds(525, 391, 168, 50);
		contentPane.add(btnRules);


		JButton btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddQuestion a= new AddQuestion();
				a.setVisible(true);
				dispose();
			}
		});
		btnAddQuestion.setBounds(525, 453, 168, 50);
		contentPane.add(btnAddQuestion);


		JLabel lblBlackjackXQuizbowl = new JLabel("Blackjack X Quizbowl");
		lblBlackjackXQuizbowl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblBlackjackXQuizbowl.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlackjackXQuizbowl.setBounds(280, 102, 669, 36);
		contentPane.add(lblBlackjackXQuizbowl);
	}
	//Saves the list of all games played
	public static void writetoFile()
	{
		try
		{
			ObjectOutputStream out=new ObjectOutputStream (new FileOutputStream("gameList.vir"));
			out.writeObject(gameList);
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//loads the list of all games played
	public static void readFromFile()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameList.vir"));
			gameList=(ArrayList<Game>)in.readObject();
			in.close();
		}
		catch(Exception e) 
		{

		}
	}
	//saves the list of all questions
	public static void saveQuestionList()
	{
		try
		{
			ObjectOutputStream out=new ObjectOutputStream (new FileOutputStream("questionList.vir"));
			out.writeObject(questionList);
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//loads the list of all questions
	public static boolean loadQuestionList()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("questionList.vir"));
			questionList=(ArrayList<Question>)in.readObject();
			in.close();
		}
		catch(Exception e) 
		{

		}
		if(questionList==null)
			return false;
		return true;
	}
	//DEBUGGING: prints full list of questions
	public static void printQList()
	{
		System.out.println("printing q list");
		for(int i=0;i<questionList.size();i++)
		{
			questionList.get(i).printQ();
		}
	}

}
