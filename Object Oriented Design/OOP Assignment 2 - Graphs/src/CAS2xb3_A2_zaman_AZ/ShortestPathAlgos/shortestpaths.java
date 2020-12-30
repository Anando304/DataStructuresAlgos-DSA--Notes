/** @file shortestpaths.java
@author Anando Zaman
@brief Computes the shortest path from all computed BFS paths. USES BFS ALGORITHMS
@date March 25,2020
@details Computes all possible paths using BFS and sorts them get lowest priced path
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
import CAS2xb3_A2_zaman_AZ.GraphTraversal.*;
import CAS2xb3_A2_zaman_AZ.Restaurants.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.Vector;

//CHEAPEST MEAL-PRICE PATH COMPUTATION USING BFS
//IF LOOKING FOR DIKSTRAS, THEN USE THE DIKSTRAS_SHORTPATHS.JAVA FILE
//This class computes the cheapest meal paths using BFS implementation by finding ALL POSSIBLE PATHS and SORTING THEM
public class shortestpaths {
	
	/*DATA STRUCTURES SETUP*/
	private static HashSet<String> marked = new HashSet<String>(); //Keeps track of marked/seen streets?
	
	@SuppressWarnings("rawtypes")
	private static ArrayList<Stack> allPaths = new ArrayList<Stack>();
	
	/*PRE-PROCESSING CHECKS IF VALID START & ENDS*/
 	public static boolean validStart(String startPoint, graph_create city_graph) {
		return city_graph.containsKey(startPoint);
	}
	
	public static boolean validEnd(String endPoint, graph_create city_graph) {
		return city_graph.containsKey(endPoint);
	}


	//Calculates all possible paths using BFS and returns a 2D ArrayList
	public static ArrayList<Vector> bfs_allPaths(String startPoint, String endPoint, graph_create city_graph) throws FileNotFoundException{
		
		Queue_BFSALL <Vector> allpaths = new Queue_BFSALL <Vector>();
		ArrayList<Vector> allpaths_FINAL = new ArrayList<Vector>(); //2D 
		
		// path vector to store the current path
	    Vector<String> current_path = new Vector<String>();
	    
	    current_path.add(startPoint);
	    allpaths.enqueue(current_path);
		marked.add(startPoint); // Mark the source
		
		
		while (!allpaths.isEmpty()){
			current_path = allpaths.dequeue(); //returns an arraylist of path at the start of the Queue

			String last = current_path.get(current_path.size() - 1).trim();//Remove next vertex/street from the current_path.
			
	        if(allpaths_FINAL.size() == 10) {
	        	return allpaths_FINAL;
	        }
	        
			if(city_graph.get_adjlist(last).get(0)!=null) {
				
				@SuppressWarnings("unchecked")
				ArrayList<Object> adjacent_cities_list = city_graph.get_adjlist(last); //Get all adj_cities attached to last added city
				
				for (Object city : adjacent_cities_list) {
					city = ((String) city).trim();
					/* *******VERY IMPORTANT PART OF THIS CODE: 
					 * Used to create all possible paths without ending on just one path by adding onto the Queue 
					 * each time a new path is found. This ensures that when we dequeue and take the path saved, 
					 * it has all possible streets as a seperate path.
					 * */
					Vector newpath = new Vector(current_path); //create a new vector that takes all elements of current_path vector
	                if(!newpath.contains(city)) { //Necessary to avoid duplicate cities/cycles in the possible paths
						newpath.add(city); //adds the new street to it
						allpaths.enqueue(newpath);	//add new path onto the queue of all_paths
							
							//Add the Path to the allpaths_FINAL lists which contains lists of all possible paths from start to end points
							if(city.equals(endPoint)) {
								allpaths_FINAL.add(newpath);
							}
	                }

				}
			}
		}
		return allpaths_FINAL;
	}
	
	public static Vector<Object> paths_price_sorted(ArrayList<Vector> paths,graph_create city_graph) throws FileNotFoundException {
		
		int path_index = 0; //used to keep track of the indices associated with a path in the paths list
		Vector<Object> all_prices_paths = new Vector<Object>(); //Vector of generic type Object so that we can specify diff data types. Basically generic
		for(Vector<String> path : paths) {
			Vector<Vector<String>> name_cost = new Vector<Vector<String>>();
			double total_mealcost = 0;
			for(int city_index = 0; city_index<path.size(); city_index++) {
				Vector<String> temp = new Vector<String>();
				if(city_index == 0) {
					temp.add("N/A"); temp.add("$0.00"); //since no restaurant visited or purchased at start of trip SOMETHING UP WITH THIS AND "" VERSUS NULL AND LINE 108 FIX ASAP
				}
				else {
					//Now now pick a restaurant that is valid for a specific state
					String city = path.get(city_index);
					boolean isMCD = Restaurants.MCD(city_graph.get_state(city),city_graph)!=null;
					boolean isBK = Restaurants.BK(city_graph.get_state(city),city_graph)!=null;
					boolean isWENDYS = Restaurants.Wendys(city_graph.get_state(city),city_graph)!=null;
					
					int item_row = 0; //always try to compare with 0 as the cheapest item in menu is found @ row 0
					ArrayList<String[]> menu = Restaurants.sorted_menu(city_graph);
					String mealchoice = menu.get(item_row)[1].trim();
					String restaurant_name = menu.get(item_row)[0].trim();
					
					if(isMCD) {
						//If previous meal is same as current meal, look for new meal until they are different. Otherwise, no need to check if MCD exists
						while((name_cost.get(city_index-1).get(0).equals(mealchoice)) && (restaurant_name.equals("MCDONALDS"))) {
							item_row++;
							mealchoice = menu.get(item_row)[1].trim();
							restaurant_name = menu.get(item_row)[0].trim();
						}
					}
					
					else if(isBK) {
						//If previous meal is same as current meal, look for new meal until they are different
						while((name_cost.get(city_index-1).get(0).equals(mealchoice)) && (restaurant_name.equals("BURGER KING"))) {
							//System.out.println(mealchoice);
							item_row++;
							mealchoice = menu.get(item_row)[1].trim();
							restaurant_name = menu.get(item_row)[0].trim();
						}
					}
					
					else if(isWENDYS) {
						//If previous meal is same as current meal, look for new meal until they are different
						while((name_cost.get(city_index-1).get(0).equals(mealchoice)) && (restaurant_name.equals("WENDYS"))) {
							item_row++;
							mealchoice = menu.get(item_row)[1].trim();
							restaurant_name = menu.get(item_row)[0].trim();
						}
					}
										
					//Save mealchoice and price for the specific restaurant in the Array
					mealchoice = menu.get(item_row)[1].trim();
					String mealprice = menu.get(item_row)[2].trim();
					temp.add(mealchoice); temp.add(mealprice);		
					total_mealcost+= Double.parseDouble(mealprice.substring(1));
				}
			name_cost.add(temp);  //Insert into the innerlist Vector
			}
		//Insert data into a single arraylist	
		Vector<Object> singleprice_path = new Vector<Object>();
		singleprice_path.add(String.valueOf(path_index));//Now insert this into the absolute outter vector to mark the index position
		path_index++;
		singleprice_path.add(name_cost);
		singleprice_path.add(String.valueOf(total_mealcost));
		
		//insert the single arraylist as a part of the all_prices_paths list
		all_prices_paths.add(singleprice_path);
		
		}
		return all_prices_paths;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String start = "BOSTON";
		String end = "MINNEAPOLIS";
		ArrayList<ArrayList<Object>> initial_cities_list = graph_create.initial_cities_list();
		graph_create city_graph = new graph_create(initial_cities_list);
		city_graph.create_edges();
		
		//ArrayList<String[]> restaurants = Restaurants.MCD(city_graph.get_state("BOSTON"),city_graph);
		//ArrayList<String[]> menu = Restaurants.sorted_menu(city_graph);

		
		/*for(int i = 0; i<menu.size(); i++) {
			System.out.println(menu.get(i)[1]);
		}*/
		
		/*for(int i = 0; i<restaurants.size(); i++) {
			System.out.println(Arrays.toString(restaurants.get(i)));
		}*/

		if (validStart(start, city_graph) && validEnd(end, city_graph)) {
			
			ArrayList<Vector> paths = bfs_allPaths(start, end, city_graph);
			Vector<Object> paths_price_sorted = paths_price_sorted(paths,city_graph);
			System.out.println(paths.get(0));
			System.out.println(paths_price_sorted.get(0)); //since the BFS automatically finds the shortest number of interesections first, we can take the first available path as this will have lowest cost
		} else {
			System.out.println("Invalid Start or End Point");
		}
	}
}
