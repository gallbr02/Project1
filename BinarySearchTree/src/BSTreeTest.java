import static org.junit.Assert.*;

import java.util.Comparator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class BSTreeTest {

	BSTree<Integer> empty;   
	BSTree<Integer> single;  
	BSTree<Integer> multi;   

	Comparator <Integer> comp;
	StringVisitor <Integer> strVisitor;
	@Before
	public void setUp()
	{
		comp = new IntComparator();
		empty = new BSTree<Integer>(comp);         
		assertEquals( empty.toString(), "[]" );

		single = new BSTree<Integer>(comp);        
		single.addLoop(8);
		assertEquals( single.toString(), "[8]" );

		multi = new BSTree<Integer>(comp);       
		multi.addLoop(6);
		multi.addLoop(4);
		multi.addLoop(7);
		multi.addLoop(3);
		multi.addLoop(9);
		multi.addLoop(1);


		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

	}

	@Test
	public void test_isEmpty() {

		assertTrue( empty.isEmpty() );
		assertEquals( empty.toString(), "[]" );

		assertFalse( single.isEmpty() );
		assertEquals( single.toString(), "[8]" );

		assertFalse( multi.isEmpty() );
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_maxValueLoop() {
		try { empty.maxValueLoop(); fail();}
		catch (NoSuchElementException e) {}
		assertEquals( empty.toString(), "[]" );

		assertTrue( single.maxValueLoop().equals(8) );
		assertEquals( single.toString(), "[8]" );

		assertTrue( multi.maxValueLoop().equals(9) );
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}

	@Test
	public void test_minValueLoop() {
		try { empty.minValueLoop(); fail();}
		catch (NoSuchElementException e) {}
		assertEquals( empty.toString(), "[]" );

		assertTrue( single.minValueLoop().equals(8) );
		assertEquals( single.toString(), "[8]" );

		assertTrue( multi.minValueLoop().equals(1) );
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_containsLoop() {
		empty.containsLoop(1);
		assertEquals( empty.toString(), "[]");

		assertTrue( single.containsLoop(8)) ;
		assertEquals( single.toString(), "[8]" );

		//assertFalse( multi.containsLoop(1)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		//assertFalse( multi.containsLoop(10)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertTrue( multi.containsLoop(3)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_addLoop() {
		empty.addLoop(1);
		assertEquals( empty.toString(), "[1]");

		single.addLoop(9) ;
		assertEquals( single.toString(), "[8 9]" );

		multi.addLoop(5) ;
		assertEquals( multi.toString(), "[6 4 7 3 5 9 1]" );
	}

	@Test
	public void test_add() {
		empty.add(1);
		assertEquals( empty.toString(), "[1]");

		single.add(9) ;
		assertEquals( single.toString(), "[8 9]" );

		multi.add(2) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1 2]" );
	}
	@Test
	public void test_maxValue() {
		try{ empty.maxValue(); fail();}
		catch (NoSuchElementException e) {}
		assertEquals( empty.toString(), "[]");

		assertTrue( single.maxValue().equals(8));
		assertEquals( single.toString(), "[8]" );

		assertTrue ( multi.maxValue().equals(9));
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_minValue() {
		try{ empty.minValue(); fail();}
		catch (NoSuchElementException e) {}
		assertEquals( empty.toString(), "[]");

		assertTrue( single.minValue().equals(8));
		assertEquals( single.toString(), "[8]" );

		assertFalse( multi.minValue().equals(4));
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertTrue( multi.minValue().equals(1)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_contains() {
		empty.contains(null);
		assertEquals( empty.toString(), "[]");

		assertTrue( single.contains(8)) ;
		assertEquals( single.toString(), "[8]" );

		//assertFalse (multi.contains(2)) ;
		//assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		//assertFalse( multi.contains(5)) ;
		//assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

		assertTrue (multi.contains(3)) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );

	}
	@Test
	public void test_remove() {
		empty.remove(0);
		assertEquals( empty.toString(), "[]");

		single.remove(8) ;
		assertEquals( single.toString(), "[]" );

		multi.remove(6) ;
		assertEquals( multi.toString(), "[4 3 7 1 9]" );
	}
	@Test
	public void test_preorder() {
		strVisitor = new StringVisitor<Integer>();
		empty.preorder(strVisitor);
		assertEquals( strVisitor.getValue(), "[]");

		strVisitor = new StringVisitor<Integer>();
		single.preorder(strVisitor) ;
		assertEquals( single.toString(), "[8]" );

		strVisitor = new StringVisitor<Integer>();
		multi.preorder(strVisitor) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_inorder() {
		strVisitor = new StringVisitor<Integer>();
		empty.inorder(strVisitor);
		assertEquals( empty.toString(), "[]");

		strVisitor = new StringVisitor<Integer>();
		single.inorder(strVisitor) ;
		assertEquals( single.toString(), "[8]" );

		strVisitor = new StringVisitor<Integer>();
		multi.inorder(strVisitor) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_postorder() {
		strVisitor = new StringVisitor<Integer>();
		empty.postorder(strVisitor);
		assertEquals( empty.toString(), "[]");

		strVisitor = new StringVisitor<Integer>();
		single.postorder(strVisitor) ;
		assertEquals( single.toString(), "[8]" );

		strVisitor = new StringVisitor<Integer>();
		multi.postorder(strVisitor) ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_CountRangeVisitor() {
//		empty.CountRangeVisitor();
//		assertEquals( empty.toString(), "[]");
//
//		single.CountRangeVisitor(8) ;
//		assertEquals( single.toString(), "[]" );
//
//		multi.CountRangeVisitor(1) ;
//		assertEquals( multi.toString(), "[6 4 7 3 9]" );
	}
	@Test
	public void test_toString() {
		empty.toString();
		assertEquals( empty.toString(), "[]");

		single.toString() ;
		assertEquals( single.toString(), "[8]" );

		multi.toString() ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_toStringPre() {
		empty.toStringPre();
		assertEquals( empty.toString(), "[]");

		single.toStringPre() ;
		assertEquals( single.toString(), "[8]" );

		multi.toStringPre() ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_equals() {
		assertFalse(empty.equals(multi));
		assertTrue(empty.equals(empty));		

		assertFalse(single.equals(empty)) ;
		assertTrue(single.equals(single));

		assertFalse(multi.equals(single)) ;
		assertTrue(multi.equals(multi));
	}
	@Test
	public void test_clone() { 
		empty.clone();
		assertEquals( empty.toString(), "[]");

		single.clone() ;
		assertEquals( single.toString(), "[8]" );

		multi.clone() ;
		assertEquals( multi.toString(), "[6 4 7 3 9 1]" );
	}
	@Test
	public void test_BSTree() {
		Integer[] numbers = {6, 4, 7, 3, 9};
		BSTree<Integer> tree = new BSTree<Integer>(numbers, comp);
		assertEquals( tree.toStringPre(), "[6 4 7 3 9]");
	}

}
