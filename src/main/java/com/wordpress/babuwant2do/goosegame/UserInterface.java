package com.wordpress.babuwant2do.goosegame;

import java.util.Scanner;

import com.wordpress.babuwant2do.goosegame.board.GameBoard;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationCreateFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.LocationInsertFailException;
import com.wordpress.babuwant2do.goosegame.exceptions.NodeCreateFailException;
import com.wordpress.babuwant2do.goosegame.game.CommandType;
import com.wordpress.babuwant2do.goosegame.game.GameAction;
import com.wordpress.babuwant2do.goosegame.game.Move;
import com.wordpress.babuwant2do.goosegame.utils.BoardBuilder;
import com.wordpress.babuwant2do.goosegame.utils.CommandParser;
import com.wordpress.babuwant2do.goosegame.utils.LocationFactory;
import com.wordpress.babuwant2do.goosegame.utils.NodeFactory;

public class UserInterface {
	
	private LocationFactory locationFactory;
	private NodeFactory nodeFactory;
	private BoardBuilder boardBuilder;
	private GameBoard gameBoard;
	CommandParser commandParser;
	
	public UserInterface(LocationFactory locationFactory, NodeFactory nodeFactory, CommandParser commandParser)
			throws LocationCreateFailException, LocationInsertFailException{
		
		this.locationFactory = locationFactory;
		this.nodeFactory = nodeFactory;
		this.commandParser = commandParser;
		
		this.boardBuilder = new BoardBuilder(locationFactory);
		this.gameBoard = new GameBoard(boardBuilder.setDefaultBoard().build(), nodeFactory, true);
		
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		GameAction action;
		String input;
		
		System.out.println(this.printHelp());
		
		while (true) {
			try {
				System.out.print("the user writes:");
				input = scanner.nextLine();
				if ("exit".equalsIgnoreCase(input)) {
					System.out.println("Bye!");
					break;
				}else if ("help".equalsIgnoreCase(input)) {
					System.out.println(this.printHelp());
				}else if("reset".equalsIgnoreCase(input.trim())){
					System.out.println(String.format("** %s **", this.gameBoard.resetBoard()));
				}
				else{
					action = commandParser.getCommand(input);
					if(action.getType() == CommandType.ADD_USER){
						System.out.println(String.format("responds: %s", gameBoard.addUser(action.getUser())));
					}else if(action.getType() == CommandType.MOVE){
						try {
							System.out.println(String.format("responds: %s", gameBoard.moveUser(action.getUser(), (Move) action.getCommand())));
						} catch (NodeCreateFailException e) {
							System.err.println("Error to execute Move");
							e.printStackTrace();
						}
					}else if(action.getType() == CommandType.RESTART){
						System.out.println(String.format("responds: %s", action.getType()));
					}else if(action.getType() == CommandType.INVALID || action.getType() != CommandType.UNHANDLED){
						System.out.println(String.format("responds: %s", action.getType()));
					}
				}
			} catch (Exception e) {
				
			}
		}
		
		scanner.close();
	}
	
	public String printHelp(){
		return new StringBuilder("Instruction: ")
				.append("\n------------------------------------")
				.append("\n To add new player type: \"add player <userName>\"")
				.append("\n To move type: \"move <userName> <dice val 1>, <dice val 2>\" or \"move <userName>\"")
				.append("\n To reset board type: \"reset\"")
				.append("\n To Exit board type: \"exit\"")
				.append("\n------------------------------------\n")
				.toString()
				;
	}

}
