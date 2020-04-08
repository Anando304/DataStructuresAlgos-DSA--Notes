/** @file GameBoardT.java
 @author Anando Zaman
 @brief GameBoardT ADT module that uses CellT module
 @date March 25,2020
 @details Model of MVC for playing the Dots game on a 6X6 GRID
 */
package matrix_dfs;

import java.util.ArrayList;
import java.util.Arrays;

/** @brief class represents the GameBoardT module to play Dots Game
 *  @details The model of MVC with various operations on a 6X6 grid
 */
public class GameBoardT {

	//State Variables
	private int moves;
	private ArrayList<Object> objectives = new ArrayList<Object>(); //Consists of cell type and the number of them required
	private int score;
	
	//Default board 6X6
	private CellT[][] boardT;

	/** @brief Constructor that initializes the GameBoardT object
	 @details GameBoardT constructor takes no parameters to create GameBoardT object
	 */
	public GameBoardT() {
		this.boardT = null;
	}

	/** @brief Initializes the GameBoard for the state variables
	 @details Takes no parameters. Assigns the default properties for the board
	 */
	public void initialize_board() {
		CellT[][] temp = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
				   {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
				   {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
				   {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
				   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
				   {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};
		
		this.boardT = temp.clone();
		this.moves = 4;
		this.objectives.add(CellT.R);
		this.objectives.add(6);
		this.score = 0;
	}

	/** @brief Getter method used to retrieve boardT grid
	 @details getter method to return the boardT state variable
	 @return Returns a 2D array of CellT representing the boardT
	 */
	public CellT[][] get_boardT(){
		return boardT;
	}

	/** @brief Getter method used to retrieve objectives
	 @details getter method to return the objectives state variable
	 @return Returns an arraylist containing objective parameters
	 */
	public ArrayList get_obj()
	{
		return objectives;
	}

	/** @brief Getter method used to retrieve score
	 @details getter method to return the score state variable
	 @return Returns an integer containing the score
	 */
	public int get_score(){
		return score;
	}

	/** @brief Getter method used to retrieve number of moves
	 @details Gets the number of moves remaining anytime during the game
	 @return Returns an integer containing the number of moves remaining
	 */
	public int get_moves(){
		return moves;
	}
	
	//kind of like a quicksort approach using p_index
	//if valid path

	/** @brief Method that updates the boardT after a valid move
	 @details Mutator method that updates the boardT with a valid path from user
	 @param path represents the 2D integer array of the path chosen by the client
	 @throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException
	 */
	public void move(int[][]path) {
		
		if((path == null) || (path.length == 0)) {
			throw new NullPointerException("Please enter a valid path");
		}
		
		//Check if valid start position
		int start_row = path[0][0];
		int start_col = path[0][1];
		if(!valid_coordinate(start_row, start_col)) {
			String coordinate = "(" + String.valueOf(start_row) + "," + String.valueOf(start_col) + ")";
			throw new IndexOutOfBoundsException("INVALID START/END POINTS = " + coordinate);
    	}
		
		//Check if valid path
		else if((valid_path(start_row, start_col, path) != null)){
			throw new IndexOutOfBoundsException("INVALID Path coordinates = " + (Arrays.toString(valid_path(start_row, start_col, path))));
		}

		//Diagonal occured
		else if(!one_direction(path)){
			throw new IllegalArgumentException("Invalid path. A diagonal occured");
		}


		if(game_over()) {
			throw new IllegalArgumentException("Game Over. Ran out of moves. Score: " + get_score());
		}
		
		else if(is_win()) {
			System.out.println("You Win!");
			System.out.println("Score: " + get_score());
			return;
		}

		CellT type = get_boardT()[start_row][start_col]; //used for comparison for objective calculation below
		drop_down_cells(path);

		//Update number of moves available & new score
		moves--;
		score+=path.length;

		//update objectives
		//if path has cells that match that of objective Cell type
		//then update objectives by reducing the target goal
		if(get_obj().get(0).equals(type)) {
			objectives.set(1, (Integer)get_obj().get(1) - path.length);
			}

		//Output updated game info to the screen
		System.out.print("MOVE: ");
		for(int[] coordinate_point : path){
			System.out.print(Arrays.toString(coordinate_point) + ", ");
		}
		System.out.println(" --> SUCCESSFUL!");

		//Check after move if ran out of moves or if won
		if(game_over()) {
			throw new IllegalArgumentException("Game Over. Ran out of moves. Score: " + get_score());
		}

		else if(is_win()) {
			System.out.println("You Win!");
			System.out.println("Score: " + get_score());
			return;
		}

	}

	/** @brief Local Method that drops the floating cells after the cells from given path are removed
	 @details Mutator method that updates the boardT by moving the above cells, down below
	 @param path represents the 2D integer array of the path chosen by the client
	 Kind of like a quicksort approach using p_index
	 if valid path
	 */
	private void drop_down_cells(int [][] path){
		//Algorithm complexitiy: 3 seperate loops to get the job done. ~ O(N), linear time

		//First, empty the values in the coordinates of the path. Fill with null
		//These are the cells that form a path that have to be removed(set to null)
		for(int[] coordinate : path) {
			boardT[coordinate[0]][coordinate[1]] = null;
		}

		//Iterate through the available path subarrays which contain the coordinates of the path
		for(int[] coordinate : path) {
			int row = coordinate[0];
			int col = coordinate[1];
			int p_index = row; //index pointer used to keep track of 1st null cell for a column

			//linear time to pull down floating cells down. O(N) complexity
			//Check each of the cells above the null cells.
			// If NOT NULL, then pull them down to p_index cell
			// Increment p_index by 1 to point to next NULL cell
			for(int temp_row = row; temp_row>=0; temp_row--) { //iterate upwards and find Non-NULL cells
				if (get_boardT()[temp_row][col] != null) {
					boardT[p_index][col] = get_boardT()[temp_row][col]; //drops Non-NULL cell down
					boardT[temp_row][col] = null; //Fill the coordinates of the dropped cells set to NULL, since no longer holds a value
					p_index--;

					//for the first row so that p_index does not become negative if removing elements of first row
					if(p_index<0){
						p_index = 0;
					}
				}
			}


			//p_index points to first non_null cell
			//all cells above p_index are now null
			//Filled dropped cells(that are now null) with a random CellT value
			while(p_index>=0) {
				boardT[p_index][col] = CellT.getRandomCell();
				p_index--;
			}
		}

	}
	

	/** @brief Local Method, verifies if user input path is valid. DOES NOT CHECK DIAGONALITY
	 @details Checks if each coordinate in path is valid and of SAME data (ie; all red, all green,etc)
	 @param start_row is an integer that represents start_row coordinate
	 @param start_col is an integer that represents start_column coordinate
	 @param path represents the 2D integer array of the path chosen by the client
	 @return returns null if valid path. Otherwise, returns the failed coordinate as an integer array
	 */
	private int[] valid_path(int start_row, int start_col,int[][] path) {

		for(int i = 0; i<path.length; i++) {
			int[] coordinate = path[i];

			if (!((valid_coordinate(coordinate[0], coordinate[1])) && (same_cell_data(start_row, start_col, coordinate[0], coordinate[1])))) {
				int[] failed_coordinate = {coordinate[0], coordinate[1]};
				return failed_coordinate;
			}
		}
		return null;	
	}

	/** @brief Local Method, verifies if path does NOT contain Diagonals
	 @details Checks if each coordinate in path does not form diagonal path
	 @param path represents the 2D integer array of the path chosen by the client
	 @return returns true if diagonals DO NOT exist. Otherwise, returns false
	 */
	private boolean one_direction(int[][] path){

		for(int i = 1; i<path.length; i++) {
			int[] coordinate = path[i];
			int[] adj_coordinate = path[i-1];
			if(!((coordinate[0] == adj_coordinate[0]) || (coordinate[1] == adj_coordinate[1]))) {
				return false;
			}
		}
		return true;
	}

	/** @brief Local Method, verifies if supplied coordinate are within bounds
	 @details Checks if coordinates are within 6X6 Gameboard grid
	 @param row represents the row index of a coordinate
	 @param col represents the col index of a coordinate
	 @return returns true if if valid coordinate. Otherwise, false
	 */
    private boolean valid_coordinate(int row, int col) {
    	return ((row>=0) && (row<6) && (col>=0) && (col<6));
    }

	/** @brief Local Method, verifies if same CellT color type for two coordinates
	 @details checks if (row,col) cell has same color as (start_row,start_col) cell.
	 @param start_row represents the row index of a coordinate
	 @param start_col represents the col index of a coordinate
	 @return returns true if both have coordinates contain same Cell colors
	 */
    private boolean same_cell_data(int start_row, int start_col, int row, int col) {
    	return (get_boardT()[start_row][start_col].equals(get_boardT()[row][col]));
    }

	/** @brief Checks if game is over when ran out of available moves
	 @details Outputs a boolean signifying if available moves is < = 0
	 @return returns True if available moves is less than or equal to 0
	 */
    public boolean game_over(){
		return get_moves()<=0;
	}

	/** @brief Checks if user has won the game
	 @details Outputs a boolean if user has met the objective
	 @return returns True if objective has reached or gone below 0
	 */
	public boolean is_win(){
		return (Integer)get_obj().get(1)<=0;
	}

}
