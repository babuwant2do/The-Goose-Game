package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class SourceNode implements NodeI{
	private Move move;
	private User user;
	private Location location;
	
	public SourceNode(Location location, Move move, User user) {
		this.location = location;
		this.move = move;
		this.user = user;
	}
	
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * get Location Node
	 */
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getBounds(){
		int newPosition = this.getPosition() + this.getMove().getTotalStep();
		return newPosition - App.MAX_LOCATION;
	}
	
	public Integer getDestination() {
		int bounds = this.getBounds();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return App.MAX_LOCATION - bounds;
			
		}
	}
	
	public Integer getNextStop() {
		int bounds = this.getBounds();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return App.MAX_LOCATION;
			
		}
	}

	@Override
	public String getResponds(String destinationAddress) {
		return String.format("%s rolls %s. %s moves from %s to %s", 
				this.getUser().getName(), this.move.getState(),
				this.getUser().getName(), 
				this.getLocation().getPosition()==1? this.getLocation().getName():this.getLocation().getPosition(),
				destinationAddress);
		//TODO: append if bounds... also for others , speially goose 
	}

	@Override
	public String getResponds() {
		return this.getResponds(this.getNextStop().toString());
	}

	private Integer getPosition() {
		return this.getLocation().getPosition();
	}
	
}
