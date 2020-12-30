/** @file Dijkstras_shortpaths.java
@author Anando Zaman
@brief Executes Dijkstras Shortest Paths ALGORITHM
@date March 25,2020
@details HashMap implementation of Dijkstras to find shortest path based on distance
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


package CAS2xb3_A2_zaman_AZ.ShortestPathAlgos;

import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//DIJKSTRAS SHORTEST PATH COMPUTATION
public class Dijkstras_shortpaths {
	
	/*DATA STRUCTURES SETUP*/
	private Map<String, String> edgeTo;  //contains the parent city that an adjacent city is attached to. Used for backtracking to find path
	private Map<String,Double> distTo; //Contains the cumulative distance from source node to another node
	private Map<String, Double> pq; //Priority Queue in the form of a HashMap. Used for extracting Minimum distTo vals

	//Constructor
	//initialize data structures
	//Runs Dijkstra algorithm, creates shortest path to each city from source/start city
	public Dijkstras_shortpaths(String source_city,graph_create city_graph) {
		 edgeTo = new HashMap<String, String>();
		 distTo = new HashMap<String, Double>();
		 pq = new HashMap<String,Double>();
		
		 /*****Running Dijkstra's algorithm*****/
		for(String city: city_graph.all_cities()) { //set initial distance to infinity for all nodes
			distTo.put(city,Double.POSITIVE_INFINITY);
		}
		
		//set source city distance to 0.0 to begin
		distTo.replace(source_city, 0.0);
		
		//add to priority queue
		pq.put(source_city,0.0);

		
		while (!pq.isEmpty()) { 
			String parent_city = extractMin(pq);
			
			//Relaxes adjacent cities to parent_city.
			for(Object adj_city : city_graph.get_adjlist(parent_city)) {
				Double weight = city_graph.get_distance(parent_city, (String)adj_city); //Extracts weight_distance to get from parent_city --> adjacent_city
				relax(parent_city, (String)adj_city, weight);
			}
				
		}
	}
	
	/**Edge Relaxation**/
	//u is parent city, v is adjacent city, w is distance_weight of the edge (u --> w)
	private void relax(String u, String v, Double w) {
		
		//IF distance from ((node u) + weight) to reach (node v), is less than current available distance at node v.
		//THEN a shorter edge/path occurs from u --> v
		//Update new cumulative distance (distTo) with new updated distance from u --> v
		//Add node on edgeTo as a valid path for backtracking
		if (((distTo.get(v)!=null) && (distTo.get(u)!=null)) && (distTo.get(v) > distTo.get(u) + w)) {
			distTo.put(v, distTo.get(u) + w);
			edgeTo.put(v, u);
			
			//UPDATE the priority Q
			//if adjacent city NOT already on pq, insert to pq
			if(!pq.containsKey(v)) {
				pq.put(v,distTo.get(v));

			}
			
			//Otherwise, update adjacent city on pq with new cumulative distance (distTo)
			else {
				pq.replace(v,distTo.get(v));
				
			}
		}
	}
	
	//Removes key from PriorityQueue with least distance value
	private static String extractMin(Map<String,Double> pq) {
		String min_key = null;
		Double min = Double.POSITIVE_INFINITY;
		for (String key : pq.keySet()) {
		    if((key != null) && (pq.get(key)<min)) {
		        min_key = key; //update min_key
		    }
		}
		pq.remove(min_key); //remove min_key from queue
		return min_key;
		
	}
	
	
	//Verifies if path can exist by checking if end-node exists
	private boolean hasPathTo(String endPoint){
			return edgeTo.containsKey(endPoint);
	}
	
	
	//Returns shortest path from start_city to end_city IF EXISTS
	public ArrayList<String> pathTo(String startPoint, String endPoint) { 

        if (!hasPathTo(endPoint)) {
        	return null;
        }
        
        Stack<String> path = new Stack<String>();
        for (String x = endPoint; x != startPoint; x = edgeTo.get(x)) {
        	path.push(x);
        }
        path.push(startPoint); //Push the original source/start node once reached.
        
        //saves path in forwards direction in an arraylist
        ArrayList<String> forwards_path = new ArrayList<String>();
        while(!path.isEmpty()) {
        	forwards_path.add(path.pop());
        }
        return forwards_path;
    }
}
