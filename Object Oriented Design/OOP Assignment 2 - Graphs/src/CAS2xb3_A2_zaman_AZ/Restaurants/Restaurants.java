/** @file Restaurants.java
@author Anando Zaman
@brief Extracts information for the various restaurant CSV
@date March 25,2020
@details Extracts meal information from BK, Mcdonalds, and Wendys CSVs
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

package CAS2xb3_A2_zaman_AZ.Restaurants;

import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import CAS2xb3_A2_zaman_AZ.SearchSortingAlgos.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurants {
	
	private HashMap<String,Double> nextnode;
	private HashMap<String,String> edgeTo;
	
	public static ArrayList<String[]>read_data(String file,String excel_type) throws FileNotFoundException {
		//excel_type is either "menu" or "restaurant"
		Scanner input = new Scanner(new File(file));
		
		int i = 0;
		ArrayList<String[]> data = new ArrayList<String[]>();
		while (input.hasNextLine()){
				String temp = input.nextLine();
				
				String[] values = temp.split(",");
				
				//special case for restaurant so that the STATE picked can be capitalized prior to insertion data arrayList
				if(excel_type.equals("restaurant")) {
					values[3] = values[3].substring(0,2).toUpperCase().trim(); //The US state of a restaurant in UpperCase to match graph
				}
				
				
				if(i>0){
					data.add(values); //Data in the form [longitude, latitude, nameOfRestaurant-CityState, Address
				}
				i++;
				
		}
		input.close();
		return data;
		
	}
	
	//Returns an ArrayList consisting of all the MCD restaurants at a state that has delta lng & lat <= 0.5
	public static ArrayList<String[]> MCD(String state, graph_create city_graph) throws FileNotFoundException{
		ArrayList<String[]> restaurant_data = read_data("2XB3_A2_DataSets/mcdonalds.csv","restaurant"); //Data already ordered in Sorted format by state. No need to sort again by state
		ArrayList<String[]> restaurants = BinarySearch.restaurants_range(restaurant_data, state, city_graph);
		return restaurants;
	}
	
	//Returns an ArrayList consisting of all the BurgerKing restaurants at a state that has delta lng & lat <= 0.5
	public static ArrayList<String[]> BK(String state, graph_create city_graph) throws FileNotFoundException{
		ArrayList<String[]> restaurant_data = read_data("2XB3_A2_DataSets/burgerking.csv","restaurant"); //Data already ordered in Sorted format by state. No need to sort again by state
		ArrayList<String[]> restaurants = BinarySearch.restaurants_range(restaurant_data, state, city_graph);
		return restaurants;
	}
	
	//Returns an ArrayList consisting of all the Wendys restaurants at a state that has delta lng & lat <= 0.5
	public static ArrayList<String[]> Wendys(String state, graph_create city_graph) throws FileNotFoundException{
		ArrayList<String[]> restaurant_data = read_data("2XB3_A2_DataSets/wendys.csv","restaurant"); //Data already ordered in Sorted format by state. No need to sort again by state
		ArrayList<String[]> restaurants = BinarySearch.restaurants_range(restaurant_data, state, city_graph);
		return restaurants;
	}
	
	//sorts the menu by price and returns as an ArrayList to the user
	public static ArrayList<String[]> sorted_menu(graph_create city_graph) throws FileNotFoundException{
		ArrayList<String[]> price_data = read_data("2XB3_A2_DataSets/menu.csv","menu"); //Data already ordered in Sorted format by state. No need to sort again by state
		for(int i = 0; i<price_data.size();i++) {
			String restaurant = price_data.get(i)[0].trim();
			if(restaurant.equals("McDonald’s")) {
				price_data.get(i)[0] = "MCDONALDS";
			}
			else if(restaurant.equals("Burger King")) {
				price_data.get(i)[0] = "BURGER KING";
			}
			else if(restaurant.equals("Wendy’s")) {
				price_data.get(i)[0] = "WENDYS";
			}
		}
		Quicksort.quicksort(price_data, 0, price_data.size()-1,2);
		return price_data;
	}
	
	
}
