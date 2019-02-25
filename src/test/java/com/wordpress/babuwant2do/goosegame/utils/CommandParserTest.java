package com.wordpress.babuwant2do.goosegame.utils;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.wordpress.babuwant2do.goosegame.game.CommandType;
import com.wordpress.babuwant2do.goosegame.game.GameAction;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class CommandParserTest {
	
	@ParameterizedTest
	@MethodSource("validInputProvider")
	public void CommandParserToSuccess(String input, String expected){
		CommandParser commandParser = new CommandParser();
		Assertions.assertEquals(expected, commandParser.extractValidCommand(input));
	}
	
	@ParameterizedTest
	@MethodSource("invalidInputProvider")
	public void CommandParserToFail(String input){
		CommandParser commandParser = new CommandParser();
		Assertions.assertNull(commandParser.extractValidCommand(input), input);
	}

	@ParameterizedTest
	@MethodSource("gameActionInputProvider")
	public void getCommand(String input, GameAction expected){
		CommandParser commandParser = new CommandParser();
		GameAction toTest = commandParser.getCommand(input);
		Assertions.assertAll("getCommand",
				() -> Assertions.assertEquals(expected.getType(), toTest.getType(), "expected.getType()"),
				() -> Assertions.assertEquals(expected.getUser(), toTest.getUser(), "expected.getUser()")
			);
		
		if(expected.getCommand() != null){
			Assertions.assertNotNull(toTest.getCommand());
			if(expected.getCommand() instanceof Move){
				Assertions.assertAll("getCommand",
						() -> Assertions.assertEquals(((Move)expected.getCommand()).getDice1(), 
								((Move)toTest.getCommand()).getDice1(), "expected.getCommand().getDice1()"),
						() -> Assertions.assertEquals(((Move)expected.getCommand()).getDice2(), 
								((Move)toTest.getCommand()).getDice2(), "expected.getCommand().getDice2()")
						);				
			}
		}else if(expected.getType() == CommandType.MOVE){
			Assertions.assertAll("getCommand with default MOVE",
					() -> Assertions.assertNotNull(toTest.getCommand(), ""),
					() -> Assertions.assertTrue(((Move)toTest.getCommand()).getDice1() > 0 && ((Move)toTest.getCommand()).getDice1() < 7 ,  "expected.getCommand().getDice1() between 1-6"),
					() -> Assertions.assertTrue(((Move)toTest.getCommand()).getDice2() > 0 && ((Move)toTest.getCommand()).getDice2() < 7 ,  "expected.getCommand().getDice2()  between 1-6")
					);	
		}
		else{
			Assertions.assertNull(toTest.getCommand());			
		}
	}
	
	private static Stream<Arguments> gameActionInputProvider() {
		return Stream.of(
				//Add 
				Arguments.of("add player Pippo", new GameAction(CommandType.ADD_USER, new User("Pippo")) ),
				Arguments.of("move     Pippo   4 , 2  ", new GameAction(CommandType.MOVE, new User("Pippo"), new Move(4, 2))),
				Arguments.of("move     Pippo  ", new GameAction(CommandType.MOVE, new User("Pippo"))),
				Arguments.of("move Pippo 4, 2", new GameAction(CommandType.MOVE, new User("Pippo"), new Move(4, 2))),
				Arguments.of("move Pippo 4,2", new GameAction(CommandType.MOVE, new User("Pippo"), new Move(4, 2)))
				);
    }
	
	private static Stream<Arguments> validInputProvider() {
		return Stream.of(
				//Add 
				Arguments.of("add player Pippo", "add player Pippo"),
				Arguments.of("ADD player Pippo1", "ADD player Pippo1"),
				Arguments.of("add player Pippo", "add player Pippo"),
				Arguments.of("add PLAyer Pippo", "add PLAyer Pippo"),
				Arguments.of("  add PLAyer Pippo", "add PLAyer Pippo"),
				
				// Move	
				Arguments.of("move Pippo 4,2", "move Pippo 4,2"),
				Arguments.of("move Pippo 4, 2", "move Pippo 4, 2"),
				Arguments.of("move Pippo 1, 6", "move Pippo 1, 6"),
				Arguments.of("move Pippo 4 , 2     ", "move Pippo 4 , 2"),
				Arguments.of("move     Pippo   4 , 2  ", "move Pippo 4 , 2"),
				
				Arguments.of("move Pippo", "move Pippo"),
				Arguments.of("move     Pippo     ", "move Pippo")
				);
	}
	
	private static Stream<Arguments> invalidInputProvider() {
		return Stream.of(
				Arguments.of("ADD player Pippo1 last"),
				Arguments.of("ADD player Pippo1 2"),
				Arguments.of("ADD player 2 Pippo1"),
	
				Arguments.of("move Pippo 4, 7"),
				Arguments.of("move Pippo 0, 6"),
				Arguments.of("move Pippo 0, 7"),
				Arguments.of("move x Pippo 0, 7"),
				Arguments.of("xmove Pippo 4,2"),
				
				Arguments.of("move x Pippo"),
				Arguments.of("x move Pippo"),
				Arguments.of("move Pippo  7"),
				Arguments.of("move Pippo x")
				);
		
	}

}
