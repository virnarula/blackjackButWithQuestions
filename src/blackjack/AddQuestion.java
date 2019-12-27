package blackjack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Creates the screen where questions are added to the questions list.
 */

public class AddQuestion extends JFrame {

	private JPanel contentPane;
	private JTextField TFQuestion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private int answer;
	private ButtonGroup group;


	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddQuestion frame = new AddQuestion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AddQuestion() {
		setTitle("Add Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TFQuestion = new JTextField();
		TFQuestion.setBounds(116, 107, 976, 37);
		contentPane.add(TFQuestion);
		TFQuestion.setColumns(10);

		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setBounds(543, 64, 132, 31);
		contentPane.add(lblQuestion);

		JLabel lblOption = new JLabel("Option 1");
		lblOption.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption.setBounds(129, 222, 216, 31);
		contentPane.add(lblOption);

		JLabel lblOption_1 = new JLabel("Option 2");
		lblOption_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption_1.setBounds(807, 222, 216, 31);
		contentPane.add(lblOption_1);

		JLabel lblOption_2 = new JLabel("Option 3");
		lblOption_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption_2.setBounds(129, 348, 216, 31);
		contentPane.add(lblOption_2);

		JLabel lblOption_3 = new JLabel("Option 4");
		lblOption_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblOption_3.setBounds(807, 348, 216, 31);
		contentPane.add(lblOption_3);

		textField = new JTextField();
		textField.setBounds(115, 265, 242, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(800, 265, 242, 31);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(115, 400, 242, 31);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(800, 402, 242, 31);
		contentPane.add(textField_3);

		JLabel lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrectAnswer.setBounds(467, 229, 242, 37);
		contentPane.add(lblCorrectAnswer);

		JRadioButton btn1 = new JRadioButton("Option 1");
		btn1.setBounds(493, 294, 141, 23);
		contentPane.add(btn1);

		JRadioButton btn2 = new JRadioButton("Option 2");
		btn2.setBounds(493, 330, 141, 23);
		contentPane.add(btn2);

		JRadioButton btn3 = new JRadioButton("Option 3");
		btn3.setBounds(493, 368, 141, 23);
		contentPane.add(btn3);

		JRadioButton btn4 = new JRadioButton("Option 4");
		btn4.setBounds(493, 403, 141, 23);
		contentPane.add(btn4);

		group= new ButtonGroup();
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);


		JLabel lblError = new JLabel("");
		lblError.setBounds(116, 494, 242, 78);
		contentPane.add(lblError);

		JButton btnDone = new JButton("Done!");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btn1.isSelected())
					answer=1;
				else if(btn2.isSelected())
					answer=2;
				else if(btn3.isSelected())
					answer=3;
				else if(btn4.isSelected())
					answer=4;
				else
					answer=0;
				
				
				String[] arr= new String[]{textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText()};
				
				if(answer==0)
				{
					lblError.setText("Please eneter valid values for everything");
					
				}
				else
				{
					Question q= new Question(TFQuestion.getText(),arr,answer);
					Home.questionList.add(q);
					Home.saveQuestionList();
					JOptionPane.showMessageDialog(null,"Your question has been added to the list.");
					clearScreen();
				}
				
			}
		});
		btnDone.setBounds(517, 470, 117, 29);
		contentPane.add(btnDone);

		JButton button = new JButton("<-- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.setVisible(true);
				dispose();
			}
		});
		button.setBounds(38, 24, 117, 29);
		contentPane.add(button);
		
	}
	//Clears the screen once a question has been successfully added.
	public void clearScreen()
	{
		TFQuestion.setText("");
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		group.clearSelection();
	}
}
