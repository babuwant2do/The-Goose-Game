package com.wordpress.babuwant2do.goosegame.board;

public class Location implements BoardLocationI{
	private Integer position;
	private String name;
	private Integer nextPosition;
	
	public Location(Integer position){
		this.position = position;
		this.name = (position ==1? "Start" : position.toString());
		this.nextPosition = position;
	}
	public Location(Integer position , String name){
		this(position);
		this.name = name;
	}
	
	protected Location(Integer position , String name, Integer nextPosition){
		this(position, name);
		this.nextPosition = nextPosition;
	}
	
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNextPosition() {
		return nextPosition;
	}
	public void setNextPosition(Integer nextPosition) {
		this.nextPosition = nextPosition;
	}
}
