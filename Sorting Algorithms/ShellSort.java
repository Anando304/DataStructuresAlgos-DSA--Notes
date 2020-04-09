package quizprep2;

public class ShellSort {
	

    public static void shellsort(int[] a) {
	int increment = a.length / 3;
	while (increment > 0) {
		
		/* Special insertion sort portion with gaps */
		for (int i = increment; i < a.length; i++) {
			int j = i;
			int temp = a[i];
			while (j >= increment && a[j - increment] > temp) {
				a[j] = a[j - increment];
				j = j - increment;
			}
			a[j] = temp;
		}
		/* ************************************** */
		
		increment *= (1.0/3.0);
	}
    }
    
	public static void main(String[] args) {
		
	}
}
