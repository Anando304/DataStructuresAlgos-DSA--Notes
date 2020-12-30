/** @file Quicksort.java
@author Anando Zaman
@brief Quicksort algorithm
@date March 25,2020
@details Sorts a given arraylist of strings.
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

public class Quicksort {
	
	//array_index is the index of the arrays inside the ArrayList for which will be used for value comparison
	//method that paritions the array recursively to sort both ends
    public static int partition(ArrayList<String[]> arrList, int start, int end, int array_index) {
		int pivot = end;
		int p_index = start;
		
		for(int i = start; i<=end-1;i++) {
			if(less(arrList.get(i)[array_index],arrList.get(pivot)[array_index])) {
				exch(arrList,i,p_index);
				p_index++;
			}
		}
    	exch(arrList,p_index,pivot);
    	return(p_index);    	
    }
    
    //Main quicksort method
    public static void quicksort(ArrayList<String[]> arr, int start, int end, int array_index) {
    	
    	if(start<end) {
    		int partition_index = partition(arr,start,end,array_index);
    		quicksort(arr,start,partition_index-1,array_index); //sort LHS
    		quicksort(arr,partition_index+1,end,array_index); //sort RHS
    	}
    }
    
    //Method that swaps value at two indices
	private static void exch(ArrayList<String[]> a, int i, int j){
		String[] temp = a.get(i); 
		a.set(i, a.get(j)); //a[i] = a[j]
		a.set(j, temp);  //a[j] = t;

		}
	
	//comparison method
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}
