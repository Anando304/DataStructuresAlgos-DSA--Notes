/** @file StackLinkedList.java
@author Anando Zaman
@brief Stacks using Arrays, Fixed size
*/
package stacks;

public class StackArray {
	
	private int size;
	private int top = -1; //Initially nothing on stack
	private Object[] arr;
	
	//Default constructor
	//Default stack size of 10, if not given
	public StackArray() {
		size = 10;
		arr = new Object[size];
	}
	
	//constructor
	public StackArray(int size) {
		arr = new Object[size];
	}
	
	public boolean isEmpty() {
		return top<0;
	}
	
	public void push(Object data) 
	{
		if(top >= size-1) {
			System.out.println("Stack is full");
			return;
		}
		
		//update top index pointer
		top++;
		arr[top] = data;
		
	}
	
	public Object pop() {
		if(top<=-1) {
			System.out.println("Stack is empty");
			return null;
		}
		
		Object data = arr[top];
		top--;
		return data;
	}
	
	public void printStack() {
		
		if(top<=-1) {
			System.out.println("Stack is empty");
			return;
		}
		
		System.out.print("Stack: ");
		for (int i = 0; i <= top; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
	}
	
	public static void main(String args[]) 
    { 
        StackArray stack = new StackArray(); 
        stack.push("A"); 
        stack.push(2); 
        stack.push("*"); 
        System.out.println("Value popped from stack: " + stack.pop()); 
        stack.printStack();
    } 
}
