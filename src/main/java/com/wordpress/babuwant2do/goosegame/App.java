package com.wordpress.babuwant2do.goosegame;

import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationInsertFailException;
import com.wordpress.babuwant2do.goosegame.utils.CommandParser;
import com.wordpress.babuwant2do.goosegame.utils.LocationFactory;
import com.wordpress.babuwant2do.goosegame.utils.NodeFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	//public static Integer MAX_LOCATION = 63;
    public static void main( String[] args )
    {
        System.out.println( "Hello! Welcome to Goose Game!" );
        try {
    		LocationFactory locationFactory = new LocationFactory();
    		NodeFactory nodeFactory = new NodeFactory();
    		CommandParser commandParser = new CommandParser();
    		
    		
			new UserInterface(locationFactory, nodeFactory, commandParser).run();
			
		} catch (LocationCreateFailException | LocationInsertFailException e) {
			e.printStackTrace();
		}
    }
}
