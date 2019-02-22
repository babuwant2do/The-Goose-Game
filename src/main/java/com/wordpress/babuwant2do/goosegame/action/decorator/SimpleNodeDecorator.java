package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.Location;

public class SimpleNodeDecorator extends NodeDecorator{
	
	public SimpleNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}

	public String getResponds() {
		return this.getPrevoiusNode().getResponds();
	}
	

	@Override
	public String getResponds(String destinationAddress) {
		return this.getPrevoiusNode().getResponds(destinationAddress);
	}

	@Override
	public Integer getBounds() {
		return this.getPosition() - App.MAX_LOCATION;
	}

	@Override
	public Integer getDestination() {
		return this.getPosition();
	}

	@Override
	public Integer getNextStop() {
		return this.getPosition();
	}
}
