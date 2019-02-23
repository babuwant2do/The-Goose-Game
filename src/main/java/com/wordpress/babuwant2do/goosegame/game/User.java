package com.wordpress.babuwant2do.goosegame.game;

public class User {
	private String name;
	
	public User(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (!(o instanceof User)) {
	      return false;
	    }
	    User cc = (User)o;
	    return cc.name.equals(name);
	  }
	 
	  public int hashCode() {
	    return this.name.hashCode();
	  }
	
}
