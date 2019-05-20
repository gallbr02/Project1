import java.util.Comparator;
/**
 * @author Brandon Gallagher
 * A representation of a generic binary search tree
 *
 * @param <E>
 */
public class BSTree<E>{
	

	/**
	 * creates and initializes an empty binary search tree
	 * @param cmp the empty binary search tree
	 */
	public BSTree(Comparator<E> cmp) {
		root = null;
		this.cmp = cmp;
	}
	/**
	 * constructor 
	 */
	public BSTree(){
		
	}

	/**
	 * adds data to the correct lace in the tree according to the comparator
	 * @param data that is being added
	 */
	public void add(E data) {
		if(root == null) {
			root = new BTNode<E>(data);
		}else {
			BTNode<E> ptr = root;
			while(ptr != null) {
				if(cmp.compare(data,  ptr.data)==-1) {
					if(ptr.left == null) {
						ptr.left = new BTNode<E>(data);
						ptr.left.parent = ptr;
						count++;
						return;
					}else {
						ptr = ptr.left;
					}
					
				}
				else if (cmp.compare(data , ptr.data)==1) {
					if(ptr.right == null) {
						ptr.right = new BTNode<E>(data);
						ptr.right.parent = ptr;
						count++;
						return;
					}
					else {
						ptr = ptr.right;
					}
					
				}
				else if(cmp.compare(data, ptr.data)== 0) {
					if(ptr.left == null) {
						ptr.left = new BTNode<E>(data);
						ptr.left.parent = ptr;
						count++;
						return;
					}else {
						ptr = ptr.left;
					}
					
				}
				
			}
			
		}
		
	}

	/**
	 * Returns true if there is at least one data matching the target
	 * using a binary search algorithm
	 * @param target that yyou are trying to match with the data
	 * @return true if there is at least one data matching the target
	 */
	public boolean contains (E target) {
		if(root == null) {
			return false;
		}
		else {
			BTNode<E> ptr = root;
			while(ptr != null) {
				if(cmp.compare(target, ptr.data)== -1) {
					ptr = ptr.left;
				}
				else if(cmp.compare(target, ptr.data)==1) {
					ptr = ptr.right;
				}
				else if(cmp.compare(target, ptr.data)== 0) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * returns the root node
	 * @return the root node
	 */
	public BTNode<E> getRoot(){
		return root;
	}
	/**
	 * returns the numberof data items stored in the tree
	 * @return the number of data items in the tree
	 */
	public int size() {
		return count;
	}
	/**
	 * returns true if the tree is empty
	 * @return true if the tree is empty or false 
	 */
	public boolean empty() {
		return (count == 0);
	}
	public String toString() {
		return preOrder(root);
	}
	public static String preOrder(BTNode ptr) {

		if(ptr == null) {
			return "";
		}else {
			String str = ptr.data + " ";
			String strL = preOrder(ptr.left);
			String strR = preOrder(ptr.right);
			//tree.root.preorder
			return str = str + " " + strL + " " + strR + " ";
		}
		//tree.root.preOrder
		/*
		String str = "" + ptr.data + " ";
		while(ptr.left != null) {
			str += ptr.left.data + " ";
		}
		return str;
		 */
	}

	private BTNode<E> root;
	private Comparator<E> cmp;
	private int count;
	private BTNode<E> parent;

	
}
