package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public interface NodeI {
	public String getResponds();
	public String getResponds(String destinationAddress);
	public Move getMove();
	public User getUser();
	public Location getLocation();

	//TODO: rename it getMoveOverFlow and make it Private or Protected
	/**
	 * if bound = 0 : wins, if -ve : normal move, if +ve: bounds
	 * @return
	 */
	public Integer getBounds();
	/**
	 * get final destination after even bounds.. and all action made
	 * @return
	 */
	public Integer getDestination();
	/**
	 * it can be the same as destination , but in case of bounds it is always end(63).
	 * @return
	 */
	public Integer getNextStop();

	
	
}
