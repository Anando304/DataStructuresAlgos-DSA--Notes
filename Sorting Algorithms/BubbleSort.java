package quizprep2;

public class BubbleSort {
	
	private static boolean less(Comparable v, Comparable w){ 
		return v.compareTo(w) < 0; // v < w
		}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i]; a[i] = a[j]; a[j] = t;
		}
	
	public static void bubblesort_generic(Comparable[] array) {
		
		for (int i = 0; i < array.length; i++) {
			for(int j = 1; j < array.length; j++) {
				
				if(less(array[j],array[j-1])){ //if previous value is greater than current value, then swap places
					exch(array, j, j-1);
				}
				
				
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
