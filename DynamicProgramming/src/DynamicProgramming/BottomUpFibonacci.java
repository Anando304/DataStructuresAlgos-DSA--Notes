/*
 * Program: Bottom-Up Fibonacci
 * Author: Anando Zaman
 * Description: Create Fibonacci Sequence by building it bottom-up
 * Iterative Dynamic approach to Fib Sequence
 * */
package DynamicProgramming;

import java.util.Scanner;

public class BottomUpFibonacci {
	
	public static long fibonacciBottomUp(int n) {
		long[] cache = new long[n+1]; //represents the 'seen' fib values saved at index n for a given fib.
		cache[0] = 0;
		cache[1] = 1;
		for (int i=2; i<=n; i++) {
			cache[i] = cache[i-1] + cache[i-2];
		}
		return cache[n];
	}
	
	public static void main(String[] args) {
		System.out.println("Enter fibonacci size: ");
		Scanner input = new Scanner(System.in);
		int fibSize = Integer.parseInt(input.next());
		input.close();
		System.out.println(fibonacciBottomUp(fibSize));

	}
}
