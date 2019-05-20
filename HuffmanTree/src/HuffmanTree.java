import java.io.IOException;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;



import javafx.scene.Node;

public class HuffmanTree{


	private HNode root;
	private PriorityQueue <HNode> pq;

	/**
	 * Builds a huffman tree from the given characters and frequencies
	 * @param frequencies the given characters are being compared to
	 */
	public HuffmanTree(TreeMap<Character, Integer> frequencies){ 

		HNodeComparator comp = new HNodeComparator();
		Set<Entry <Character, Integer>> set = frequencies.entrySet();
		pq = new PriorityQueue<HNode>(comp);

		for(Entry<Character, Integer> etr: set)
		{
			char chr = etr.getKey();
			int value = etr.getValue();
			HNode leaf = new HNode(chr, value);
			pq.add(leaf);
		}
		int size = pq.size();
		for(int i = 1; i<= size -1; i++) {
			HNode h1 = pq.remove();
			HNode h2 = pq.remove();
			HNode h3 = new HNode(h1, h2);
			pq.add(h3);
		}	
	}

	/**
	 * Binary code of the given symbol as a string '0' and '1'
	 * @param symbol that returns the binary code of itself
	 * @return the binary code of the symbol
	 */
	public String encodeLoop (char symbol) {
		String str = "";
		HNode curr = pq.peek();
		while(curr != null ) {
			if(curr.isLeaf())
			{
				return str;
			}
			else if(curr.left.contains(symbol)) {
				str = str + "0";
				curr = curr.left;
			}
			else if(curr.right.contains(symbol)) {
				str = str + "1";
				curr = curr.right;
			}
		}
		return str;
		
	}
	/**
	 * 
	 * @param symbol that returns the binary code of itself
	 * @return The binary encoding of the given symbol
	 */
	public String encode(char symbol) {
		return encode(symbol, pq.peek());
	}
	/**
	 * 
	 * @param symbol given symbol as a string
	 * @param node that starts the string of '0' and '1'
	 * @return the binary encoding of the given symbol
	 */
	public String encode(char symbol,HNode node) {
		String str = "";
		HNode curr = node;
		if(curr == null) {
			return str;
		}
		else {
			if( curr.isLeaf()) {
				return str;
			}
			if(curr.left.contains(symbol)) {
				str = str + '0';
				str += encode(symbol, curr.left);
			}
			if(curr.right.contains(symbol)) {
				str = str + '1';
				str += encode(symbol, curr.right);
			}
		}
		return str;
	}
	/**
	 * 
	 * @param code that is given to match the string
	 * @return the symbol that corresponds with the given code
	 */
	public char decode(String code) { 
		char chr = 0;
		HNode curr = pq.peek();
		for(int i = 0; i<code.length(); i++) {
			if(code.charAt(i)== 0) {
				curr = curr.left;
				chr = code.charAt(i);
				//chr += code.charAt(i);
			}
			else if(code.charAt(i)==1) {
				curr = curr.right;
				chr = code.charAt(i);
				//chr += code.charAt(i);
			}else {
				return chr;
			}
		}
		return chr;
	}
	
	/**
	 * Writes an individual bits of the binary encoding of the given symbol to the given bit stream
	 * @param symbol that is given to write the individual bits
	 * @param stream 1 and 0 are written in the given bit stream, instead of being appended to a string
	 * @return true if the code was written successfully
	 * @throws IOException 
	 */
	public boolean writeCode(char symbol, BitOutputStream stream) throws IOException {
		HNode curr = pq.peek();
		if(curr == null) {
			return false;
		}
		while(curr != null ) {
			if(curr.isLeaf()) {
				return true;
			}
			if(curr.left.contains(symbol)) {
				stream.writeBit(0);
				curr = curr.left;
			}
			if(curr.right.contains(symbol)) {
				stream.writeBit(1);
				curr = curr.right;
			}
		}
		stream.close();
		return true;
	}

	/**
	 * 
	 * @param stream that the individual bits of the binary encoding
	 * @return the corresponding character of the binary encoding of the next symbol
	 * @throws IOException
	 */
	public char readCode(BitInputStream stream) throws IOException{
		char chr = '0';
		HNode curr = pq.peek();
		if(curr != null)
		stream.readBit();
			
		
		stream.close();
		return chr;
	}
}
