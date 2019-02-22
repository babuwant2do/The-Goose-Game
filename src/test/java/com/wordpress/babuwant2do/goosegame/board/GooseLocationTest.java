package com.wordpress.babuwant2do.goosegame.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class GooseLocationTest {
	@Test
	void GooseLocation() {
		Integer position = 5;
		Integer nextLocation = position;
		String name = "XX_XX";
		String defaultName = "The Goose";
		GooseLocation location = null;
		
		//test with default name : 
		location = new GooseLocation(position);
		assertNotNull(location.getPosition(), String.format("new GooseLocation(%d).getPosition()", position));
		assertNotNull(location.getName(),String.format("new GooseLocation(%d).getName()", position));
		assertNotNull(location.getNextPosition(), String.format("new GooseLocation(%d).getNextPosition()", position));
		
		assertEquals( position.intValue(),location.getPosition().intValue(), String.format("new GooseLocation(%d).getPosition()", position));
		assertEquals( position,location.getPosition(), String.format("new GooseLocation(%d).getPosition() {not intValue()}", position));
		assertEquals(defaultName,location.getName(), String.format("new GooseLocation(%d).getName()", position, nextLocation));
		assertEquals( nextLocation.intValue(),location.getNextPosition().intValue(), String.format("new GooseLocation(%d).getNextPosition()", position));
		
		
		//test with specific name name : 
		location = new GooseLocation(position, name);
		assertNotNull(location.getPosition(), String.format("new GooseLocation(%d, %s).getPosition()", position, name));
		assertNotNull(location.getName(),String.format("new GooseLocation(%d, %s).getName()", position, name));
		assertNotNull(location.getNextPosition(), String.format("new GooseLocation(%d, %s).getNextPosition()", position, name));
		
		assertEquals( position.intValue(),location.getPosition().intValue(), String.format("new GooseLocation(%d, %s).getPosition()", position, name));
		assertEquals( position,location.getPosition(), String.format("new GooseLocation(%d, %s).getPosition() {not intValue()}", position, name));
		assertEquals(name,location.getName(), String.format("new GooseLocation(%d, %s).getName()", position, name));
		assertEquals( nextLocation.intValue(),location.getNextPosition().intValue(), String.format("new GooseLocation(%d, %s).getNextPosition()", position, name));

	}
}
