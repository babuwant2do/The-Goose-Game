package com.wordpress.babuwant2do.goosegame.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wordpress.babuwant2do.goosegame.board.BridgeLocation;
import com.wordpress.babuwant2do.goosegame.board.GooseLocation;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;
import com.wordpress.babuwant2do.goosegame.utils.LocationFactory;

public class LocationFactoryTest {
	
	static LocationFactory f = null;
	
	@BeforeAll
    static void initAll() {
    	LocationFactoryTest.f= new LocationFactory(); 
    }
	
	@Test
	public void typeCheck(){
		assertNotNull(LocationFactoryTest.f, "Location factory created null");
		try {
			assertTrue(LocationFactoryTest.f.create(LocationType.DEFAULT, 1) instanceof Location, "LocationFactoryTest.f.create(LocationType.DEFAULT, 1) instanceof Location");
			assertTrue(LocationFactoryTest.f.create(LocationType.BRIDGE, 1,2) instanceof Location, "LocationFactoryTest.f.create(LocationType.BRIDGE, 1,2) instanceof Location");
			assertTrue(LocationFactoryTest.f.create(LocationType.BRIDGE, 1,2) instanceof BridgeLocation, "LocationFactoryTest.f.create(LocationType.BRIDGE, 1,2) instanceof BridgeLocation");
			assertTrue(LocationFactoryTest.f.create(LocationType.GOOSE, 1) instanceof Location, "LocationFactoryTest.f.create(LocationType.GOOSE, 1) instanceof Location");
			assertTrue(LocationFactoryTest.f.create(LocationType.GOOSE, 1) instanceof GooseLocation ,"LocationFactoryTest.f.create(LocationType.GOOSE, 1) instanceof GooseLocation");
		} catch (LocationCreateFailException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void exceptionCheck(){
		// case 1 : Trying to create Bridge Type with wrong 
		final LocationType type1 = LocationType.BRIDGE;
		LocationCreateFailException ex1 = Assertions.assertThrows(LocationCreateFailException.class, 
			() -> {
			    f.create(type1, 1);
			  });
		assertEquals("nextPosition is missing for Location type BRIDGE.", ex1.getMessage());
		
		//case 2
		final LocationType type2 = LocationType.DEFAULT;
		 ex1 = Assertions.assertThrows(LocationCreateFailException.class, () -> {
			 f.create(type2, 1, 2);
		 });
		 assertEquals(String.format("Location with nextPosition can be created for type %s.",type2), ex1.getMessage());
		 
		 //case 3
		 final LocationType type3 = LocationType.GOOSE;
		 ex1 = Assertions.assertThrows(LocationCreateFailException.class, () -> {
			 f.create(type3, 1, 5);
		 });
		 assertEquals(String.format("Location with nextPosition can be created for type %s.",type3), ex1.getMessage());
		
	}
	
	
	@AfterAll
    static void cleanAll() {
		LocationFactoryTest.f= null;
    }

}
