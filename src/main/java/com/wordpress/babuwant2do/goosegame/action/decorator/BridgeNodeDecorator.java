package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;

public class BridgeNodeDecorator extends NodeDecorator{

	public BridgeNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}

	
	@Override
	public Integer getDestination() {
		return this.getLocation().getNextPosition();
	}
	
	public String getResponds() {
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(this.getLocation().getName())).append(String.format(". %s jumps to %d", this.getUser().getName(), this.getLocation().getNextPosition()));
		sb.append(this.getPostfix());
		return sb.toString();
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(this.getLocation().getName())).append(String.format(". %s jumps to %s", this.getUser().getName(), destinationAddress));
		
		sb.append(this.getPostfix());
		return sb.toString();
	}


	@Override
	public Integer getNextStop() {
		return this.getDestination();
	}

}
