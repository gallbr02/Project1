import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author Brandon Gallagher
 * vertex Specification for a graph
 */
public class Vertex {
	public Vertex(String label) {
		this.label = label;
		adj = new TreeMap<String, Edge>();
		id = currentid++;
		pred = null;
		cost = 0;
		visited = false;
	
	}
	/**
	 * resets this vertex for graph algorithms
	 */
	public void reset() {
		visited = false;
		cost = Double.POSITIVE_INFINITY;
		pred = null;
	}
	/**
	 * marks this vertex as processed
	 */
	public void mark() {
		visited = true;
	}
	/**
	 * marks this vertex as not prosessed
	 */
	public void unmark() {
		visited = false;
	}
	/**
	 * sets the cost of this vertex to c
	 * @param c what the vertex is being set to
	 */
	public void setCost(double c) {
		cost = c;
	}
	/**
	 * adds c to the current cost of this vertex path
	 * @param c that is being added to the vertex
	 */
	public void addCost(double c) {
		cost = cost +c;
	}
	/**
	 * returns whether this vertex is processed or not
	 * @return whether this vertex is processed or not
	 */
	public boolean isMarked() {
		return visited;
	}
	/**
	 * returns the nubmer of neighbors this vertex has
	 * @return the number of neighbors
	 */
	public int getAdjSize() {
		return adj.size();
	}
	/**
	 * return the adjacency list as a treemap of outgoing edges sorted by dst vertex label
	 * @return the list as a treemap
	 */
	public TreeMap<String, Edge> getAdj(){
		return adj;
	}
	/**
	 * returns the edge connecting this vertex to the vertex labeled dst, or return null 
	 * if no such edge exists
	 * @param dst that the vertex is being labeled to
	 * @return the edges connected to this vertex
	 */
	public Edge getAdj(String dst) {
		return adj.get(dst);
	}
	/**
	 * return the info of this vertex 
	 */
	public String toString() {
		String str = "";
		str+= label + " (" + id + "," + adj.size()+ "):";
		
		Set<String> setV = adj.keySet();
		Iterator<String> iter = setV.iterator();
		String label;
		while(iter.hasNext()) {
			label = iter.next();
			str+= label + " (" + adj.get(label).weight + ") ";
		}
		return str;
	}
	/**
	 * creates and adds a new edge with vdst and w to this vertex neighbor list.
	 * neighbors must be ordered in alphabetically increasing order of desention vertex labels
	 * @param vdst is a new neighbor
	 * @param w what is being added to the edge
	 */
	public void addAdj(Vertex vdst, double w) {
		adj.put(vdst.label, new Edge(this,vdst,w));
	}
	/**
	 * adds the given edge with to this vertexs neighbor list
	 * neighbors must be ordered in alphabetically increasing order of desention vertex
	 * @param e the given edge that is bing added
	 */
	public void addAdj(Edge e) {
		adj.put(e.dest.label, e);
	}

	protected int id;
	protected String label;
	protected Vertex pred;
	protected double cost;
	protected boolean visited;
	protected TreeMap<String,Edge> adj;
	private static int currentid;

}
