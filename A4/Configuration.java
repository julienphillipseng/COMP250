package assignment4Game;

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
		
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		// ADD YOUR CODE HERE
		this.board[index][available[index]] = player;
		available[index]++;
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		// ADD YOUR CODE HERE
		if(this.horizontalWin(lastColumnPlayed, player) || this.verticalWin(lastColumnPlayed, player) || 
			this.leftDiagonalWin(lastColumnPlayed, player) || this.RightDiagonalWin(lastColumnPlayed, player)) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public int canWinNextRound (int player){
		// ADD YOUR CODE HERE
		for(int col = 0; col < 7; col++) {
			if(this.available[col] != 6) {
			this.addDisk(col,player);
			if(this.isWinning(col,player)) {
				this.deleteDisk(col);
				return col;
			}
			else {
				deleteDisk(col);
			}
			
			}
		}
		return -1;
	}
	
	public int canWinTwoTurns (int player){
		// ADD YOUR CODE HERE
		int opponent;
		
		if(player == 1) {
			opponent = 2;
		}else {
			opponent = 1;
		}
		
		
		for(int column = 0; column < 7; column++) {
			if(this.available[column] != 6) {
				this.addDisk(column,player);
				int canWin = 1;
				for(int columnOpponent = 0; columnOpponent < 7 ; columnOpponent++) {
					if(this.available[columnOpponent] != 6) {
						this.addDisk(columnOpponent,opponent);
						if(this.canWinNextRound(player) == -1) {
							canWin = 0;
						}
						this.deleteDisk(columnOpponent);
					}
				}
				this.deleteDisk(column);
				if(canWin == 1) {
					return column;
				}
			}
		}
		return -1;
	}
	
	public void deleteDisk (int index) {
		
		int rowNum = this.available[index] - 1;
		if(rowNum < 0) {
			return; // there is no row less than zero, so do nothing
		}
		else {
			this.board[index][rowNum] = 0;
			this.available[index]--;
		}
	}
	
	private boolean verticalWin(int column, int player) {
		
		int rowNum = this.available[column]- 1;
		if(rowNum < 0) {
			return false;
		}
		if(rowNum + 1 < 4) {				
			return false;
		}
		for(int i = 0; i < 4; ++i) {				
			if(this.board[column][rowNum - i] != player) {	
				return false;
			}
		}
		return true;
	}
	
	private boolean horizontalWin(int column, int player) {
		
		int count = 0;								
		int rowNum = this.available[column] - 1;			
		if(rowNum < 0) {
			return false;
		}
		for(int i = 0; i < 7; ++i) {			
			if(this.board[i][rowNum] == player) {
				count++;							
				if(count >= 4) {			
					return true;	//there are 4 in a row
				}
			}
			else {								
				count = 0; //restart from zero
			}
		}
			return false;
	}
	
	private boolean RightDiagonalWin(int column, int player) {
		
		int rowNum = this.available[column] -1;	
		
		if(rowNum < 0) {
			return false;
		}
		
		int iteration = 0;
		int count = 0;
		
		while(true) {
			if((column + iteration < 7) && (rowNum - iteration) >= 0) {
				if(this.board[column + iteration ][rowNum - iteration] == player) {
					iteration++;
					count++;
				}
				else {					
					break;						
				}
			}
			else {
				break;
			}
		}
		
		iteration = 1;
		
		while(true) {
			if((column - iteration >= 0) && (rowNum + iteration) < 6) {
				if(this.board[column - iteration][rowNum + iteration] == player) {
					iteration++;
					count++;
				}
				else {					
					break;						
				}
			}
			else {
				break;
			}
		}
		if(count >= 4) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean leftDiagonalWin(int column, int player) {
		
		int rowNum = this.available[column] - 1;	
		
		if(rowNum < 0) {
			return false;
		}
		
		int iteration = 0;
		int count = 0;

		
		while(true) {
			if((column - iteration >= 0) && (rowNum - iteration) >= 0) {
				if(this.board[column - iteration][rowNum - iteration] == player) {
					iteration++;
					count++;					
				}
				else {					
					break;						
				}
			}
			else {
				break;
			}
		}
		
		iteration = 1;
		
		while(true) {
			if((column + iteration < 7) && (rowNum + iteration) < 6) {
				if(this.board[column + iteration][rowNum + iteration] == player) {
					iteration++;
					count++;
				}
				else {					
					break;						
				}
			}
			else {
				break;
			}
		}
		
		if(count >= 4) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
}
