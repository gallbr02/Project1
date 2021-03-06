import java.util.Comparator;
/**
 * 
 * @author Brandon gallagher
 * vertex comparator
 */
public class VertexCostComp implements Comparator<Vertex>{

	@Override
	public int compare(Vertex o1, Vertex o2) {
		
		return (int)(o1.cost - o2.cost);
	}

}
