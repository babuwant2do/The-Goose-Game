package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class SourceNode implements NodeI{
	private Move move;
	private User user;
	private Location location;
	private final Integer winLocation;
	
	public SourceNode(Location location, Move move, User user, Integer winLocation) {
		this.location = location;
		this.move = move;
		this.user = user;
		this.winLocation = winLocation;
	}
	
	@Override
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	
	@Override
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * get Location Node
	 */
	@Override
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	public Integer getDestination() {
		int bounds = this.getStepsOverFlow();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return this.getWinLocation() - bounds;
			
		}
	}
	
	//TODO: make it private and test using Java Reflection
	protected Integer getNextStop() {
		int bounds = this.getStepsOverFlow();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return this.getWinLocation();
			
		}
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(String.format("%s rolls %s. %s moves from %s to %s", 
				this.getUser().getName(), this.move.getState(),
				this.getUser().getName(), 
				this.getLocation().getPosition()== 0? this.getLocation().getName():this.getLocation().getPosition(),
				destinationAddress));

		sb.append(this.getPostfix());
		return sb.toString();		
	}

	@Override
	public String getResponds() {
		return this.getResponds(this.getNextStop().toString());
	}

	/**
	 * get current node position in the board.
	 * @return
	 */
	private Integer getPosition() {
		return this.getLocation().getPosition();
	}

	@Override
	public Integer calculateNewPssiblePosition() {
		return this.getPosition() + this.getMove().getTotalStep();
	}

	@Override
	public Location getSourceLocation() {
		return this.location;
	}

	@Override
	public Integer getWinLocation() {
		return this.winLocation;
	}
	
}
