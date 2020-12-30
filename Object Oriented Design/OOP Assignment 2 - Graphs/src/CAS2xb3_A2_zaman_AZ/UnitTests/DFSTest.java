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

import static org.junit.Assert.*;

import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import CAS2xb3_A2_zaman_AZ.GraphTraversal.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DFSTest {

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
		
		/**INITIALIZE GRAPH**/
		ArrayList<ArrayList<Object>> initial_cities_list = graph_create.initial_cities_list(); //Extracts the available cities from USCITIES Dataset
		city_graph = new graph_create(initial_cities_list); //initialize empty graph with these cities
		city_graph.create_edges(); //create the edges. Essentially adjacency list associated to each city
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDfs() throws FileNotFoundException {
		
		//Find the route from start to end using DFS
		ArrayList<String> DFS_path = new ArrayList<String>(); //Used to contain Forwards order path
		if (DFS.validStart(start_end[0], city_graph) && DFS.validEnd(start_end[1], city_graph)) {
			DFS.dfs(start_end[0], start_end[1], city_graph);
			Stack<String> path = DFS.pathTo(start_end[0], start_end[1]);
			System.out.print("DFS: ");
			String DFS_pop;
			while(!path.isEmpty()) {
				DFS_pop = path.pop();//save top element
				DFS_path.add(DFS_pop);//add top element to forwards order list
				if(path.size()>0) {
					System.out.print(DFS_pop + ", ");
				}
				else {
					System.out.print(DFS_pop + " ");
				}
				
			}
			System.out.println("\n");
			
		} else {
			System.out.println("Invalid Start or End Point");
		}
		
		
		//TEST IF CITIES ARE IN CORRECT ORDER OF ADJACENCY
		//Checks if adjacency list of current city CONTAINS the next_city from DFS route.
		//If true, then next_city from DFS route is connected in the correct order
		System.out.println("TESTING DFS order of paths...");
		System.out.println("-----------------------------");
		for(int i = 0; i<DFS_path.size()-1; i++) {
			String current_city = DFS_path.get(i).trim();
			String next_city = DFS_path.get(i+1).trim();
			ArrayList<Object> connected_adj_cities = city_graph.get_adjlist(current_city); /**extracts adjacent connected cities to current city**/
			
			/**Verifies if order of DFS is correct by checking if adjacency list of current city contains the next_city from BFS**/
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
