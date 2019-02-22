package com.wordpress.babuwant2do.goosegame.board;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


public class LocationTest {
	
	
	@Test
	void BasicLocationWithDefaultName() {
		
		Integer position = 5;
		Integer nextLocation = position;
//		String name = "XX_XX";
		String defaultName = position.toString();
		Location location = null;
		
		//test with default name : 
		location = new Location(position);
		assertNotNull(location.getPosition(), String.format("new Location(%d, %d).getPosition()", position, nextLocation));
		assertNotNull(location.getName(),String.format("new Location(%d, %d).getName()", position, nextLocation));
		assertNotNull(location.getNextPosition(), String.format("new Location(%d, %d).getNextPosition()", position, nextLocation));
		
		assertEquals( position.intValue(),location.getPosition().intValue(), String.format("new Location(%d, %d).getPosition()", position, nextLocation));
		assertEquals( position,location.getPosition(), String.format("new Location(%d, %d).getPosition() {not intValue()}", position, nextLocation));
		assertEquals(defaultName,location.getName(), String.format("new Location(%d, %d).getName()", position, nextLocation));
		assertEquals( nextLocation.intValue(),location.getNextPosition().intValue(), String.format("new Location(%d, %d).getNextPosition()", position, nextLocation));
		
	}
	
	@Test
	void BasicLocationWithSpecificName() {
		
		Integer position = 5;
		Integer nextLocation = position;
		String name = "XX_XX";
//		String defaultName = position.toString();
		Location location = null;
		
		
		//test with specific name name : 
		location = new Location(position, name);
		assertNotNull(location.getPosition(), String.format("new Location(%d, %s).getPosition()", position, name));
		assertNotNull(location.getName(),String.format("new Location(%d, %s).getName()", position, name));
		assertNotNull(location.getNextPosition(), String.format("new Location(%d, %s).getNextPosition()", position, name));
		
		assertEquals( position.intValue(),location.getPosition().intValue(), String.format("new Location(%d, %s).getPosition()", position, name));
		assertEquals( position,location.getPosition(), String.format("new Location(%d, %s).getPosition() {not intValue()}", position, name));
		assertEquals(name,location.getName(), String.format("new Location(%d, %s).getName()", position, name));
		assertEquals( nextLocation.intValue(),location.getNextPosition().intValue(), String.format("new Location(%d, %s).getNextPosition()", position, name));
		
	}
	
	
	
	
}
