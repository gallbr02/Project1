
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


public class DLinkedListTest
{
	/**
	 * Lists for the three different configurations to test.
	 */
	private DLinkedList<Integer> empty;   // empty list
	private DLinkedList<Integer> single;  // one-element list
	private DLinkedList<Integer> multi;   // multi-element list
	private DLinkedList<Integer> multi2;


	/**
	 * Re-initializes the lists for the three different configurations.
	 * Called automatically by the JUnit framework before each of the
	 * test methods below.
	 */
	@Before
	public void setUp()
	{
		empty = new DLinkedList<Integer>();         // []
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single = new DLinkedList<Integer>();        // [8]
		single.addFirst(8);
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );

		multi = new DLinkedList<Integer>();         // [4 3 5 7 1 6]
		multi.addFirst(6);
		multi.addFirst(1);
		multi.addFirst(7);
		multi.addFirst(5);
		multi.addFirst(3);
		multi.addFirst(4);
	
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		multi2 = new DLinkedList<Integer>();         // [4 3 5 7 1 6]
		multi2.addFirst(9);
		multi2.addFirst(1);
		multi2.addFirst(7);
		multi2.addFirst(5);
		multi2.addFirst(3);
		multi2.addFirst(9);
		
		assertEquals( multi2.toString(), "[9 3 5 7 1 9]" );
		assertEquals( multi2.toStringRev(),"[9 1 7 5 3 9]" );
	}


	/**
	 * Test the addFirst method. The elements were added in *setUp*
	 */
	//@Test
	public void test_addFirst()
	{
		empty.addFirst(9);
		assertEquals( empty.toString(), "[9]" );
		assertEquals( empty.toStringRev(),"[9]" );

		single.addFirst(2);
		assertEquals( single.toString(), "[2 8]" );
		assertEquals( single.toStringRev(),"[8 2]" );
		
		multi.addFirst(2);
		assertEquals( multi.toString(), "[2 4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4 2]" );
	}


	/**
	 * Test the addLast method.
	 */
	@Test
	public void test_addLast()
	{
		empty.addLast(0); 
		assertEquals( empty.toString(), "[0]" );
		assertEquals( empty.toStringRev(),"[0]" );

		single.addLast(8);
		assertEquals( single.toString(), "[8 8]" );
		assertEquals( single.toStringRev(),"[8 8]" );
		
		multi.addLast(5);
		assertEquals( multi.toString(), "[4 3 5 7 1 6 5]" );
		assertEquals( multi.toStringRev(),"[5 6 1 7 5 3 4]" );
		
	}


	/**
	 * Test the isEmpty method.
	 */
	@Test
	public void test_isEmpty()
	{
		
		assertTrue( empty.isEmpty() );
		assertEquals( empty.toString(), "[]" );
		

		assertFalse( single.isEmpty() );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );

		
		assertFalse( multi.isEmpty() );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}


	/**
	 * Test the getFirst() method.
	 */
	//@Test
	public void test_getFirst()
	{
		try { empty.getFirst(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

	
		assertTrue( single.getFirst().equals(8) );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );

		
		assertTrue( multi.getFirst().equals(4) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}
	//@Test
	public void test_getLast(){
		try { empty.getLast(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		assertTrue( single.getLast().equals(8));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );

		assertTrue( multi.getLast().equals(6));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}
	/*@Test
	public void test_add(){
		
		assertEquals( empty.toString(), "[4 2]" );
		assertEquals( empty.toStringRev(),"[2 4]" );

		single.add( 7, 2 );
		assertEquals( single.toString(), "[8 7]" );
		assertEquals( single.toStringRev(),"[7 8]" );

		multi.add( 9, 2);
		assertEquals( multi.toString(), "[4 3 5 7 1 6 9]" );
		assertEquals( multi.toStringRev(),"[9 6 1 7 5 3 4]" );
	}*/
	
	//@Test
	public void test_get(){
		try {empty.get(0);}
		catch (IndexOutOfBoundsException e)  {/*test passed*/}
		
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		try {single.get(1);}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );
		
		try {single.get(-1);}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );
		
		//single.get(0);
		//assertEquals( single.toString(), "[8]" );
		//assertEquals( single.toStringRev(),"[8]" );


		try {multi.get(2);}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( multi.toString(), "[3]" );
		assertEquals( multi.toStringRev(),"[3]" );
		
		try {multi.get(0);}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( multi.toString(), "[]" );
		assertEquals( multi.toStringRev(),"[]" );
		
		//assertTrue( multi.get(2));
		assertEquals( multi.toString(), "[2 5 4 6 7 8]" );
		assertEquals( multi.toStringRev(),"[8 7 6 4 5 2]" );
		
		assertTrue( multi.get(8));
		assertEquals( multi.toString(), "[2 5 4 6 7 8]" );
		assertEquals( multi.toStringRev(),"[8 7 6 4 5 2]" );
	}
	
	//@Test
	public void test_set(){
		empty.set(0, 8 );
		assertEquals( empty.toString(), "[8]" );
		assertEquals( empty.toStringRev(),"[8]" );

		single.set( 7, 2 );
		assertEquals( single.toString(), "[8 7]" );
		assertEquals( single.toStringRev(),"[7 8]" );

		multi.set( 9, 2);
		assertEquals( multi.toString(), "[4 3 5 7 1 6 9]" );
		assertEquals( multi.toStringRev(),"[9 6 1 7 5 3 4]" );
	}
	//@Test
	public void test_contains(){
		assertFalse(empty.contains(2));	
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		assertTrue(single.contains(8));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );
		
		assertFalse(single.contains(9));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );


		assertTrue( multi.contains(4));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		assertTrue ( multi.contains(6));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		assertTrue ( multi.contains(7));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		assertFalse( multi.contains(2));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
	}
	//@Test
	/*
	public void test_toStringRev(){
		empty.toStringRev();
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.toStringRev();
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );

		multi.toStringRev();
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}
	*/
	//@Test
	/*
	public void test_toString(){
	
		empty.toString();
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.toString();
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );

		multi.toString();
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}
	*/
	//@Test
	public void test_clear(){
		empty.clear();
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.clear();
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );

		multi.clear();
		assertEquals( multi.toString(), "[]" );
		assertEquals( multi.toStringRev(),"[]" );
	}
	//@Test
	public void test_containsIter(){
		
		assertFalse(empty.containsIter(2));	
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		assertTrue(single.containsIter(8));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );
		
		assertFalse(single.containsIter(9));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );


		assertTrue( multi.containsIter(4));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		assertTrue ( multi.containsIter(6));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		assertTrue ( multi.containsIter(7));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		assertFalse( multi.containsIter(2));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
	}
	@Test
	public void test_removeFirst() {
		try{empty.removeFirst(); fail();}
		catch(NoSuchElementException e) {}
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		
		single.removeFirst();
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );
		
		multi.removeFirst();
		assertEquals( multi.toString(), "[3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3]" );
	}
	@Test
	public void test_removeLast() {
		try{empty.removeLast(); fail();}
		catch(NoSuchElementException e) {}
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.removeLast();
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );
		
		multi.removeLast();
		assertEquals( multi.toString(), "[4 3 5 7 1]" );
		assertEquals( multi.toStringRev(),"[1 7 5 3 4]" );
	}
	
	@Test
	public void test_removeItem() {
		assertFalse( empty.removeItem(0));
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		assertTrue( single.removeItem(8));
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );

		assertFalse( multi.removeItem(9));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );

		assertTrue( multi.removeItem(4));
		assertEquals( multi.toString(), "[3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3]" );
		
		assertTrue( multi.removeItem(6));
		assertEquals( multi.toString(), "[3 5 7 1]" );
		assertEquals( multi.toStringRev(),"[1 7 5 3]" );
		
		assertTrue( multi.removeItem(7));
		assertEquals( multi.toString(), "[3 5 1]" );
		assertEquals( multi.toStringRev(),"[1 5 3]" );
	}
	@Test
	public void test_removeAll() {
		empty.removeAll(0);
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

	//	single.removeAll(8);
	//	assertEquals( single.toString(), "[]" );
	//	assertEquals( single.toStringRev(),"[]" );
		
		multi.removeAll(7);
		assertEquals( multi.toString(), "[4 3 5 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 5 3 4]" );
		
		multi2.removeAll(9);
		assertEquals( multi2.toString(), "[3 5 7 1]" );
		assertEquals( multi2.toStringRev(),"[1 7 5 3]" );
	}
	@Test
	public void test_equals() {
		empty.equals(multi);
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.equals(empty);
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );
		
		multi.equals(single);
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}
	@Test
	public void test_clone() {
		empty.clone();
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.clone();
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(),"[8]" );
		
		multi.clone();
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
	}
	@Test
	public void test_remove() {
		try{empty.remove(0); fail();}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.remove(0); 
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );
		
		try{multi.remove(-1); fail();}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		try{multi.remove(6);fail();}
		catch (IndexOutOfBoundsException e) {}
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4]" );
		
		multi.remove(0); 		
		assertEquals( multi.toString(), "[3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3]" );
		
		multi.remove(2); 		
		assertEquals( multi.toString(), "[3 5 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 5 3]" );
		
		multi.remove(3); 		
		assertEquals( multi.toString(), "[3 5 1]" );
		assertEquals( multi.toStringRev(),"[1 5 3]" );	
	}
	@Test
	public void test_addAll() {
		empty.addAll(0, Arrays.asList(3, 4, 5, 6));
		assertEquals( empty.toString(), "[3 4 5 6]" );
		assertEquals( empty.toStringRev(),"[6 5 4 3]" );

		single.addAll(0, Arrays.asList(3 ));
		assertEquals( single.toString(), "[3 8]" );
		assertEquals( single.toStringRev(),"[8 3]" );

		multi.addAll(0, Arrays.asList(3, 4, 5, 6));
		assertEquals( multi.toString(), "[3 4 5 6 4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 4 6 5 4 3]" );
		
		multi.addAll(5, Arrays.asList(2,1));
		assertEquals( multi.toString(), "[3 4 5 6 4 2 1 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3 1 2 4 6 5 4 3]" );
		
		multi.addAll(12, Arrays.asList(9,9));
		assertEquals( multi.toString(), "[3 4 5 6 4 2 1 3 5 7 1 6 9 9]" );
		assertEquals( multi.toStringRev(),"[9 9 6 1 7 5 3 1 2 4 6 5 4 3]" );

	}
	
	@Test
	public void test_removeAllIter() {
		empty.removeAllIter(0);
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(),"[]" );

		single.removeAllIter(8);
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(),"[]" );
		
		multi.removeAllIter(4);
		assertEquals( multi.toString(), "[3 5 7 1 6]" );
		assertEquals( multi.toStringRev(),"[6 1 7 5 3]" );
	}
	
	@Test
	public void test_iter_fails() {
		
	}
	

}
















