package matrix_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class DFS {
	
	//global instance variables
	private HashSet<String> marked; //Keeps track of marked/seen cells, string of (row+col), ie ("72") represents (7,2)
	private Stack<Vector<Integer>> marked_path; //stack containing the current path. Can be used for backtracking
	private Stack<Vector<Integer>> FINAL_PATH; //STACK CONTAINING FINAL PATH if FOUND
	private int[][] matrix;
	private int start_x;
	private int start_y;
	private int end_x;
	private int end_y;
	
	
	public DFS(int matrix[][],int start_x, int start_y, int end_x, int end_y) {
		this.matrix = matrix;
		this.start_x = start_x;
		this.start_y = start_y;
		this.end_x = end_x;
		this.end_y = end_y;
		
		this.marked = new HashSet<String>();
		this.marked_path = new Stack<Vector<Integer>>();
		this.FINAL_PATH = new Stack<Vector<Integer>>();
		
	}
	// (-1,0) = up, (0,1) = right, (1,0) = down, (0, - 1) = left
	
    //private int[] neighborRows = {-1, 0, 1, 0};
    //private int[] neighborColumns = {0, 1, 0, -1};
    
    private void DFS_RUN() {
    	
    	//if invalid start & end coordinates
    	if(!validStartEnd(matrix, start_x, start_y, end_x, end_y)) {
    		System.out.println("INVALID START/END POINTS");
    		return;
    	}
    	
    	//if both values at those coordinates are the NOT same.
    	//can't find path if diff values, ie RED in one cell while GREEN in END. BOTH have to be either RED or GREEN.
    	else if(!same_cell_data(matrix, start_x, start_y, end_x, end_y)) {
    		System.out.println("INVALID START/END CELL-DATA");
    		return;
    	}
    	
    	//Assuming valid start/end points and same_cell_data for both since did not fail above cases
		//if start and end points are the same, then start point is the only path
    	if(reached_end_point(start_x, start_y, end_x, end_y) ) {
    		//return (start_x,start_y)
    		System.out.println("SAME START AND END POINTS");
    		return;
    	}
    	
    	//otherwise, find path to end point
    	movements(matrix, start_x, start_y, end_x, end_y, "U");
    	

    	
    }
    
    private boolean validStartEnd(int matrix[][],int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if within matrix boundaries
    	return ((start_x>=0) && (start_x<matrix.length) && (start_y>=0) && (start_y<matrix[0].length));
    	
    }
    
    private boolean same_cell_data(int matrix[][],int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if the data between two cells are the same
    	//NOTE: start_x and start_y will change each time recursively find new point to check, but end_x and end_y will remain same as OUR DESTINATION remains UNCHANGED
    	return (matrix[start_x][start_y]  == matrix[end_x][end_y]);	
    }
    
    private boolean reached_end_point(int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if reached the end point meaning both start and end coordinates are the same
    	return ((start_x == end_x) && (start_y == end_y));	
    }
    
    
    private void movements(int matrix[][],int start_x, int start_y, int end_x, int end_y, String direction) {
    	
    	int old_start_x = start_x;
    	int old_start_y = start_y;
    	
    	if(direction.equals("U")) {
        	start_x = start_x - 1; //since checking above row. x is rows, y is cols
    	}
    	
    	if(direction.equals("R")) {
        	start_y = start_y + 1; //since checking column on the right. rows stays constant since on the same row
    	}
    	
    	if(direction.equals("D")) {
        	start_x = start_x + 1; //since checking row below, column stays same
    	}
    	
    	if(direction.equals("L")) {
        	start_y = start_y - 1; //since checking column on the left, rows stay constant
    	}
    	
    	
		//Check if coordinates within bounds
    	if(validStartEnd(matrix,start_x,start_y,end_x,end_y)) {

    		//check if current cell has NOT yet been seen/marked
    		if(!marked.contains(String.valueOf(start_x)+String.valueOf(start_y))) {
    			marked.add(String.valueOf(start_x)+String.valueOf(start_y));
    			
    			//if current cell value matches desired cell value AND (current & end) coordinates are the SAME, that means we found our path
    			if(same_cell_data(matrix,start_x,start_y,end_x,end_y) && reached_end_point(start_x,start_y,end_x,end_y)) {
    				Vector<Integer> coordinate = new Vector<Integer>();
    				coordinate.add(start_x);
    				coordinate.add(start_y);
    				System.out.println("RAND");
            		marked_path.add(coordinate); //add coordinate to seen path
            		FINAL_PATH = (Stack<Vector<Integer>>) marked_path.clone(); //creates a shallow copy of final path
            		return;
            		
    			}
    			
    			//if current cell-value matches desired cell-value BUT (current & end) coordinates are NOT the SAME, then NOT yet found path but found a valid cell to add to our path 
    			else if(same_cell_data(matrix,start_x,start_y,end_x,end_y)) {
    	    		System.out.println(start_x + " " + start_y + " " + end_x + " " + end_y + " " + direction);
    				Vector<Integer> coordinate = new Vector<Integer>();
    				coordinate.add(start_x);
    				coordinate.add(start_y);
    				
            		marked_path.add(coordinate);
            		
            		//Check adjacent cells if another path can be made
            		//Here we use new start_x and start_y modified from above because it is a cell within boundaries, has not been previously seen, and contains same value we are looking for
                	//Check any direction as long as it's not same one as before.
            		if(direction.equals("U")) {
                    	System.out.println("Ran R");
                    	movements(matrix, start_x, start_y, end_x, end_y, "R");
                	}
                	
                	if(direction.equals("R")) {
                    	System.out.println("Ran D");
                    	movements(matrix, start_x, start_y, end_x, end_y, "D");
                	}
                	
                	if(direction.equals("D")) {
                    	System.out.println("Ran L");
                    	movements(matrix, start_x, start_y, end_x, end_y, "L");
                	}
                	
                	if(direction.equals("L")) {
                    	System.out.println("Ran U");
                		movements(matrix, start_x, start_y, end_x, end_y, "U");
                	}
                	
                	//return;
            	}
    			
    			//Does not contain correct cell data. ie; maybe we want only RED but we found BLUE. SO we don't go this direction and revert backwards
    			//We don't do anything because below if statements revert backwards and check adjacent positions
    			
    		}
    		
    		
    		//do nothing if cell already seen. Revert backwards will be taken care of below if statements

    	}

		//check cell in diff direction if this cell has been seen, if this cell is NOT within bounds, OR if this cell has diff cell value than we are looking for
		//use old start_x and start_y because the new one checked 
    	System.out.println(marked);
    	if(direction.equals("U")) {
    		//essentially return movements but since void function, just do movements
    		//if direction was UP but nothing found, then check Right
        	movements(matrix, old_start_x, old_start_y, end_x, end_y, "R");
    	}
    	
    	else if(direction.equals("R")) {
        	movements(matrix, old_start_x, old_start_y, end_x, end_y, "D");
    	}
    	
    	else if(direction.equals("D")) {
        	movements(matrix, old_start_x, old_start_y, end_x, end_y, "L");
    	}
    	
    	else if(direction.equals("L")) {
    		return;
    	}
    	
    	return;
    }
    
    
    private void DFS_movements(int matrix[][],int start_x, int start_y, int end_x, int end_y) {
    	
    	
    	//if checked up,right,down,left for a particular node and found no available paths, that means reached a dead end, so pop this node off the stack of current_path
    	//so backtrack and go back to previous node and see if any other available paths from there
    	//before doing that, pop off current node as a possibility in the path.
    	//NOTE; this node is still marked in the HashSet meaning we can't re-encounter this node. This prevents looping
    	marked_path.pop();
    }
    
	
	
	public static void main(String[] args) {
		
		int matrix [][] = {{0,1,0,1,0,0},
						   {0,0,0,1,0,0},
						   {0,1,1,1,0,0},
						   {0,1,1,0,0,0}};
		
		DFS test = new DFS(matrix,0,0,1,1);
		test.DFS_RUN();
		//System.out.println();
	}
}
