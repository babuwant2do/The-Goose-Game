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
			return App.MAX_LOCATION - bounds;
			
		}
	}
	
	//TODO: make it private and test using Java Reflection
	protected Integer getNextStop() {
		int bounds = this.getStepsOverFlow();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return App.MAX_LOCATION;
			
		}
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(String.format("%s rolls %s. %s moves from %s to %s", 
				this.getUser().getName(), this.move.getState(),
				this.getUser().getName(), 
				this.getLocation().getPosition()==1? this.getLocation().getName():this.getLocation().getPosition(),
				destinationAddress));
//		int overFlowStrps = this.getStepsOverFlow();
//		if(overFlowStrps > 0){
//			sb.append(String.format(". %s bounces! %s returns to %d", this.getUser().getName(), this.getUser().getName(), this.getDestination()));
//		}else if(overFlowStrps == 0){
//			sb.append(String.format(". %s Wins!!",  this.getUser().getName()));
//		}
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
	
}
