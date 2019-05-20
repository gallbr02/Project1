import java.util.ArrayList;
import java.util.HashMap;

public class DisjointSet<E> {
	private class DSNode<E>{

		public DSNode(E d) {
			data = d;
			parent = d;
			rank = 0; 
		}
		public String toString() {
			String str = "";
			return str += "(" + data +"," + parent + "," + rank + ")";
		}
		E data;
		E parent;
		int rank;
	}
	/**
	 *  
	 */
	public DisjointSet(Iterable<E> items) {
		set = new HashMap<E, DSNode<E>>();
		for(E v : items) {
			DSNode<E> obj = new DSNode<E>(v);
			set.put(v, obj);
			makeSet(v);
		}
	}
	/**
	 * finds the dsnode object associated with item and sets its parent to item and rank to 0
	 * @param item associated with the object
	 */
	public void makeSet(E item) {
		set.get(item).parent = item;
		set.get(item).rank = 0;

	}
	/**
	 * finds the dsnode object associated with item and return 
	 * the representative of the set item belongs to. 
	 * must also path compress so that the next time findRep(item) is called it will be quick
	 * @param item associated with the object
	 * @return the represenatative of the set item belongs to
	 */
	public E findRep(E item) {
		DSNode<E> node = set.get(item);
		if(node.parent != item) {
			node.parent = findRep(node.parent);
		}
		//System.out.println("find rep" +  node);

		//	if(set.get(item) == item) {
		//	 save item into node, save the items parent into a node outside if
		//	 comparing the data of parent node to curr
		//		}

		return node.parent;
	}

	/**
	 * finds the dsnode object associated with d1 and d2 
	 * perform a union operation on them if they are not the same
	 * @param d1 
	 * @param d2
	 */
	public void union(E d1, E d2) {
		E a = findRep(d1);
		E b = findRep(d2);
		DSNode<E> tmp = set.get(a);
		DSNode<E> tmp2 = set.get(b);
		if(tmp.parent != tmp2.parent) {

			if(tmp.rank < tmp2.rank) {
				tmp.parent =  tmp2.parent;
				//tmp.rank += 1;
			}else if(tmp.rank > tmp2.rank){
				tmp2.parent =  tmp.parent;
				//tmp2.rank +=1;
			}else {
				tmp2.parent = tmp.parent;
				tmp.rank++;
			}
		}

	}
	public String toString() {
		String str = "";
		for(DSNode<E> n : set.values()) {
			str+= ("(" + n.data + " ," + n.parent + "," + n.rank +")");
		}
		return str;
	}
	private HashMap<E, DSNode<E>> set;

	public static void main(String[] args) {

		ArrayList<String> letters = new ArrayList<String>();
		letters.add("A");
		letters.add("B");
		letters.add("C");
		letters.add("D");
		letters.add("E");
		letters.add("F");
		letters.add("G");
		letters.add("H");

		DisjointSet<String> dset = new DisjointSet<String>(letters);

		System.out.println("DisjointSet with: " + letters);
		System.out.println(dset);

		System.out.println("\nunion('A', 'B')");
		dset.union("A", "B");
		System.out.println(dset);

		System.out.println("\nunion('B', 'C')");
		dset.union("B", "C");
		System.out.println(dset);

		System.out.println("\nunion('E', 'F')");
		dset.union("E", "F");
		System.out.println(dset);

		System.out.println("\nunion('G', 'H')");
		dset.union("G", "H");
		System.out.println(dset);

		System.out.println("\nunion('C', 'H')");
		dset.union("C", "H");
		System.out.println(dset);

		System.out.println("\nunion('G', 'F')");
		dset.union("G", "F");
		System.out.println(dset);

		System.out.println("\nunion('D', 'H')");
		dset.union("D", "H");
		System.out.println(dset);
	}

}
