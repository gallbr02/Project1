/**
 * 
 * @author Brandon gallagher
 * Edge class in the representation of a graph
 */
public class Edge {

	/**
	 * instantices a new edge with vsrc as source vertex
	 * @param vsrc source vertex
	 * @param vdst destination vertex
	 * @param w weight
	 */
	public Edge(Vertex vsrc, Vertex vdst, double w) {
		source = vsrc;
		dest = vdst;
		weight = w;
	}

	/**
	 * instantices a new edge with vsrc as source vertex
	 * @param vsrc source vertex
	 * @param vdst destination vertex
	 */
	public Edge(Vertex vsrc, Vertex vdst) {
		source = vsrc;
		dest = vdst;
		weight = 1;
	}

	/**
	 * overrides the equal methos in object class and returns true if obj is edge
	 * and its source and destination labels match the labels of this edge source and destination
	 */
	public boolean equals(Object obj) {
		return source == ((Edge)obj).source && dest == ((Edge)obj).dest;  
	}

	/**
	 * return the info of this edge 
	 */
	public String toString() {

		return source.label + "-" + dest.label + "-" + weight;
	}
	protected Vertex source;
	protected Vertex dest;
	protected double weight;
}
