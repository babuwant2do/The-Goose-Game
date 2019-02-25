package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;

public class SimpleNodeDecorator extends NodeDecorator{
	
	public SimpleNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}

	@Override
	public String getResponds() {
		return this.getPrevoiusNode().getResponds();
	}

	@Override
	public String getResponds(String destinationAddress) {
		return this.getPrevoiusNode().getResponds(destinationAddress);
	}

	@Override
	public Integer getDestination() {
		return this.getPosition();
	}

	@Override
	public Integer getNextStop() {
		return this.getDestination();
	}
}
