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
		DNode<E> next;
		DNode<E> previous;
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
		newNode.previous = tail;
		tail.next = newNode;
		newNode.next = null;
		tail = newNode;
	}
	/** 
	 * add the item at the index position
	 * @param index that the item is being added at
	 * @param item that is being added to the index
	 */
	public void add1(int index,E item) {
		DNode<E> newNode = new DNode (item, null, null);
		DNode<E> ptr = head.next;
		DNode<E> trail = head;
		int count = 0;
		if(index == count) {
			tail.next =newNode;
			newNode.previous = tail;
			tail= newNode;
		}
		else if(index ==0 ) {
			head.next = newNode;

		}

	}
	/**
	 * remove all data from the list
	 */
	public void clear() {
		if(empty()) {
			return;
		}
		DNode<E> ptr = head.next;
		DNode<E> trail;
		while(ptr != null) {
			trail = ptr;
			trail.data = null;
			ptr = ptr.next;
		}
		head = tail;
	}
	/**
	 * return data from that index
	 * @param index if valid, return that item
	 * @return the data at the index or return null
	 */
	public E get(int index) {
		if(index >= 0 || index < size()) {
			DNode<E> ptr = head;
			int count = 0;
			while(count != index) {
				ptr = ptr.next;
				count++;
			}
			E data = ptr.data;
			return data;	
		}
		else {
			return null;
		}
	}
	/**
	 * replace the index position and return the old data that is being replaced.
	 * @param index that the item will be set to
	 * @param item that is being set in that particular index
	 * @return return old data that is being replaced or return null
	 */
	public E set(int index, E item) {
		DNode<E> ptr = head;
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
			if (item.equals(ptr.data)) {
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
		while(ptr != null && !item.equals(ptr.data)) {
			ptr = ptr.next;
			count++;
		}
		if(ptr == null){
			return -1;
		}else {
			return count;
		}
	}
	/**
	 * return the index of the last occurence
	 * @param item that is being returned
	 * @return 1 if the list does not contain the item
	 */
	public int lastIndexOf(E item) {
		DNode<E> ptr = tail;
		int count = 0;
		while(ptr != null && !item.equals(ptr.data)) {
			ptr = ptr.previous;
			count++;
		}
		if(ptr == null) {
			return -1;
		}else {
			return count;
		}	
	}
	/**
	 * how big the list is 
	 * @return the number of items in that list
	 */
	public int size() {
		int count = 0;
		DNode<E> ptr = head.next;
		while(ptr != null)
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

	/**
	 * An array containing all of the elements in the list
	 * in the order of the list
	 * @return the elements in list order
	 */
	public E[]toArray(){
		int i = 0;
		E[] newArray = (E[]) new Object[size()];
		DNode<E> ptr = head.next;
		while(ptr != null) {
			newArray[i] = ptr.data;
			ptr = ptr.next;
			i++;
		}
		return newArray;
	}

	/**
	 * remove element if index is valid at the index position
	 * in the list 
	 * @param index that the elemetn is being removed at
	 * @return the data being removed
	 */
	public E remove(int index) {
		System.out.println("removeIndex" + index);
		DNode<E> ptr = head.next;
		int i = 0;
		if(index < 0 || index >= size()) {
			return null;
		}

		while(i != index) {
			ptr = ptr.next;
			i++;
		}
		System.out.println(i + " " + ptr.data);

		ptr.previous.next = ptr.next;
		if(ptr.next != null) {
			ptr.next.previous = ptr.previous;
		}else {
			tail = tail.previous;
		}
		return ptr.data;
	}

	/**
	 * remove the first occurrence of target in the list 
	 * @param target is the element being removed
	 * @return true or false depending if the element is in the list or not
	 */
	public boolean remove1 (E target) {
		if(empty()) {
			return false;
		}
		DNode<E> ptr = head.next;
		while(ptr.data != target) {
			if(ptr.next == null) {
				return false;
			}else {
				ptr = ptr.next;
			}
		}
		ptr.previous.next = ptr.next;
		if(ptr.next != null) {
			ptr.next.previous = ptr.previous;
		}else {
			tail = tail.previous;
		}
		return true;
	}

	/**
	 * remove all the elements whose index is between
	 * fromIndex and toINdex.
	 * 
	 * if fromIndex is too small remove from the beginning
	 * of the list.
	 * 
	 * iftoIndex is too large remove until the end of the list.
	 * 
	 * if the indices are out of order, throw and illegal
	 * argument exception.
	 * @param fromIndex index where we start to search
	 * for the elements
	 * @param toIndex index to stop to search for the element
	 */
	public void removeRange(int fromIndex, int toIndex)throws Exception {
		DNode<E> ptr1 = head;
		DNode<E> temp = ptr1;
		DNode<E> ptr2 = head;
		
		int i = 0;
		if(fromIndex > toIndex) {
			throw new Exception("fromIndex is greater than toIndex");
		}
		else if(fromIndex < 0) {
			fromIndex = 0;
		}
		else if(toIndex > size() - 1) {
			toIndex = size() - 1;
		}
		for(int j = 0; j < fromIndex -1; j++) {
			ptr1 = ptr1.next;
		}
		
		//System.out.println("From Index" + fromIndex);
		//System.out.println("To Index" + toIndex);

		if(toIndex == size()-1) {
			for(int j = 0; j < toIndex; j++) {
				ptr2 = ptr2.next;
			}	
			
			tail = ptr1;
			temp = ptr2;
			while (temp != ptr1.next) {
				temp = temp.previous;
				temp.next.previous = null;
				temp.next = null;
				
			}
			
		}
		ptr1.previous.next = ptr2.next;
		ptr2.next.previous = ptr1.previous;
		tail = ptr1.previous;
		
		/*
		else {
			while(i < toIndex) {
				ptr2 = ptr2.next;
				i++;
			}
			ptr1.previous.next = ptr2.next;
			ptr2.next.previous = ptr1.previous;
			tail = ptr1.previous;
		}	
		
		if(ptr2 == tail) {
			temp.previous.next = null;
		}else {
			ptr1.previous.next = ptr2.next;
			ptr2.next.previous = ptr1.previous;
			tail = tail.previous;
		}
*/
		//if(toIndex == size()-1) 
		//	while(ptr1 != ptr2) {
		//			ptr2 = ptr2.previous;
		//			ptr2.next = null;
		//		else {
		//			ptr1.previous.next = ptr2.next;
		//			ptr2.next.previous = ptr1.previous;
		//			tail = tail.previous;
		//		}
		//}
		//}
		//if I try this method one more time I might break my computer
	}


	/**
	 * inserts all elements in the given collection 
	 * @param index where we start the inserting
	 * @param collection that the element are in.
	 */
	public void addAll(int index, Iterable<E> collection) {
		DNode<E> ptr = head;
		for(int i = 0; i< index; i++) {
			ptr = ptr.next;
		}
		DNode<E> temp = ptr.next;
		for(E element : collection) {
			DNode<E> newNode = new DNode<E>(element, temp, ptr);	
			ptr.next = newNode;
			newNode.previous = ptr;
			ptr = newNode;
		}
		if(temp == null) {
			tail = ptr;
		}else {
			temp.previous = ptr;
			ptr.next = temp;
		}
	}

	private DNode<E> head;
	private DNode<E> tail;


}
