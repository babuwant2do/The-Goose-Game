package com.wordpress.babuwant2do.goosegame.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BridgeLocationTest {
	@Test
	void BridgeLocation() {
		Integer position = 5;
		Integer nextLocation = 12;
		String name = "XX_XX";
		String defaultName = "The Bridge";
		BoardLocationI location = null;
		
		//test with default name : 
		location = new BridgeLocation(position, nextLocation);
		assertNotNull(location.getPosition(), String.format("new BridgeLocation(%d, %d).getPosition()", position, nextLocation));
		assertNotNull(location.getName(),String.format("new BridgeLocation(%d, %d).getName()", position, nextLocation));
		assertNotNull(location.getNextPosition(), String.format("new BridgeLocation(%d, %d).getNextPosition()", position, nextLocation));
		
		assertEquals( position.intValue(),location.getPosition().intValue(), String.format("new BridgeLocation(%d, %d).getPosition()", position, nextLocation));
		assertEquals( position,location.getPosition(), String.format("new BridgeLocation(%d, %d).getPosition() {not intValue()}", position, nextLocation));
		assertEquals(defaultName,location.getName(), String.format("new BridgeLocation(%d, %d).getName()", position, nextLocation));
		assertEquals( nextLocation.intValue(),location.getNextPosition().intValue(), String.format("new BridgeLocation(%d, %d).getNextPosition()", position, nextLocation));

		// test with specific name
		position = 2;
		nextLocation = 10;
		location = new BridgeLocation(position, name, nextLocation);
		
		assertNotNull(location.getPosition(), String.format("new BridgeLocation(%d, %s, %d).getPosition()", position, name, nextLocation));
		assertNotNull(location.getName(),String.format("new BridgeLocation(%d, %s,, %d).getName()", position, name, nextLocation));
		assertNotNull(location.getNextPosition(), String.format("new BridgeLocation(%d, %s,, %d).getNextPosition()", position, name, nextLocation));
		
		assertEquals( position.intValue(), location.getPosition().intValue(), String.format("new BridgeLocation(%d, %s,, %d).getPosition()", position, name, nextLocation));
		assertEquals( name, location.getName(), String.format("new BridgeLocation(%d, %s,, %d).getName()", position, name, nextLocation));
		assertEquals( nextLocation.intValue(), location.getNextPosition().intValue(), String.format("new BridgeLocation(%d, %s,, %d).getNextPosition()", position, name, nextLocation));
	}
}
