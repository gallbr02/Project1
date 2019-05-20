import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class DListTest {

	@Before
	public void setUp() throws Exception {
		lEmpty = new DList<String>();
		lSingle = new DList<String>();
		lMulti = new DList<String>();

		lSingle.add("A");

		lMulti.add("B");
		lMulti.add("C");
	}

	@Test
	public void test_toString() {
		lSingle.toString();
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());

		lMulti.toString();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());

		lMulti.toString();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	


		lMulti.toString();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	}
	@Test
	public void test_toStringBackward() {
		lSingle.toStringBackward();
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());		
		lMulti.toStringBackward();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());		

		lMulti.add("D");

		lMulti.toStringBackward();
		assertEquals("[ B C D  ]", lMulti.toString());
		assertEquals("[ D C B  ]", lMulti.toStringBackward());	

		lMulti.add("E");

		lMulti.toStringBackward();
		assertEquals("[ B C D E  ]", lMulti.toString());
		assertEquals("[ E D C B  ]", lMulti.toStringBackward());	}
	@Test
	public void test_add() {
		lSingle.add("D");
		assertEquals("[ A D  ]", lSingle.toString());
		assertEquals("[ D A  ]", lSingle.toStringBackward());	

		lMulti.add("D");
		assertEquals("[ B C D  ]", lMulti.toString());
		assertEquals("[ D C B  ]", lMulti.toStringBackward());	

		lMulti.add("E");
		assertEquals("[ B C D E  ]", lMulti.toString());
		assertEquals("[ E D C B  ]", lMulti.toStringBackward());	

		lMulti.add("X");
		assertEquals("[ B C D E X  ]", lMulti.toString());
		assertEquals("[ X E D C B  ]", lMulti.toStringBackward());	}
	@Test
	public void test_add1() {
		lSingle.add1(1,"A");
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		lMulti.add1(2, "Z");
		assertEquals("[ B Z  ]", lMulti.toString());
		assertEquals("[ Z B  ]", lMulti.toStringBackward());	

		lMulti.add1(3, "Y");
		assertEquals("[ 3 ]", lMulti.toString());
		assertEquals("[ 3 ]", lMulti.toStringBackward());	

		lMulti.add1(4, "X");
		assertEquals("[ 4 ]", lMulti.toString());
		assertEquals("[ 4 ]", lMulti.toStringBackward());	}

	@Test
	public void test_clear() {
		lEmpty.clear();
		assertEquals("[  ]", lEmpty.toString());
		assertEquals("[  ]", lEmpty.toStringBackward());	

		lSingle.add("M");
		assertEquals("[ A M  ]", lSingle.toString());

		lSingle.clear();
		assertTrue("[  ]", lSingle.empty());

		lMulti.clear();
		assertTrue("[  ]", lMulti.empty());
		assertEquals("[  ]", lMulti.toStringBackward());	

		lMulti.add("F");
		assertEquals("[ F  ]", lMulti.toString());

		lMulti.clear();
		assertTrue("[  ]", lMulti.empty());
		assertEquals("[  ]", lMulti.toStringBackward());	

		lMulti.add("G");
		assertEquals("[ G  ]", lMulti.toString());

		lMulti.clear();
		assertTrue("[  ]", lMulti.empty());
		assertEquals("[  ]", lMulti.toStringBackward());	

	}

	@Test
	public void test_get() {
		try{lEmpty.get(0);}
		catch (IndexOutOfBoundsException e) { }
		assertEquals("[  ]", lEmpty.toString());
		assertEquals("[  ]", lEmpty.toStringBackward());	


		try{lSingle.get(0);}
		catch (IndexOutOfBoundsException e) { }
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		try{lMulti.get(1);}
		catch (IndexOutOfBoundsException e) { }
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		lMulti.add("Z");

		try{lMulti.get(3);}
		catch (IndexOutOfBoundsException e) { }
		assertEquals("[ B C Z  ]", lMulti.toString());
		assertEquals("[ Z C B  ]", lMulti.toStringBackward());	
	}

	@Test
	public void test_set() {
		lMulti.add("M");
		lMulti.add("N");


		lSingle.set(0,"A");
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		lMulti.set(2,"Z");
		assertEquals("[ B Z M N  ]", lMulti.toString());
		assertEquals("[ N M Z B  ]", lMulti.toStringBackward());	

		lMulti.set(3,"Y");
		assertEquals("[ B Z Y N  ]", lMulti.toString());
		assertEquals("[ N Y Z B  ]", lMulti.toStringBackward());	

		lMulti.set(4, "X");
		assertEquals("[ B Z Y X  ]", lMulti.toString());
		assertEquals("[ X Y Z B  ]", lMulti.toStringBackward());	}

	@Test
	public void test_contains() {
		lSingle.contains("A");
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		lMulti.contains("B");
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		lMulti.contains("C");
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		assertFalse(lMulti.contains("Z"));
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	}

	@Test
	public void test_indexfOf() {
		assertEquals(1, lSingle.indexOf("A"));
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		assertEquals(1, lMulti.indexOf("B"));
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		assertEquals(2, lMulti.indexOf("C"));
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		lMulti.add("X");

		assertEquals(3, lMulti.indexOf("X"));
		assertEquals("[ B C X  ]", lMulti.toString());
		assertEquals("[ X C B  ]", lMulti.toStringBackward());


	}

	@Test
	public void test_lastIndexfOf() {
		assertEquals(1,lSingle.lastIndexOf("A"));
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		assertEquals(2,lMulti.lastIndexOf("B"));
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		lMulti.add("B");

		assertEquals(3,lMulti.lastIndexOf("B"));
		assertEquals("[ B C B  ]", lMulti.toString());
		assertEquals("[ B C B  ]", lMulti.toStringBackward());

		assertNotEquals(4,lMulti.lastIndexOf("Y"));
		assertEquals("[ B C B  ]", lMulti.toString());
		assertEquals("[ B C B  ]", lMulti.toStringBackward());	

		assertNotEquals(5,lMulti.lastIndexOf("X"));
		assertEquals("[ B C B  ]", lMulti.toString());
		assertEquals("[ B C B  ]", lMulti.toStringBackward());	}

	@Test
	public void test_size() {


		assertEquals(1,lSingle.size());
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		assertEquals(2,lMulti.size());
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());

		lMulti.add("L");

		assertEquals(3,lMulti.size());
		assertEquals("[ B C L  ]", lMulti.toString());
		assertEquals("[ L C B  ]", lMulti.toStringBackward());	

		lMulti.add("Q");
		assertEquals(4,lMulti.size());
		assertEquals("[ B C L Q  ]", lMulti.toString());
		assertEquals("[ Q L C B  ]", lMulti.toStringBackward());	}

	@Test
	public void test_empty() {

		lSingle.empty();
		assertEquals("[ A  ]", lSingle.toString());
		assertEquals("[ A  ]", lSingle.toStringBackward());	

		lMulti.empty();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		lMulti.empty();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	

		lMulti.empty();
		assertEquals("[ B C  ]", lMulti.toString());
		assertEquals("[ C B  ]", lMulti.toStringBackward());	}

	private DList<String> lEmpty;
	private DList<String> lSingle;
	private DList<String> lMulti;

}
