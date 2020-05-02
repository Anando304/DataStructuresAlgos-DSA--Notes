package BinTree;

/* BinarySearchTree(BST) DataStructure
 * By: Anando Zaman
 * Details: API to create BST and perform operations on it
 * Each node is unique. No duplicate nodes
 * */
public class BST {
	
	//Creates Node Structure
	static class Node {
		int key;
		Node left;
		Node right;
		//Initialize node via constructor
		Node(int key){
			this.key = key;
		}
	}
	
	
	//Global instance/state variable for root node of BST class.
	private Node root;
	
	//Creates node not connected to anything. Basically a leaf node.
	private Node createNode(int key) {
		Node node = new Node(key);
		//initially, not connected to anything.
		node.left = null; 
		node.right = null;
		
		return node;
	}

	
	//Create and insert a node with a given key
	//Each node is unique, cannot occur more than once with same key.
	//Alternative method: check if next node is null.
	//Similar concept: BinarySearch
	private Node addNode(Node root, int targetKey) {
		
		//Base case, if reached node that is null.
		//Therefore, previous node is a leaf node OR no tree exists
		if(root == null) {
			return createNode(targetKey);
		}
		
		//key already exists
		//prevent duplicate key
		else if(root.key == targetKey) {
			return null;
		}
		
		else if(targetKey < root.key) {
			root.left = addNode(root.left,targetKey);
		}
		
		else if (targetKey > root.key) {
			root.right = addNode(root.right,targetKey);
		}
		
        /* return the node pointer */
        return root; 
	}
	
	//Search for a node
	private Node findNode (Node root, int key) {
		
		if(root == null) {
			return null;
		}
		
		else if(key < root.key) {
			return findNode(root.left,key);
		}
		
		else if(key > root.key) {
			return findNode(root.right,key);
		}
		
		//found the matching key
		return root;
	}
	
	
	//Find the successor to a node
	//In other words, the leftmost-node value of the right-child. Recall SFWRENG 2C03: DataStructs & Algorithms
	//Predecessor is rightmost-node of left-child node
	//So initially when using this function, pass in right child, then keep passing in left until hit null
	private Node findSuccessor(Node root) {
		
		//Base case: Found the successor
		if(root.left == null) {
			return root;
		}
		
		return findSuccessor(root.left);
	}
	
	//Delete a node
	//Three cases: delete leaf node, delete node with one child, delete node with 2 children(Hibbard deletion Algorithm)
	private Node delNode (Node root, int key) {
		
		//Element to delete DOES NOT EXIST.
		if(root == null) {
			return null;
		}
		
		else if(key < root.key) {
			return findNode(root.left,key);
		}
		
		else if(key > root.key) {
			return findNode(root.right,key);
		}
		
		//Otherwise, found the matching node with key
		else {
			//Then node to delete is a leaf-node
			if (root.left==null && root.right==null){
				root = null; //set this node to null, effectively deleting it
			}
			
			//if only one child(left child DNE, but right child does exist)
			//Then replace this node with its corresponding right-child
			else if(root.left==null) {
				root = root.right;
			}
			
			//if right child DNE but left child does, replace root with left child
			else if(root.right==null) {
				root = root.left;
			}
			
			//both children exist.
			//Use Hibbard deletion. Replace node with its successor, and then remove the other instance of successor.
			//This maintains BST order
			else {
				//Find successor key
				int successor = findSuccessor(root).key;
				//replace value with suc key
				root.key = successor;
				//delete the successor from right subtree, then assign the new updated right tree to the right of this node
				root.right = delNode(root.right, successor);
				return root; //return the updated tree by returning reference to current node
				
			}
		}
		return root;
		
		
	}
	
	//Exported function to insert into node. Used by the API
	public void insert(int targetKey) {
		
		//point root to head of root of tree.
		this.root = addNode(this.root, targetKey);
	}
	
	//Exported and used by the API. Executes findNode
	public Node find(int findKey) {
			
		return findNode(this.root, findKey);
	}
	
	
	//Traverse in the form: left root right
	public void inorderTraversal (Node root) {
		
		if(root != null) {
			inorderTraversal(root.left);
			System.out.println(root.key);
			inorderTraversal(root.right);
		}
	}
	
	//Middle left right
	public void preorderTraversal (Node root) {
		
		if(root != null) {
			System.out.println(root.key);
			inorderTraversal(root.left);
			inorderTraversal(root.right);
		}
	}
	
	// left right middle
	public void postorderTraversal (Node root) {
		
		if(root != null) {
			inorderTraversal(root.left);
			inorderTraversal(root.right);
			System.out.println(root.key);
		}
	}
	
	
	//Main Method for execution
	public static void main(String [] args) {
		BST tree = new BST();
		tree.insert(8);
		tree.insert(4);
		tree.insert(14);
		tree.insert(3);
		tree.insert(7);
		tree.insert(12);
		tree.insert(19);
		
		tree.preorderTraversal(tree.root);
		
		Node findNode = tree.find(10);
	}
}
