package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.board.Location;

public class BridgeNodeDecorator extends NodeDecorator{

	public BridgeNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}

	public String getResponds() {
		return this.getResponds(this.getLocation().getName());
	}
	
	@Override
	public Integer getDestination() {
		return this.getLocation().getNextPosition();
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(destinationAddress)).append(". Pippo jumps to ").append(this.getLocation().getNextPosition());
//		if(this.getStepsOverFlow() > 0){
//			sb.append(String.format(". %s bounces! %s returns to %d", this.getUser().getName(), this.getUser().getName(), this.getDestination()));
//		}
		sb.append(this.getPostfix());
		return sb.toString();
	}

	@Override
	public Integer calculateNewPssiblePosition() {
		return this.getLocation().getNextPosition();
	}

//
//	@Override
//	public Integer getStepsOverFlow() {
//		return this.getLocation().getNextPosition() - App.MAX_LOCATION;
//	}
//	@Override
//	public Integer getNextStop() {
//		return this.getDestination();
//	}

}
