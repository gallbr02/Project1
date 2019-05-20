import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashTableTest {
	

	@Before
	public void setUp() throws Exception {
		lEmpty = new HashTable<Integer,String>();
		lSingle = new HashTable<Integer,String>();
		lMulti = new HashTable<Integer,String>();
		
		lSingle.put(1, "A");
		lMulti.put(2, "B");
		lMulti.put(3, "C");
		lMulti.put(4, "D");
	}

	@Test
	public void test_hash() {
		
	}
	@Test 
	public void test_put() {
		assertEquals("A", lSingle.put(1, "Brandon"));
	}
	@Test
	public void test_containsKey() {
		assertEquals("A", lSingle.containsKey(1));
	}@Test
	public void test_containsValue() {
		assertEquals("A", lSingle.containsValue("A"));
	}@Test
	public void test_get() {
		lSingle.get(1);
		assertEquals("F A", lSingle.toString());
	}@Test
	public void test_remove() {
		lSingle.remove(1);
		assertEquals("F A ", lSingle.toString());
	}@Test
	public void test_size() {
		equals( lSingle.size());
	}
	@Test
	public void test_empty() {
		equals( lSingle.empty());
	}@Test
	public void test_clear() {
		lSingle.clear();
		assertEquals(" ", lSingle.toString());
	}
	@Test
	public void test_toString() {
		lSingle.toString();
		assertEquals("", lSingle.toString());
	}
	private HashTable<Integer,String> lEmpty;
	private HashTable<Integer,String> lSingle;
	private HashTable<Integer,String> lMulti;

}
