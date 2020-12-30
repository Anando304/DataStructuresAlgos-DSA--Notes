/** @file BinarySearch.java
@author Anando Zaman
@brief Executes Modified BinSearch Algorithm to output ranges
@date March 25,2020
@details Finds the range for restaurants to occur by US-STATE and Long & Lat info
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

package CAS2xb3_A2_zaman_AZ.SearchSortingAlgos;

import java.util.ArrayList;
import CAS2xb3_A2_zaman_AZ.GraphCreation.*;


public class BinarySearch {
	
	//Recursive implementation
	//Modification of BinSearch that finds the first and last occurence of a type(specific location/index(ie; US STATES) column)
	//index for US-states in ArrayList is index 3
	private static int[] range_find_search(ArrayList<String[]> data, int start, int end, String key, int index) {

		//base case
		if(start>end) {
			return null;
		}
		
		int mid = start + (end-start)/2;
		
		//if found an occurence
		if((data.get(mid)[index]).compareTo(key) == 0) {
			int first = mid;
			int last = mid;
			
			//find first occurence
			while(((data.get(first)[index]).compareTo(key) == 0) && (first>=0)){
				first--;
				
			}
			
			//find last occurence
			while(((data.get(last)[index]).compareTo(key) == 0) && (last<data.size()-1)) {
				last++;
			}
			
			int[] startend = {first+1,last-1}; //save as an array to be returned as the start-end range indices
			return startend;
		}
		
		//Otherwise, check RHS(right-hand side) of array since key value is larger than current mid 
		else if(less(data.get(mid)[index],key)) {
			return range_find_search(data,mid + 1,end,key,index);
		}
		
		//check LHS since key value is smaller than current mid
		else {
			return range_find_search(data,start,mid-1,key,index);
		}
	}
	
	
	
	private static boolean less(String v, String w) {
		return (v).compareTo(w) < 0; //compareTo returns negative if v < w, returns 0 if v = w, returns 1 if v>w
	}
	
	
	//Outputs an arraylist containing the restaurants for the given US state index/row range that also is within 0.5 long & lat of a state
	//Uses BinarySearch from above to get the start-end range of a US state in restaurant CSV
	//Static so that can be used without needing to declare an object
	public static ArrayList<String[]> restaurants_range(ArrayList<String[]> restaurant_data, String state, graph_create city_graph){
		
		String city_val = null;
		
		//finds the city corresponding to the state
		for(String city : city_graph.all_cities()) {
			
			//if found the corresponding city to a state
			if(city_graph.get_state(city).equals(state)) {
				city_val = city;
				break;
			}
		}
		
		Double lat = city_graph.get_lat(city_val);
		Double lng = city_graph.get_long(city_val);
		
		int[] startend = range_find_search(restaurant_data, 0, restaurant_data.size()-1,state, 3);
		ArrayList<String[]> restaurants = new ArrayList<String[]>(); //Returns the restaurants for a given state
		
		//if start-end points exist for a given state
		if (startend != null) {
			for(int i = startend[0]; i<=startend[1]; i++) {
				
				//lng,lat
				if((restaurant_data.get(i)[0] != null) && (restaurant_data.get(i)[1] != null)) {
					Double delta_lng = Math.abs(Double.parseDouble(restaurant_data.get(i)[0]) - lng);
					Double delta_lat = Math.abs(Double.parseDouble(restaurant_data.get(i)[1]) - lat);
					
					//append to the valid restaurants list that fall within 0.5 range
					if((delta_lng<=0.5) && (delta_lat<=0.5)) {
						restaurants.add(restaurant_data.get(i));
					}
				}
				
				else {
					return null;
				}
			}
			return restaurants;
		}
		
		//otherwise, return null
		return null;
	}
}
