/** @file StackLinkedList.java
@author Anando Zaman
@brief Dynamic stacks using LinkedLists
@details Linkedlist implementation of stack
*/
package stacks;

//Linkedlist implementation of a stack data structure
// Generic stack containing any data type
public class StackLinkedList {
	
	
	//represents the top node value. 
	//Kind of acts like a pointer to Top node
	//Initially null for empty stack
	Node top_node = null; 
	
	
	//Kind of like creating a struct node in C
	static class Node<T> {
		T key; //generic data contained in the node
		Node node; //Used to get the node connected to in the stack. Kind of like a pointer
		
		Node(T key){
			this.key = key;
		}
	}
		
		
	public boolean isEmpty() {
		return top_node.equals(null);
	}
	
	
	//Add node with specified key to the stack
	public <T> void push(T key) {
		
		//create a new node with the key value
		Node new_node = new Node(key);
		
		//if stack is empty,
		//then set top_node to new node created
		if(top_node == null) {
			top_node = new_node;
		}
		
		//insert node, update top_node to reflect new node, connect top_node to previous top_node
		else {
			
			//Create a temp variable to save the current top_node
			Node temp = top_node;
			//update top_node to reflect new_node
			top_node = new_node;
			//Connect the top_node to the previous temp which contains the previous top_node
			new_node.node = temp;
		}
	}
	
	//Removes and returns a generic object type from the top of the stack
	public <T> Object pop() {
		
		//if stack is empty while trying to pop
		if(top_node == null) {		
			System.out.println("Stack is empty");
			return null;
		}
		
		Object key = top_node.key;
		//Set top_node to the next node in the stack
		top_node = top_node.node; 
		return key;
		
	}
	
	public <T> Object peak() {
		
		if(top_node == null) {
			System.out.println("Stack is empty"); 
			return -1;
		}
		
		return top_node.key;
	}
	
	
	public static void main(String[] args) {
		
		StackLinkedList stack = new StackLinkedList();
		stack.push(2);
		stack.push(4);
		stack.push(8);
		stack.push(0);
		System.out.println("Push: 2\n Push: 4\n Push: 8\n Push: 0\n");
		System.out.println("Peak: " + stack.peak());
		System.out.println(stack.pop());
		System.out.println("Peak: " + stack.peak());
		stack.push(-72);
		System.out.println("Push: " + -72);
		System.out.println("Pop: " +stack.pop());
		stack.push("A");
		System.out.println("Push: A");
		System.out.println("Peak: " + stack.peak());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Pop: " + stack.pop());
		System.out.println("Pop: " + stack.pop());
		}
		
}

