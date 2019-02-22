package com.wordpress.babuwant2do.goosegame.board;

public class WinLocation extends Location{

	public WinLocation(Integer position) {
		super(position, "Finish");
	}
	public WinLocation(Integer position, String name) {
		super(position, name);
	}
}
