package CAS2xb3_A2_zaman_AZ.GraphTraversal;
import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/** @file BFS.java
@author Anando Zaman
@brief Executes BFS Algorithm
@date March 25,2020
@details Runs BFS algorithm to find a path
*/

/* Student Information
* -------------------
* Student Name: Zaman, Anando
* Course Code: CS/SE 2XB3
* Lab Section: 02
*
* I attest that the following code being submitted is my own individual
work.
*/

public class BFS {

		private static Map<String, String> edgeTo = new HashMap<String, String>(); //contains information of predecessor nodes. Used for traversing backwards
		private static HashSet<String> marked = new HashSet<String>(); //Keeps track of marked/seen streets
		private static Queue<String> queue = new Queue<String>();
		
		@SuppressWarnings("rawtypes")
		
		/** PRE-PROCESSING CHECKS IF VALID START & ENDS **/
	 	public static boolean validStart(String startPoint, graph_create graph) {
			return graph.containsKey(startPoint);
		}
		
		public static boolean validEnd(String endPoint, graph_create graph) {
			return graph.containsKey(endPoint);
		}
		
		
		/**EXECUTES BFS**/
		public static void bfs(String startPoint, String endPoint, graph_create graph){
			marked.add(startPoint); // Mark the source
			queue.enqueue(startPoint); // and put it on the queue.
			
			while (!queue.isEmpty()){
				
				String v = queue.dequeue(); //Remove next vertex/street from queue
				
				if(graph.containsKey(v)) {
					
					ArrayList<Object> adj_cities = (ArrayList<Object>) graph.get_adjlist(v); //adjacency list to a city		
					
					for (Object city : adj_cities) {
						city = ((String) city).trim();
						if (!marked.contains(city)){ //For every unmarked adjacent city,
							edgeTo.put((String) city,v); //Keeps track of node connected to current adjacent city. Used for backtracking later
							marked.add((String) city);	//mark it because path is known
							queue.enqueue((String) city);	//and add it to the queue.
						}
					}
				}
			}
		}
		
		//Verifies if path can exist by checking if end-node exists
		public static boolean hasPathTo(String endPoint){
				return marked.contains(endPoint);
		}
		
		//Traverses backwards from destination/end node to the starting node with info from hashmap
		public static Stack<String> pathTo(String startPoint, String endPoint) { 

	        if (!hasPathTo(endPoint)) {
	        	return null;
	        }
	        
	        Stack<String> path = new Stack<String>();
	        for (String x = endPoint; x != startPoint; x = edgeTo.get(x)) {
	        	path.push(x);
	        }
	        
	        path.push(startPoint); //Push the original source/start node once reached.
	        return path;
	    }
}
