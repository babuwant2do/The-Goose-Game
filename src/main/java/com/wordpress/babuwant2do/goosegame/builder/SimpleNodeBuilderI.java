package com.wordpress.babuwant2do.goosegame.builder;

import com.wordpress.babuwant2do.goosegame.action.decorator.NodeI;

public interface SimpleNodeBuilderI  extends NodeBuilderBaseI{
	public default NodeI build() {
		return null;
	}
}
