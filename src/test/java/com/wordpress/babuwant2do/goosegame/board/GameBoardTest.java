package com.wordpress.babuwant2do.goosegame.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationInsertFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.NodeCreateFailException;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;
import com.wordpress.babuwant2do.goosegame.utils.BoardBuilder;
import com.wordpress.babuwant2do.goosegame.utils.LocationFactory;
import com.wordpress.babuwant2do.goosegame.utils.LocationType;
import com.wordpress.babuwant2do.goosegame.utils.NodeFactory;


public class GameBoardTest {
	
	GameBoard gameBoard;
	LocationFactory locationFactory;
	
	@BeforeEach
	public void init(){
		//5, 9, 14, 18, 23, 27
		locationFactory = new LocationFactory();
		try {
			BoardBuilder boardBuilder = new BoardBuilder(locationFactory);
			
//			boardBuilder.addLocation(locationFactory.create(LocationType.BRIDGE, 6, 12))
//						.addLocation(locationFactory.create(LocationType.GOOSE, 5))
//						.addLocation(locationFactory.create(LocationType.GOOSE, 9))
//						.addLocation(locationFactory.create(LocationType.GOOSE, 14))
//						.addLocation(locationFactory.create(LocationType.GOOSE, 18))
//						.addLocation(locationFactory.create(LocationType.GOOSE, 27));
			
			this.gameBoard = new GameBoard(boardBuilder.build(), new NodeFactory());
		} catch (LocationCreateFailException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	@AfterEach
    void cleanAll() {
		this.locationFactory = null;
		this.gameBoard = null;
    }
	
//	@Test
	public void moveUser(){
		this.gameBoard.addUser(new User("Pippo"));
		try {
			assertNotNull(this.gameBoard.moveUser(new User("Pippo"), new Move(4, 2)), "move Pippo 4, 2");
		} catch (NodeCreateFailException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void resetBoard(){
		Assertions.assertTrue(false, "check resetBoard()");
	}
	
	@Test
	public void addUser(){
		assertAll("CheckingAdd Player",
			    () -> assertNotNull(this.gameBoard.addUser(new User("Pippo")), "gameBoard.addUser(user)"),
			    () -> assertEquals("players: Pippo, Pluto", this.gameBoard.addUser(new User("Pluto"))),
			    () -> assertEquals("Pippo: already existing player", this.gameBoard.addUser(new User("Pippo")))
			);
	}
	
	@Test
	public void Scenario1(){
		User pippo = new User("Pippo");
		User pluto = new User("Pluto");
		
		this.gameBoard.addUser(pippo );
		this.gameBoard.addUser(pluto);
		
		assertAll("**Scenarios:** 1. Start : ",
			    () -> assertEquals("Pippo rolls 4, 2. Pippo moves from Start to 6"
			    		, this.gameBoard.moveUser(pippo, new Move(4, 2))),
			    () -> assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4"
			    		, this.gameBoard.moveUser(pluto, new Move(2, 2))),
			    () -> assertEquals("Pippo rolls 2, 3. Pippo moves from 6 to 11"
			    		, this.gameBoard.moveUser(pippo, new Move(2, 3)))
			    
			);
		
	}
	
	
	@Test
	public void Scenario2(){
		try {
			LocationFactory sc_locationFactory = new LocationFactory();
			BoardBuilder sc_boardBuilder = new BoardBuilder(sc_locationFactory);
			
			GameBoard sc_gameBoard = new GameBoard(sc_boardBuilder.build(), new NodeFactory());
			
//			sc_boardBuilder.addLocation(sc_locationFactory.create(LocationType.BRIDGE, 6, 12))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 5))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 9))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 14))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 18))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 27));

			
			User pippo = new User("Pippo");
			User pluto = new User("Pluto");
			
			sc_gameBoard.addUser(pippo );
			sc_gameBoard.addUser(pluto);
			
			assertAll("**Scenarios:** 1. Start : ",
					() -> assertEquals("Pippo rolls 4, 2. Pippo moves from Start to 6"
							, sc_gameBoard.moveUser(pippo, new Move(4, 2))),
					() -> assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4"
							, sc_gameBoard.moveUser(pluto, new Move(2, 2))),
					() -> assertEquals("Pippo rolls 2, 3. Pippo moves from 6 to 11"
							, sc_gameBoard.moveUser(pippo, new Move(2, 3)))
					
					);
			//Reset Board
			sc_gameBoard.resetBoard();
			
			assertAll("**Scenarios:** 1: Repeate. Start : ",
					() -> assertEquals("Pippo rolls 4, 2. Pippo moves from Start to 6"
							, sc_gameBoard.moveUser(pippo, new Move(4, 2))),
					() -> assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4"
							, sc_gameBoard.moveUser(pluto, new Move(2, 2))),
					() -> assertEquals("Pippo rolls 2, 3. Pippo moves from 6 to 11"
							, sc_gameBoard.moveUser(pippo, new Move(2, 3)))
					
					);
			
		} catch (LocationCreateFailException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void Scenario3(){
		try {
			LocationFactory sc_locationFactory = new LocationFactory();
			BoardBuilder sc_boardBuilder = new BoardBuilder(sc_locationFactory);
			
			GameBoard sc_gameBoard = new GameBoard(sc_boardBuilder.build(), new NodeFactory());
			
//			sc_boardBuilder.addLocation(sc_locationFactory.create(LocationType.BRIDGE, 6, 12))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 5))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 9))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 14))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 18))
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 27));
			
			
			User pippo = new User("Pippo");
			User pluto = new User("Pluto");
			
			sc_gameBoard.addUser(pippo );
			sc_gameBoard.addUser(pluto);
			
			//Set location of user
			Map<User, Location> newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 60));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			assertEquals("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!"
					, sc_gameBoard.moveUser(pippo, new Move(1, 2)));
			
			//Reset Board
			sc_gameBoard.resetBoard();
			//Set location of user
			newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 60));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			assertEquals("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61"
					, sc_gameBoard.moveUser(pippo, new Move(3, 2)));
			
			
			
		} catch (LocationCreateFailException | NodeCreateFailException  e) {
			e.printStackTrace();
		}
		
	}

	
	//			**Scenarios:**
//			1. Get to "The Bridge"
	@Test
	public void Scenario4(){
		try {
			LocationFactory sc_locationFactory = new LocationFactory();
			BoardBuilder sc_boardBuilder = new BoardBuilder(sc_locationFactory);
			
			sc_boardBuilder.addLocation(sc_locationFactory.create(LocationType.BRIDGE, 6, 12));
			GameBoard sc_gameBoard = new GameBoard(sc_boardBuilder.build(), new NodeFactory());
			
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 5))
			
			User pippo = new User("Pippo");
			User pluto = new User("Pluto");
			
			sc_gameBoard.addUser(pippo );
			sc_gameBoard.addUser(pluto);
			
			//Set location of user
			Map<User, Location> newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 4));
			newLocation.put(pluto, sc_locationFactory.create(LocationType.DEFAULT, 4));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			
			//Reset Board
			//Set location of user
			assertEquals("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12"
					, sc_gameBoard.moveUser(pippo, new Move(1, 1)));
			assertEquals("Pluto rolls 1, 1. Pluto moves from 4 to The Bridge. Pluto jumps to 12"
					, sc_gameBoard.moveUser(pluto, new Move(1, 1)));
			
		} catch (LocationCreateFailException | NodeCreateFailException | LocationInsertFailException e) {
			e.printStackTrace();
		}
		
	}
	//			**Scenarios:**
//			1. Get to "Single/double Jump"
	@Test
	public void Scenario5(){
		try {
			LocationFactory sc_locationFactory = new LocationFactory();
			BoardBuilder sc_boardBuilder = new BoardBuilder(sc_locationFactory);
			
			sc_boardBuilder.addLocation(sc_locationFactory.create(LocationType.GOOSE, 5))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 9))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 14))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 18))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 27));
			
			GameBoard sc_gameBoard = new GameBoard(sc_boardBuilder.build(), new NodeFactory());
			
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 5))
			
			User pippo = new User("Pippo");
			User pluto = new User("Pluto");
			
			sc_gameBoard.addUser(pippo );
			sc_gameBoard.addUser(pluto);
			
			//Set location of user
			Map<User, Location> newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 3));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			
			//Reset Board
			//Set location of user
			assertEquals("Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7"
					, sc_gameBoard.moveUser(pippo, new Move(1, 1)));
			
			// MULTI JUMP
			
			//Reset Board
			sc_gameBoard.resetBoard();
			//Set location of user
			newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 10));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			//Reset Board
			//Set location of user
			assertEquals("Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22"
					, sc_gameBoard.moveUser(pippo, new Move(2, 2)));
			
			//Reset Board
			sc_gameBoard.resetBoard();
			//Set location of user
			newLocation = new HashMap<>();
			newLocation.put(pluto, sc_locationFactory.create(LocationType.DEFAULT, 10));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			// test for pluto
			assertEquals("Pluto rolls 2, 2. Pluto moves from 10 to 14, The Goose. Pluto moves again and goes to 18, The Goose. Pluto moves again and goes to 22"
					, sc_gameBoard.moveUser(pluto, new Move(2, 2)));
			
		} catch (LocationCreateFailException | NodeCreateFailException | LocationInsertFailException e) {
			e.printStackTrace();
		}
	}
	
	//			**CUSTOM Scenarios:**
	//			1. Get to "Single/double Jump"
	@Test
	public void Scenario6(){
		try {
			LocationFactory sc_locationFactory = new LocationFactory();
			BoardBuilder sc_boardBuilder = new BoardBuilder(sc_locationFactory);
			
			sc_boardBuilder
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 4)) // custom location
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 5))
			.addLocation(sc_locationFactory.create(LocationType.BRIDGE, 6, 10))// custom to 10 instead of 12
//			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 9))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 10))//custom location
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 14))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 18))
			.addLocation(sc_locationFactory.create(LocationType.GOOSE, 27));
			
			GameBoard sc_gameBoard = new GameBoard(sc_boardBuilder.build(), new NodeFactory());

			// add user
			User pippo = new User("Pippo");
			User pluto = new User("Pluto");
			sc_gameBoard.addUser(pippo );
			sc_gameBoard.addUser(pluto);
			
			// location [4:G][5:G][6:B-12][9:G][12:G][14:G][18:G][27:G]
			/*
			 * Pippo (1,1) [2] -> [2->6(b)->10->14->18->22] 
			 * "Pippo rolls 2, 2. Pippo moves from 2 to The Bridge. Pippo jumps to 10, The Goose. Pippo moves again and goes to  14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22"
			 */
			
			//Set location of user
			Map<User, Location> newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 2));
//			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 10));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			
			//Reset Board
			//Set location of user
			assertEquals("Pippo rolls 2, 2. Pippo moves from 2 to The Bridge. Pippo jumps to 10, The Goose. Pippo moves again and goes to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22"
					, sc_gameBoard.moveUser(pippo, new Move(2, 2)));
			
			/*
			// MULTI JUMP
			
			//Reset Board
			sc_gameBoard.resetBoard();
			//Set location of user
			newLocation = new HashMap<>();
			newLocation.put(pippo, sc_locationFactory.create(LocationType.DEFAULT, 10));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			//Reset Board
			//Set location of user
			assertEquals("Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22"
					, sc_gameBoard.moveUser(pippo, new Move(2, 2)));
			
			//Reset Board
			sc_gameBoard.resetBoard();
			//Set location of user
			newLocation = new HashMap<>();
			newLocation.put(pluto, sc_locationFactory.create(LocationType.DEFAULT, 10));
			sc_gameBoard.setUserInitialLocation(newLocation);
			
			// test for pluto
			assertEquals("Pluto rolls 2, 2. Pluto moves from 10 to 14, The Goose. Pluto moves again and goes to 18, The Goose. Pluto moves again and goes to 22"
					, sc_gameBoard.moveUser(pluto, new Move(2, 2)));
					
					*/
			
		} catch (LocationCreateFailException | NodeCreateFailException | LocationInsertFailException e) {
			e.printStackTrace();
		}
	}
	

}
