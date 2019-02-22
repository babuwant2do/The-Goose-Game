package com.wordpress.babuwant2do.goosegame.action.decorator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.wordpress.babuwant2do.goosegame.board.*;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class SourceNodeTest {
	
	@Test
	public void testResponse(){
		
		//Expected value
		Integer exp_position = 5;
		Integer exp_dice1 = 2;
		Integer exp_dice2 = 3;
		Integer exp_nextLocation = 10;
		String exp_userName = "Pippo";
		String responseFormate = "%s rolls %d, %d. %s moves from %s to %d";
		String exp_response = String.format(responseFormate,
				exp_userName, exp_dice1, exp_dice2, exp_userName,
				( exp_position == 1 ? "Start": exp_position), exp_nextLocation);

		//dependency
		User user = new User(exp_userName);
		Location location = new Location(exp_position);
		Move move = new Move(exp_dice1, exp_dice2);
		
		//TO Test
		SourceNode node = new SourceNode(location, move, user);
		assertEquals( exp_response,node.getResponds(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
	}
	
	/**
	 * 
	 * @param node - to test 
	 * @param exp_userName
	 * @param exp_position
	 * @param exp_dice1
	 * @param exp_dice2
	 * @param exp_destination
	 * @param exp_bounds
	 * @param exp_nextStop
	 */
	@ParameterizedTest(name = "{index} => SourceNode(pos={2}, user={1}, move=({3},{4}))}")
	@DisplayName("Should calculate the correct sum")
	@MethodSource("taskTotalUnitProvider")
	public void testLocation(SourceNode node, 
			String exp_userName, Integer exp_position, 
			Integer exp_dice1, Integer exp_dice2, Integer exp_destination,
			Integer exp_bounds, Integer exp_nextStop){
		
		assertEquals( exp_userName,node.getUser().getName(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_position,node.getLocation().getPosition(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getLocation().getPosition()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_destination,node.getDestination(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
		assertAll("Checking Players Move",
			    () -> assertEquals(exp_dice1, node.getMove().getDice1(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getMove().getDice1()", exp_position, exp_userName, exp_dice1, exp_dice2)),
			    () -> assertEquals(exp_dice2, node.getMove().getDice2(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getMove().getDice2()", exp_position, exp_userName, exp_dice1, exp_dice2))
			);

		assertEquals( exp_bounds,node.getStepsOverFlow(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_nextStop,node.getNextStop(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
	}
	
	/**
	 * 
	 * @param exp_userName - user name
	 * @param exp_position - index/position of the node
	 * @param exp_dice1 - value of dice 1
	 * @param exp_dice2 - valuue of dice 2
	 * @param exp_bounds - if bound = 0 : wins, if -ve : normal move, if +ve: bounds back
	 * @param exp_destination - final destination 
	 * @param exp_nextStop - if there is any intermediate stop (immediate next)
	 * @return
	 */
	private static Arguments createArg( 
			String exp_userName, Integer exp_position, 
			Integer exp_dice1, Integer exp_dice2, 
			Integer exp_bounds, 
			Integer exp_destination,
			Integer exp_nextStop){
		
		//dependency
		User user = new User(exp_userName);
		Location location = new Location(exp_position);
		Move move = new Move(exp_dice1, exp_dice2);
		
		//TO Test
		SourceNode node = new SourceNode(location, move, user);
		
		return Arguments.of(node, exp_userName, exp_position, exp_dice1,exp_dice2, exp_destination, exp_bounds, exp_nextStop);
		
	}
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> taskTotalUnitProvider() {
		
		List<Arguments> list = new ArrayList<Arguments>();
		//user, position, dice1, dice2, bounds,[ destination, nextStop ] 
		list.add(createArg("Pippo", 1, 2, 3, -57, 6, 6)); // test normal
		list.add(createArg("Pippo", 5, 2, 3, -53, 10, 10)); // test normal
		list.add(createArg("Pippo", 60, 3, 2, 2, 61, 63)); // test bounds
		list.add(createArg("Pippo", 60, 1, 2, 0, 63, 63)); // test wins
		list.add(createArg("Pippo", 60, 1, 1, -1, 62, 62)); // normal 
		
		return list.stream();
    }
	
	
	/**
	 * "Pippo rolls 1, 2. Pippo moves from 4 to {7}"
	 * 
	 * "Pippo rolls 1, 1. Pippo moves from 4 to {The Bridge}| 
	 * 					. Pippo jumps to {12}"
	 *  Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7
	 * "Pippo rolls 1, 1. Pippo moves from 3 to {5, The Goose}| 
	 * 					. Pippo moves again and goes to {7}"
	 * 
	 * "Pippo rolls 2, 2. Pippo moves from 10 to {14, The Goose}| 
	 * 					. Pippo moves again and goes to {18, The Goose}
	 * 					. Pippo moves again and goes to {22}"
	 * 
	 * 
	 * WINS:
	 * "Pippo rolls 1, 2. Pippo moves from 60 to 63
	 * 					. Pippo Wins!!"
	 * 
	 * "Pippo rolls 3, 2. Pippo moves from 60 to 63
	 * 					. Pippo bounces! Pippo returns to 61"
	 */
	
	
//	@BeforeAll
//    void beforeAllTests() {}
//
//    @AfterAll
//    void afterAllTests() {}
//
//    @BeforeEach
//    void beforeEachTest() {}
//
//    @AfterEach
//    void afterEachTest() {}

}
