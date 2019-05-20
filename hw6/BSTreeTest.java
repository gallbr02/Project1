
public class BSTreeTest {

	public static void main(String[] args) {
		BSTree<Integer> bst = new BSTree<Integer>(new IntComparator1());
		System.out.println(bst.empty());
		bst.add(11);
		bst.add(40);
		bst.add(15);
		bst.add(52);
		bst.add(2);
		bst.add(27);
		bst.add(99);
		bst.add(52);
		bst.add(11);
		bst.add(12);
		bst.add(17);
		bst.add(3);
		
		
	
		System.out.println(bst);
		System.out.println("removing right most " + bst.removeRightMostNode(bst.getRoot()).data);
		System.out.println(bst);
		System.out.println("removing right most " + bst.removeRightMostNode(bst.getRoot()).data);
		System.out.println(bst);
	


		System.out.println(bst.contains(3));
		System.out.println(bst.contains(2));
		System.out.println(bst.contains(1));
		System.out.println(bst.contains(99));
		System.out.println(bst);
		
		bst.add(100);
		System.out.println(bst);
		System.out.println(bst.contains(0));
		bst.add(-1);
		System.out.println(bst);
		
		System.out.println("Testing empty");
		System.out.println(bst.empty());
		
		System.out.println(bst.getRoot().data + " GET ROOT");
		
		
//		BSTree<Double> bst1 = new BSTree<Double>( new DoubleComparator());
//		bst1.add(1.0);
//		bst1.add(7.0);
//		bst1.add(9.0);
//		bst1.add(76.0);
//		bst1.add(11.0);
//		bst1.add(99.0);
//		bst1.add(-14.0);
////		
//		System.out.println();
//		System.out.println("TESTING DOUBLE BSTREE");
//		System.out.println(bst1);
//		System.out.println(bst1.contains(9.0));
//		System.out.println(bst1.contains(1.0));
//		System.out.println(bst1.contains(34.0));
//		System.out.println(bst1.contains(-14.0));
//
//		System.out.println(bst1.size());
//		bst1.add(17.0);
//		System.out.println(bst1);
//		bst1.empty();
//		System.out.println(bst1.empty());
	}

}
