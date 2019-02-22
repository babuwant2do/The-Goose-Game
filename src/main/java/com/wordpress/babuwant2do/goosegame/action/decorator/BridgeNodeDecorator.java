package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;

public class BridgeNodeDecorator extends NodeDecorator{

	public BridgeNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}

	public String getResponds() {
		return this.getResponds(this.getLocation().getName());
	}
	
	public Integer getDestination() {
		return this.getLocation().getNextPosition();
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(destinationAddress)).append(". Pippo jumps to ").append(this.getLocation().getNextPosition());
		return sb.toString();
	}

	@Override
	public Integer getBounds() {
		return 0;
	}

	@Override
	public Integer getNextStop() {
		return this.getDestination();
	}

}
