package blackjack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Screen that takes a question as an argument and creates buttons to answer. Acts on gameplay screen when buttons are clicked
 */

public class QuizQuestion extends JFrame {

	private JPanel contentPane;
	private JButton gameButtons[]= new JButton[4];
	private int answer=0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizQuestion frame = new QuizQuestion(null);
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
	public QuizQuestion(Question q) {
		setTitle("Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblQuestion = new JLabel(q.getQ());
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblQuestion.setBounds(48, 35, 513, 39);
		contentPane.add(lblQuestion);
		
		gameButtons[0]=new JButton(q.getArr()[0]);
		gameButtons[1]=new JButton(q.getArr()[1]);
		gameButtons[2]=new JButton(q.getArr()[2]);
		gameButtons[3]=new JButton(q.getArr()[3]);
		
		gameButtons[0].setBounds(33, 101, 217, 82);
		gameButtons[1].setBounds(319, 101, 217, 82);
		gameButtons[2].setBounds(33, 217, 217, 82);
		gameButtons[3].setBounds(319, 217, 217, 82);
		
		for(int i=0;i<4;i++)
		{			
			gameButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			contentPane.add(gameButtons[i]);
			if(i==q.getCorrect()-1)
			{
				gameButtons[i].addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						Gameplay.hitCorrect();
						dispose();
					}
				});
			}
			else
			{
				gameButtons[i].addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						Gameplay.hitWrong();
						dispose();
					}
				});
			}
		}
	}
}
