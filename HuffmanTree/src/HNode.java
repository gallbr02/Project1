import javafx.scene.Node;

public class HNode{
	HNode right;
	HNode left;
	String symbol;
	int frequency;
	/**
	 * Creates a leaf node representing the given character
	 * @param c the given character
	 * @param f the given frequency
	 */
	public HNode(char c, int f){
		left = null;
		right = null;
		frequency = f;
		this.symbol = c+ "";
	}
	/**
	 * Creates a node at the given left and right children
	 * @param left
	 * @param right
	 */
	public HNode(HNode left, HNode right){
		this.left = left;
		this.right = right;
		frequency = left.frequency + right.frequency;
		symbol = left.symbol + right.symbol;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		if(left == null && right == null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 * @param ch
	 * @return
	 */
	public boolean contains (char ch) {
		for(int i=0; i<symbol.length(); i++) {
			if(symbol.charAt(i)== ch) {
				return true;
			}
		}
		return false;
	}
}






