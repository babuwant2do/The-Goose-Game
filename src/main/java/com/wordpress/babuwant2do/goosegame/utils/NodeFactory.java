package com.wordpress.babuwant2do.goosegame.utils;

import com.wordpress.babuwant2do.goosegame.action.decorator.BridgeNodeDecorator;
import com.wordpress.babuwant2do.goosegame.action.decorator.GooseNodeDecorator;
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

public class NodeFactory {
	/**
	 * create source Node
	 * @param location
	 * @param user
	 * @param move
	 * @return
	 */
	public NodeI create(Location location, User user, Move move){
		return new SourceNode(location, move, user);
	}
	
	/**
	 * Create Destination Node depends on Location Type
	 * @param sourceNode
	 * @param location
	 * @return
	 */
	public NodeI create(NodeI sourceNode, BoardLocationI location) throws NodeCreateFailException{
		if(location instanceof BridgeLocation){
			return new BridgeNodeDecorator(sourceNode, (BridgeLocation)location);
		}else if(location instanceof GooseLocation){
			return new GooseNodeDecorator(sourceNode, (GooseLocation)location);
		}else if(location instanceof Location){
			return new SimpleNodeDecorator(sourceNode, (Location)location);
		}
		throw new NodeCreateFailException("Unmanageable source node provided.");
	}
}
