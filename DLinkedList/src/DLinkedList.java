
/**
 * Implementation of a Singly-Linked List.
 *
 * @author  __your_name___
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

public class DLinkedList<E> implements Iterable<E>
{
	// Representation of the list nodes
	private class Node
	{
		E data;          // the data value stored at the node
		Node  next; // the successor of this node
		Node previous;
		// creates a node with the given data item and no successor
		Node(E d)
		{
			data = d;
			next = null;
			previous = null;
		}
	}
	/**
	 * The first node in the list.
	 * The last node in the list.
	 */
	private Node head;
	private Node last;
	// put comment in Javadoc style
	public DLinkedList()
	{
		Node dummy = new Node(null);
		head = dummy;
		last = dummy;
	}
	// put comment in Javadoc style
	/**
	 * If the list is empty
	 * @return the data null;
	 */
	public boolean isEmpty()
	{
		return head.next == null;
	}
	/**
	 * Returns the first element in the list.
	 *
	 * @return the first element in the list
	 * @throws NoSuchElementException when the list is empty
	 */
	/*public E getFirst()
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.next.data;
	}
	 */
	/**
	 * Returns the last element in the list.
	 * 
	 * @return the last element in the list.
	 * @throws NoSuchElementException when list is empty
	 */
	/*
	public E getLast(){
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}else{
			Node curr = head;
			while(curr.next != null){
				curr = curr.next;
			}
			return curr.data;
		}
	}

	// put comment in Javadoc style
	/**
	 *Adds the given item to the end of the list
	 *
	 * @param item the element to add
	 */
	public void addLast(E item)//only 4 lines
	{
		Node newNode = new Node(item);
		if(isEmpty()){
			head.next = newNode;
			newNode.previous = head;
			last = newNode;
		}else{
			newNode.previous = last;
			last.next= newNode;
			newNode.next = null;
			last = newNode;
		}
	}
	/**
	 * Adds the given element to the front of the list.
	 *
	 * @param data the element to add
	 */
	public void addFirst(E item)
	{
		Node newNode = new Node(item);
		if(isEmpty()){
			head.next = newNode;
			newNode.previous = head;
			last = newNode;
		}else{
			newNode.previous = head;
			newNode.next = head.next;
			head.next = newNode;
			Node curr = newNode.next;
			curr.previous = newNode;
		}
	}
	/**
	 * Add the given item at the given index
	 * @param index to the node that you are adding
	 * @param item added to the list
	 */
	/*	public void add(int index, E item)
	{
		Node newNode = new Node(item);
		newNode = head.next;
		newNode.previous = head;
		for(int i = 0; i< index; i++){
			//head.next = newNode;
			//newNode.previous = head;
			//head = head.next;
		}
	}
	 */
	/**
	 * returns the value at the given index
	 * @param index element that was called
	 * @return the element that you call
	 */
	/*	E get(int index){
		if(this.isEmpty()){
			throw new IndexOutOfBoundsException();
		}
		Node curr = head;
		int count = 0;
		while(count != index) {
			curr = curr.next;
			count++;
		}
		return null;
	}
	 */
	/**
	 * Replaces the node at the given index in the list
	 * @param index elements in the list
	 * @param items element that you set
	 * @return null
	 */
	/*	E set(int index, E item){
		Node curr = head;
		int count = 0;
		while(count != index) {
			curr = curr.next;
			count++;
		}
		E data = curr.data;
		curr.data = item;
		return data;
	}
	 */
	/**
	 * Determines if the list contains the given item
	 * @param items in the given list
	 * @return true
	 */
	/*
	public boolean contains(E items){
		Node curr = head;
		while(curr != null) {
			if(curr.data == items) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	 */
	/**
	 * clears all elements in the list
	 * return the cleared list
	 */
	/*
	public void clear(){
		if(isEmpty()) {
			return;
		}
		Node curr = head.next;
		Node prev;
		while(curr != null){
			prev = curr;
			prev.data = null;
			curr = curr.next;
		}
	}
	 */
	// put comment in Javadoc style
	/**
	 * changing integers to string
	 * return string representation of the list
	 */
	public String toString()
	{
		String str = "";
		Node curr = head.next;
		// add each data item to the result string
		while (curr != null) {
			str += curr.data + " ";
			curr = curr.next;
		}
		// remove trailing space and enclose in [ ]
		str = "[" + str.trim() + "]";
		return str;
	}
	/**
	 * reversing toString method
	 * @return the string
	 */
	public String toStringRev(){
		String str = "";
		Node curr = last;
		while(curr!= head){
			str += curr.data + " ";
			curr = curr.previous;
		}
		str = "[" + str.trim() + "]";
		return str;
	}
	/**
	 * Determines if the list contains the given item
	 * @param items visit the nodes as it searches for the item
	 * @return null
	 */
	/*
	public boolean containsIter(E item) { 
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			E data = iter.next();
			if(data == item) {
				return true;
			}
		}
		return false;

		for (E data : this) {
			if(data == item) {
				return true;
			}
		}
		return false;

	}
	 */

	/**
	 * Goes through list one item at a time
	 * @return 
	 * @return if hasNext is true or false
	 */
	private class DLinkedListIterator implements Iterator<E>
	{
		private Node curr;
		private int index;
		private boolean nextCalled;
		public DLinkedListIterator(){
			curr = head.next;
			index = 0;
			nextCalled = false;
		}
		public boolean hasNext(){
			if(curr != null){
				return true;
			}else{
				return false;
			}
		}
		public E next() {
			if(this.hasNext()) {
				E result = curr.data;
				curr = curr.next;
				index++;
				nextCalled = true;
				return result;
			}else {
				throw new NoSuchElementException();

			}
		}
		public void remove() {
			if(!nextCalled) {
				throw new UnsupportedOperationException();
			}else {
				nextCalled = true;
				if(!hasNext()) {
					last = last.previous;
					last.next = null;
				}else {
					Node temp = curr;
					temp.previous.next = curr.next;
					temp.next.previous = curr.previous;
				}
			}

		}
	}
	public Iterator<E> iterator() {
		return new DLinkedListIterator();
	}
	E removeFirst() {
		if(this.isEmpty()) {
			throw new NoSuchElementException ();
		}else {
			Node curr = head.next;
			curr.previous.next = curr.next;
			if(curr.next != null) {
				curr.next.previous = curr.previous;
			}else {
				last = head;
			}
			return curr.data;
		}
	}
	/**
	 * removes the last element in the list
	 * @return 
	 */
	E removeLast() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		Node curr = last;
		last = last.previous;
		last.next = null;
		return curr.data;
	}
	/**
	 * Removes the element at the given index
	 * @param index
	 * @return the value of the removed element
	 */
	E remove(int index){
		if(this.isEmpty()|| index < 0 || index > 5) {
			throw new IndexOutOfBoundsException ();
		}
		Node curr = head.next;
		int count = 0;
		while(count != index) {
			curr = curr.next;
			count++;
		}
		curr.previous.next = curr.next;
		if(curr.next != null) {
			curr.next.previous = curr.previous;
		}else {
			last = last.previous;
		}
		return curr.data;
	}
	/**
	 * removes the first occurence of the given item in the list
	 * @param item is the given item
	 * @return true if the element is removed
	 */
	public boolean removeItem(E item) {
		if(isEmpty()){
			return false;
		}
		Node curr = head.next;
		while(curr.data != item) {
			if(curr.next == null) {
				return false;
			}else {
				curr = curr.next;
			}
		}
		curr.previous.next = curr.next;
		if(curr.next != null) {
			curr.next.previous = curr.previous;
		}else {
			last = last.previous; 
		}
		return true;
	}
	/**
	 * removes all occurrences of the given item in the list
	 * @param item is the item we remove in all occurences
	 * @return true if the value of the element was removed
	 */
	public boolean removeAll(E item) {
		if(isEmpty()){
			return false;
		}
		Node curr = head.next;
		while(curr != last) {
			if(curr.data == item) {
				curr.previous.next = curr.next;
				if(curr.next != null) {
					curr.next.previous = curr.previous;
				}else {
					last = last.previous;
				}
			}
			curr = curr.next;
		}
		return true;
	}
	/**
	 * determine is this list is equal to the given list
	 * @param list
	 * return 
	 */
	public boolean equals(Object other)
	{
		if (this == other) {                    
			return true;
		}
		else if ( !(other instanceof DLinkedList) ) {    
			return false;
		}
		else {                                
			DLinkedList otherList = (DLinkedList) other;      
			Iterator<E> iter = this.iterator();
			Iterator<E> iter2 = otherList.iterator();
			while(iter.hasNext() && iter2.hasNext()) {
				if(iter.next().equals(iter2.next())){
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * returns a copy of the list
	 */
	public Object clone()
	{
		try {
			DLinkedList<E> copyList = (DLinkedList<E>) super.clone();			
			Node dummy = new Node(null);
			copyList.head = dummy;
			copyList.last = copyList.head;
			Iterator<E> iter = this.iterator();
			while(iter.hasNext()) {
				copyList.addLast(iter.next());
			}
			return copyList;  
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Inserts all elements of the given collection
	 * @param index in which we give the collection
	 * @param collection all elements that were inserted
	 */
	public void addAll(int index, Iterable<E> collection) {
		Node curr = head;
		for(int i = 0; i< index; i++) {
			curr = curr.next;	
		}
		Node node = curr.next;
		for(E number : collection) {
			Node newNode = new Node(number);
			curr.next = newNode;
			newNode.previous = curr;
			curr = newNode;
		}
		if(node == null) {
			last = curr;
		}else {
			node.previous = curr;
			curr.next = node;
		}
	}
	/**
	 * Same as removeAll but uses an iterator
	 * @param item that is being removed
	 * @return the item being removed
	 */
	public boolean removeAllIter (E item) {
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			E data = iter.next();
			if(data == item) {
				return true;
			}
		}
		return false;
	}
}
