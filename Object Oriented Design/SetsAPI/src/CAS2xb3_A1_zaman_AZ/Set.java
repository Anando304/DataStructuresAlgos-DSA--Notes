package CAS2xb3_A1_zaman_AZ;

import java.io.IOException;

public class Set {

	//Instance variables
	private String[] Arr1;
	private int Arr1_index;
	
	/*constructor is like a static method but it 
	can refer directly to an instance variable. It has no return value.
	Purpose is to initialize instance variables.*/
	public Set(String[] arr1){
		String[] array_temp = new String[20];
		
		if(arr1.length>20) { //print error msg if array size is greater than 20 elements
			System.out.println("Error; The String Array entered is greater than 20");
			return;
		}
		
		int i = 0;
		for(i = 0; i<arr1.length; i++) { //creation of instance variables "this.Arr1"
			
			if(arr1[i] == null) {
				break;
			}
			
			array_temp[i] = arr1[i];
		}
		this.Arr1 = array_temp; //state variable for array
		this.Arr1_index = i; //index state variable of last non-null element in the array
		
	}
	
	//Default constructor. If no parameter Set object created
	public Set() {
		String[] array_temp = new String[20];
		this.Arr1 = array_temp;
		this.Arr1_index = 0;
	}
	
	//Getter method returns Arr1_index
	public int get_Arr1_index() {
		return this.Arr1_index;
	}
	
	//Getter method returns Arr1 array
	public String[] get_Arr1() {
		return this.Arr1;
	}
	
	//Mutator method that appends a string to the Set object's Arr1 state variable if no duplicates exist
	//Max size of 20.
	public void append(String s) {
		
		//Check if Array is currently full. If so, Arr1_index will be less than 20 as 20 is out of range
		if(this.Arr1_index < 20) {
			
			//Check if duplicate element already exists
			for(int i = 0; i <= this.Arr1_index; i++) {
				if(this.Arr1.equals(s)) {
					System.out.println("Duplicate found");
					return;
				}
			}
			this.Arr1[this.Arr1_index] = s; //Insert the element to the Arr1_index position
			this.Arr1_index++; //Increase index for next entry by 1
		}
		
		//print "ARRAY IS FULL" to the console if cannot append
		else {
			System.out.println("ARRAY IS FULL");
			return;
		}
	}
	
	//Mutator method that removes the element if it exists in the Set Object.
	//Shifts over all elements 1 position to the left after removal
	//If element does not exist, prints "NOTHING TO REMOVE" to the console
	public void remove(String s) {
		
		int index = 0;
		boolean element_exists = false;
		
		//Checks if element exists in the array of the Set Object
		for(index = 0; index<this.Arr1_index; index++) {
			if(this.Arr1[index].equals(s)) {
				this.Arr1[index] = null;
				element_exists = true;
				break;
			}
		}
		
		//Shift elements to the left if the element removed is not the one at the end
		System.out.println(index +" " + this.Arr1_index);
		if((index-1 != this.Arr1_index-1) && (element_exists == true)) {
			for(int i = index; i<this.Arr1_index-1; i++) {
				this.Arr1[i] = this.Arr1[i + 1];
			}
			this.Arr1[Arr1_index-1] = null; //changes the value of the end of the last index to null as all shifted to the left by 1
			this.Arr1_index--;
		}
		
		 //Nothing to remove
		else {
			System.out.println("NOTHING TO REMOVE");
			return;
		}
	}
	
	//Returns type Set object that finds the Union between this set and set2 passed
	public Set Union(Set set2) {
		
		Set array_union = new Set(set2.Arr1); //Set object to be returned with union values
		array_union.Arr1_index = set2.Arr1_index; //so that it inherits all the properties and does not change Arr1_index to length of set2.Arr1 which may have null values incorporated for length 		
		
		for (int i = 0; i<this.Arr1_index;i++) {
			int count = 0; //Used to check if element has been seen in the set.
			
			//Checks if element has already been seen.  If count equals 1, then element has been seen
			for(int j = 0; j<set2.Arr1_index; j++) {
				if(this.Arr1[i].equals(set2.Arr1[j])) {
					count++;
					break;
				}
			}
			
			//If element in set1 does not exist in set 2, then append the element from set1 into array_union set
			if(count==0) {
				array_union.append(this.Arr1[i]);
			}
			
		}
		
		return array_union;
	}
	
	//Returns type Set object that finds the Intersection between this set and set2 passed
	public Set Intersection(Set set2) {

		Set array_intersection = new Set(); //Empty set. Set object to be returned with Intersection values
		
		for (int i = 0; i<this.Arr1_index;i++) {
			
			//Check if element in this set exists in set2. If so, append element to array_intersection
			for(int j = 0; j<set2.Arr1_index; j++) {
				if(this.Arr1[i].equals(set2.Arr1[j])) {
					array_intersection.append(this.Arr1[i]);
					break;
				}
			}
			
		}
		
		return array_intersection;
	}
	
	//Returns type Set object that finds the Difference between this set and set2 passed
	public Set Difference(Set set2) {

		Set array_diff = new Set(); //Empty array set. Will return with set difference values
		
		//Find elements that exist in set1 but not in set2. Append these to array_diff
		for (int i = 0; i<this.Arr1_index;i++) {
			int count = 0; //Used to check if element has been seen in the set
			
			for(int j = 0; j<set2.Arr1_index; j++) {
				if(this.Arr1[i].equals(set2.Arr1[j])) {
					count++;
					break;
				}
			}
			
			//If element in set1 does not exist in set 2, then append the element from set1 into array_union set
			if(count==0) {
				array_diff.append(this.Arr1[i]);
			}
			
		}
			
		
		return array_diff;
	}
	
	//Cartesian product of this set and set2 passed
	public Set Product(Set set2) {

		Set array_product = new Set(); //Empty array set. Will return with Cartesian product set
		
		//For each element in this set, concatenates with each element in set2
		for (int i = 0; i<this.Arr1_index;i++) {
			
			for(int j = 0; j<set2.Arr1_index; j++) {
				String prod = this.Arr1[i] + set2.Arr1[j];
				array_product.append(prod);
			}
			
		}
		
		return array_product;
	}
	
	public boolean isEqual(Set set2) {
		//Lengths will be the same(no repeated elements in the sets) and each element of set1 must exist in set2 for the sets to be considered equal
		//Checks if each element of set1 exists in set2. 
		//After checking a value, if false for certain set of comparison(after inner loop ends), then is_equal becomes false and returns
		
		if (this.Arr1_index == set2.Arr1_index) {
			
			boolean is_equal = false;
			for (int i = 0; i<this.Arr1_index;i++) {
				is_equal = false; //reset to false to check if next element of Arr1 exists in set2. Then change to true
				
				for(int j = 0; j<set2.Arr1_index; j++) {
					if(this.Arr1[i].equals(set2.Arr1[j])) {
						is_equal = true;
					}
					
				}
				
				//If is_equal is ever false, then there exists an element that does not exist. So returns false and ends the method
				if(is_equal == false) {	
					return false;
				}
				
			}
			
			return is_equal; //Otherwise, returns true if both sets are equal
		}
		
		return false; //if lengths are not the same, then cannot be equal sets
	}
	
	public boolean isSubset(Set set2) {
		
		//Similar to isEqual. However, length of the set does not matter here.
		boolean is_subset = false;
		for (int i = 0; i<this.Arr1_index;i++) {
			is_subset = false; //reset to false to check if next element of Arr1 exists in set2. Then change to true
			
			for(int j = 0; j<set2.Arr1_index; j++) {
				if(this.Arr1[i].equals(set2.Arr1[j])) {
					is_subset = true;
				}
				
			}
			
			//If is_subset is ever false, then there exists an element that does not exist in the other set. So returns false and ends the method
			if(is_subset == false) {	
				return false;
			}
			
		}
		return is_subset; //Otherwise, returns is_subset which is true
	}
	
	//Returns int of total number of (non-null) elements in the set
	public int getCount() {
		return this.Arr1_index;
	}
	
	//Returns String form of the Set objects Arr1 state variable
	public String toString() {
		String set_tostring = "{";
		for (int i = 0; i<this.Arr1_index; i++) {
			
			if(i != this.Arr1_index - 1) {
				set_tostring += this.Arr1[i] + ", "; //Concatenates each element with the "," character
			}
			
			else {
				set_tostring += this.Arr1[i]; //Once hits end of the array, then just concatenates last element. No need for "," character
			}
		}
		set_tostring += "}";
		return set_tostring;
	}
	
}
