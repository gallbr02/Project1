import java.util.Comparator;
/**
 * 
 * @author Brandon Gallagher
 *	 edge comparator
 */
public class EdgeLabelComp implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		return o1.dest.label.compareTo(o2.dest.label);
	}

}
