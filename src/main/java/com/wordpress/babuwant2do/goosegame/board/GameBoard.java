package com.wordpress.babuwant2do.goosegame.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.wordpress.babuwant2do.goosegame.action.decorator.NodeI;
import com.wordpress.babuwant2do.goosegame.exceptions.NodeCreateFailException;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;
import com.wordpress.babuwant2do.goosegame.utils.NodeFactory;

public class GameBoard {
	/**
	 * board Space
	 */
	private List<Location> boardLocations;
	/**
	 * boards current status: RUNNING when first move executed.
	 */
	private BoardStatus boardStatus;
	/**
	 * List of all participants
	 */
	private List<User> users;
	
	private User winner;
	/**
	 * stores User Current Location
	 */
	private Map<User, Location> usersLocation; 
	/**
	 * 
	 */
	private NodeFactory nodeFactory;
	
	public GameBoard(List<Location> boardLocations, NodeFactory nodeFactory){
		this.boardLocations = boardLocations;
		this.nodeFactory = nodeFactory;
		this.winner = null;
		this.users = new ArrayList<>();
		usersLocation = new HashMap<>();
		this.boardStatus = BoardStatus.INITALIZED;
	}
	
	/**
	 * 
	 * @param user
	 * @param move
	 * @return
	 * @throws NodeCreateFailException
	 */
	public String moveUser(User user, Move move) throws NodeCreateFailException{
		if(this.boardStatus == BoardStatus.FINISHED){
			return "need to Restart, The winner is: "+  this.winner.getName();
		}
		
		if(this.users.contains(user)){
			if(this.boardStatus == BoardStatus.INITALIZED) this.boardStatus = BoardStatus.RUNNING;
			return this.manageMovement(this.nodeFactory.create(this.usersLocation.get(user), user, move));
		}else{
			return String.format("%s not part of current Game", user.getName());
		}
	}
	
	private String manageMovement(NodeI sourceNode) throws NodeCreateFailException{
		int src = sourceNode.getLocation().getPosition();
		int dest = sourceNode.getDestination();
		
		while(src !=  dest){
			sourceNode = this.nodeFactory.create(sourceNode, this.boardLocations.get(dest));
			src = sourceNode.getLocation().getPosition();
			dest = sourceNode.getDestination();
		}
		
		this.usersLocation.put(sourceNode.getUser(), sourceNode.getLocation());
		
		if(dest == this.boardLocations.get(this.boardLocations.size() -1).getPosition()){
			this.boardStatus = BoardStatus.FINISHED;
		}
		return sourceNode.getResponds();
	}
	
	/**
	 * add player in the Game.
	 * @param user
	 * @return
	 */
	public String addUser(User user){
		if(this.users.contains(user)){
			return String.format("%s: already existing player", user.getName());
		}
		this.users.add(user);
		this.usersLocation.put(user, this.boardLocations.get(0));
		return String.format("players: %s", this.users.stream()
				.map(u -> u.getName()).collect(Collectors.joining(", ")));
	}
	
	/**
	 * to set users initial Location
	 * @param usersNewLocation
	 */
	public void setUserInitialLocation(Map<User, Location> usersNewLocation){
		//TODO: check if Default Location and In range
		if(usersNewLocation != null){
			boolean test = usersNewLocation.keySet().stream().allMatch(u->this.usersLocation.containsKey(u));
			if(test){
				for (Map.Entry<User,Location> entry : usersNewLocation.entrySet()) {
				    this.usersLocation.put(entry.getKey(), entry.getValue());
				  }
			}else{
				System.out.println("---------->    >>> ALL USERS ARE NOT IN THE BOARD!! @@@@@@@@@@@@@@@@");
				//TODO: throws Exception
			}
		}
	}
	
	public void resetBoard(){
		if(this.boardStatus != BoardStatus.INITALIZED){
			this.winner = null;
			this.usersLocation = this.usersLocation.entrySet().stream().collect(Collectors.toMap(e -> e.getKey() , e -> this.boardLocations.get(0))); 
			this.boardStatus = BoardStatus.INITALIZED;
		}
	}
	
	public void resetGame(){
		this.winner = null;
		this.users = new ArrayList<>();
		usersLocation = new HashMap<>();
		this.boardStatus = BoardStatus.INITALIZED;
		this.resetBoard();
	}
	
}
