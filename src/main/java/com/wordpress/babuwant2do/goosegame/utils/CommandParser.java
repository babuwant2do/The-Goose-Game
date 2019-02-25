package com.wordpress.babuwant2do.goosegame.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wordpress.babuwant2do.goosegame.game.CommandType;
import com.wordpress.babuwant2do.goosegame.game.GameAction;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.game.User;

/**
 * "add player Pippo"
 * "move Pippo 4, 2"
 * "move Pippo"
 */

public class CommandParser {
	private Pattern[] cmdPattern ={
			Pattern.compile("(?i)^(\\s)*(add)(\\s)+(player)(\\s)+(\\w)+(\\s)*$"), // add user x
			Pattern.compile("(?i)^(\\s)*(move)(\\s)+(\\w)+((\\s)+[1-6](\\s)*[,](\\s)*[1-6])?(\\s)*$"), //"move Pippo 4, 2" | "move Pippo"
//			Pattern.compile("(?i)^(move)(\\s)+(\\w)+(\\s)+[1-6](\\s)*[,](\\s)*[1-6](\\s)*$"),
//				Pattern.compile("(?i)^(add player )(\\w)+$"),
//				Pattern.compile("(?i)^(move )(\\w)+ $"),
			};
	
	/**
	 * move     Pippo   4    ,    2  
----- TOKEN ----- : move
----- TOKEN ----- : Pippo
----- TOKEN ----- : 4
----- TOKEN ----- : ,
----- TOKEN ----- : 2
move     Pippo
----- TOKEN ----- : move
----- TOKEN ----- : Pippo
add player Pippo
----- TOKEN ----- : add
----- TOKEN ----- : player
----- TOKEN ----- : Pippo

	 * @param input
	 * @return
	 */
	public GameAction getCommand(String input){
		System.out.println(input);
		String validCommand = this.extractValidCommand(input);
		if(validCommand != null){
			String[] cmdToken = validCommand.trim().replaceAll(" +", " ").split("\\s"); 
			for (int i = 0; i < cmdToken.length; i++) {
				System.out.println("----- TOKEN ----- : "+ cmdToken[i]);
			}
			if(cmdToken[0].equalsIgnoreCase("add") && cmdToken.length == 3 ){
				return new GameAction(CommandType.ADD_USER, new User(cmdToken[2]));
			}else if(cmdToken[0].equalsIgnoreCase("move")){
				
				if(cmdToken.length == 2){
					return new GameAction(CommandType.MOVE, new User(cmdToken[1]));
				}else if( cmdToken.length == 5){
					return new GameAction(CommandType.MOVE, new User(cmdToken[1]), new Move(Integer.parseInt(cmdToken[2]), Integer.parseInt(cmdToken[4])));
				}
				
			}else{
				return new GameAction(CommandType.UNHANDLED);
			}
		}else{
			return new GameAction(CommandType.INVALID);
		}
		
		return null;
	}
	
	
	
	public String extractValidCommand(String input){
		Matcher m;
		for(int i = 0; i< this.cmdPattern.length; i++){
			 m = this.cmdPattern[i].matcher(input);
			 if(m.find()){
				 return m.group().trim().replaceAll("(\\s)+", " ");
			 }
		}
		return null;
	}
	
	
//	public static void main(String[] args){
//		CommandParser cp = new CommandParser();
//		cp.getCommand("move     Pippo   4    ,    2  ");
//		cp.getCommand("move     Pippo");
//		cp.getCommand("add player Pippo");
//	}
}

