import java.util.LinkedList;

/**
 * 
 * @author gallbr02
 *	reps a binary tree node
 */
public class BTNode<E> {
	E data;
	BTNode<E> left, right;
	BTNode<E> parent;
	public BTNode() {
		
	}
	public BTNode(E d) {
		this(d, null, null);
	}
	public BTNode(E d, BTNode<E> l, BTNode<E> r) {
		data = d;
		left = l;
		right = r;
	}
	
	/**
	 * returns true if this node is a leaf node and false otherwise
	 * @return true if this node is a leaf node and false otherwise
	 */
	public boolean isLeaf() {
		return (left == null && right == null);
	}
	/**
	 * returns true if this node has a left child and false otherwise
	 * @return true if this node has a left child and false otherwise
	 */
	public boolean hasLeft() {
		return (left != null);
	}
	/**
	 * returns true if this node has a right child and false otherwise
	 * @return true if this node has a right child and false otherwise
	 */
	public boolean hasRight() {
		return (right != null);
	}
	
	/**
	 * returns new root of the tree which
	 * is a copy of the given tree
	 */
	public static <E> BTNode<E> copyTree(BTNode<E> ptr){
		if(ptr == null) {
			return null;
		}
		return new BTNode<E> (ptr.data, copyTree(ptr.left), copyTree(ptr.right));
		
	}
	/*
	public static <E> BTNode<E> scaleTree(BTNode<E> ptr, double sf){
		ptr.data * sf;
		scaleTree(ptr.left, sf);
		scaleTree(ptr.right, sf);
	}
	*/
	
	// countNodes from given BTNode, ptr
	// static: only paramenters are accessed
	//			no access to instance member variables
	public static int countNodes(BTNode ptr) {
		if(ptr == null) {
			return 0;
		}
		else {
			return (1 + countNodes(ptr.left)+ countNodes(ptr.right));
		}
	}
	//returns height or number of levels rooted at level
	public static int getHeight(BTNode ptr) {
		
		if(ptr == null) {
			return 0;
		}
		else {
			//get the height of left child and all decendents
			//get the height of right child
			int lHeight= getHeight(ptr.left);
			int rHeight= getHeight(ptr.right);
			if(lHeight > rHeight) {
				return lHeight + 1;
			}else {
				return rHeight + 1;
			}
			//compare the two and return the larger
		}
	}
	//tree traversal algorithms
	//pre-order:
	
//	public static String preOrder(BTNode ptr) {
//		
//		if(ptr == null) {
//			return "";
//		}else {
//			String str = ptr.data + " ";
//			String strL = preOrder(ptr.left);
//			String strR = preOrder(ptr.right);
//			
//			return str = str + " " + strL + " " + strR + " ";
//		}
		//tree.root.preOrder
		/*
		String str = "" + ptr.data + " ";
		while(ptr.left != null) {
			str += ptr.left.data + " ";
		}
		return str;
		 */
//	}
	
	public static <E> String levelorder(BTNode<E> ptr) {
		// start with root by adding it to an empty queue
		// use Java LinkedList: addLast, pollFirst
		
		// as long as queue is not empty
		// dequeue one node
		// visit it (print its data or add data to string
		
		// add its children to the queue if they exist;
		
		LinkedList<BTNode<E>> queue = new LinkedList<BTNode<E>>();
		queue.addLast(ptr);
		String str = " ";
		
		while(!queue.isEmpty()) {
			BTNode<E> current = queue.pollFirst();
			if(current.hasLeft()) {
				queue.addLast(current.left);
			}
			if(current.hasRight()) {
				queue.addLast(current.right);
			}
			// add curr data to str
			str += current.data + " ";
			// add children of curr to queue;
			// if they exist;
		}
		return str;
		
	}
	
	
	public static void main (String[] args) {//	left,right
		BTNode<Integer> n15 = null;
		BTNode<Integer> n25 = null;
		BTNode<Integer> n2 = null;
		BTNode<Integer> n8 = null;

		BTNode<Integer> n9 = new BTNode<Integer>(9, n8, null);

		BTNode<Integer> n20 = new BTNode<Integer>(20, n15, n25);

		BTNode<Integer> n5 = new BTNode<Integer>(5, n2, n9);

		BTNode<Integer> n0 = new BTNode<Integer>(10, n5, n20);
		
		System.out.println(countNodes(n0));
		System.out.println(getHeight(n0));
		
		System.out.println(levelorder(n0));

	}
		
}
