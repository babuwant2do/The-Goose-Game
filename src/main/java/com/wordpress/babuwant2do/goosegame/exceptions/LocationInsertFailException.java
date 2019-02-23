package com.wordpress.babuwant2do.goosegame.exceptions;

public class LocationInsertFailException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1102914767894783105L;
	public LocationInsertFailException(){
		super("Failed to insert Location.");
	}
	public LocationInsertFailException(String message){
		super("Failed to insert Location. "+message);
	}

}
