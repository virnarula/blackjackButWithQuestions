	package blackjack;

import java.io.Serializable;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Question object. Holds a question, arrray of four options, and a correct index number
 */
public class Question implements Serializable
{
	private String q;
	private String[] arr;
	int correct;
	public Question(String q, String[] arr, int correct) {
		this.q = q;
		this.arr = arr;
		this.correct = correct;
	}
	public String getQ() {
		return q;
	}
	public void printQ()
	{
		System.out.println(q);
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String[] getArr() {
		return arr;
	}
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	
}
