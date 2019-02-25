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

//import com.wordpress.babuwant2do.goosegame.board.BridgeLocation;
import com.wordpress.babuwant2do.goosegame.board.GooseLocation;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class GoesNodeDecoratorTest {
	
	@ParameterizedTest(name = "{index} => SourceNode(pos={2}, user={1}, move=({3},{4}))}")
	@DisplayName("test Public Methods")
	@MethodSource("taskTotalUnitProvider")
	public void testLocation(GooseNodeDecorator node, 
			String exp_userName, Integer exp_SrcPosition, 
			Integer exp_dice1, Integer exp_dice2, Integer exp_destination,
			Integer exp_bounds, Integer exp_nextStop, Integer exp_currentPosition){
		
		//user
		assertNotNull(node.getUser(),String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_userName,node.getUser().getName(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		
		//move
		assertNotNull(node.getMove(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		assertAll("Checking Players Move",
			    () -> assertEquals(exp_dice1, node.getMove().getDice1(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getMove().getDice1()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2)),
			    () -> assertEquals(exp_dice2, node.getMove().getDice2(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getMove().getDice2()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2))
			);
		
		// Location : current location (dest position)
		assertNotNull(node.getLocation(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_currentPosition,node.getPosition(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getLocation().getPosition()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		
		// bounds
		assertNotNull(node.getStepsOverFlow(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_bounds,node.getStepsOverFlow(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		
		//Destination : next Node 
		assertNotNull(node.getDestination(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_destination,node.getDestination(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		
		//Next Stop
		assertNotNull(node.getNextStop(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getUser().getName()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		assertEquals( exp_nextStop,node.getNextStop(), String.format("new SourceNode(%d, {%s}, {%d, %d}).getDestination()", exp_SrcPosition, exp_userName, exp_dice1, exp_dice2));
		
		//Print
//		System.out.println("GGGGGGGGGGGGGGGG  "+node.getResponds());
		
	}
	
	/**
	 * 
	 * @param userName
	 * @param src_position
	 * @param dice1
	 * @param dice2
	 * @param dest_position
	 * @param destToJumpLocation
	 * @param exp_nextStop
	 * @param exp_bounds
	 * @param exp_destination
	 * @return
	 */
	private static Arguments createArg( 
			String userName, 
			Integer src_position, 
			Integer dice1, Integer dice2, 
			Integer dest_position,
			Integer exp_nextStop,
			Integer exp_bounds, 
			Integer exp_destination
			){
		
		//[1]user, [2]SrcPosition, [3]dice1, [4]dice2, [5]dest_pos,[ [6]JumpLocation, 
		// [7]Exp_nextStop ] [8] exp_bounds [9] exp_destination
		//"Pippo", 1, 				2, 			3,		 0, 		6, 				6				10)
		
		//dependency
		User user = new User(userName);
		Location location = new Location(src_position);
		Move move = new Move(dice1, dice2);
		
		//TO Test
		SourceNode sourceNode = new SourceNode(location, move, user);
		
		//dest node
		Location currentLocation = new GooseLocation(dest_position);
		GooseNodeDecorator destNode = new GooseNodeDecorator(sourceNode, currentLocation);
		
		return Arguments.of(destNode, 
				userName, src_position, 
				dice1,dice2, exp_destination, 
				exp_bounds, exp_nextStop, dest_position);
		
	}
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> taskTotalUnitProvider() {
		
		List<Arguments> list = new ArrayList<Arguments>();
		
		// [1]user, [2]SrcPosition, [3]dice1, [4]dice2, [5]dest_pos,
		// [6]Exp_nextStop ] [7] exp_bounds [8] exp_destination
				
		list.add(createArg("Pippo", 1, 2, 3, 6, 11, -52, 11)); // test normal
		list.add(createArg("Pippo", 3, 1, 1, 5, 7, -56, 7)); // test normal
		list.add(createArg("Pippo", 55, 2, 3, 60, 63, 2, 61)); // test normal
		list.add(createArg("Pippo", 57, 2, 1, 60, 63, 0, 63)); // test normal
//		list.add(createArg("Pippo", 4, 1, 1, 6, 12, 0, 12)); // test normal
//		list.add(createArg("Pippo", 5, 2, 3, 0, 10, 10)); // test normal
//		list.add(createArg("Pippo", 60, 3, 2, 0, 61, 61)); // test bounds
//		list.add(createArg("Pippo", 60, 1, 2, 0, 63, 63)); // test wins
//		list.add(createArg("Pippo", 60, 1, 1, 0, 62, 62)); // normal 
//		
		return list.stream();
    }
}
