/** @file LowestMealCost.java
@author Anando Zaman
@brief Calculates Meal information for a path
@date March 25,2020
@details Computes restaurants, meals, and cost at each city in the path. Also calculates total cost of meals of entire path
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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;
import CAS2xb3_A2_zaman_AZ.GraphCreation.*;
import CAS2xb3_A2_zaman_AZ.ShortestPathAlgos.*;

public class LowestMealCost {
	
	//Outputs 2D Vector with information about the meals and prices for a given path and graph
	public static Vector<Object> paths_price_sorted(ArrayList<String> path,graph_create city_graph) throws FileNotFoundException {
			
			//Vector in the form [ [Restaurant,Item1,cost1], [Restaurant2,item2,cost2], [Restaurant3,item3,cost3], ... , [RestaurantN,itemN,costN], total_mealcost)
			Vector<Object> meals_data = new Vector<Object>();
			double total_mealcost = 0;
			
			//Check each city in the path
			for(int city_index = 0; city_index<path.size(); city_index++) {
				
				//temporary Vector that will contain Restaurant name, meal, AND cost. ie, [MCDONALDS,McChicken - Meal, $3.79]
				Vector<String> temp = new Vector<String>();
				
				//At the start, no restaurant visited
				if(city_index == 0) {
					temp.add("N/A"); temp.add("N/A"); temp.add("$0.00"); 
				}
				
				//Otherwise, select meal at a city
				else {
					
					String city = path.get(city_index);
					
					//Pick restaurant that is AVAILABLE within a specific US STATE
					boolean isMCD = Restaurants.MCD(city_graph.get_state(city),city_graph)!=null;
					boolean isBK = Restaurants.BK(city_graph.get_state(city),city_graph)!=null;
					boolean isWENDYS = Restaurants.Wendys(city_graph.get_state(city),city_graph)!=null;
					
					
					//Gets data from Restaurants in the form [restaurant name, meal, price]
					//This data is in sorted in ascending order of cost
					ArrayList<String[]> menu = Restaurants.sorted_menu(city_graph);
					
					 //Absolute Cheapest meal index, since sorted from lowest to highest price
					int item_row = 0;
					
					//previous city meal information
					String prev_mealchoice = ((Vector<String>) meals_data.get(city_index-1)).get(1);
					
					
					//Initialize variables for new meal
					String new_mealchoice = null;
					String new_restaurant_name = null;
					String mealprice = null;
					
					
					//if McDonalds is AVAILABLE in the US STATE
					if(isMCD) {
						
						//Grabs the first row of data from menu dataset
						new_mealchoice = menu.get(item_row)[1].trim();
						new_restaurant_name = menu.get(item_row)[0].trim();
						
						//look for cheapest meal for the entire menu dataset
						while( item_row != menu.size()-1) {
							
							//if new meal is NOT SAME as prev meal, AND restaurant read is MCDONALDS, then break out of loop
							if ( !(new_mealchoice.equals(prev_mealchoice)) && (new_restaurant_name.equals("MCDONALDS")) ) {
								break;
							}
							
							//otherwise, increment rows by 1 to check next row data
							else {
								item_row++;
								new_mealchoice = menu.get(item_row)[1].trim();
								new_restaurant_name = menu.get(item_row)[0].trim();
							}
							
							

						}
					}
						
					//Otherwise, if BK is AVAILABLE IN US STATE
					else if(isBK) {

						new_mealchoice = menu.get(item_row)[1].trim();
						new_restaurant_name = menu.get(item_row)[0].trim();
						
						//look for cheapest meal for the entire menu dataset
						while( item_row != menu.size()-1) {
							
							//if new meal is NOT SAME as prev meal, AND restaurant read is MCDONALDS, then break out of loop
							if ( !(new_mealchoice.equals(prev_mealchoice)) && (new_restaurant_name.equals("BURGER KING")) ) {
								break;
							}
							
							//otherwise, increment rows by 1 to check next row data
							else {
								item_row++;
								new_mealchoice = menu.get(item_row)[1].trim();
								new_restaurant_name = menu.get(item_row)[0].trim();
							}
							
							

						}
					}
					
					//Otherwise, if WENDYS is AVAILABLE IN US STATE
					else if(isWENDYS) {
						

						new_mealchoice = menu.get(item_row)[1].trim();
						new_restaurant_name = menu.get(item_row)[0].trim();
						
						//look for cheapest meal for the entire menu dataset
						while( item_row != menu.size()-1) {
							
							//if new meal is NOT SAME as prev meal, AND restaurant read is WENDYS, then break out of loop
							if ( !(new_mealchoice.equals(prev_mealchoice)) && (new_restaurant_name.equals("WENDYS")) ) {
								System.out.println(new_mealchoice);
								break;
							}
							
							//otherwise, increment rows by 1 to check next row data
							else {
								item_row++;
								new_mealchoice = menu.get(item_row)[1].trim();
								new_restaurant_name = menu.get(item_row)[0].trim();
							}
							
							

						}
					}
					
					//Save Restaurant name, meal-choice, and price in the temp vector of data
					mealprice = menu.get(item_row)[2].trim();
					temp.add(new_restaurant_name);temp.add(new_mealchoice); temp.add(mealprice);
					
					 //update running total of Total mealcost
					total_mealcost+= Double.parseDouble(mealprice.substring(1));

				}
				
				//insert the temporary Vector (ie; [MCDONALDS, McChicken - Meal, $3.79]) into meals_data 2D Vector
				meals_data.add(temp);
			}
			
			//insert TOTAL cost once all meals have been accounted for
			meals_data.add(String.valueOf(total_mealcost));
			return meals_data;
	}
}
