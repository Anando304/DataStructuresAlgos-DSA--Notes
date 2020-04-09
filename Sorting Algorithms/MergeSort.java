package quizprep2;

public class MergeSort {

	public static void merge(int[] left, int[] right, int[] outputA) {
    	// ie; leftA = [1,7,3,9,4]
    	//    rightA = [9,2,4,5]
    	//   outputA = [1, 2, 3, 4, 4, 5, 7, 9, 9]
    	
    	int leftA_index = 0;
    	int rightA_index = 0;
    	int outputA_index = 0;
    	
    	//merge the two arrays in order
    	/*Sorts the two left & right arrays. Overwrites the original array A by combining the sorted arrays */
    	while((leftA_index<left.length) && (rightA_index<right.length)) {
    		
    		if(left[leftA_index] < right[rightA_index]) {
    			outputA[outputA_index] = left[leftA_index];
    			leftA_index++;
    		}
    		
    		else {
    			outputA[outputA_index] = right[rightA_index];
    			rightA_index++;
    		}
    		
    		outputA_index++;
    	}
    	
    	/* If any elements remaining in left[], copy them over */
    	while(leftA_index<left.length) {
    		outputA[outputA_index] = left[leftA_index];
    		leftA_index++;
    	}
    	
    	/* If any elements remaining in right[], copy them over */
    	while(rightA_index<right.length) {
    		outputA[outputA_index] = right[rightA_index];
    		rightA_index++;
    	}
    }
    
    
    public static void mergesort(int[] arr) { //Recursively merge and sort
    	
    	if(arr.length == 1) { //base case
    		return;
    	}
    	
    	//partioning indices
    	int mid = arr.length/2;
    	int[] left = new int[mid];
    	int[] right = new int[arr.length-mid];
    	
    	//creation of left and right subarrays
    	for (int i = 0; i<mid; i++) {
    		left[i] = arr[i];
    	}
    	
    	for(int i = 0; i<arr.length-mid; i++) {
    		right[i] = arr[i+mid];
    	}
    	
        /*Recursively call mergeSort to continue to create smaller sub-arrays for left and right sides*/
        mergesort(left);
        mergesort(right);

        /*Sort & Merge the final arrays traversing back up the tree*/
        merge(left,right,arr);
    }
    
	public static void main(String[] args) {
		
	}
}
