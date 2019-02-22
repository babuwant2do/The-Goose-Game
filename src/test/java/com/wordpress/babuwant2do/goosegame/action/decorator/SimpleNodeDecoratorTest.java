package com.wordpress.babuwant2do.goosegame.action.decorator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class SimpleNodeDecoratorTest {

	@ParameterizedTest(name = "{index} => SourceNode(pos={2}, user={1}, move=({3},{4}))}")
	@DisplayName("test Public Methods")
	@MethodSource("taskTotalUnitProvider")
	public void testLocation(SimpleNodeDecorator node, 
			String exp_userName, Integer exp_position, 
			Integer exp_dice1, Integer exp_dice2, Integer exp_destination,
			Integer exp_bounds, Integer exp_nextStop){
		
		//user
		assertNotNull(node.getUser(),String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_userName,node.getUser().getName(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
		//move
		assertNotNull(node.getMove(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertAll("Checking Players Move",
			    () -> assertEquals(exp_dice1, node.getMove().getDice1(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getMove().getDice1()", exp_position, exp_userName, exp_dice1, exp_dice2)),
			    () -> assertEquals(exp_dice2, node.getMove().getDice2(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getMove().getDice2()", exp_position, exp_userName, exp_dice1, exp_dice2))
			);
		
		// Location
		assertNotNull(node.getLocation(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_destination,node.getLocation().getPosition(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getLocation().getPosition()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
		// bounds
		assertNotNull(node.getBounds(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_bounds,node.getBounds(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
		//Destination
		assertNotNull(node.getDestination(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_destination,node.getDestination(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
		//Next Stop
		assertNotNull(node.getNextStop(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_position, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_nextStop,node.getNextStop(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_position, exp_userName, exp_dice1, exp_dice2));
		
		//Print
		System.out.println("88888888888888888888  "+node.getResponds());
		
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
		SourceNode sourceNode = new SourceNode(location, move, user);
		
		//dest node
		Location currentLocation = new Location(exp_destination);
		SimpleNodeDecorator simpleNode = new SimpleNodeDecorator(sourceNode, currentLocation);
		
		return Arguments.of(simpleNode, exp_userName, exp_position, exp_dice1,exp_dice2, exp_destination, exp_bounds, exp_nextStop);
		
	}
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> taskTotalUnitProvider() {
		
		List<Arguments> list = new ArrayList<Arguments>();
		//[1]user, [2]SrcPosition, [3]dice1, [4]dice2, [5]bounds,[ [6]destination, [7]nextStop ] 
		list.add(createArg("Pippo", 1, 2, 3, -57, 6, 6)); // test normal
		list.add(createArg("Pippo", 5, 2, 3, -53, 10, 10)); // test normal
		list.add(createArg("Pippo", 60, 3, 2, -2, 61, 61)); // test bounds: bounds -2 because it counts bounds from 61
		list.add(createArg("Pippo", 60, 1, 2, 0, 63, 63)); // test wins
		list.add(createArg("Pippo", 60, 1, 1, -1, 62, 62)); // normal 
		
		return list.stream();
    }
}
