import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * 
 * @author Brandon Gallagher
 * BTreeNode.java
 * Representation of a Binary tree node
 *
 * @param <E>
 */
public class BTreeNode<E> {
	public BTreeNode(BTreeNode<E> p, Comparator<E> c,int deg) {
		this.parent = p;
		this.cmp = c;
		this.degree = deg;
	}

	/**
	 * returns the data content of this node as a String enclosed in a pair of square bracket
	 */
	public String toString() {
		String str = "[ ";
		for(int i = 0; i < data.size(); i ++) {
			str += data.get(i) + " ";
		}
		str += " ]";
		return str;
	}
	/**
	 * returns true if this node is a leaf
	 * @return true if it is a leaf node
	 */
	public boolean isLeaf() {
		return (childList.size() == 0);
	}
	/**
	 * adds the newChild to the end of the child list
	 * @param newChild that is being added to the end of the list
	 */
	public void addChild(BTreeNode<E> newChild) {
		childList.add(newChild);
	}
	/**
	 * populates the children list with the children in newChildren.
	 * Assusme that the children array list of this BTreeNode is empty.
	 * Make sure each child in newChildren also knows who its new parent is. 
	 * @param newChildren
	 */
	public void setChildren(List<BTreeNode<E>> newChildren) {
		for(int i = 0; i < newChildren.size(); i++) {
			childList.add(newChildren.get(i));
			newChildren.get(i).parent = this;
		}
	}
	/**
	 * returns the child of this node at position pos(0-based)
	 * if there is no child at pos, return null
	 * @param pos the child is being returned at
	 * @return the child of the node at that position
	 */
	public BTreeNode<E> getChild(int pos){
		BTreeNode<E> child = null;
		if(pos < 0 || pos >= childList.size()) {
			return null;
		}else {
			child = childList.get(pos);
			return child;
		}
	}
	/**
	 * returns the number of data elements in this node
	 * @return the number of data elements in this node
	 */
	public int dataSize() {
		return data.size();
	}
	/**
	 * returns the data at position pos(0-based) in this node.
	 * if there is no data at pos, return null
	 * @param pos the data is being returned
	 * @return the data of the node at the position
	 */
	public E getData(int pos) {
		if(pos < 0 || pos >= data.size()) {
			return null;
		}else {
			return data.get(pos);
		}
	}
	/**
	 * populate the data list with the elements in newDataSet using either of the following ArrayList methods
	 * @param pos the the data is being placed
	 */
	public void setData(List<E> newDataSet) {
		data.addAll(newDataSet);
	}
	/**
	 * 4 lines
	 * returns the position(index) in which new_data should be placed in the data array list
	 * @param new_data data that should be placed in the data array
	 * @return the position (index)
	 */
	public int findInsertPos(E new_data) {
		int index = 0;
		while(index < dataSize() && cmp.compare(new_data, data.get(index)) > 0) {
			++index;
		}
		return index;
	}
	/**
	 * returns true if this node has target among its data or false otherwise
	 * this method uses a binary-search-like algorithm
	 * @param target to see if the tree conatins it
	 * @return true is this node has the target
	 */
	public boolean contains(E target) {
		return indexOf(target) != -1;
	}
	/**
	 * returns the index in which target is found in this nodes data array list or -1 if not found.
	 * This method must use a binary-search-like algorithm
	 * @param target that is being found in the tree
	 * @return the index in which the target is found
	 */
	public int indexOf(E target) {
		int low = 0;
		int high = dataSize();
		int mid = 0;
		boolean found = false;
		while(low <= high) {
			mid = (low + high)/2;
			if(target.equals(data.get(mid)))  {
				return mid;
			}
			else if(cmp.compare(target, data.get(mid)) < 0) {
				high = mid -1;
			}
			else {
				low = mid + 1;
			}
		}if(found == true) {
			return mid;
		}
		return -1;
	}
	/**
	 * return true if there is an overflow in this node
	 * (more than allowed number of children or data values)
	 * for a B-Tree of degree(a.k.a order) deg, the max number of children at any node is deg
	 * @return true if there is an overflow in this node
	 */
	public boolean isOverflow() {
		return (data.size() >= degree);
	}
	/**
	 * returns true if there is an underflow in this node
	 * (fewer that min number of children or data values)
	 * For a B-Tree of degree deg, the min number of children at all internal nodes is ceiling(deg/2)
	 * @return true if there is an underflow in this node
	 */
	public boolean isUnderflow() {
		return (data.size() <= Math.ceil(degree)/2);
	}
	/**
	 * 17 lines
	 * handles the overflow
	 * 1. create one new node:right
	 * 2. remove and add all data values right of the middle index( not including) to the new right sibling
	 * 3. if this node has children, remove and add all children right of the middle index
	 *    (not including) to the new right sibling
	 * 4. if this node is root(has no parent), create a new node, parent, and establish a proper relationship
	 * 	  between this node and the new parent THEN between the new right node and the new parent
	 * 5. promote(remove and add) the data value in the middle of this node to the parent
	 * 6. if parent is newly created, return the nuew parent
	 * 7. otherwise check overflow on the parent and handle split if necessary
	 * @return the split item
	 */

	private BTreeNode<E> split(){
		BTreeNode<E> right = new BTreeNode<E>(null, cmp, degree);
		int mid = degree/2;
		for(int i = mid + 1; i < data.size(); i++) {
			right.data.add(data.get(i));
		}

		for(int i = data.size()-1; i > mid; --i ) {
			data.remove(i);
		}

		if(childList.size() > 0) {
			for(int i = mid +1; i < childList.size(); ++i) {
				right.addChild(childList.get(i));
				childList.get(i).parent = right;
			}

			for(int j = childList.size()-1; j > mid ; j--) {
				childList.remove(j);
			}
		}
		if(parent == null) {
			BTreeNode<E> newParent = new BTreeNode<E>(null, cmp, degree);
			newParent.addChild(this);
			newParent.addChild(right);

			parent = newParent;
			right.parent = newParent;

			newParent.add(data.get(mid));
			data.remove(mid);
			return newParent;
		}else {
			right.parent= parent;

			int indx = parent.findInsertPos(data.get(mid));
			parent.childList.add(indx+ 1, right);
			parent.data.add(indx, data.get(mid));
			right.parent = parent;
			data.remove(mid);

			if(parent.isOverflow()) {
				return parent.split();
			}
		}
		return null;	
	}
	/**
	 * adds d to this nodes data collection in the right place 
	 *    (all data in the data arraylist must be ordered) and split if there is overflow
	 * Returns null if there is no overflow after adding d. it returns the newly created node(parent)
	 *    at the top-most level if split in this node resulted in new nodes
	 * @param d data being added
	 * @return the newly cerated node(parent)
	 */
	public BTreeNode<E> add(E d){
		data.add(findInsertPos(d),d);
		if(isOverflow()) {
			return split();
		}else {
			return null;
		}
	}
	/**
	 * return the degree
	 * @return degree
	 */	
	public int getDegree() {
		return degree;
	}
	/**
	 * return the data array list
	 * @return data array
	 */
	public ArrayList<E> getData(){
		return data;
	}
	/**
	 * return the child array list
	 * @return child array
	 */
	public ArrayList<BTreeNode<E>> getChildren(){
		return childList;
	}
	public static void main(String[] args) {
		BTreeNode<Integer> bt = new BTreeNode<Integer>(null, new IntComparator(), 5);
		ArrayList<BTreeNode<Integer>> Alist = new ArrayList<BTreeNode<Integer>>();
		BTreeNode<Integer> butt = new BTreeNode<Integer>(null, new IntComparator(), 3);
		BTreeNode<Integer> butt1 = new BTreeNode<Integer>(null, new IntComparator(), 3);
		BTreeNode<Integer> butt2= new BTreeNode<Integer>(null, new IntComparator(), 3);
		BTreeNode<Integer> butt3= new BTreeNode<Integer>(null, new IntComparator(), 3);
		
		Alist.add(butt1);
		Alist.add(butt2);
		Alist.add(butt3);
		
		System.out.println("Array list: "+ Alist);
		System.out.println("get child: "+ bt.getChild(0));
		System.out.println("data at index 0 " + bt.getData(0));
		//System.out.println("Position to insert " + bt.findInsertPos(0));
		bt.add(3);
		System.out.println(bt.toString());
		bt.add(10);		
		System.out.println();


		System.out.println("Number of values " + bt.data.size());
		System.out.println("data at index 0 " + bt.getData(0));
		System.out.println();

		System.out.println("Data at index 1 "+ bt.getData(1)) ;
		System.out.println("get child  "+ bt.getChildren()) ;
		System.out.println("Degree is "+ bt.getDegree()) ;
		System.out.println("contains 3 " + bt.contains(3));
		System.out.println();

		//System.out.println("indexOf 10 "+ bt.indexOf(10)) ;
		System.out.println(bt);
		System.out.println();

		bt.add(5);
		System.out.println("Position to insert " + bt.findInsertPos(9));
		bt.add(17);
		//System.out.println("split node :" + bt.split());
		
		System.out.println("indexOf 17 "+ bt.indexOf(17)) ;
		System.out.println(bt);
		System.out.println();

		bt.add(20);
		bt.add(21);
		bt.add(22);
		bt.add(23);
		//stepdown or findleaf

		//System.out.println("indexOf 20 "+ bt.indexOf(20)) ;

		System.out.println("The new parent"+bt);
		System.out.println("The left child "+bt);
		System.out.println();

		System.err.println("is leaf: "+ bt.parent.isLeaf());
		System.out.println("is underflow: "+ bt.parent.isUnderflow());
	}


	private BTreeNode<E> parent;
	private Comparator<E> cmp;
	ArrayList<E> data = new ArrayList<E>();
	ArrayList<BTreeNode<E>> childList = new ArrayList<BTreeNode<E>>();
	private int degree;



}
