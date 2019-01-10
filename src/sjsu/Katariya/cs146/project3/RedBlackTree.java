package sjsu.Katariya.cs146.project3;


/**
 * @author jay
 *THe following class represents a red black Tree
 * @param <Key> is the Type of RedBlack Tree that one wants to make.
 */
public class RedBlackTree<Key extends Comparable<Key>> {	
	
	
	
		private static RedBlackTree.Node<String> root; // IS the root of the tree
		
		

		/**
		 * @author jay
		 *
		 * @param <Key>
		 */
		public static class Node<Key extends Comparable<Key>> { //changed to static 
			
			  Key key;  		  
			  Node<String> parent;
			  Node<String> leftChild;
			  Node<String> rightChild;
			  boolean isRed;
			  int color;
			  
			  /**
			   * Default constructor for the node class
			 * @param data is the data of the node.
			 */
			public Node(Key data){
				  this.key = data;
				  leftChild = null;
				  rightChild = null;
			  }		
			  
			  /**
			   * Compare To method of the node class that compares two elements
			 * @param n is the other node that we want to compare the current node with
			 * @return returns a number according to the compare to method
			 */
			public int compareTo(Node<Key> n){ 	//this < that  <0
			 		return key.compareTo(n.key);  	//this > that  >0
			  }
			  
			
			
			  /**
			   * Checks if a node is a leaf.
			 * @return true if the node is a leaf, false if it is not.
			 */
			public boolean isLeaf(){
				  if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
				  if (this.equals(root)) return false;
				  if (this.leftChild == null && this.rightChild == null){
					  return true;
				  }
				  return false;
			  }
		}


		

		 /**
		  * Returns true is a node is a leaf Node.
		  * @param n is the node that we want to check
		  * @return true id the node is a leaf node.
		  */
		public boolean isLeaf(RedBlackTree.Node<String> n){
			  if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
			  if (n.equals(root)) return false;
			  if (n.leftChild == null && n.rightChild == null){
				  return true;
			  }
			  return false;
		  }
		
		
		
		public interface Visitor<Key extends Comparable<Key>> {
			/**
			This method is called at each node.
			@param n the visited node
			*/
			void visit(Node<Key> n);  
		}
		
		public void visit(Node<Key> n){
			
			if(n != null)
				System.out.println(n.key);
		}
		
		/**
		 * Start at the root node and traverse the tree using preorder
		 */
		public void printTree(){  //preorder: visit, go left, go right
			RedBlackTree.Node<String> currentNode = root;	
			printTree(currentNode);
		}
		
		
		/**
		 * Start at the root node and traverse the tree using preorder
		 * @param is the node where we start from.
		 */
		public void printTree(RedBlackTree.Node<String> node){
		
			if(node == null)
				return;
			
			System.out.print(node.key);
			if (node.isLeaf()){
				return;
			}
			
			printTree(node.leftChild);
			printTree(node.rightChild);
		}
		
		
		
		
		
		/**
		 * place a new node in the binary search tree with data the parameter and color it red.
		 * @param data is the string value of the node one wants to insert.
		 */
		public void addNode(String data){  	//this < that  <0.  this > that  >0
		 //	fill
			// place a new node in the RB tree with data the parameter and color it red. 
			Node<String> y = null;
			Node<String> x = root;
			Node<String> z = new Node(data);
			
			while(x != null)
			{
				y = x;
				if(z.key.compareTo(x.key) < 0)
					x = x.leftChild;	
				else
					x = x.rightChild;
				
			}
			z.parent = y;
			
			if(y == null)
				root = z;
			else if(z.key.compareTo(y.key) < 0)
				y.leftChild = z;
			else
				y.rightChild = z;
			
			z.leftChild  = null;
			z.rightChild = null;
			z.color = 0;				//Redy
			fixTree(z);
			
		}	

		
		/**
		 * Inserts a node to the RB Tree
		 * @param data is the data that one wants to add to the tree.
		 */
		public void insert(String data){
			addNode(data);	
		}
		
		
		/**
		 *  Looks up a a node which has a string key k
		 * @param k is the strign that we want to lookup.
		 * @return True if the key is found, false if it is not.
		 */
		public boolean lookup(String k){
			
			Node<String> node = root;
			
			
			while(node != null)
			{
				int comparison = node.key.compareTo(k);
				
				if(node.key.equals(k))
					return true;
				else if(comparison < 1)
					node = node.rightChild;
				else
					node = node.leftChild;
			}
			
			return false;

			
		}
	 	
		
		
		
		
		

		/**
		 * Gets the sibling of a node 
		 * @param n is the node whose sibling we want.
		 * @return the sibling of the node that was passed.
		 */
		public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){
			  
			
			Node<String> sibling = null;
			
			
			if(n == n.parent.rightChild)   //If the node is Right Child 
			{
				sibling = n.parent.leftChild;
				return sibling;
			}
			else 					//If the node is the Left Child.
			{
				sibling = n.parent.rightChild;
				return sibling;
			}
		
			
		}
		
		
		/**
		 * Gets the aunt of a node.
		 * @param n is the node whose aunt we want.
		 * @return the aunt of the node that was passed.
		 */
		public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
			
			Node<String> parent = n.parent;
			Node<String> aunt =  getSibling(parent);
			return aunt;
			
		}
		
		
		
		/**
		 * Gets the grandparent of a node.
		 * @param n is the node whose grandparent we want.
		 * @return returns the grandparent of the node.
		 */
		public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
			return n.parent.parent;
		}
		
		
		
		
		
		/**
		 * Rotates a subtree left, using a node
		 * @param x is the node around which we want to turn the subtree.
		 */
		public void rotateLeft(RedBlackTree.Node<String> x){
			
			
			Node<String> y = x.rightChild;   // set y 
			x.rightChild = y.leftChild; // turn y's left subtree into x's right subtree.
			
			if(y.leftChild != null)
				y.leftChild.parent = x;
			
			y.parent = x.parent; 	// link x's parent to y.
			
			if(x.parent == null)
				root = y;
			else if(x == x.parent.leftChild)
				x.parent.leftChild = y;
			else
				x.parent.rightChild = y;
			
			y.leftChild = x;	//put x on y's left.
			x.parent = y;
			
			
		}
		
		
		
		/**
		 * Rotates a subtree right, using a node
		 * @param x is the node around which we want to turn the subtree.
		 */
		public void rotateRight(RedBlackTree.Node<String> x){
			//
			
			Node<String> y = x.leftChild;   // set y 
			x.leftChild = y.rightChild; // turn y's right subtree into x's left subtree.
			
			if(y.rightChild != null)
				y.rightChild.parent = x;
			
			y.parent = x.parent; 	// link x's parent to y.
			
			if(x.parent == null)
				root = y;
			else if(x == x.parent.rightChild)
				x.parent.rightChild = y;
			else
				x.parent.leftChild = y;
			
			y.rightChild = x;	//put x on y's right.
			x.parent = y;
			
		}
		
		

		
		
		
		
		/**
		 * Fixes the colors of the tree if there is a disparity in the colors of the tree
		 * @param z is the node that was added and needs to be fixed.
		 */
		public void fixTree(RedBlackTree.Node<String> z) {
			
			if(z == root)
			{
				z.color = 1;     // Blacke
				return;
			}
			
			if(z.parent.color == 1)   // Blacke
				return;
			
			if(z.color == 0 && z.parent.color == 0)   //Redy & Redy
			{
				if(getAunt(z) == null || getAunt(z).color == 1)   //Blacke
				{
					if(z.parent == z.parent.parent.leftChild && z == z.parent.rightChild)
					{
						Node<String> y = z.parent;
						rotateLeft(z.parent);
						fixTree(y);
						
					}
					if(z.parent == z.parent.parent.rightChild && z == z.parent.leftChild)
					{
						Node<String> y = z.parent;
						rotateRight(z.parent);
						fixTree(y);
				
					}
					if(z.parent == z.parent.parent.leftChild && z == z.parent.leftChild)
					{
						z.parent.color = 1;     //Blacke
						z.parent.parent.color = 0;    // Redy 
						rotateRight(z.parent.parent);
						return;
						
					}
					if(z.parent == z.parent.parent.rightChild && z == z.parent.rightChild)
					{
						z.parent.color = 1;		//Blacke
						z.parent.parent.color = 0;		//Redy 
						rotateLeft(z.parent.parent);
						return;
					}
						
					
			
				}
				else if(getAunt(z).color == 0)			//Redy
				{
					z.parent.color = 1;		//Blacke
					getAunt(z).color = 1;	//Blacke
					z.parent.parent.color = 0;	//Redy
					fixTree(z.parent.parent);
					
				}
			}
			
			
			
		}
		
		
		/**
		 * Checks if a node is empty
		 * @param n is the node that we want to check if it is empty
		 * @return returns if the node is empty.
		 */
		public boolean isEmpty(RedBlackTree.Node<String> n){
			if (n.key == null){
				return true;
			}
			return false;
		}
		
		
		
		 
		/**
		 * Checks if a node is the left child
		 * @param parent is the parent node
		 * @param child is the child node
		 * @return returns true id the child is the left child.
		 */
		public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
		{
			if (child == child.parent.leftChild ) {//child is less than parent
				return true;
			}
			return false;
		}

		/**
		 * Visits the tree in pre order starting order.
		 * @param v is the visitor
		 */
		public void preOrderVisit(Visitor<String> v) {
		   	preOrderVisit(root, v);
		}
		 
		 
		/**
		 * Pre - order visits the node in according
		 * @param n is the node 
		 * @param v is the visitor node.
		 */
		private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
		  	if (n == null) {
		  		return;
		  	}
		  	v.visit(n);
		  	preOrderVisit(n.leftChild, v);
		  	preOrderVisit(n.rightChild, v);
		}	
		
		
		
		

		
		
		
	}

