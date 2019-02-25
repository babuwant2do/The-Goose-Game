package com.wordpress.babuwant2do.goosegame.action.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.wordpress.babuwant2do.goosegame.App;
import com.wordpress.babuwant2do.goosegame.board.BridgeLocation;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class NodeTest {
	
	@Test
	public void selfTest(){
		int MAX_LOCATION= 63;
		assertEquals(6, NodeTest.calculateDest(0, 2,4, MAX_LOCATION));
		assertEquals(11, NodeTest.calculateDest(6, 2,3, MAX_LOCATION));
		assertEquals(62, NodeTest.calculateDest(60, 1,1, MAX_LOCATION));
		assertEquals(63, NodeTest.calculateDest(60, 2,1, MAX_LOCATION));
		assertEquals(61, NodeTest.calculateDest(60, 2,3, MAX_LOCATION));
	}

	@ParameterizedTest
	@DisplayName("Should calculate the correct sum")
	@MethodSource("responseNodeProvider")
	public void parameterizedTestResponse(NodeI node, String exp_response){
		assertEquals( exp_response ,node.getResponds());		
	}	
	
	
	
	/**
	 * calculate destination based on source position and move by dice1 and dice 2
	 * @param fromPosition
	 * @param dice1Val
	 * @param dice2Val
	 * @param BOUND
	 * @return
	 */
	public static int calculateDest(int fromPosition,int dice1Val, int dice2Val, final int BOUND){
		Integer toPositionCalc = fromPosition + dice1Val + dice2Val;
		if(toPositionCalc > BOUND){
			toPositionCalc = BOUND - ((toPositionCalc)% BOUND);			
		}
		
		return toPositionCalc;
	} 
	
	/**
	 * 
	 * @param userName
	 * @param fromPosition
	 * @param dice1Val
	 * @param dice2Val
	 * @return
	 */
	private static NodeI createSourceNodeWithMove( 
			String userName, Integer fromPosition, 
			Integer dice1Val, Integer dice2Val
			){
		
		//dependency
		User user = new User(userName);
		Location location = new Location(fromPosition);
		Move move = new Move(dice1Val, dice2Val);
		
		//TO Test
		Integer MAX_LOCATION_TEST = 63;
		return new SourceNode(location, move, user, MAX_LOCATION_TEST);
		
	}
	
	/**
	 * Create a move from source 'fromPosition' : destination is a simple Node
	 * @param userName
	 * @param fromPosition
	 * @param dice1Val
	 * @param dice2Val
	 * @param toPosition
	 * @return
	 */
	private static NodeI createSimpleNodeByMove( 
			String userName, Integer fromPosition, 
			Integer dice1Val, Integer dice2Val, 
			Integer toPosition
			){
		
		// Src node
		NodeI sourceNode = createSourceNodeWithMove(userName, fromPosition, dice1Val, dice2Val);
		// Dest node
		Location currentLocation = new Location(toPosition);
		
		return new SimpleNodeDecorator(sourceNode, currentLocation);
	}
	
	/**
	 * Create a move from source 'fromPosition' : destination is a Bridge Node
	 * @param userName
	 * @param fromPosition
	 * @param dice1Val
	 * @param dice2Val
	 * @param toPosition
	 * @return
	 */
	private static NodeI createBridgeNodeByMove( 
			String userName, Integer fromPosition, 
			Integer dice1Val, Integer dice2Val, 
			Integer toPosition, Integer jumpLocation
			){
		
		// Src node
		NodeI sourceNode = createSourceNodeWithMove(userName, fromPosition, dice1Val, dice2Val);
		// Dest node
		Location currentLocation = new BridgeLocation(toPosition, jumpLocation);
		
		return new BridgeNodeDecorator(sourceNode, currentLocation);
	}
	
	/**
	 * 
	 * @param exp_userName
	 * @param exp_position
	 * @param exp_dice1
	 * @param exp_dice2
	 * @param exp_destination
	 * @param exp_response
	 * @return
	 */
	private static Arguments createArgForSimpleNode( 
			String exp_userName, Integer exp_position, 
			Integer exp_dice1, Integer exp_dice2, 
			Integer exp_destination,
			String exp_response
			){
				
		NodeI simpleNode = NodeTest.createSimpleNodeByMove(exp_userName, exp_position, exp_dice1, exp_dice2, exp_destination);
		return Arguments.of(simpleNode, exp_response);
		
	}
	
	private static Arguments createArgForBridgeNode( 
			String exp_userName, Integer exp_position, 
			Integer exp_dice1, Integer exp_dice2, 
			Integer jumpLocation,  Integer exp_destination,
			String exp_response
			){
		
		NodeI node = NodeTest.createBridgeNodeByMove(exp_userName, exp_position, exp_dice1, exp_dice2, jumpLocation, exp_destination);
		return Arguments.of(node, exp_response);
		
	}
	
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> responseNodeProvider() {
		
		List<Arguments> list = new ArrayList<Arguments>();
		//[1]user, [2]SrcPosition, [3]dice1, [4]dice2, [5]bounds,[ [6]destination,	
		// bad [7]nextStop ] 
		list.add(createArgForSimpleNode("Pippo", 0, 4, 2, 6, "Pippo rolls 4, 2. Pippo moves from Start to 6")); // test normal
		list.add(createArgForSimpleNode("Pluto", 0, 4, 2, 6, "Pluto rolls 4, 2. Pluto moves from Start to 6")); // test normal
		list.add(createArgForSimpleNode("Pippo", 60, 1, 2, 63, "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!")); // test normal
		list.add(createArgForSimpleNode("Pippo", 60, 3, 2, 61, "Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61")); // test normal
		
		list.add(createArgForBridgeNode("Pippo", 4, 1, 1, 12, 12, "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12")); // test normal

		return list.stream();
    }
}
