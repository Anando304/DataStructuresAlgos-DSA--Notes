package matrix_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import matrix_dfs.CellT;

public class DFS {

	// (-1,0) = up, (0,1) = right, (1,0) = down, (0, - 1) = left
	//Generic matrix, will be used to take type of CellT enums
	public static Stack<String> DFS_EXECUTE(CellT[][] matrix, int start_x, int start_y, int end_x, int end_y) {
		
    	//if invalid start & end coordinates
    	if(!validStartEnd(matrix, start_x, start_y, end_x, end_y)) {
    		System.out.println("INVALID START/END POINTS");
    		return null;
    	}
    	
    	//if both values at those coordinates are the NOT same.
    	//can't find path if diff values, ie RED in one cell while GREEN in END. BOTH have to be either RED or GREEN.
    	else if(!same_cell_data(matrix, start_x, start_y, end_x, end_y)) {
    		System.out.println("INVALID START/END CELL-DATA");
    		return null;
    	}
    	
    	//Assuming valid start/end points and same_cell_data for both since did not fail above cases
		//if start and end points are the same, then start point is the only path
    	if(reached_end_point(start_x, start_y, end_x, end_y) ) {
    		//return (start_x,start_y)
    		System.out.println("SAME START AND END POINTS");
    		return null;
    	}
    	

    	HashMap<String,String> edgeTo  = new HashMap<String,String>();
    	HashSet<String> marked  = new HashSet<String>(); //Keeps track of marked/seen cells, string of (row+col), ie ("72") represents (7,2)
    	
    	//otherwise, find path to end point
    	movements(matrix, start_x, start_y, end_x, end_y,marked,edgeTo);
    	return pathTo(start_x+","+start_y, end_x+","+end_y,edgeTo,marked);

    }
    
    private static boolean validStartEnd(CellT[][] matrix,int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if within matrix boundaries
    	return ((start_x>=0) && (start_x<matrix.length) && (start_y>=0) && (start_y<matrix[0].length));
    	
    }
    
    private static boolean same_cell_data(CellT[][] matrix,int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if the data between two cells are the same
    	//NOTE: start_x and start_y will change each time recursively find new point to check, but end_x and end_y will remain same as OUR DESTINATION remains UNCHANGED
    	return (matrix[start_x][start_y].equals(matrix[end_x][end_y]));	
    }
    
    private static boolean reached_end_point(int start_x, int start_y, int end_x, int end_y) {
    	
    	//checks if reached the end point meaning both start and end coordinates are the same
    	return ((start_x == end_x) && (start_y == end_y));	
    }
    
    
    private static void movements(CellT[][] matrix,int start_x, int start_y, int end_x, int end_y,HashSet<String> marked,HashMap<String,String> edgeTo) {

		//Check if coordinates within bounds
    	if(validStartEnd(matrix,start_x,start_y,end_x,end_y)) {
    		
        	marked.add(String.valueOf(start_x)+","+String.valueOf(start_y)); // Mark the source
        	
        	int[][] adjacent_coordinates = {{start_x-1,start_y},{start_x,start_y+1},{start_x+1,start_y},{start_x,start_y-1}};
	        	for(int[] coordinate : adjacent_coordinates) {
	        		
	        		
	        		//if validStartEnd & have the same data value(ie; 0 or 1) then continue DFS recursively
	        		if(validStartEnd(matrix,coordinate[0],coordinate[1],end_x,end_y) && same_cell_data(matrix,coordinate[0],coordinate[1],end_x,end_y)) {
	        		String temp = String.valueOf(coordinate[0]) + "," + String.valueOf(coordinate[1]);
	        		
	        		//check if adjacent cells have NOT yet been seen/marked
	        		if(!marked.contains(temp)) {
	        			
	        			String startPoint = String.valueOf(start_x) + "," + String.valueOf(start_y);
	        			String endPoint = String.valueOf(end_x) + "," + String.valueOf(end_y);
	        			if(startPoint.equals(endPoint)) {
	    					edgeTo.put(temp,startPoint);
	    					return;
	        			}
	    				movements(matrix, coordinate[0], coordinate[1],end_x,end_y, marked, edgeTo);  //run
	    				edgeTo.put(temp,startPoint);
	        		}
	        	}
        	}
    	}
    }
    
	//Verifies if path can exist by checking if end-node exists in the edgeTo HashMap
	private static boolean hasPathTo(String endPoint,HashSet<String> marked){
			return marked.contains(endPoint);
	}
    
	private static Stack<String> pathTo(String startPoint, String endPoint, HashMap<String,String> edgeTo, HashSet<String> marked) { //Traverses backwards from destination/end node to the starting node with info from hashmap
		
		//Checks if a path is available
        if (!hasPathTo(endPoint,marked)) {
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
		
		CellT matrix [][] = {{CellT.R,CellT.R,CellT.R,CellT.B,CellT.R,CellT.R},
							   {CellT.O,CellT.R,CellT.R,CellT.R,CellT.B,CellT.O},
							   {CellT.B,CellT.B,CellT.O,CellT.G,CellT.G,CellT.G},
							   {CellT.G,CellT.R,CellT.R,CellT.G,CellT.O,CellT.B},
							   {CellT.B,CellT.B,CellT.R,CellT.O,CellT.G,CellT.B},
							   {CellT.R,CellT.R,CellT.O,CellT.B,CellT.G,CellT.B}};

		
		
		Stack<String> path = DFS.DFS_EXECUTE(matrix,0,0,0,2);
		System.out.println(path);

	}
}
