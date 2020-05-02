/* Fibonacci Dynamic Programming - TopDown Approach
 * By: Anando Zaman
 * Description: Excessive recursive calls can cause stackOverFlow errors and slower runtimes due to re-computation.
 * Soln: Instead, we will 'cache' the value and return it if needs to be re-accessed. TopDown Approach, recursively
 * */
package DynamicProgramming;

import java.util.Scanner;

public class TopDownFibonacci {
	
	private static long[] cache = new long[100]; //represents the 'seen' fib values saved at index n for a given fib.
	public static long fibonacciTopDown(int n) {
		//Base case
		if (n == 0) { return 0; }
		else if (n == 1) { return 1; }
		 //return cached value if previously computed
		else if(cache[n]>0) { return cache[n]; }
		
		//Otherwise, compute the new fib value and save to the cache
		cache[n] = fibonacciTopDown(n-1) + fibonacciTopDown(n-2);
		return cache[n]; 
	}
	
	public static void main(String[] args) {
		System.out.println("Enter fibonacci size: ");
		Scanner input = new Scanner(System.in);
		int fibSize = Integer.parseInt(input.next());
		input.close();
		System.out.println(fibonacciTopDown(fibSize));
	}
}
