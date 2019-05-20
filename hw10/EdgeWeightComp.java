import java.util.Comparator;

/**
 * 
 * @author Brandon gallagher
 * Edge comparator
 */
public class EdgeWeightComp implements Comparator<Edge> {

	@Override
	public int compare(Edge o1, Edge o2) {
		return (int) (o1.weight - o2.weight);
	}

}
