package com.wordpress.babuwant2do.goosegame.game;

import java.util.Random;

public class Move implements GameCommandI{
	private Integer dice1;
	private Integer dice2;
	
	public Move(Integer dice1, Integer dice2){
		this.dice1 = dice1;
		this.dice2 = dice2;
	} 
	public Move(){
		Random r = new Random();
		this.dice1 = r.nextInt((6 - 1) + 1) + 1;
		this.dice2 = r.nextInt((6 - 1) + 1) + 1;
	} 
	
	public Integer getTotalStep(){
		return this.dice1 + this.dice2;
	}
	
	public Integer getDice1() {
		return dice1;
	}
	public void setDice1(Integer dice1) {
		this.dice1 = dice1;
	}
	public Integer getDice2() {
		return dice2;
	}
	public void setDice2(Integer dice2) {
		this.dice2 = dice2;
	}
	
	public String getState() {
		return String.format("%d, %d", this.getDice1(), this.getDice2());
	}
//
//	public String getResponds() {
//		return String.format("rolls %d, %d", this.getDice1(), this.getDice2());
//	}
//	
}
