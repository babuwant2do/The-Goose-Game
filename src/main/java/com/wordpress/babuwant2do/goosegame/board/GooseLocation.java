package com.wordpress.babuwant2do.goosegame.board;

/**
 * The spaces 5, 9, 14, 18, 23, 27 have a picture of "The Goose"
 * @author mohammedali
 *
 */
public class GooseLocation extends Location{

	public GooseLocation(Integer position) {
		super(position, "The Goose");
	}
	public GooseLocation(Integer position, String name) {
		super(position, name);
	}
	
	
}
