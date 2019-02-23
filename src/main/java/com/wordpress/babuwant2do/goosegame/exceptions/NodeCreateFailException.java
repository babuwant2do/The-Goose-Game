package com.wordpress.babuwant2do.goosegame.exceptions;

public class NodeCreateFailException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1993069893651255707L;
	public NodeCreateFailException(){
		super("Failed to create Node.");
	}
	public NodeCreateFailException(String message){
		super("Failed to create Node. "+message);
	}

}
