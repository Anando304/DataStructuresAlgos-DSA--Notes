package matrix_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoardT {
	
	private int moves;
	private ArrayList<Object> objectives = new ArrayList<Object>();
	private int score;
	
	//Default board 6X6
	private CellT[][] boardT = new CellT[6][6];
	
	public GameBoardT() {
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
	public void move(int row1, int col1, int row2, int col2) {
		ArrayList<ArrayList<Integer>> path = DFS.DFS_EXECUTE(boardT,row1,col1,row2,col2);

		if(moves<=0) {
			System.out.println("Game Over. Ran out of moves");
			System.out.println("Score: " + score);
		}
		
		else if((Integer)objectives.get(1)<=0) {
			System.out.println("You Win!");
			System.out.println("Score: " + score);
		}
		
		if(! (path == null)) {
			CellT type = boardT[row1][row2]; //used for comparison for objective calculation
			//Empty the values in the coordinates of the path. Fill with null
			for(ArrayList<Integer> coordinate : path) {
				boardT[coordinate.get(0)][coordinate.get(1)] = null;
			}
			
			for(ArrayList<Integer> coordinate : path) {
				int row = coordinate.get(0);
				int col = coordinate.get(1);
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
		score+=path.size();
		if(objectives.get(0).equals(type)) {
			System.out.println("PASSED");
			objectives.set(1, (Integer)objectives.get(1) - path.size());
			}
		
		System.out.println("MOVE " + "("+ String.valueOf(row1) +","+ String.valueOf(col1) + ") ," + "("+ String.valueOf(row2) +","+ String.valueOf(col2) + ")" + " --> SUCCESSFUL!");
		System.out.println("Objective: " + objectives.get(0) + ", " + objectives.get(1));
		System.out.println("Score: " + score);
		System.out.println("Remaining moves: " + moves);
		}
		
		/*else {
			System.out.println
		}*/

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
	

	public static void main(String[] args) {
		/*String matrix [][] = {{"RED","RED","RED","BLUE","RED","RED"},
				   {"ORANGE","RED","RED","RED","BLUE","ORANGE"},
				   {"BLUE","BLUE","ORANGE","BLACK","BLACK","BLACK"},
				   {"BLACK","RED","RED","BLACK","ORANGE","BLUE"},
				   {"BLACK","BLUE","RED","ORANGE","BLACK","BLUE"},
				   {"RED","RED","ORANGE","BLUE","BLACK","BLUE"}};*/
		
		CellT matrix [][] = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
						   {CellT.O,CellT.R,CellT.B,CellT.B,CellT.B,CellT.O},
						   {CellT.O,CellT.O,CellT.O,CellT.G,CellT.G,CellT.G},
						   {CellT.G,CellT.R,CellT.R,CellT.O,CellT.O,CellT.B},
						   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
						   {CellT.R,CellT.B,CellT.O,CellT.B,CellT.G,CellT.B}};
		
		GameBoardT game = new GameBoardT();
		game.initialize_board();
		game.print_board();
		game.move(2, 3, 2, 5);
		game.print_board();
		game.move(2, 0, 2, 1);
		game.print_board();
		game.move(1, 0, 1, 2);
		game.print_board();
		
		//game.move(row1, col1, row2, col2);
		//System.out.println(DFS.DFS_EXECUTE(matrix,0,0,0,2));
		
		/*try {
			System.out.println(arr[3]);
		}catch (Exception e) {
			System.out.println(e);
		}*/

		

		//System.out.println(CellT.getRandomCell());
		//System.out.println(game.boardT[0][3]);
		//System.out.println(game.objectives);
		
		/*String[] arr1 = {"1","2","3"};
		String[] arr2 = arr1;*/
	}
	


}
