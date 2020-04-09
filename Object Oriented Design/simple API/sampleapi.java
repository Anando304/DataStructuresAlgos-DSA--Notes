import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sampleapi {
	
	private final String name;
	private final int age;
	private final double height; //In meters
	private final String favfood;
	
	public sampleapi() {
		
		this.name = "Jonathan";
		this.age = 1;
		this.height = 1.2;
		this.favfood = "Pizza";
		
	}
	
	public sampleapi(String name, int age, double height, String food) {
		
		this.name = name;
		this.age = age;
		this.height = height;
		this.favfood = food;
		//
	}
	
	public int age() {
		return this.age;
	}
	
	public double height() {
		return this.height;
	}
	
	public String name() {
		
		return this.name;
	}
	
	public String food() {
		
		return this.favfood;
	}
	
	public String fullname (String lastname) {
		return this.name + " " + lastname;
	}


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		sampleapi person1 = new sampleapi("Anando",19,1.6,"Chili");
		//Scanner input = new Scanner(new File("input.txt")); 
		System.out.println(person1.fullname("Zaman"));
		

	}

}
