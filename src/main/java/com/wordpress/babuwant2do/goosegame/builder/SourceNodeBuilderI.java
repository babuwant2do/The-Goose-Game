package com.wordpress.babuwant2do.goosegame.builder;

import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

public interface SourceNodeBuilderI extends NodeBuilderBaseI{
	public SourceNodeBuilderI setUser(User user);
	public NodeBuilder setUser(String name);
	public SourceNodeBuilderI setMove(Move move);
	public SourceNodeBuilderI setMove(Integer dice1, Integer dice2);
	
}
