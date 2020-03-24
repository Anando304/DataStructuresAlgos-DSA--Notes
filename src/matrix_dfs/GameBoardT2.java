package matrix_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//GameBoard done using user input array of path rather than Dynamic path creation using DFS
public class GameBoardT2 {
	
	private int moves;
	private ArrayList<Object> objectives = new ArrayList<Object>();
	private int score;
	
	//Default board 6X6
	private CellT[][] boardT = new CellT[6][6];
	
	public GameBoardT2() {
		this.boardT = null;
	}
	
	public void initialize_board() {
		CellT[][] temp = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
				   {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
				   {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
				   {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
				   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
				   {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};
		
		this.boardT = temp.clone();
		this.moves = 20;
		this.objectives.add(CellT.R);
		this.objectives.add(6);
		this.score = 0;
	}
	
	//kind of like a quicksort approach using p_index
	//if valid path
	public void move(int[][]path) {
		
		if(path == null) {
			System.out.println("Please enter a valid path");
			return;
		}
		
		//Check if valid start position
		int start_row = path[0][0];
		int start_col = path[0][1];
		if(!valid_coordinate(start_row, start_col)) {
			String coordinate = "(" + String.valueOf(start_row) + "," + String.valueOf(start_col) + ")";
    		System.out.println("INVALID START/END POINTS = " + coordinate); //raise indexoutofbounds
    		return;
    	}
		
		//Check if valid path
		//if false. then return coordinates failed at
		else if((valid_path(start_row, start_col, path) != null)){
    		System.out.println("INVALID Path coordinates = " + (Arrays.toString(valid_path(start_row, start_col, path)))); //raise invalidArgumentException
			return;
			
		}
		
		if(moves<=0) {
			System.out.println("Game Over. Ran out of moves");
			System.out.println("Score: " + score);
		}
		
		else if((Integer)objectives.get(1)<=0) {
			System.out.println("You Win!");
			System.out.println("Score: " + score);
		}
		
		if(! (path == null)) {
			CellT type = boardT[start_row][start_col]; //used for comparison for objective calculation
			//Empty the values in the coordinates of the path. Fill with null
			for(int[] coordinate : path) {
				boardT[coordinate[0]][coordinate[1]] = null;
			}
			
			for(int[] coordinate : path) {
				int row = coordinate[0];
				int col = coordinate[1];
				int p_index = row; //index pointer used to keep track of next top index that is null
				
				//linear time to pull down the above existing cells below. O(n) complexity
				//Check each cells above cells removed from path. If exist, pull them down to p_index cell and increment p_index by 1 for pointer to next cell
				for(int temp_row = row; temp_row>=0; temp_row--) {
					if (boardT[temp_row][col] != null) {
						boardT[p_index][col] = boardT[temp_row][col]; //drops cell down
						boardT[temp_row][col] = null; //make the cell that previously had a value prior to dropping down, be null since nothing in there now
						p_index--;
					}
				}
				
				//Filled dropped cells(that are now null) with a random CellT value
				while(p_index>=0) {
					boardT[p_index][col] = CellT.getRandomCell();
					p_index--;
				}
			}
		moves--;
		score+=path.length;
		if(objectives.get(0).equals(type)) {
			System.out.println("PASSED");
			objectives.set(1, (Integer)objectives.get(1) - path.length);
			}
		
		System.out.println("MOVE " + "("+ String.valueOf(start_row) +","+ String.valueOf(start_col) + ") ," + "("+ String.valueOf(path[path.length-1][0]) +","+ String.valueOf(path[path.length-1][1]) + ")" + " --> SUCCESSFUL!");
		System.out.println("Objective: " + objectives.get(0) + ", " + objectives.get(1));
		System.out.println("Score: " + score);
		System.out.println("Remaining moves: " + moves);
		}
		

	}
	
	public void print_board() {
		for(CellT[] row : boardT) {
			for(CellT col : row) {
				System.out.print(col + "   ");
			}
			System.out.println();
		}
		System.out.println("***************************");
	}
	
	//Checks if path given by user is valid
	//If return null, then it is a valid array. If invalid coordinate occurs, then returns an integer array with those coordinates
	private int[] valid_path(int start_row, int start_col,int[][] path) {
    	

		for(int[] coordinate : path) {
			if( !((valid_coordinate(coordinate[0],coordinate[1])) && (same_cell_data(start_row,start_col,coordinate[0],coordinate[1]))) ){
				int[] failed_coor= {coordinate[0],coordinate[1]};
				return failed_coor;
			}
			
		}
		
		return null;	
	}
	
	
    private boolean valid_coordinate(int row, int col) {
    	
    	//checks if within 6x6 board boundaries
    	return ((row>=0) && (row<6) && (col>=0) && (col<6));
    	
    }
    
    private <T> boolean same_cell_data(int start_row, int start_col, int row, int col) {
    	
    	//checks if coordinate in path matches the Cell color of start type.
    	return (boardT[start_row][start_col].equals(boardT[row][col]));	
    }
	
	
	

	public static void main(String[] args) {
		
		GameBoardT2 game = new GameBoardT2();
		game.initialize_board();
		game.print_board();
		int[][] path = {{0,0},{0,1},{0,2}};
		game.move(path);
		game.print_board();
		int[][] path2 = {{2,0},{2,1}};
		game.move(path2);
		game.print_board();
		int[][] path3 = {{4,0},{4,1}};
		game.move(path3);
		game.print_board();

		
	}
	


}
