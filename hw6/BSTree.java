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
			count++;
		}else {
			BTNode<E> ptr = root;
			while(ptr != null) {
				if(cmp.compare(data,  ptr.data)<=0) {
					if(ptr.left == null) {
						ptr.left = new BTNode<E>(data);
						ptr.left.parent = ptr;
						count++;
						return;
					}else {
						ptr = ptr.left;
					}
				}
				else if(cmp.compare(data, ptr.data)>=0){
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
				else if(cmp.compare(data, ptr.data)==0) {
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

	/**
	 * 
	 * @param ptr
	 * @return
	 */
	private static<E> E getRightMostData(BTNode<E> ptr) {
		if(ptr.right == null) {
			return ptr.data;
		}
		else {
			return getRightMostData(ptr.right);
		}

	}
	/**
	 * returns the right most data in the tree rooted at ptr recursively
	 * @param ptr the data being returned
	 * @return the data being removed
	 */
	public static <E> BTNode<E> removeRightMostNode(BTNode<E> ptr){  
		if(ptr == null) {
			return null;
		}
		else {
			BTNode<E> parentOfRightMost = ptr;
			BTNode<E> rightMost = ptr.left; 

			if(ptr.hasLeft()) {
				while(rightMost.right != null) {
					parentOfRightMost = rightMost;
					rightMost = rightMost.right;
				}
				ptr.data = rightMost.data;
				if(parentOfRightMost.right == rightMost) {
					if(rightMost.isLeaf()) {
						parentOfRightMost = null;
					}else {
						parentOfRightMost.right = rightMost.left;
					}

				}else {
					parentOfRightMost.left = rightMost.left;
				}
			}
			return rightMost;
		}
	}

	/**
	 * removes the first occurrence(closest to root)
	 * @param target from this binary search tree 
	 * @return the removed value or null if target does not exist in this tree
	 */
	public E remove(E target) {
		parent = null;
		BTNode<E> ptr = root;
		//int comp = cmp.compare(target, ptr.data);
		while(ptr != null) {
			parent = ptr;
			//checking the leaf remove
			if(target.equals(ptr.data) && ptr.isLeaf()) {
				if(cmp.compare(target, ptr.data) > 0) {
					parent.right = null;
				}
				else {
					parent.left = null;
				}
				return ptr.data;
			}

			//checking the not leaf and does not have a right leaf
			else if(target.equals(ptr.data) && !ptr.isLeaf()) {
				if(!ptr.hasRight() && parent.left == ptr) {
					parent.right = ptr.left;
				}
				else if(!ptr.hasLeft() && parent.right == ptr.right) {
					parent.right = ptr.right;
				}
				else if(ptr.hasLeft()) {
					parent.left = ptr.right;
				}
				else {
					if(ptr == root) {
						BTNode<E> right = removeRightMostNode(ptr);
						ptr = right;
						if(right.isLeaf()) {
							parent.right = null;
						}else{
							parent.left = right.left;
						}
					} 
				}
				return ptr.data;
			}	
			count --;
		}
		return target;	
	}
	/**
	 * for debugging purposes, returns a print friendly string 
	 * with entire content of this tree in level order
	 */
	public String toString() {
		return BTNode.levelorder(root);
	}


	private BTNode<E> root;
	private Comparator<E> cmp;
	private int count;
	private BTNode<E> parent;


}
