/** @file graph_create.java
@author Anando Zaman
@brief Creates graph using CSV
@date March 25,2020
@details Used for creation of graph
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

package CAS2xb3_A2_zaman_AZ.GraphCreation;
import CAS2xb3_A2_zaman_AZ.DistanceCalculator.Distancecalc;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

//This class creates a undirectional graph
//This graph is a vertex weighted graph that behaves like a edge weighted graph
//This concept was discussed in Algorithms SE 2C03 course
public class graph_create {
	
	//2D ArrayList with generic Object type, allowing for diff datatypes such as lists, Strings and doubles to be used
	private HashMap<String,ArrayList<Object>> graph; 
	
	/**Constructor**/
	public graph_create(ArrayList<ArrayList<Object>> initial_cities_list) {
		
		graph = new HashMap<String,ArrayList<Object>>();
		
		//creates graph with the following information, (adjacent cities list, long, lat, US STATE, adjacent cities distance list)
		for(ArrayList<Object> city : initial_cities_list) {
			ArrayList<Object> innerList = new ArrayList<Object>();
			innerList.add(null);//placeholder for the list of adjacent cities
			innerList.add(city.get(1)); //adds the longitude for the city
			innerList.add(city.get(2)); //adds the latitude for the city
			innerList.add(city.get(3)); //adds the US STATE for the city
			innerList.add(null); //list of adjacent distances placeholder. Essentially the edges

			graph.put((String) city.get(0),innerList); //Add above information to hashmap graph
		
			}	
	}
	
	/** Getter to Extract city latitude **/
	public Double get_lat(String city) {
		return Double.parseDouble((String) graph.get(city).get(1));
	}
	
	/** Getter to Extract city Longitude **/
	public double get_long(String city) {
		return Double.parseDouble((String) graph.get(city).get(2));
	}
	
	/** Getter that extracts distance for adjacent city to current city **/
	public Double get_distance(String city, String adj_city) {
		int index = get_adjlist(city).indexOf(adj_city); //returns index of adjacent city of a parent city
		ArrayList<Object> adj_distance_data = (ArrayList<Object>) (graph.get(city).get(4)); //list of distance for all adjacent_cities to a given city
		return (Double) adj_distance_data.get(index); //returns distance for specified adj_city index in adj_distance_data list
	}
	
	/** Getter to Extract adj_city distance list **/
	public ArrayList<Object> get_distance_list(String city) {
		return (ArrayList<Object>) (graph.get(city).get(4)); //list of distance for all adjacent_cities to a given city
	}
	
	/** Getter to Extract city STATE **/
	public String get_state(String city) {
		return (String) graph.get(city).get(3);
	}
	
	/** Getter to Extract city adjacency list **/
	public ArrayList<Object> get_adjlist(String city) {
		return (ArrayList<Object>) graph.get(city).get(0);
	}
	
	/** getter that gets all_city keys **/
	public Set<String> all_cities(){
		return graph.keySet();
	}
	
	/**Getter to extract all the cities available in USCities.csv along with latitude, longitude and US-STATE information in the form of an ArrayList.
	*Will be used to setup the initial graph with this data for each city as a node, with initially a null adjacency list
	*/
	public static ArrayList<ArrayList<Object>> initial_cities_list() throws FileNotFoundException {
		Scanner input = new Scanner(new File("2XB3_A2_DataSets/USCities.csv"));
		int instance = 0; //to avoid adding headers into the graph
		
		ArrayList<ArrayList<Object>> initial_cities_list = new ArrayList<ArrayList<Object>>();
		
		//create the 2D list consisting of [CITY_NAME, LAT, LONG, STATE]
		while (input.hasNextLine()){
			String temp = input.nextLine();
			
			String[] values = temp.split(","); //Creates a string array seperated at the commas
			
			if(instance>0) {
				ArrayList<Object> inner_list = new ArrayList<Object>();
				inner_list.add(values[3].trim()); //City name
				inner_list.add(values[4]); //latitude
				inner_list.add(values[5]); //longitude
				inner_list.add(values[2]); //State info
				
				initial_cities_list.add(inner_list); //save to the 2D outter portion
			}
			instance++;
		}
		input.close();
		return initial_cities_list;
	}
	
	
	/** USES the graph with null adjacency list placeholders
	*Uses the adj_cities method
	*Creates the edges using the adj_cities method to fill in the null adjlists with a list that navigates to next connected node
	*Creates list with distances in order for adjacent cities **/
	public void create_edges() throws FileNotFoundException {
		
		for(String city : graph.keySet()) { //iterate through all available US cities
			
			ArrayList<String> adj_cities_list = adj_cities(city); //generates the adjacency list
			
			ArrayList<Double> adj_cities_distance = new ArrayList<Double>(); //generates the adjacency list
			
			//Calculates distance for each adjacent city for parent city
			//saves distance sequence
			for(String adj_city : adj_cities_list) {
				Double city_lat = get_lat(city);
				Double city_lng = get_long(city);
				
				Double adj_city_lat = get_lat(adj_city);
				Double adj_city_lng = get_lat(adj_city);
				
				Double distance = Distancecalc.distance(city_lat, city_lng, adj_city_lat, adj_city_lng);

				adj_cities_distance.add(distance);
			}
			
			
			graph.get(city).set(0, adj_cities_list); //updates with adjacent nodes arraylist (Adjacency list).
			graph.get(city).set(4, adj_cities_distance);

			
		}
	}
	
	
	/** method that creates and returns an adjacency list of cities to a given city **/
	private ArrayList<String> adj_cities(String city) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File("2XB3_A2_DataSets/connectedCities.txt"));
		ArrayList<String> adjacentCities = new ArrayList<String>(); //list with all cities adjacent to given city
		
		
		while (input.hasNextLine()){
			String temp = input.nextLine();
			String[] values = temp.split(",");
		
			//Due to nature of undirected Graph, we need two if statements to create graph for each direction from first and second columns
			if(values[0].toUpperCase().trim().equals(city.trim())) {
				adjacentCities.add(values[1].toUpperCase().trim());
			}	
			
			
			if(values[1].toUpperCase().trim().equals(city.trim())) {
				adjacentCities.add(values[0].toUpperCase().trim());
			}	
			
			
		}
		input.close();
		
		return adjacentCities;
	}
	
	//getter that checks if a city exists in the graph
	public boolean containsKey(String startPoint) {
		// TODO Auto-generated method stub
		return graph.containsKey(startPoint);
	}

	//getter that extracts the key if it exists
	public Object get(String v) {
		// TODO Auto-generated method stub
		if(graph.containsKey(v)) {
			return v;
		}
		return null;
	}




}
