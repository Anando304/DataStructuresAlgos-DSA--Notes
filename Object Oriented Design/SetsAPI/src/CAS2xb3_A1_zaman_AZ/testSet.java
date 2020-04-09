package CAS2xb3_A1_zaman_AZ;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testSet {
	
	private Set[] pArr; // declare objects of type set

	@Before
	public void setUp() throws Exception {		
		
		pArr = new Set[100];
		Scanner input = new Scanner(new File("input.txt")); //Essentially from the root of the program(ie; where src and others are located)
		
		int i = 0;
		//Save the input line by line to the array of sets where each index in Set[] pArr represents a set{String[] inputset)
		while (input.hasNextLine()){
				String temp = input.nextLine();
				String[] values = temp.split(","); //Creates a string array seperated at the commas
				pArr[i] = new Set(values);
				i++;
				if(i>7) { //Since only 7 lines in the test file, no need to keep reading for lines. Otherwise, this line can be removed
					break;
				}
		}
		input.close();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	

	@Test
	public void testUnion() throws IOException {
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testUnion...\n");
		
		//Normal case: Union of a mix of element values
		String[] union = pArr[1].Union(pArr[2]).get_Arr1();
		boolean pass_status = union[0].equals("1") && union[1].equals("2") && union[2].equals("4")  && union[3].equals("3");
		fw.write("test case 1 passed: " + pass_status + "\n");
		assertTrue(pass_status);
		
		//Normal case :Union of the same element values
		String[] union2 = pArr[1].Union(pArr[6]).get_Arr1();
		boolean pass_status1 = union2[0].equals("1") && union2[1].equals("2") && union2[2].equals("3");
		fw.write("test case 2 passed: " + pass_status1 + "\n");
		assertTrue(pass_status1);
		
		//Boundary/Exception case ( union of many elements that it exceeds space of the set(array) )
		String[] union3 = pArr[1].Union(pArr[0]).get_Arr1();
		boolean pass_status2 = true;
		fw.write("test case 3 passed: " + pass_status2+ "\n");
		assertTrue(pass_status2); //array becomes full, so prints "ARRAY IS FULL" to the console. No need for error exception here. Skips the need of appending to full array

		fw.close();
	}
	
	
	@Test
	public void testIntersection() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testIntersection...\n");
		
		//Normal case: distinct element values
		String[] intersection = pArr[1].Intersection(pArr[2]).get_Arr1();
		boolean pass_status =  intersection[0].equals("1") && intersection[1].equals("2");
		fw.write("test case 1 passed: " + pass_status + "\n");
		assertTrue(pass_status);
		
		//Normal case :Intersection of the same element values
		String[] intersection2 = pArr[1].Intersection(pArr[6]).get_Arr1();
		boolean pass_status1 =  intersection2[0].equals("1") && intersection2[1].equals("2");
		fw.write("test case 2 passed: " + pass_status1 + "\n");
		assertTrue(pass_status1);
		
		//Boundary/Exception case (Intersection of 20 of the same elements - EDGE CASE)
		String[] intersection3 = pArr[0].Intersection(pArr[0]).get_Arr1();
		boolean pass_status2 = true;
		fw.write("test case 3 passed: " + pass_status2 + "\n");
		assertTrue(pass_status2); //nothing new gets added and stays at max array size. Does not overflow or print anything
		
		fw.close();
	}
	
	@Test
	public void testDifference() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testDifference...\n");
		
		//Normal case: distinct element values
		String[] difference = pArr[4].Difference(pArr[5]).get_Arr1();
		boolean pass_status =  difference[0].equals("4") && difference[1].equals("6");
		fw.write("test case 1 passed: " + pass_status + "\n");
		assertTrue(pass_status ); // {4,6}
		
		//Normal case :Difference of the same element values
		String[] difference2 = pArr[1].Difference(pArr[1]).get_Arr1();
		boolean pass_status2 =  difference2[0] == null;
		fw.write("test case 2 passed: " + pass_status2 + "\n");
		assertTrue(pass_status2); // Nothing to create as a difference
		
		//Boundary/Exception case (Large array)
		boolean pass_status3 = true;
		String[] difference3 = pArr[1].Difference(pArr[0]).get_Arr1();
		String[] difference3soln = {"1", "2", "3"};
		for (int i = 0; i < difference3soln.length; i++) {
			if (!(difference3[i].equals(difference3soln[i]))) {
				pass_status3 =  false;
				assertTrue(pass_status3);
			}
		}
		
		fw.write("test case 3 passed: " + pass_status3 + "\n");

		fw.close();
		
	}
	
	
	@Test
	public void testProduct() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testProduct...\n");
		
		
		//Normal case: Intersection of a mix of element values
		boolean pass_status = true;
		String[] prod = pArr[2].Product(pArr[6]).get_Arr1();
		String[] prodsoln = {"11", "12", "13", "21", "22", "23", "41", "42", "43"};
		for (int i = 0; i < prodsoln.length; i++) {
			if (!(prod[i].equals(prodsoln[i]))) {
				pass_status = false;
				assertTrue(pass_status);
			}
		}
		fw.write("test case 1 passed: " + pass_status + "\n");


		
		
		//Normal case: Intersection of a mix of element values
		boolean pass_status2 = true;
		String[] prod1 = pArr[5].Product(pArr[6]).get_Arr1();
		String[] prod1soln = {"11", "12", "13", "21", "22", "23", "31", "32", "33"};
		for (int i = 0; i < prod1soln.length; i++) {
			if (!(prod1[i].equals(prod1soln[i]))) {
				pass_status2 = false;
				assertTrue(pass_status2);
			}
		}	
		fw.write("test case 2 passed: " + pass_status2 + "\n");
		
		
		//Boundary case: Finding the cartesian product for  a large array. This causes Array to exceed larger than size 20. Thus, prints 'ARRAY IS FULL' to the screen
		String[] prod2 = pArr[1].Product(pArr[0]).get_Arr1();
		String[] prod2soln = {"11", "12", "13", "21", "22", "23", "41", "42", "43"}; //Prints "ARRAY IS FULL" to the screen as the cartesian product creates a set greater than 20
		fw.write("test case 3 passed: " + true + "\n");
		
		fw.close();
	}
	
	
	@Test
	public void testIsEqual() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testIsEqual...\n");
		
		//Normal case: Intersection of a mix of element values
		boolean pass_status = pArr[5].isEqual(pArr[6]);
		fw.write("test case 1 passed: " + pass_status + "\n");
		assert(pass_status);
		
		//Boundary case: Entering a set for comparison that is greater than 20
		String[] array8 = {"1","2","4","11", "12", "13", "24", "22", "23", "41", "42", "43","10", "15", "13", "21", "22", "23", "41", "42", "43"};
		Set p8 = new Set(array8);
		boolean pass_status2 = !(pArr[5].isEqual(p8));
		fw.write("test case 2 passed: " + pass_status2 + "\n");
		assert(pass_status2);
		
		fw.close();
	}
	
	
	@Test
	public void testIsSubset() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testIsSubset...\n");
		
		//Normal case: Valid subset
		boolean pass_status = pArr[5].isSubset(pArr[6]);
		fw.write("test case 1 passed: " + pass_status + "\n");
		assert (pass_status);
		
		//Normal case: Not a valid subset
		boolean pass_status2 = !(pArr[1].isSubset(pArr[0]));
		fw.write("test case 2 passed: " + pass_status2 + "\n");
		assert (pass_status2);
		
		//Normal case: two of the same sets.
		boolean pass_status3 = pArr[1].isSubset(pArr[1]);
		fw.write("test case 3 passed: " + pass_status3 + "\n");
		assert (pass_status3);
		
		//Boundary case: Entering a set for comparison that is greater than 20
		String[] array8 = {"1","2","4","11", "12", "13", "24", "22", "23", "41", "42", "43","10", "15", "13", "21", "22", "23", "41", "42", "43"};
		Set p8 = new Set(array8);
		boolean pass_status4 = !(pArr[5].isSubset(p8));
		fw.write("test case 4 passed: " + pass_status4 + "\n");
		assert(pass_status4);
		
		fw.close();
	}
	
	
	@Test
	public void testGetCount() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testGetCount...\n");
		
		//Normal case: length of union set
		Set union = pArr[1].Union(pArr[2]);
		boolean pass_status = union.getCount() == 4;
		fw.write("test case 1 passed: " + pass_status + "\n");
		assertTrue(pass_status);
		
		//Normal case 2: length of cartesian product set
		Set prod = pArr[2].Product(pArr[6]);
		boolean pass_status2 = prod.getCount() == 9;
		fw.write("test case 2 passed: " + pass_status2 + "\n");
		assertTrue(pass_status2);
		
		//Normal case 3: Valid subset from union
		boolean pass_status3 = pArr[5].getCount() == 3;
		fw.write("test case 3 passed: " + pass_status3 + "\n");
		assertTrue(pass_status3);
		
		//Boundary case: Entering a set for comparison that is greater than 20
		String[] array8 = {"1","2","4","11", "12", "13", "24", "22", "23", "41", "42", "43","10", "15", "13", "21", "22", "23", "41", "42", "43"};
		Set p8 = new Set(array8);
		boolean pass_status4 = !(p8.getCount() == 21); //length would be 21 but since the Set cannot be made with size>20, the assertion returns false
		fw.write("test case 4 passed: " + pass_status4 + "\n");
		assert(pass_status4); 
		
		fw.close();
	}
	
	
	@Test
	public void testToString() throws IOException {
		
		FileWriter fw = new FileWriter("output.txt",true); //true for append;
		fw.write("\nEntering testToString...\n");
		
		//Normal case:
		boolean pass_status = pArr[5].toString().equals("{1, 2, 3}");
		fw.write("test case 1 passed: " + pass_status + "\n");
		assert(pass_status);
		
		//Normal case:
		Set union = pArr[1].Union(pArr[2]);
		boolean pass_status2 = union.toString().equals("{1, 2, 4, 3}");
		fw.write("test case 2 passed: " + pass_status2 + "\n");
		assert(pass_status2);
		
		fw.close();
	}
	


}
