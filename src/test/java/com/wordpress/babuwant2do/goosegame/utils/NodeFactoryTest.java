package com.wordpress.babuwant2do.goosegame.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wordpress.babuwant2do.goosegame.action.decorator.BridgeNodeDecorator;
import com.wordpress.babuwant2do.goosegame.action.decorator.GooseNodeDecorator;
import com.wordpress.babuwant2do.goosegame.action.decorator.NodeDecorator;
import com.wordpress.babuwant2do.goosegame.action.decorator.NodeI;
import com.wordpress.babuwant2do.goosegame.action.decorator.SimpleNodeDecorator;
import com.wordpress.babuwant2do.goosegame.action.decorator.SourceNode;
import com.wordpress.babuwant2do.goosegame.board.BoardLocationI;
import com.wordpress.babuwant2do.goosegame.board.BridgeLocation;
import com.wordpress.babuwant2do.goosegame.board.GooseLocation;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.exceptions.NodeCreateFailException;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;
import com.wordpress.babuwant2do.goosegame.utils.NodeFactory;

public class NodeFactoryTest {
	static NodeFactory nodeFactory = null;
	
	static SourceNode sourceNode;
	
	static Location location;
	static BridgeLocation bridgeLocation;
	static GooseLocation gooseLocation;
	static User user;
	static Move move;
	private static Integer MAX_LOCATION_TEST = 63;
	
	@BeforeAll
    static void initAll() {
    	NodeFactoryTest.nodeFactory= new NodeFactory(); 
    	
    	location = new Location(0);
    	gooseLocation = new GooseLocation(1);
    	bridgeLocation = new BridgeLocation(1, 2);
    	
    	sourceNode = new SourceNode(location, move, user, NodeFactoryTest.MAX_LOCATION_TEST);

    	user = new User("XXX");
    	move = new Move(2, 3);
    }
	
	@Test
	public void typeCheck(){
		assertNotNull(NodeFactoryTest.nodeFactory, "Location factory created null");
		try {
			assertTrue(NodeFactoryTest.nodeFactory.create(location, user, move, MAX_LOCATION_TEST) instanceof NodeI, "nodeFactory.create(location, user, move) instanceof NodeI");
			assertTrue(NodeFactoryTest.nodeFactory.create(location, user, move, NodeFactoryTest.MAX_LOCATION_TEST) instanceof SourceNode, "nodeFactory.create(location, user, move) instanceof SourceNode");
			
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, location) instanceof NodeI, "nodeFactory.create(sourceNode, location) instanceof NodeI");
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, location) instanceof NodeDecorator, "nodeFactory.create(sourceNode, location) instanceof NodeDecorator");
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, location) instanceof SimpleNodeDecorator, "nodeFactory.create(sourceNode, location) instanceof SimpleNodeDecorator");

			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, bridgeLocation) instanceof NodeI, "nodeFactory.create(sourceNode, bridgeLocation) instanceof NodeI");
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, bridgeLocation) instanceof NodeDecorator, "nodeFactory.create(sourceNode, bridgeLocation) instanceof NodeDecorator");
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, bridgeLocation) instanceof BridgeNodeDecorator, "nodeFactory.create(sourceNode, bridgeLocation) instanceof BridgeNodeDecorator");
			
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, gooseLocation) instanceof NodeI, "nodeFactory.create(sourceNode, gooseLocation) instanceof NodeI");
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, gooseLocation) instanceof NodeDecorator, "nodeFactory.create(sourceNode, gooseLocation) instanceof NodeDecorator");
			assertTrue(NodeFactoryTest.nodeFactory.create(sourceNode, gooseLocation) instanceof GooseNodeDecorator, "nodeFactory.create(sourceNode, gooseLocation) instanceof GooseNodeDecorator");
		
		} catch (NodeCreateFailException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void exceptionCheck(){
		// case 1 : Trying to create Bridge Type with wrong 
		NodeCreateFailException ex1 = Assertions.assertThrows(NodeCreateFailException.class, 
			() -> {
			    nodeFactory.create(sourceNode, new TestInvalidLocation());
			  });
		
		assertEquals("Failed to create Node. Unmanageable source node provided.", ex1.getMessage());
	}
	
	@AfterAll
    static void cleanAll() {
		NodeFactoryTest.nodeFactory= null;
		location = null;
    	gooseLocation = null;
    	bridgeLocation = null;
    }	
}

class TestInvalidLocation implements BoardLocationI{
	public TestInvalidLocation() {
	}

	@Override
	public Integer getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNextPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
