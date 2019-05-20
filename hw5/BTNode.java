/**
 * 
 * @author gallbr02
 *	reps a binary tree node
 */
public class BTNode<E> {
	E data;
	BTNode<E> left, right;
	// BTNode<E> parent;
	public BTNode() {
		
	}
	public BTNode(E d) {
		this(d, null, null);
	}
	public BTNode(E d, BTNode<E> l, BTNode<E> r) {
		data = d;
		left = l;
		right = r;
	}
	// countNodes from given BTNode, ptr
	// static: only paramenters are accessed
	//			no access to instance member variables
	public static int countNodes(BTNode ptr) {
		if(ptr == null) {
			return 0;
		}
		else {
			return (1 + countNodes(ptr.left)+ countNodes(ptr.right));
		}
	}
	//returns height or number of levels rooted at level
	public static int getHeight(BTNode ptr) {
		
		if(ptr == null) {
			return 0;
		}
		else {
			//get the height of left child and all decendents
			//get the height of right child
			int lHeight= getHeight(ptr.left);
			int rHeight= getHeight(ptr.right);
			if(lHeight > rHeight) {
				return lHeight + 1;
			}else {
				return rHeight + 1;
			}
			//compare the two and return the larger
		}
	}
	//tree traversal algorithms
	//pre-order:
	
	public static String preOrder(BTNode ptr) {
		
		if(ptr == null) {
			return "";
		}else {
			String str = ptr.data + " ";
			String strL = preOrder(ptr.left);
			String strR = preOrder(ptr.right);
			
			return str = str + " " + strL + " " + strR + " ";
		}
		/*
		String str = "" + ptr.data + " ";
		while(ptr.left != null) {
			str += ptr.left.data + " ";
		}
		return str;
		 */
	}
	public static void main (String[] args) {//	left,right
		BTNode<Integer> n15 = null;
		BTNode<Integer> n25 = null;
		BTNode<Integer> n2 = null;
		BTNode<Integer> n8 = null;

		BTNode<Integer> n9 = new BTNode<Integer>(9, n8, null);

		BTNode<Integer> n20 = new BTNode<Integer>(20, n15, n25);

		BTNode<Integer> n5 = new BTNode<Integer>(5, n2, n9);

		BTNode<Integer> n0 = new BTNode<Integer>(10, n5, n20);
		
		System.out.println(countNodes(n0));
		System.out.println(getHeight(n0));
		System.out.println(preOrder(n0));

	}
		
}
