package com.wordpress.babuwant2do.goosegame.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wordpress.babuwant2do.goosegame.board.BoardLocationI;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationInsertFailException;

@ExtendWith(MockitoExtension.class)
public class BoardBuilder {
	private Integer winPosition;
	private Map<Integer, Location>  locationMap = new HashMap<>();
	private LocationFactory locationFactory;
	
	public BoardBuilder(LocationFactory locationFactory) throws LocationCreateFailException{
		this(locationFactory, 63);
	}
	
	public BoardBuilder(LocationFactory locationFactory, Integer winPosition) throws LocationCreateFailException{
		this.winPosition = winPosition;
		this.locationFactory = locationFactory;
		
		this.locationMap.put(0, this.locationFactory.create(LocationType.DEFAULT, 0));
		this.locationMap.put(this.winPosition, this.locationFactory.create(LocationType.DEFAULT, this.winPosition));
	}
	/**
	 * 
	 * @return
	 */
	public BoardBuilder reset(){
		this.locationMap.clear();
		return this;
	}
	
	/**
	 * 
	 * @param location
	 * @return
	 * @throws LocationInsertFailException
	 */
	public BoardBuilder addLocation(Location location) throws LocationInsertFailException{
		if(location != null && location.getPosition() > 0 && location.getPosition() < this.winPosition ){
			this.locationMap.put(location.getPosition(), location);
			return this;
		}
		throw new LocationInsertFailException();
	}
	
	/**
	 * 
	 * @return
	 * @throws LocationCreateFailException
	 */
	public List<Location> build() throws LocationCreateFailException{
			
			List<Location> board = new ArrayList<>(this.winPosition);
			for (int i = 0; i <= this.winPosition; i++) {
				if(this.locationMap.containsKey(i)){
					board.add(this.locationMap.get(i));
				}else{
					board.add(this.locationFactory.create(LocationType.DEFAULT, i));
				}
			}
		return board;
	}
}

//		if(!this.	locationmap.isEmpty()){
//			List<Integer> locationInOrder = new ArrayList<Integer>(this.locationmap.keySet());
//			Collections.sort(locationInOrder);
//			Stream.of(locationInOrder).forEach(item -> System.out.println(">>>>>>>>>>>>>>>>>>>>>>   >>>>> "+ item + ","));
//			int customLocationIndex = 0;
//				while(i < locationInOrder.get(customLocationIndex) && i <= this.winPosition){}
//		}