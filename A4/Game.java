package assignment4Game;

import java.io.*;

public class Game {
	
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		// ADD YOUR CODE HERE
		String userInputColumn = "";
		int column = 0;
		
		System.out.print("Enter the column you want to place your disk in: ");
        try{
            userInputColumn = keyboard.readLine(); 
            column = Integer.parseInt(userInputColumn);
            
            if (column < 0 || column > 6) {
            		System.out.println("Invalid column number");
            		return getNextMove(keyboard, c, player);
            }
            if(c.available[column] >= 6) {
            		System.out.println("This column is full - choose again");
            		return getNextMove(keyboard, c, player);
            }
        }
        catch(Exception e){
            System.out.println("Enter valid column");
            return getNextMove(keyboard, c, player);
        }
		return column;
	}
	
	public static int firstMovePlayer1 (){
		return 3;	
	}
	
	public static int movePlayer1 (int columnPlayed2, Configuration c){
		// ADD YOUR CODE HERE
		Integer canWinNext = new Integer(c.canWinNextRound(1));
		if(canWinNext != -1) {
			return canWinNext;
		}
		Integer canWinNextTwo = new Integer(c.canWinTwoTurns(1));
		if(canWinNextTwo != -1) {
			return canWinNextTwo;
		}	
		
		Integer start = new Integer(columnPlayed2);
		for(int i = 0; i < 7; ++i) {
			columnPlayed2 += i*expCalc(-1,i);
			if(columnPlayed2 < 7 && columnPlayed2 >= 0){
				if(c.available[columnPlayed2] < 6) {
					return columnPlayed2;
				}
			}
		}	
		return start; 
		
	}
	
	private static int expCalc(int number, int exponent) {
		if(exponent == 0) {
			return 1;
		}
		else if(exponent == 1){
		  return number;
		}
		return number*expCalc(number, exponent -1);
	}
	
}
