package com.wordpress.babuwant2do.goosegame.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.wordpress.babuwant2do.goosegame.board.BoardLocationI;
import com.wordpress.babuwant2do.goosegame.board.BridgeLocation;
import com.wordpress.babuwant2do.goosegame.board.GooseLocation;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationInsertFailException;

public class BoardBuilderTest {
	private static LocationFactory locationFactory;
	
	
	@Test
	public void addLocationDoesNotThrowException(){
		for (int i = 1; i < 63; i++) {	
			final int position = i;
			Assertions.assertDoesNotThrow(
					() -> {
						new BoardBuilder(locationFactory).addLocation(locationFactory.create(LocationType.DEFAULT, position));
					}, "boardBuilder.addLocation At "+ position);
		}
		
		for (int i = 1; i < 6; i++) {	
			final int position = i;
			Assertions.assertDoesNotThrow(
					() -> {
						new BoardBuilder(locationFactory, 6).addLocation(locationFactory.create(LocationType.DEFAULT, position));
					}, "boardBuilder.addLocation At {"+ position+ "}, with winLocation "+ 6);
		}
	}
	
	@ParameterizedTest
	@MethodSource("addLocationThrowExceptionProvider")
	public void addLocationThrowException(Integer position){
		LocationInsertFailException ex1 = Assertions.assertThrows(LocationInsertFailException.class, 
				() -> {
					new BoardBuilder(locationFactory).addLocation(locationFactory.create(LocationType.DEFAULT, position));
				}, "boardBuilder.addLocation At "+ position);
		assertEquals("Failed to insert Location.", ex1.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("addLocationWithCustomLimitThrowExceptionProvider")
	public void addLocationThrowException(Integer position, Integer winLocation){
		LocationInsertFailException ex1 = Assertions.assertThrows(LocationInsertFailException.class, 
				() -> {
					new BoardBuilder(locationFactory, winLocation).addLocation(locationFactory.create(LocationType.DEFAULT, position));
				}, "boardBuilder.addLocation At {"+ position + "} With Limit(winLocation): "+ winLocation);
		assertEquals("Failed to insert Location.", ex1.getMessage());		
	}
	
	@ParameterizedTest
	@MethodSource("buildForDefaultWinLocationProvider")
	public void buildForDefaultWinLocation(List<BoardLocationI> locations, Map<Integer, Class> expectedType, Integer winLocation){
		try {
			BoardBuilder boardBuilder = new BoardBuilder(locationFactory, winLocation);
			
			locations.forEach(location -> {
				try {
					boardBuilder.addLocation(location);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			List<BoardLocationI> board = boardBuilder.build();
			
			for (int i = 0; i < board.size(); i++) {
				if(expectedType.containsKey(i)){
					Assertions.assertTrue(expectedType.get(i).isInstance(board.get(i)), 
							String.format(" ** board[%d] instanceOf %s", i, expectedType.get(i))
							);
				}else{
					final int j = i;
					assertAll("Checking Default Location",
							() -> Assertions.assertTrue(board.get(j) instanceof Location, 
									String.format(" ** board[%d] instanceOf %s", j, expectedType.get(j))),
							() -> Assertions.assertFalse(board.get(j) instanceof BridgeLocation, 
									String.format(" ** board[%d] instanceOf %s", j, expectedType.get(j))),
							() -> Assertions.assertFalse(board.get(j) instanceof GooseLocation, 
									String.format(" ** board[%d] instanceOf %s", j, expectedType.get(j)))
							);
				}
			}
		} catch (LocationCreateFailException e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 * Example 1: buildForDefaultWinLocationProvider
	 * @return
	 */
	private static Arguments buildForDefaultWinLocationExample1(){
		List<Location> locations = new ArrayList<>(); 
		Map<Integer, Class> expectedType = new HashMap<Integer, Class>();
		Integer winLocation = 20;
		try {
			locations.add(locationFactory.create(LocationType.BRIDGE, 5, 13));
			locations.add(locationFactory.create(LocationType.GOOSE, 11));
			
			
			for (Location location : locations) {
				if(location instanceof BridgeLocation){
					expectedType.put(location.getPosition(), BridgeLocation.class);
				}else if (location instanceof GooseLocation) {
					expectedType.put(location.getPosition(), GooseLocation.class);
				}else{
					expectedType.put(location.getPosition(), Location.class);
				}
			}
			
//			expectedType.put(0, GooseLocation.class);
			
			return Arguments.of(locations, expectedType, winLocation);
			
		} catch (LocationCreateFailException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * Example 2: buildForDefaultWinLocationProvider
	 * @return
	 */
	private static Arguments buildForDefaultWinLocationExample2(){
		List<Location> locations = new ArrayList<>(); 
		Map<Integer, Class> expectedType = new HashMap<Integer, Class>();
		Integer winLocation = 20;
		try {
			locations.add(locationFactory.create(LocationType.BRIDGE, 5, 13));
			locations.add(locationFactory.create(LocationType.GOOSE, 11));
			
			
			for (Location location : locations) {
				if(location instanceof BridgeLocation){
					expectedType.put(location.getPosition(), BridgeLocation.class);
				}else if (location instanceof GooseLocation) {
					expectedType.put(location.getPosition(), GooseLocation.class);
				}else{
					expectedType.put(location.getPosition(), Location.class);
				}
			}
//			expectedType.put(0, GooseLocation.class);
			
			return Arguments.of(locations, expectedType, winLocation);
		} catch (LocationCreateFailException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	private static Stream<Arguments> buildForDefaultWinLocationProvider(){
		List<Arguments> list = new ArrayList<>();
		Arguments arg = buildForDefaultWinLocationExample2();
		if(arg != null) list.add(arg);
		Arguments arg2 = buildForDefaultWinLocationExample2();
		if(arg2 != null) list.add(arg2);
			
		return list.stream();
	}
	
	/**
	 * 
	 * @return
	 */
	private static Stream<Arguments> addLocationThrowExceptionProvider(){
		return Stream.of(
				Arguments.of(0),
				Arguments.of(63)
				);
	}
	
	/**
	 * 
	 * @return
	 */
	private static Stream<Arguments> addLocationWithCustomLimitThrowExceptionProvider(){
		return Stream.of(
				Arguments.of(0, 60),
				Arguments.of(60, 60)
//				,Arguments.of(59, 60),Arguments.of(1, 60)
				);
	}
	

	@BeforeAll
	static void initAll(){
		locationFactory = new LocationFactory();		
	} 
	
	@AfterAll
	static void cleanAll(){
		locationFactory = new LocationFactory();		
	} 
	
}


//	@BeforeEach
//    void initEach() {
//		try {
//			boardBuilder= new BoardBuilder(locationFactory);
//		} catch (LocationCreateFailException e) {
//			e.printStackTrace();
//		} 
//    }
//	
//	@AfterEach
//	void cleanEach(){
//		boardBuilder.reset();
//		boardBuilder = null;
//	}

/*	
@Test
public void build(){
	try {
		BoardBuilder boardBuilder = new BoardBuilder(locationFactory);
		
		
		List<BoardLocationI> board = boardBuilder.addLocation(locationFactory.create(LocationType.BRIDGE, 5, 12)) // bridge at 5
					.addLocation(locationFactory.create(LocationType.GOOSE, 7)) // Goose at 7
					.build();
		Assertions.assertTrue(board.get(0) instanceof Location);
		Assertions.assertTrue(board.get(5) instanceof Location);
		Assertions.assertTrue(board.get(5) instanceof BridgeLocation);
		Assertions.assertTrue(board.get(7) instanceof Location);
					
		for (int i = 0; i < board.size(); i++) {
			if(i == 5|| i == 7){
				
			}else{
				assertAll("Checking Default Location",
					    () -> Assertions.assertTrue(board.get(0) instanceof Location),
					    () -> Assertions.assertFalse(board.get(0) instanceof BridgeLocation),
					    () -> Assertions.assertFalse(board.get(0) instanceof GooseLocation)
					);
			}
		}
		
	} catch (LocationCreateFailException | LocationInsertFailException e) {
		e.printStackTrace();
	}
}
*/

