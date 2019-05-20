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
		this(vsrc, vdst,1);
	}

	/**
	 * overrides the equal methos in object class and returns true if obj is edge
	 * and its source and destination labels match the labels of this edge source and destination
	 */
	public boolean equals(Object obj) {
		return source.equals(obj) && dest.equals(dest); 
		//((Edge)obj).source && dest == ((Edge)obj).dest;  
	}

	/**
	 * return the info of this edge 
	 */
	public String toString() {
		String str = "";
		str +=source.label + "-" + dest.label + "-" + weight;
		return str;
	}
	/**
	 * Sets the weight of this Edge to w
	 * @param w setting the weight
	 */
	public void setWeight(double w) {
		
	}
	protected Vertex source;
	protected Vertex dest;
	protected double weight;
}
