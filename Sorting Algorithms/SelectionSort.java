package quizprep2;

public class SelectionSort {
	
	public static void selectionsort(int[] array) {
		// selection sort selects the element you want to sort and puts it in its final
		// position immediately
		
		for (int i = 0; i<array.length; i++) {
			int min = i; //default minimum index. Gets updated each time loop is ran
			for (int j = i+1; j<array.length; j++) {
				if(array[j]<array[min]) {
					min = j; //update new min index for preparation of swapping after					
				}
			}
			int temp = array[i];
			array[i] = array[min]; //swap the smaller element (which as at index min) into index i
			array[min] = temp;
			
		}
	}
	
	public static void main(String[] args) {
		
	}
}
