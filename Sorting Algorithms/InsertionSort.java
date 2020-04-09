package quizprep2;

public class InsertionSort {

	public static void insertionsort(int[] array) {
		
		/*Start sorting from index 1 as we need to be able to compare atleast one index to the left(index 0) in the while loop*/
		for(int i = 1; i<array.length; i++) { //used for creating temporary holes for comparisons till end of array
			
			int value = array[i]; //temporary hole entry value
			int hole = i; //temporary index for hole
			
			while((hole>0) && (array[hole-1]>value)){ //this part essentially sorts it in ascending order
				array[hole] = array[hole-1]; //shift over nodes to the right until finds a previous node which is smaller than value(origional hole node)
				hole--; //check each previous node
			}
			
			array[hole] = value; //At the end of the loop, the value(smallest value) gets assigned to the new hole position marking it as the current smallest value in sorted order
		}
	}
	
	public static void main(String[] args) {
		
	}
}
