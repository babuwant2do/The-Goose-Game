package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.Location;

public class GoesNodeDecorator extends NodeDecorator{

	/**
	 * 
	 * @param sourceNode TODO: need to change NodeI to GooesNode to make sure that only gooes can inset
	 * @param currentLocation
	 */
	public GoesNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}

	public String getResponds() {
		return this.getResponds(String.format("%d, %s", this.getPosition(), this.getLocation().getName()));
	}

	public Integer getDestination() {
		int bounds = this.getBounds();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return App.MAX_LOCATION - bounds;
		}
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(destinationAddress)).append(". Pippo moves again and goes to ").append(this.getNextStop());
		return sb.toString();
	}

	@Override
	public Integer getBounds() {
		int newPosition = this.getPosition() + this.getMove().getTotalStep();
		return newPosition - App.MAX_LOCATION;
	}

	@Override
	public Integer getNextStop() {
		int bounds = this.getBounds();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return App.MAX_LOCATION;
		}
	}
}
