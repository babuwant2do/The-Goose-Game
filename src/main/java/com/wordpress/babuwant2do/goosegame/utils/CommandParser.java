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
		String validCommand = this.extractValidCommand(input);
		
		if(validCommand != null){
			String[] cmdToken = validCommand.split("\\s"); 
			if(cmdToken[0].equalsIgnoreCase("add") && cmdToken.length == 3 ){
				return new GameAction(CommandType.ADD_USER, new User(cmdToken[2]));
			}else if(cmdToken[0].equalsIgnoreCase("move")){
				
				/**
				 * ------ length : 3
				 * move Pippo 4,2 => ['4,2']
				 * ------ length : 4
				 * move Pippo 4 ,2 => ['4',',2']
				 * move Pippo 4, 2 => ['4,' , '2']
				 * -----------------------------------
				 * move Pippo 4 , 2 => ['4', ',', '2']
				 */
				Move move = null;
				if(cmdToken.length == 2){
					move = new Move();
				}else if( cmdToken.length == 3){
					String[] moveTok = cmdToken[2].split(","); 
					int dice1 =  Integer.parseInt(moveTok[0]);
					int dice2 = Integer.parseInt(moveTok[1]);
					move = new Move(dice1, dice2);
				}else if( cmdToken.length == 4){
					int dice1 =  Integer.parseInt(cmdToken[2].replace(",", ""));
					int dice2 = Integer.parseInt(cmdToken[3].replace(",", ""));
					move = new Move(dice1, dice2);
				}else if( cmdToken.length > 4){
					int dice1 = Integer.parseInt(cmdToken[2]);
					int dice2 = Integer.parseInt(cmdToken[cmdToken.length-1]);
					move = new Move(dice1, dice2);
				}
				if(move != null){
					return new GameAction(CommandType.MOVE, new User(cmdToken[1]), move);
				}
				
			}else{
				return new GameAction(CommandType.UNHANDLED);
			}
		}
		return new GameAction(CommandType.INVALID);
	}
	
	
	public String extractValidCommand(String input){
		Matcher m;
		for(int i = 0; i< this.cmdPattern.length; i++){
			 m = this.cmdPattern[i].matcher(input);
			 if(m.find()){
				 return m.group().replaceAll("(\\s)+", " ").trim();
			 }
		}
		return null;
	}
	
	
//	public static void main(String[] args){
//		CommandParser cp = new CommandParser();
//		cp.getCommand("move Pippo 4, 2");
//		cp.getCommand("move Pippo 4,2");
//		cp.getCommand("move     Pippo   4    ,    2  ");
////		cp.getCommand("move     Pippo");
////		cp.getCommand("add player Pippo");
//	}
}

