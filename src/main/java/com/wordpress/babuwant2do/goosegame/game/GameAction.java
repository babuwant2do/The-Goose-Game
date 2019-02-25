package com.wordpress.babuwant2do.goosegame.game;

public class GameAction {
	private CommandType type;
	private User user;
	private GameCommandI command;
	
	public GameAction(CommandType type){
		this.type = type;
	}
	public GameAction(CommandType type, User user){
		this(type);
		this.user = user;
	}
	public GameAction(CommandType type, User user, GameCommandI command){
		this(type, user);
		this.command = command;
	}
	public CommandType getType() {
		return type;
	}
	public void setType(CommandType type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public GameCommandI getCommand() {
		return command;
	}
	public void setCommand(GameCommandI command) {
		this.command = command;
	}
	
	
	
}
