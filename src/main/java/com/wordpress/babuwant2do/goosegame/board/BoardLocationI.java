package com.wordpress.babuwant2do.goosegame.board;

public interface BoardLocationI {
	public Integer getPosition();
	public String getName();
	/**
	 * return if there is some jump location: (default is current position)
	 * @return
	 */
	public Integer getNextPosition();
}
