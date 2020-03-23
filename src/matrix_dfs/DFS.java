package matrix_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import matrix_dfs.CellT;

public class DFS<T> {
	
	//global instance variables
	private HashMap<String,String> edgeTo;
	private HashSet<String> marked; //Keeps track of marked/seen cells, string of (row+col), ie ("72") represents (7,2)
	private T[][] matrix; //Generic matrix, will be used to take type of CellT enums
	private int start_x;
	private int start_y;
	private int end_x;
	private int end_y;
	
	
	public DFS(T[][] matrix2,int start_x, int start_y, int end_x, int end_y) {
		this.matrix = matrix2;
		this.start_x = start_x;
		this.start_y = start_y;
		this.end_x = end_x;
		this.end_y = end_y;
		this.marked = new HashSet<String>();
		this.edgeTo = new HashMap<String,String>();
		
	}
	// (-1,0) = up, (0,1) = right, (1,0) = down, (0, - 1) = left
    
	public void DFS_EXECUTE() {
    	
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
    	movements(matrix, start_x, start_y, end_x, end_y);
    	

    	
    }
    
    private boolean validStartEnd(T[][] matrix2,int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if within matrix boundaries
    	return ((start_x>=0) && (start_x<matrix2.length) && (start_y>=0) && (start_y<matrix2[0].length));
    	
    }
    
    private boolean same_cell_data(T[][] matrix2,int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if the data between two cells are the same
    	//NOTE: start_x and start_y will change each time recursively find new point to check, but end_x and end_y will remain same as OUR DESTINATION remains UNCHANGED
    	return (matrix2[start_x][start_y].equals(matrix2[end_x][end_y]));	
    }
    
    private boolean reached_end_point(int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if reached the end point meaning both start and end coordinates are the same
    	return ((start_x == end_x) && (start_y == end_y));	
    }
    
    
    public void movements(T[][] matrix2,int start_x, int start_y, int end_x, int end_y) {

		//Check if coordinates within bounds
    	if(validStartEnd(matrix2,start_x,start_y,end_x,end_y)) {
    		
        	marked.add(String.valueOf(start_x)+","+String.valueOf(start_y)); // Mark the source
        	
        	int[][] adjacent_coordinates = {{start_x-1,start_y},{start_x,start_y+1},{start_x+1,start_y},{start_x,start_y-1}};
	        	for(int[] coordinate : adjacent_coordinates) {
	        		
	        		
	        		//if validStartEnd & have the same data value(ie; 0 or 1) then continue DFS recursively
	        		if(validStartEnd(matrix2,coordinate[0],coordinate[1],end_x,end_y) && same_cell_data(matrix2,coordinate[0],coordinate[1],end_x,end_y)) {
	        		String temp = String.valueOf(coordinate[0]) + "," + String.valueOf(coordinate[1]);
	        		
	        		//check if adjacent cells have NOT yet been seen/marked
	        		if(!marked.contains(temp)) {
	        			
	        			String startPoint = String.valueOf(start_x) + "," + String.valueOf(start_y);
	        			String endPoint = String.valueOf(end_x) + "," + String.valueOf(end_y);
	        			if(startPoint.equals(endPoint)) {
	    					edgeTo.put(temp,startPoint);
	    					return;
	        			}
	    				movements(matrix2, coordinate[0], coordinate[1],end_x,end_y);  //run
	    				edgeTo.put(temp,startPoint);
	        		}
	        	}
        	}
    	}
    }
    
	//Verifies if path can exist by checking if end-node exists in the edgeTo HashMap
	public boolean hasPathTo(String endPoint){
			return marked.contains(endPoint);
	}
    
	public Stack<String> pathTo(String startPoint, String endPoint) { //Traverses backwards from destination/end node to the starting node with info from hashmap
		
		//Checks if a path is available
        if (!hasPathTo(endPoint)) {
        	return null;
        }
        
        Stack<String> path = new Stack<String>();
        String x = endPoint;
        while((x != startPoint) && (x != null)) {
        	path.push(x);
        	x = edgeTo.get(x);
        }
        return path;
    }
    		
    
	
	
	public static void main(String[] args) {
		
		String matrix [][] = {{"RED","RED","RED","BLUE","RED","RED"},
						   {"ORANGE","RED","RED","RED","BLUE","ORANGE"},
						   {"BLUE","BLUE","ORANGE","BLACK","BLACK","BLACK"},
						   {"BLACK","RED","RED","BLACK","ORANGE","BLUE"},
						   {"BLACK","BLUE","RED","ORANGE","BLACK","BLUE"},
						   {"RED","RED","ORANGE","BLUE","BLACK","BLUE"}};
		
		DFS test = new DFS(matrix,0,0,0,3);
		test.DFS_EXECUTE();
		Stack<String> path = new Stack<String>();
		path = test.pathTo("0,0", "0,3");
		System.out.println(path);

	}
}
