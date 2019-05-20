import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
/**
 * 
 * @author Brandon Gallagher
 * BTree.java
 * representation of a binary tree
 * @param <E>
 */
public class BTree<E> {
	public BTree(Comparator<E> c, int deg) {
		this.cmp = c;
		this.degree = deg;
		root = null;
	}
	/**
	 * looks through the data values in ptr and returns the child to which target might belong.
	 * if ptr is a leaf node, it returns null
	 * @param ptr data values that we look through
	 * @param target the child that belongs to it
	 * @return the child to which target might belong
	 */
	private BTreeNode<E> stepDown(BTreeNode<E> ptr, E target){
		if(ptr == null || ptr.isLeaf()) {
			return null;
		}else {
			//for(int i = 0; i < ptr.dataArray.size(); i++) {
			//? if(cmp.compare(target,ptr.dataArray.get(ptr.findInsertPos(target))< 0);
			//	if(cmp.compare(target, ptr.dataArray.get(i))< 0) {
			//ptr.findInsertPos(target);
			return ptr.getChild(ptr.findInsertPos(target));
			//	}
			//}
		}
	}
	/**
	 * finds and returns the leaf node to which target should be added 
	 * or belong by calling stepDown method from above repeatedly until a leaf node is reached
	 * @param target that should be added
	 * @return the leaf node 
	 */
	private BTreeNode<E> findLeaf(E target){
		BTreeNode<E> ptr = root;
		while(!ptr.isLeaf()) {
			ptr = stepDown(ptr, target);
		}
		return ptr;
		//		if(ptr == null) {
		//			return null;
		//		}else {
		//			return stepDown(ptr,target);
		//		}
	}
	/**
	 * add data to the tree according to the algorithm decribed below assuming that no duplicate data is passed to this method
	 * (no checking fr duplicates is required)
	 * 1. find the leaf node new_data should be added to
	 * 2. add data to the node correctly (all data items at a node should be ordered)
	 * 3. starting from the leaf node new_data is added to (step 2)
	 *    check overflow and split if necessary, continue upward possibly all the way up to the root node
	 * 4. make any final adjustments if necessary
	 * @param data that is being added
	 */
	public void add(E new_data) {
		if(root == null) {
			BTreeNode<E> nn = new BTreeNode<E>(null, cmp, 3);
			nn.add(new_data);
			root = nn;
		}else {
			BTreeNode<E> node = findLeaf(new_data);

			BTreeNode<E> newRoot = node.add(new_data);
			if(newRoot != null) {
				root = newRoot;
			}
		}
	}
	/**
	 * finds and returns the node containing target if it exists in this tree. 
	 * Returns null if target is not in the tree
	 * @param target that exists in the tree
	 * @return the node containing target if it exists in this tree
	 */
	private BTreeNode<E> getNode(E target){
		BTreeNode<E> ptr = root;
		if(ptr == null) {
			return null;
		}
		while(!ptr.contains(target)) {

			ptr = stepDown(ptr, target);
		}
		if(ptr.contains(target)) {
			return ptr;
		}else {
			return null;
		}
	}

	/**
	 * returns the content of the tree as a string for easy printing folowing the level order traversal alorithm
	 */
	public String toString() {
		BTreeNode<E> ptr = root;
		if(ptr == null) {
			return "[ ]";
		}
		String str = "[ ";
		LinkedList <BTreeNode<E>> queue = new LinkedList <BTreeNode<E>>();
		queue.addLast(ptr);
		
		while(!queue.isEmpty()) {
			ptr= queue.pollFirst();
			str += ptr + " ";
			queue.addAll(ptr.getChildren());
		}
		
		str += " ]";
		return str;
	}
	/**
	 * return the degree of this tree
	 * @return the degree of this tree
	 */
	public int getDegree() {
		return degree;
	}
	/**
	 * retruns the pointer to the root of this tree 
	 * (This is for the visualizer)
	 * @return the pointer to the root of this tree
	 */
	public BTreeNode<E> getRoot(){
		return root;
	}

	public static void main (String[] args) {
		BTree<Integer> bt = new BTree<Integer>(new IntComparator(), 3);
		System.out.println(bt.getNode(5));
		System.out.println(bt);
		bt.add(5);
		bt.add(10);
		bt.add(20);
		bt.add(21);
		System.out.println(bt);
		System.out.println(bt.toString());
		
		System.out.println("Root is: " + bt.getRoot());
		System.out.println("Degree is : "+bt.getDegree());
		System.out.println("Find leaf "+bt.findLeaf(21));
		
		bt.add(72);
		bt.add(1);
		bt.add(34);
		bt.add(99);

		System.out.println(bt);
		System.out.println("Root is: " + bt.getRoot());
		System.out.println("Get node 10 "+bt.getNode(10));

	}
	private Comparator<E> cmp;
	private int degree;
	private BTreeNode<E> root;

}
