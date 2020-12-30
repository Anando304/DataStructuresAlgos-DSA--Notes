/* Student Information
* -------------------
* Student Name: Zaman, Anando
* Course Code: CS/SE 2XB3
* Lab Section: 02
*
* I attest that the following code being submitted is my own individual
work.
*/

package CAS2xb3_A2_zaman_AZ.UnitTests;

import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import CAS2xb3_A2_zaman_AZ.GraphTraversal.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BFSTest {
	//private global variables
	private String[] start_end;
	graph_create city_graph;
	
	//Things to setup before every test
	@Before
	public void setUp() throws Exception {
		
		/**LOAD INPUT START/END DATA**/
		Scanner input = new Scanner(new File("data/input.txt"));
		start_end = new String[2]; //array that contains [start_city, end_city]
		int i = 0;
		while (i<2){
			start_end[i] = input.nextLine().trim().toUpperCase();
			i++;	
		}
		input.close();
		
		/**Initialize graph**/
		ArrayList<ArrayList<Object>> initial_cities_list = graph_create.initial_cities_list(); //Extracts the available cities from USCITIES Dataset
		city_graph = new graph_create(initial_cities_list); //initialize empty graph with these cities
		city_graph.create_edges(); //create the edges. Essentially adjacency list associated to each city
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBfs() throws FileNotFoundException {
		
		//Find the route from start to end using BFS
		ArrayList<String> BFS_path = new ArrayList<String>(); //Used to contain Forwards order path
		if (BFS.validStart(start_end[0], city_graph) && BFS.validEnd(start_end[1], city_graph)) {
			BFS.bfs(start_end[0], start_end[1], city_graph);
			Stack<String> path = BFS.pathTo(start_end[0], start_end[1]);
			System.out.print("BFS: ");
			String BFS_pop;
			while(!path.isEmpty()) {
				BFS_pop = path.pop();//save top element
				BFS_path.add(BFS_pop);//add top element to forwards order list
				if(path.size()>0) {
					System.out.print(BFS_pop + ", ");
				}
				else {
					System.out.print(BFS_pop + " ");
				}
				
			}
			System.out.println("\n");
			
		} else {
			System.out.println("Invalid Start or End Point");
		}
		
		
		//TEST IF CITIES ARE IN CORRECT ORDER OF ADJACENCY
		//Checks if adjacency list of current city CONTAINS the next_city from BFS route.
		//If true, then next_city from BFS route is connected in the correct order
		System.out.println("TESTING BFS order of paths...");
		System.out.println("-----------------------------");
		for(int i = 0; i<BFS_path.size()-1; i++) {
			String current_city = BFS_path.get(i).trim();
			String next_city = BFS_path.get(i+1).trim();
			ArrayList<Object> connected_adj_cities = city_graph.get_adjlist(current_city); /**extracts adjacent connected cities to current city**/
			
			/**Verifies if order of BFS is correct by checking if adjacency list of current city contains the next_city from BFS**/
			if(connected_adj_cities.contains(next_city)) {
				System.out.println(next_city + " is part of the adjacency list of " + current_city); 
				System.out.println("Therefore, " + next_city + " is in the CORRECT order\n");
				assertTrue(true); 
			}
			
			/**Otherwise, invalid order. assert false**/
			else {
				System.out.println(next_city + " is NOT part of the adjacency list of " + current_city); 
				System.out.println("Therefore, " + next_city + " is in the INCORRECT order\n");
				assertTrue(false); 
			}
			
		}
			
	}

}
