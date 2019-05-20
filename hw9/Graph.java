import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Brandon Gallagher
 * representation of a graph
 */
public class Graph {
	/**
	 * reads vertices from a text file named vFile and sets up the vertices for this graph
	 * @param vfile contains a list of labels seperated by a single space
	 * @param efile conatins an adjacency matrix of weighs; 
	 * @throws FileNotFoundException 
	 */
	public Graph(String vfile, String efile)  {
		verts = new ArrayList<Vertex>();
		File v = new File(vfile);

		try {
			Scanner	vInput = new Scanner(v);
			while(vInput.hasNext()) {
				Vertex vertex = new Vertex(vInput.next());
				verts.add(vertex);
			}
			vInput.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(vfile + " Problem");
			System.exit(1);
		}

		adjmat = new double[vfile.length()][vfile.length()];
		edges = new ArrayList<Edge>();

		File e = new File(efile);

		try {
			Scanner	eInput = new Scanner(e);
			for(int i = 0; i < vfile.length(); i++){
				for(int j = 0; j < vfile.length(); j++) {
					adjmat[i][j] = eInput.nextDouble();

					if(adjmat[i][j] != 0) {
						Edge ed = new Edge(verts.get(i), verts.get(j), adjmat[i][j]);

						edges.add(ed);

						verts.get(i).addAdj(verts.get(j),adjmat[i][j]);
					}
				}	
			}
			eInput.close();
		}
		catch(FileNotFoundException f) {
			System.out.println(efile + " Problem");
			System.exit(1);
		}
	}
	/**
	 * resets predecessor, cost, and visited fields of all vertices in this graph
	 */
	public void resetVerts() {
		for(Vertex v : verts) {
			v.reset();
		}		
	}
	/**
	 * return the list of vertices
	 * @return the list of vertices
	 */
	public ArrayList<Vertex> getVerts(){
		return verts;
	}
	/**
	 * returns the number of vertices in this graph
	 * @return the number of vertices
	 */
	public int nVerts() {
		return verts.size();

	}
	/**
	 * returns the vertex associated with label. return null if no vertex in this graph
	 * @param label that is associated with vertex
	 * @return the vertex associated with label
	 */
	public Vertex getVertex(String label) {
		for(Vertex v : verts) {
			if(v.label.equals(label)) {
				
				return v;
			}
			// should i use maplabel?
		}
		return null;
	}
	/**
	 * retruns the index-th vertex. returns null if index is invalid
	 * @param index that is being returned
	 * @return the index
	 */
	public Vertex getVertex(int index) {
		return verts.get(index);
	}
	/**
	 * returns the edge from vsrc to vdst. Returns null if no edge exists from vsrc to vdst
	 * @param vsrc to vdst
	 * @param vdst that is being returned from vsrc
	 * @return the edge
	 */
	public Edge getEdge(Vertex vsrc, Vertex vdst) {
		for(Edge e : edges) {
			if(e.source.equals(vsrc) && e.dest.equals(vdst));
			return e;
		}
		return null;
	}
	/**
	 * returns the edge from src to dst. Returns null if no edge exists from src to dst
	 * @param src to dst
	 * @param dst that is being returned from src
	 * @return the edge
	 */
	public Edge getEdge(String src, String dst) {
		Vertex sObj =getVertex(src);
		if(sObj.getAdj(dst) == null) {
			return null;
		}else {
			return sObj.getAdj(dst);
		}
			
	}
	/**
	 * returns the adjacency matrix of this graph
	 * @return the adjacency matrix of this graph
	 */
	public double[][] adjMatrix() {
		return adjmat;

	}
	/**
	 * returns the info of this graph in String format
	 */
	public String toString() {
		String str = "[Graph w/ " + verts.size() + " Vertices]" + "\n";
		
		for(int i = 0; i < verts.size(); i++) {
			Vertex ptr = verts.get(i);
			str+= ptr.toString() + "\n";
		}
		return str;
	}
	public static void main (String[] args) {
		Graph g = new Graph("g1_labels", "g1_w");
		System.out.println(g.toString());
	}
	protected ArrayList<Vertex> verts;
	protected double[][] adjmat;
	protected HashMap<String, Vertex> mapLabel;
	protected ArrayList<Edge> edges;

}
