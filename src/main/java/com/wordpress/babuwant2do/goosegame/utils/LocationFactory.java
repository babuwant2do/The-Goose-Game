package com.wordpress.babuwant2do.goosegame.utils;

import com.wordpress.babuwant2do.goosegame.board.BridgeLocation;
import com.wordpress.babuwant2do.goosegame.board.GooseLocation;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;

public class LocationFactory {
	
	public Location create(LocationType type, Integer position, Integer nextPosition) throws LocationCreateFailException{
		if(position == nextPosition){
			return this.create(type, nextPosition);
		}
		if(type != LocationType.BRIDGE){
			throw new LocationCreateFailException(String.format("Location with nextPosition can be created for type %s.",type));
		}
		return new BridgeLocation(position, nextPosition);
	} 
	
	public Location create(LocationType type, Integer position ) throws LocationCreateFailException{
		if(type == LocationType.BRIDGE){
			throw new LocationCreateFailException(String.format("nextPosition is missing for Location type %s.",type));
		}
		
		if(type == LocationType.DEFAULT) {
			return new Location(position);
		}else if(type == LocationType.GOOSE) {
			return new GooseLocation(position);
		}
		return null;
	} 
}
