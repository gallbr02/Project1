/**
 * 
 * @author Brandon Gallagher
 * representation of a huffman tree
 *
 */
public class HuffmanCODEC {

	private HuffmanTree codeTree;
	private HuffmanNode root;


	public HuffmanCODEC (HuffmanTree tree){
		codeTree = tree;
		root = codeTree.getRoot();
	}
	/**
	 * encodes msg using the huffman code tree 
	 * returns an encoded string with only 0 and 1
	 * @param msg that is being encoded
	 * @return an encoded string with 0s and 1s
	 */
	public String encode(String msg) {

		String str = "";
		//go to each char in the message
		for(int i = 0; i < msg.length(); i++) {
			HuffmanNode ptr = root;
			char c = msg.charAt(i);

			//if it does go right and see if 
			//the right contains it if not check left
			while(!ptr.isLeaf()) {
				if(((HuffmanNode)ptr.getRight()).contains(c)) {
					ptr = (HuffmanNode) ptr.getRight();
					str+= "1";
				}
				else if(((HuffmanNode)ptr.getLeft()).contains(c)) {
					ptr = (HuffmanNode) ptr.getLeft();
					str+= "0";
				}
				// if it does not contain the msg return null
				else {
					return null;
				}
			}
		}
		return str;
	}
	/**
	 * decoded the message using huffman code tree
	 * returns the decoded the given code tree or return null;
	 * @param bits thats is being decoded
	 * @return the decoded message or return null
	 */
	public String decode(String bits) {
		String str = "";
		HuffmanNode ptr = root;
		
		// go thru all the bits
		for(int i = 0; i < bits.length(); i++) {
			char c = bits.charAt(i);

			// check if its 1 or 0
			if(c == '1') {
				ptr = (HuffmanNode) ptr.getRight();

			}else {
				ptr = (HuffmanNode) ptr.getLeft();
			}
			// if leaf get the value
			if(ptr.isLeaf()) {
				str += ptr.leafValue();
				ptr = root;
			}
		}

		return str;
	}
}
