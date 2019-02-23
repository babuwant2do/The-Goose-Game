package com.wordpress.babuwant2do.goosegame.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.wordpress.babuwant2do.goosegame.game.User;

public class GameBoard {
	private List<BoardLocationI> boardLocations;
	private BoardStatus boardStatus;
	private List<User> users;
	
	public final Integer WIN_POSITION;
	
	public GameBoard(){
		this(63);
	}
	
	public GameBoard(int winPosition){
		this.WIN_POSITION = winPosition;
		this.resetGame();
	}
	
	public String addUser(User user){
		if(this.users.contains(user)){
			return String.format("%s: already existing player", user.getName());
		}
		this.users.add(user);
		return String.format("players: %s", this.users.stream().map(u -> u.getName()).collect(Collectors.joining(", ")));
	}
	
	public void resetBoard(){
		this.boardStatus = BoardStatus.INITALIZED;
	}
	
	public void resetGame(){
		this.users = new ArrayList<>();
	}
	
}
