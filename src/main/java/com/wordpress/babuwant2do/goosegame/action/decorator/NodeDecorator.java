package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public abstract class NodeDecorator implements NodeI{
	private NodeI sourceNode;
	private Location currentLocation;
	
	public NodeDecorator(NodeI sourceNode, Location currentLocation){
		this.sourceNode = sourceNode;
		this.currentLocation = currentLocation;
	}
	
	protected NodeI getPrevoiusNode(){
		return this.sourceNode;
	}

	/**
	 * get previous board Location
	 * @return
	 */
	protected Location getPreviousLocation() {
		return this.sourceNode.getLocation();
	}
	
	/**
	 * Actual position of current Node, without any action
	 * @return
	 */
	protected Integer getPosition() {
		return this.currentLocation.getPosition();
	}
	
	protected Integer getNextStop() {
		return this.getDestination();
	}
	
	
	/**
	 * get Current Board Location (without any action on this node)
	 */
	@Override
	public Location getLocation() {
		return currentLocation;
	}
	
	@Override
	public User getUser() {
		return this.sourceNode.getUser();
	}
	@Override
	public Move getMove() {
		return this.sourceNode.getMove();
	}
	
	@Override
	public Location getSourceLocation() {
		return this.sourceNode.getSourceLocation();
	}
}
