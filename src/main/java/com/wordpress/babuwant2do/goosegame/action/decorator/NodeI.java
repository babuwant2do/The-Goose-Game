package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public interface NodeI {
	public Move getMove();
	public User getUser();
	public Location getLocation();

	/**
	 * get final destination after even bounds.. and all action made
	 * @return
	 */
	public Integer getDestination();
	
	public String getResponds();
	public String getResponds(String destinationAddress);
	/**
	 * JUst calculate the next Position(valid/invalid) after impose All action on current position.
	 * it provides the new Position even After the END, including overflow.
	 * @return
	 */
	public Integer calculateNewPssiblePosition();

	
	/**
	 * count the steps over/before the final/END. Number represent the Steps Behind/After w.r.t END.
	 * @return - 0 if reach at END(win), -ve if behind the END, +ve if steps overflow.
	 */
	public default Integer getStepsOverFlow() {
		return this.calculateNewPssiblePosition() - App.MAX_LOCATION;
	}
	
}
