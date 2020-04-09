package quizprep2;

public class QuickSort {
	
    public static int partition(Integer[] arr, int start, int end) {
		int pivot = end;
		int p_index = start;
		
		for(int i = start; i<=end-1;i++) {
			if(less(arr[i],arr[pivot])) {
				exch(arr,i,p_index);
				p_index++;
			}
		}
    	exch(arr,p_index,pivot);
    	return(p_index);    	
    }
    
    public static void quicksort(Integer[] arr, int start, int end) {
    	
    	if(start<end) {
    		int partition_index = partition(arr,start,end);
    		quicksort(arr,start,partition_index-1); //sort LHS
    		quicksort(arr,partition_index+1,end); //sort RHS
    	}
    }
    
    
	//Below methods needed for Generic data types for comparisons
	private static boolean less(Comparable v, Comparable w){ 
		return v.compareTo(w) < 0; // v < w
		}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i]; a[i] = a[j]; a[j] = t;
		}
	
	public static void bubblesort(int[] array) {
		
		for (int i = 0; i < array.length; i++) {
			for(int j = 1; j < array.length; j++) {
				
				if(array[j-1] > array[j]) { //if previous value is greater than current value, then swap places
					int temp = array[j-1]; //temp variable for storing the larger value
					array[j-1] = array[j];
					array[j] = temp;
				}
				
				
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
