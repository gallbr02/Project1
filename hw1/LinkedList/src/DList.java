import javafx.scene.Node;

/**
 * DList.java
 * @author Brandon Gallagher
 * Representation of a doubly linked list
 *
 */
public class DList<E> {
	
	class DNode<E>{
		
		public DNode (E d, DNode<E> n) {
			data = d;
			next = n;
		}
		E data;
		DNode next;
	}
	/**
	 * Set up of the DList
	 * empty list by initializing member variables appropriately
	 */
	public DList() {
		head = null;
		tail = null;
	}
	/**
	 * Returns a string representation of the list
	 * for an empty list return "[]"
	 * for non- empty list return [d1, d2, d3]
	 */
	public String toString() {
		return null;
	}
	/**
	 * Returns a string representation of the list from back to front
	 * for an empty list return "[]"
	 * for non- empty list return [d3, d2, d1]
	 */
	public String toStringBackward() {
		return null;
	}
	/**
	 * add item to the end of the list
	 * @param item that is being added
	 */
	public void add(E item) {
		
	}
	/**
	 * add the item at the index position
	 * @param index that the item is being added at
	 * @param item that is being added to the index
	 */
	public void add1(int index,E item) {
		
	}
	/**
	 * remove all data from the list
	 */
	public void clear() {
		
	}
	/**
	 * return data from that index
	 * @param index if valid, return that item
	 * @return the data at the index or return null
	 */
	public E get(int index) {
		return null;	
	}
	/**
	 * replace the index position and return the old data that is being replaced.
	 * @param index that the item will be set to
	 * @param item that is being set in that particular index
	 * @return return old data that is being replaced or return null
	 */
	public E set(int index, E item) {
		return item;
	}
	/**
	 * return true if the item is contained in the list
	 * @param item that is being search for in the list
	 * @return true if this list contains the item
	 */
	public boolean contains (E item) {
		return false;
	}
	/**
	 * Return the index of the first occurrence of item
	 * @param item that is being returned
	 * @return 1 if the list does not contain the item.
	 */
	public int indexOf(E item) {
		return 0;
	}
	/**
	 * return the index of the last occurence
	 * @param item that is being returned
	 * @return 1 if the list does not contain the item
	 */
	public int lastIndexOf(E item) {
		return 0;	
	}
	/**
	 * how big the list is 
	 * @return the number of items in that list
	 */
	public int size() {
		return 0;
	}
	/**
	 * does the list have anything in it
	 * @return true if this list is empty
	 */
	public boolean empty() {
		return false;
	}
	private DNode<E> head;
	private DNode<E> tail;

}
