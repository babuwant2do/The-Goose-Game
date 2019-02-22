package com.wordpress.babuwant2do.goosegame.board;

/**
 * Space "6" is "The Bridge"
 * @author mohammedali
 *
 */
public class BridgeLocation extends Location{
	public BridgeLocation(Integer position, Integer nextPosition ) {
		this(position, "The Bridge", nextPosition);
	}
	public BridgeLocation(Integer position, String name, Integer nextPosition ) {
		super(position, name, nextPosition);
	}
}
