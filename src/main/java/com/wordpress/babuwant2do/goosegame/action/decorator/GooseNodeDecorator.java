package com.wordpress.babuwant2do.goosegame.action.decorator;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.Location;

public class GooseNodeDecorator extends NodeDecorator{

	/**
	 * 
	 * @param sourceNode TODO: need to change NodeI to GooesNode to make sure that only gooes can inset
	 * @param currentLocation
	 */
	public GooseNodeDecorator(NodeI sourceNode, Location currentLocation) {
		super(sourceNode, currentLocation);
	}


	@Override
	public Integer getDestination() {
		int bounds = this.getStepsOverFlow();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return this.getWinLocation() - bounds;
		}
	}

	@Override
	public String getResponds() {
		String destinationAddress = String.format("%d, %s", this.getPosition(), this.getLocation().getName());
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(destinationAddress))
				.append(String.format(". %s moves again and goes to %d", this.getUser().getName(), this.getNextStop()));
		
		sb.append(this.getPostfix());
		return sb.toString();
	}

	@Override
	public String getResponds(String destinationAddress) {
		StringBuilder sb = new StringBuilder(this.getPrevoiusNode().getResponds(String.format("%d, %s", this.getPosition(), this.getLocation().getName()))).
				append(String.format(". %s moves again and goes to %s", this.getUser().getName(), destinationAddress));
		sb.append(this.getPostfix());
		return sb.toString();
	}

	@Override
	public Integer getNextStop() {
		int bounds = this.getStepsOverFlow();
		if(bounds <= 0){
			return this.getPosition() + this.getMove().getTotalStep();
		}else{
			return this.getWinLocation();
		}
	}

	@Override
	public Integer calculateNewPssiblePosition() {
		return this.getPosition() + this.getMove().getTotalStep();
	}
}
