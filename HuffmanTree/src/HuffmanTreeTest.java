import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeTest<E> {
	private HNode node1;
	private	HNode node2;
	private HNode node3;
	private HNode node4;
	private HNode node5;
	private HNode node6;
	private HNode node7;
	private HNode node8;
	private HNode node9;
	private HNode node10;
	private  HNode node11;

	private HuffmanTree tree;
	private TreeMap <Character, Integer> freq;
	 
	@Before
	public void setUp() {
		freq = new TreeMap<Character, Integer>();
		freq.put('a', 20);
		freq.put('b', 55);
		freq.put('c', 20);
		freq.put('d', 25);
		freq.put('e', 30);
		freq.put('r', 35);
		tree = new HuffmanTree(freq);
		
		node1 = new HNode('a', 20);
		node2 = new HNode('b', 55);
		node3 = new HNode('c', 20);
		node4 = new HNode('d', 25);
		node5 = new HNode('e', 30);
		node6 = new HNode('r', 35);
		node7 = new HNode(node1, node2);
		node8 = new HNode(node3, node4);
		node9 = new HNode(node7, node6);
		node10 = new HNode(node8, node5);
		node11 = new HNode(node10, node9);
		
	}

	@Test
	public void test_isLeaf() {
		assertTrue(node1.isLeaf());
		assertTrue(node2.isLeaf());
		assertTrue(node3.isLeaf());
		assertTrue(node4.isLeaf());
		assertTrue(node5.isLeaf());
		assertTrue(node6.isLeaf());
		assertFalse(node7.isLeaf());
		assertFalse(node8.isLeaf());
		assertFalse(node9.isLeaf());
		assertFalse(node10.isLeaf());
		assertFalse(node11.isLeaf());
	}
	@Test
	public void test_contains() {
		assertTrue(node1.contains('a'));
		assertTrue(node2.contains('b'));
		assertTrue(node3.contains('c'));
		assertFalse(node4.contains('a'));
		assertFalse(node8.contains('e'));
		assertTrue(node9.contains('b'));
		assertTrue(node10.contains('e'));
		assertTrue(node11.contains('d'));
	}
	@Test
	public void test_encodeLoop() {
		assertEquals(tree.encodeLoop('a'), "010");
		assertEquals(tree.encodeLoop('b'), "10");
		assertEquals(tree.encodeLoop('c'), "011");
		assertEquals(tree.encodeLoop('d'), "110");
		assertEquals(tree.encodeLoop('e'), "111");
		assertEquals(tree.encodeLoop('r'), "00");
	}
	@Test
	public void test_encode() {
		assertEquals(tree.encode('a'), "010");
		assertEquals(tree.encode('b'), "10");
		assertEquals(tree.encode('c'), "011");
		assertEquals(tree.encode('d'), "110");
		assertEquals(tree.encode('e'), "111");
		assertEquals(tree.encode('r'), "00");
	}
	@Test
	public void test_decode() {
		assertEquals(tree.decode("010"), 'a');
		assertEquals(tree.decode("10"), 'b');
		assertEquals(tree.decode("011"), 'c');
		assertEquals(tree.decode("110"), 'd');
		assertEquals(tree.decode("111"), 'e');
		assertEquals(tree.decode("00"), 'r');
	}


}
