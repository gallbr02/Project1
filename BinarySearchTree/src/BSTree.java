
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BSTree<E>  {


	private class Node
	{
		E data;          
		Node left ; 
		Node right;
		Node parent;
		Node(E d)
		{
			data = d;
			left = null;
			right = null;
			parent = null;
		}
		public E getData() {
			return data;
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		public Node getParent() {
			return parent;
		}
	}
	private Node root;
	private Comparator <E> comp;

	public BSTree(Comparator <E> comp) {
		root = null;
		this.comp = comp;
	}
	/** 
	 * Determines if the tree is empty
	 * @return if the root is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}
	/**
	 * 
	 * @return the largest value in the tree
	 */
	E maxValueLoop() {
		if(root == null) {
			throw new NoSuchElementException();
		}
		Node max = findMaxValueLoop(root);
		E maxData = max.data;
		return maxData;
	}
	/**
	 * 
	 * @param curr the given curr
	 * @return the node with the largest value 
	 */
	private Node findMaxValueLoop (Node curr) {
		if(curr == null) {
			return null;
		}
		while(curr.right != null) {
			curr = curr.right;
		}
		return curr;	
	}
	/**
	 * finds the smallest value in the tree
	 * @return the smallest value
	 */
	E minValueLoop() {
		if(root == null) {
			throw new NoSuchElementException();
		}
		Node min = findMinValueLoop(root);
		E minData = min.data;
		return minData;
	}
	/**
	 * Finds the min value
	 * @param curr the given node
	 * @return the node with the smallest value
	 */
	private Node findMinValueLoop(Node curr) {
		if(curr == null) {
			return null;
		}
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}
	/**
	 * Determines if the tree has the given item
	 * @param item to see if the tree has
	 * @return if it is true
	 */
	public boolean containsLoop(E item) {
		Node curr = root;
		if(findNodeLoop(curr, item) == null) {
			return false;
		}
		return true;
	}
	/**
	 * Finds the node in the sub tree
	 * @param curr the current position
	 * @param item the item to find
	 * @return the current item
	 */
	private Node findNodeLoop(Node curr, E item) {
		while(curr != null) {
			if(comp.compare(item, curr.data)==-1) {
				curr = curr.left;
			}
			else if(comp.compare(item,  curr.data)==1) {
				curr = curr.right;
			}else {
				return curr;
			}
		}
		return null;
	}

	/**
	 * adds the item to the tree
	 * @param item being added
	 */
	public void addLoop(E item) {
		if(root == null) {
			root = new Node (item);
			return;
		}
		Node curr = root;
		while(curr != null) {
			if(comp.compare(item, curr.data)==-1) {
				if(curr.left == null) {
					curr.left = new Node (item);
					curr.left.parent = curr;
					return;
				}else {
					curr = curr.left;
				}
			}
			else if(comp.compare(item, curr.data)==1) {
				if(curr.right == null) {
					curr.right = new Node (item);
					curr.right.parent = curr;
					return;
				}
				curr = curr.right;
			}
		}
	}
	/**
	 * adds the given item to the tree
	 * @param item being added
	 */
	public void add(E item) {
		if(root == null) {
			root = new Node(item);
		}else {
			add(root, item);
		}
	}
	/**
	 * adds the given item to the tree
	 * @param curr the current position
	 * @param item being added
	 */
	public void add(Node curr, E item) {
		if(root == null) {
			root = new Node (item);
			return;
		}
		if(comp.compare(item, curr.data)==-1) {
			if(curr.left == null) {
				curr.left = new Node (item);
				curr.left.parent = curr;
				return;
			}else {
				add(curr.left, item);	
			}
		}
		else if(comp.compare(item, curr.data)==1) {
			if(curr.right == null) {
				curr.right = new Node (item);
				curr.right.parent = curr;

				return;
			}else {
				add(curr.right,item);
			}
		}
	}

	/**
	 * find the max value 
	 * @return the data 
	 */
	E maxValue() {
		if(root == null) {
			throw new NoSuchElementException();
		}
		Node max = findMaxNode(root);
		E maxData = max.data;
		return maxData;
	}
	/**
	 * finds the max node
	 * @param curr at the current position
	 * @return the max node
	 */
	private Node findMaxNode (Node curr) {
		if(curr == null) {
			return null;
		}
		else if(curr.right == null) {
			return curr;
		}else {
			return findMaxNode(curr.right);
		}
	}
	/**
	 *
	 * @return the smallest node
	 */
	E minValue() {
		if(root == null) {
			throw new NoSuchElementException();
		}
		Node min = findMinNode(root);
		E minData = min.data;
		return minData;
	}
	/**
	 * finds the smallest value in the node
	 * @param curr at the current position
	 * @return the min node found
	 */
	private Node findMinNode (Node curr) {
		if(curr == null) {
			return null;
		}
		else if(curr.left == null) {
			return curr;
		}else {
			return findMinNode(curr.left);
		}

	}
	/**
	 * determined if the tree contains the item
	 * @param item that it contains
	 * @return if it is true or false
	 */
	public boolean contains (E item) {
		if(findNode(root,item)!=null ) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Finds the node in the tree
	 * @param curr at the current position
	 * @param item that is being found
	 * @return the current item found
	 */
	private Node findNode (Node curr, E item) {
		if(curr == null) {
			return null;
		}
		if(comp.compare(item, curr.data)==0) {
			return curr;
		}
		else if(comp.compare(item, curr.data)==-1) {
			findNode(curr.left, item);
		}
		else if(comp.compare(item,  curr.data)==1) {
			findNode(curr.right, item);
		}
		return curr;
	}
	/**
	 * Removes the given item
	 * @param item being removed
	 * @return if it is true or false
	 */
	public boolean remove(E item) {
		Node remove = findNode(root, item);
		if(remove == null) {
			return false;
		}
		else if(remove.right== null || remove.left == null) {
			removeMissing(remove);
			return true;
		}
		else if(remove.right != null && remove.left != null) {
			removeHasBoth(remove);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Remove the missing node in the tree
	 * @param node that is being removed
	 */
	public void removeMissing(Node node) {
		if(node == root) {
			if (node.right != null) {
				node = node.right;
				root = node;
			}
			else if(node.left != null) {
				node = node.left;
				root = node;
			}else {
				root = null;
			}
			return;
		}
		if(node.right == null && node.left == null) {
			if(node.parent.right == node) {
				node.parent.right = null;
			}
			else {
				node.parent.left = null;
			}
		}
		else if(node.right!= null && node.left == null) {
			if(node.parent.left == node) {
				node.parent.left = node.right;
			}else {
				node.parent.right = node.right;
				node.right.parent = node.parent;
			}
			return;
		}
		else if(node.right == null && node.left != null) {
			if(node.parent.right == node) {
				node.parent.right = node.left;
			}else {
				node.parent.left = node.left;
				node.left.parent = node.parent;
			}
			return;
		}

	}
	/**
	 * Removes the given node assuming it has exactly two children
	 * @param node that is being removed
	 */
	public void removeHasBoth (Node node) {
		Node max = findMaxNode(node.left);
		node.data = max.data;
		removeMissing(max);
	}
	/**
	 * 
	 * @param visitor
	 */
	public void preorder(Visitor<E> visitor) {
		preorder(visitor, root);
	}
	/**
	 * 
	 * @param visitor
	 * @param curr
	 */
	public void preorder(Visitor<E> visitor, Node curr) {
		if(curr == null) {
			return;
		}else {
			visitor.visit(curr.data);
			preorder(visitor, curr.left);
			preorder(visitor, curr.right);
		}
	}
	/**
	 * 
	 * @param visitor
	 */
	public void inorder(Visitor<E> visitor) {
		inorder(visitor,root);
	}
	/**
	 * 
	 * @param visitor
	 * @param curr
	 */
	public void inorder(Visitor<E> visitor, Node curr) {
		if(curr == null) {
			return;
		}
		inorder(visitor,curr.left);
		visitor.visit(curr.data);
		inorder(visitor,curr.right);
	}
	/**
	 * 
	 * @param visitor
	 */
	public void postorder(Visitor<E> visitor) {
		postorder(visitor,root);
	}
	/**
	 * 
	 * @param visitor
	 * @param curr
	 */
	public void postorder(Visitor<E> visitor, Node curr) {
		if(curr == null) {
			return;
		}
		postorder(visitor, curr.left);
		postorder(visitor, curr.right);
		visitor.visit(curr.data);
	}
	/**
	 * 
	 * @author gallbr02
	 *
	 * @param <E>
	 */
	public class CountRangeVisitor<E>{
	}
	/**
	 * 
	 * @return
	 */
	private class LevelIterator implements Iterator<E>{
		Node curr;
		Queue<Node> q;
		public LevelIterator() {
			q = new LinkedList<Node>();
			if(root == null) {
				return;
			}else {
				q.add(root);
			}
		}
		public boolean hasNext() {
			if(!(q.isEmpty())) {
				return true;
			}else {
				return false;
			}
		}
		public E next() {
			if(this.hasNext()==true) {
				curr = q.remove();
				if(curr.left != null) {
					q.add(curr.left);
				}
				if(curr.right != null) {
					q.add(curr.right);
				} 		
			}
			return curr.data;	
		}
	}
	/**
	 * 
	 * @return
	 */
	private class PreIterator implements Iterator<E>{
		private Node curr;
		private Stack<Node> p;
		public PreIterator() {
			p = new Stack<Node>(); 
			if(root == null) {
				return;
			}else {
				p.add(root);
			}
		}
		public boolean hasNext() {
			if(!(p.isEmpty())) {
				return true;
			}else {
				return false;
			}
		}
		public E next() {
			if(this.hasNext()==true) {
				curr = p.pop();
				if(curr.left != null) {
					p.push(curr.left);
				} 	
				if(curr.right != null) {
					p.push(curr.right);
				}
			}
			return curr.data;
		}
	}
	/**
	 * 
	 */
	public String toString() {
		Iterator<E> iterator = new LevelIterator();
		String str ="";
		while(iterator.hasNext()) {
			str += " " + iterator.next();
		}
		str = "[" + str.trim() + "]";
		return str;
	}
	/**
	 * 
	 * @return
	 */
	public String toStringPre() {
		Iterator<E>preIterator = new PreIterator();
		String str= "";
		while(preIterator.hasNext()) {
			str+= " " +preIterator.next();
		}
		str = "[" + str.trim() + "]";
		return str;

	}
	/**
	 * 
	 */
	public boolean equals(Object obj) {
		BSTree<E> tree = (BSTree<E>) obj;
		if(this == obj) {
			return true;
		}
		else if(!(obj instanceof BSTree)) {
			return false;
		}
		else {
			return equals(this.root, tree.root );
		}
	}
	/**
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean equals(Node root1, Node root2) {
		if(root1 == null || root2 == null) {
			return false;
		}else if(root1.data.equals(root2.data)) {
			if(equals(root1.right, root2.right)== true && equals(root1.left, root2.left)== true){
				return true;
			}
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 
	 */
	public Object clone() {
		try {
			BSTree<E> copy = (BSTree<E>) super.clone();
			copy.comp = this.comp;
			copy.root = copyTree(this.root);
			return copy;
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param curr
	 * @return
	 */
	private Node copyTree(Node curr) {
		Node leftTri;
		Node rightTri;
		if(curr == null) {
			return null;
		}
		Node copy = new Node(curr.data);
		leftTri =copyTree(copy.left);
		rightTri=copyTree(copy.right);
		leftTri.parent=copy;
		copy.left = leftTri;
		rightTri.parent = copy;
		copy.right = rightTri;
		return copy;

	}
	/**
	 * 
	 * @param items
	 * @param comp
	 */
	BSTree(E[] items, Comparator<E> comp){
		this.comp = comp;
		this.root = rebuildPreorder(items, 0, items.length);
	}
	/**
	 * 
	 * @param items
	 * @param i
	 * @param j
	 * @return
	 */
	private Node rebuildPreorder(E[] items, int i, int j) {
		if(i == j) {
			return null;
		}else {
			Node curr = new Node(items[i]);
			int m = i +1;
			while(comp.compare(items[i], items[m])== 1) {
				m++;
			}
			

		}
		return root;

	}



}
