/** @file DFS.java
@author Anando Zaman
@brief Executes DFS ALGORITHM
@date March 25,2020
@details Finds a path via DFS
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

package CAS2xb3_A2_zaman_AZ.GraphTraversal;

import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class DFS {
	
	private static Map<String, String> edgeTo = new HashMap<String, String>(); // last vertex on known path to this vertex. Will be used to backtrack & find path
	private static HashSet<String> marked = new HashSet<String>(); //Keeps track of marked/seen streets
	
	@SuppressWarnings("rawtypes")
	/*PRE-PROCESSING CHECKS IF VALID START & ENDS*/
 	public static boolean validStart(String startPoint, graph_create graph) {
		return graph.containsKey(startPoint);
	}
	
	public static boolean validEnd(String endPoint, graph_create graph) {
		return graph.containsKey(endPoint);
	}
	
	//Recursive DFS goes through each node and its adjacent nodes, saving each path until finds endPoint.
	//Creates all possible paths that can be backtracked using the edgeTo Hashmap, which consists of child and parent cities
	public static void dfs(String startPoint, String endPoint, graph_create graph){
		
		marked.add(startPoint); // Mark the source
		ArrayList<Object> adj_cities = (ArrayList<Object>) graph.get_adjlist(startPoint);
		for (Object city : adj_cities) {
			city = ((String) city).trim();
			if (!marked.contains(city)){ 	//For every unmarked adjacent vertex,
				
				if(startPoint.equals(endPoint)) { //To prevent going down deeper into call stack of a path if endPoint already found
					edgeTo.put((String) city,startPoint);
					return;
				}
				
				dfs((String) city, endPoint, graph);  //Recursively Run DFS for adjacent city until found endPoint
				edgeTo.put((String) city,startPoint);
			}
		}
	}
	
	//Verifies if path can exist by checking if end-node exists
	public static boolean hasPathTo(String endPoint){
			return marked.contains(endPoint);
	}
	
	//Traverses backwards from destination city to the starting/source city with info from edgeTo hashmap
	//Returns Stack with given path in reverse order
	public static Stack<String> pathTo(String startPoint, String endPoint) { 

        if (!hasPathTo(endPoint)) {
        	return null;
        }
        
        //stack that keeps track of path in reverse order
        Stack<String> path = new Stack<String>();
        for (String x = endPoint; x != startPoint; x = edgeTo.get(x)) {
        	path.push(x); //push city onto the stack
        }
        
        path.push(startPoint); //Push the original source/start node once reached.
        return path;
    }

}
