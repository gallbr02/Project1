import javafx.scene.Node;

/**
 * DList.java
 * @author Brandon Gallagher
 * Representation of a doubly linked list
 *
 */
public class DList<E> {

	class DNode<E>{

		public DNode (E d, DNode<E> n, DNode<E> p) {
			data = d;
			next = n;
			previous = p;
		}
		E data;
		DNode next;
		DNode previous;
	}
	/**
	 * Set up of the DList
	 * empty list by initializing member variables appropriately
	 */
	public DList() {
		DNode<E> dummy = new DNode<E> (null, null, null);
		head = dummy;
		tail = dummy;
	}
	/**
	 * Returns a string representation of the list
	 * for an empty list return "[]"
	 * for non- empty list return [d1, d2, d3]
	 */
	public String toString() {
		String str = "[ ";
		DNode<E> ptr = head.next;
		while (ptr != null) {
			str = str + ptr.data + " ";
			ptr = ptr.next;
		}
		str = str + " ]";

		return str;
	}
	/**
	 * Returns a string representation of the list from back to front
	 * for an empty list return "[]"
	 * for non- empty list return [d3, d2, d1]
	 */
	public String toStringBackward() {
		String str = "[ ";
		DNode<E> ptr = tail;
		while (ptr != head) {
			str = str + ptr.data + " ";
			ptr = ptr.previous;

		}
		str = str + " ]";

		return str;
	}
	/**
	 * add item to the end of the list
	 * @param item that is being added
	 */
	public void add(E item) {
		DNode<E> newNode = new DNode<E> (item, null, null);
		if(head.next == null) {
			head.next = newNode;
			newNode.previous = head;
			tail = newNode;
		}
		else {
			newNode.previous = tail;
			tail.next = newNode;
			newNode.next = null;
			tail = newNode;
		}
	}
	/** 
	 * add the item at the index position
	 * @param index that the item is being added at
	 * @param item that is being added to the index
	 */
	public void add1(int index,E item) {
		DNode newNode = new DNode (item, null, null);
		DNode ptr = head;
		DNode trail = ptr;
		int count = 0;
		if(index > 0 && index < count) {
			int tracker = -1;
			while(tracker != index) {
				trail = ptr;
				ptr = ptr.next;
				tracker++;
			}
			ptr.previous = newNode;
			trail.next = newNode;
			newNode.next = ptr;
			newNode.previous = trail;
		}else if(index == 0) {
			ptr = ptr.next;
			trail.next = newNode;
			ptr.previous = newNode;
			newNode.next = ptr;
		}
		// pretty lost where to go from here. Very Stuck

	}
	/**
	 * remove all data from the list
	 */
	public void clear() {
		if(empty()) {
			return;
		}
		DNode ptr = head.next;
		DNode trail;
		while(ptr != null) {
			trail = ptr;
			trail.data = null;
			ptr = ptr.next;
		}

	}
	/**
	 * return data from that index
	 * @param index if valid, return that item
	 * @return the data at the index or return null
	 */
	public E get(int index) {
		if(this.empty()) {
			throw new IndexOutOfBoundsException();
		}
		DNode ptr = head;
		int count = 0;
		while(count != index) {
			ptr = ptr.next;
			count++;
		}
		return null;	
	}
	/**
	 * replace the index position and return the old data that is being replaced.
	 * @param index that the item will be set to
	 * @param item that is being set in that particular index
	 * @return return old data that is being replaced or return null
	 */
	public E set(int index, E item) {
		DNode ptr = head;
		int count = 0;
		while(count != index) {
			ptr = ptr.next;
			count++;
		}
		E data = (E) ptr.data;
		ptr.data = item;
		return data;
	}
	/**
	 * return true if the item is contained in the list
	 * @param item that is being search for in the list
	 * @return true if this list contains the item
	 */
	public boolean contains (E item) {
		DNode newNode = new DNode (item, null, null);
		DNode ptr = head;
		while(ptr != null ) {
			if (newNode == item) {
				return true;
			}else {
				ptr = ptr.next;
			}
		}
		return false;
	}
	/**
	 * Return the index of the first occurrence of item
	 * @param item that is being returned
	 * @return 1 if the list does not contain the item.
	 */
	public int indexOf(E item) {
		DNode ptr = head;
		int count = 0;
		if(ptr.next != null) {
			while(ptr.data != item) {
				ptr = ptr.next;
				count++;
			}	
		}
		return count;
	}
	/**
	 * return the index of the last occurence
	 * @param item that is being returned
	 * @return 1 if the list does not contain the item
	 */
	public int lastIndexOf(E item) {
		DNode ptr = tail;
		int count = 0;
		if(ptr.previous !=null) {
			while(ptr.data != null) {
				ptr = ptr.previous;
				count++;
			}
		}
		return count;	
	}
	/**
	 * how big the list is 
	 * @return the number of items in that list
	 */
	public int size() {
		int count = 0;
	    DNode ptr = head;
	    while(ptr.next != null)
	    {
	        ptr = ptr.next;
	        count++;     
	    }
	    return count;
	}
	/**
	 * does the list have anything i n it
	 * @return true if this list is empty
	 */
	public boolean empty() {
		return head.next == null;
	}
	private DNode<E> head;
	private DNode<E> tail;


}
