package com.wordpress.babuwant2do.goosegame.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wordpress.babuwant2do.goosegame.game.User;


public class GameBoardTest {
	
	GameBoard gameBoard;
	GameBoard gameBoardWithWinPos;
	
	@BeforeEach
	public void init(){
		this.gameBoard = new GameBoard();
		this.gameBoardWithWinPos = new GameBoard(20);
	}
	

	@AfterEach
    void cleanAll() {
		this.gameBoard = null;
		this.gameBoardWithWinPos = null;
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
	public void board(){
		assertEquals("players: Pluto", this.gameBoard.addUser(new User("Pluto")));
	}

}
