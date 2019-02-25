package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public interface NodeI {
	public Move getMove();
	public User getUser();
	public Location getLocation();
	public Location getSourceLocation();
	public Integer getWinLocation();

	/**
	 * get final destination after even bounces.. and all action made
	 * @return
	 */
	public Integer getDestination();
	/**
	 * intermediate Stop between source and Destination
	 * @return
	 */
	public Integer getNextStop();
	/**
	 * final response: as final step
	 * @return
	 */
	public String getResponds();
	/**
	 * response depend on destination address / additional info from latest node
	 * @param destinationAddress
	 * @return
	 */
	public String getResponds(String destinationAddress);
	
	
	/**
	 * prepare postfix for response. it generate postfix if step is Finish(win) or Over the Finish (overflow).
	 * Note: this part suppose to add at the end of Node response. 
	 * @return
	 */

	public default String getPostfix() {
		if(( this.getNextStop() - this.getDestination()) > 0){
			return String.format(". %s bounces! %s returns to %d", this.getUser().getName(), this.getUser().getName(), this.getDestination());
		}else if(this.getDestination() == this.getWinLocation()){
			return String.format(". %s Wins!!",  this.getUser().getName());
		}
		return "";		
	}
	
}
