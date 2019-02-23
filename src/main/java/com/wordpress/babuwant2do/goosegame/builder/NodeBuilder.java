package com.wordpress.babuwant2do.goosegame.builder;

import com.wordpress.babuwant2do.goosegame.action.decorator.NodeI;
import com.wordpress.babuwant2do.goosegame.action.decorator.SourceNode;
import com.wordpress.babuwant2do.goosegame.board.Location;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public class NodeBuilder implements SourceNodeBuilderI, SimpleNodeBuilderI, BridgeNodeBuilderI{
	private Move move;
	private User user;
	private Location location;
	
//	public NodeBuilder(Location location) {
//		this.location = location;
//	}
//	
//	
//	@Override
//	public NodeI build() {
//		if(this.location instanceof )
//	}
	
	public NodeBuilder setLocation(Location location) {
		this.location = location;
		return this;
	}

	@Override
	public NodeBuilder setUser(User user) {
		this.user = user;
		return this;
	}
	
	@Override
	public NodeBuilder setUser(String name) {
		if(this.user == null){
			this.user = new User(name);
		}
		return this;
	}
	
	@Override
	public NodeBuilder setMove(Integer dice1, Integer dice2) {
		if(this.move == null){
			this.move = new Move(dice1, dice2);
		}else{
			this.move.setDice1(dice1);
			this.move.setDice2(dice2);
		}
		return this;
	}

	@Override
	public NodeBuilder setMove(Move move) {
		this.move = move;
		return this;
	}

}
