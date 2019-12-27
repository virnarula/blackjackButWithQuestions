package blackjack;

import java.io.Serializable;

/**
 * @author 19vnarula
 * @date May 5th, 2018
 * @purpose Game object. Stores highscore, name, currentmoney, and status.
 */

public class Game implements Serializable
{
	int turns, money, highScore;
	String name;
	boolean isComplete;
	public Game(String name, int money)
	{
		turns=0;
		this.money=money;
		highScore=this.money;
		this.name=name;
		isComplete=false;
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}	
}
