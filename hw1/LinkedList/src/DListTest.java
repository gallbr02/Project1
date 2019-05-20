import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DListTest {

	@Before
	public void setUp() throws Exception {
		lEmpty = new DList<String>();
		lSingle = new DList<String>();
		lMulti = new DList<String>();
	}

	@Test
	public void test_toString() {
		lSingle.toString();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());
		
		lMulti.toString();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());
		
		lMulti.toString();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		
		lMulti.toString();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	@Test
	public void test_toStringBackward() {
		lSingle.toStringBackward();
		assertEquals("[ A ]", lSingle.toString());
		assertEquals("[ A ]", lSingle.toStringBackward());		
		lMulti.toStringBackward();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());		
		lMulti.toStringBackward();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());		
		lMulti.toStringBackward();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	@Test
	public void test_add() {
		lSingle.add("A");
		assertEquals("[ A ]", lSingle.toString());
		assertEquals("[ A ]", lSingle.toStringBackward());	
		 
		lMulti.add("Z");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.add("Y");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.add("X");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	@Test
	public void test_add1() {
		lSingle.add1(1,"A");
		assertEquals("[ 1 ]", lSingle.toString());
		assertEquals("[ 1 ]", lSingle.toStringBackward());	
		
		lMulti.add1(2, "Z");
		assertEquals("[ 2 ]", lMulti.toString());
		assertEquals("[ 2 ]", lMulti.toStringBackward());	
		
		lMulti.add1(3, "Y");
		assertEquals("[ 3 ]", lMulti.toString());
		assertEquals("[ 3 ]", lMulti.toStringBackward());	
		
		lMulti.add1(4, "X");
		assertEquals("[ 4 ]", lMulti.toString());
		assertEquals("[ 4 ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_clear() {
		lSingle.clear();
		assertEquals("[ ]", lSingle.toString());
		assertEquals("[ ]", lSingle.toStringBackward());	
		
		lMulti.clear();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.clear();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.clear();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_get() {
		lSingle.get(1);
		assertEquals("[ 1 ]", lSingle.toString());
		assertEquals("[ 1 ]", lSingle.toStringBackward());	
		
		lMulti.get(2);
		assertEquals("[ 2 ]", lMulti.toString());
		assertEquals("[ 2 ]", lMulti.toStringBackward());	
		
		lMulti.get(3);
		assertEquals("[ 3]", lMulti.toString());
		assertEquals("[ 3 ]", lMulti.toStringBackward());	
		
		lMulti.get(4);
		assertEquals("[ 4 ]", lMulti.toString());
		assertEquals("[ 4 ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_set() {
		lSingle.set(1,"A");
		assertEquals("[ 1 ]", lSingle.toString());
		assertEquals("[ 1 ]", lSingle.toStringBackward());	
		
		lMulti.set(2,"Z");
		assertEquals("[ 2 ]", lMulti.toString());
		assertEquals("[ 2 ]", lMulti.toStringBackward());	
		
		lMulti.set(3,"Y");
		assertEquals("[ 3 ]", lMulti.toString());
		assertEquals("[ 3 ]", lMulti.toStringBackward());	
		
		lMulti.set(4, "Z");
		assertEquals("[ 4 ]", lMulti.toString());
		assertEquals("[ 4 ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_contains() {
		lSingle.add("A");
		assertEquals("[ A ]", lSingle.toString());
		assertEquals("[ A ]", lSingle.toStringBackward());	
		
		lMulti.add("Z");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.add("Y");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.add("X");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_indexfOf() {
		lSingle.indexOf("A");
		assertEquals("[ A ]", lSingle.toString());
		assertEquals("[ A ]", lSingle.toStringBackward());	
		
		lMulti.indexOf("Z");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.add("Y");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.add("X");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_lastIndexfOf() {
		lSingle.lastIndexOf("A");
		assertEquals("[ A ]", lSingle.toString());
		assertEquals("[ A ]", lSingle.toStringBackward());	
		
		lMulti.lastIndexOf("Z");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.lastIndexOf("Y");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.lastIndexOf("X");
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_size() {
		lSingle.size();
		assertEquals("[  ]", lSingle.toString());
		assertEquals("[ A ]", lSingle.toStringBackward());	
		
		lMulti.size();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.size();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	
		
		lMulti.size();
		assertEquals("[ A ]", lMulti.toString());
		assertEquals("[ A ]", lMulti.toStringBackward());	}
	
	@Test
	public void test_empty() {
		lSingle.empty();
		assertEquals("[  ]", lSingle.toString());
		assertEquals("[  ]", lSingle.toStringBackward());	
		
		lMulti.empty();
		assertEquals("[  ]", lMulti.toString());
		assertEquals("[  ]", lMulti.toStringBackward());	
		
		lMulti.empty();
		assertEquals("[  ]", lMulti.toString());
		assertEquals("[  ]", lMulti.toStringBackward());	
		
		lMulti.empty();
		assertEquals("[  ]", lMulti.toString());
		assertEquals("[  ]", lMulti.toStringBackward());	}
	
	private DList<String> lEmpty;
	private DList<String> lSingle;
	private DList<String> lMulti;

}
