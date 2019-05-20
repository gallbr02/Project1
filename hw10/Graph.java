import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

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
		adjmat = new double[vfile.length()][vfile.length()];
		edges = new ArrayList<Edge>();
		mapLabel = new HashMap<String, Vertex>();
		
		 try {
	            Scanner fin = new Scanner(new File(vfile));
	            while (fin.hasNext()) {
	                String label = fin.next();
	                Vertex v = new Vertex(label);
	                verts.add(v);
	                System.out.println(v.label);
	                mapLabel.put(label, v);
	            }
	        }
	        catch(FileNotFoundException e1) {
	            System.err.println("Graph constructor: " + vfile + " not found");
	            e1.printStackTrace();
	        }
	        catch(Exception e2) {
	            System.err.println("Graph constructor: error reading vertex labels from " + vfile);
	            e2.printStackTrace();
	        }
	        try {
	            Scanner fin = new Scanner(new File(efile));
	            System.out.println("Verts.size" + verts);
	            int n = verts.size();
	            adjmat = new double[n][n];
	            for (int r = 0; r < n; ++r) {
	                Vertex vr = verts.get(r);
	                for (int c = 0; c < n; ++c) {
	                    double w = fin.nextDouble();
	                    if (w != 0) {
	                        Vertex vc = verts.get(c);
	                        Edge ed = new Edge(vr, vc, w);
	                        vr.addAdj(ed);
	                        edges.add(ed);
	                        adjmat[r][c] = w;
	                    }
	                    else {
	                        adjmat[r][c] = Double.POSITIVE_INFINITY;
	                    }
	                }
	            }
	        }
	        catch(FileNotFoundException e3) {
	            System.err.println("Graph constructor: " + efile + " not found");
	            e3.printStackTrace();
	        }
	        catch(Exception e4) {
	            System.err.println("Graph constructor: error reading edge info from "  +  efile);
	            e4.printStackTrace();
	        }
		
		
//		verts = new ArrayList<Vertex>();
//		File v = new File(vfile);
//
//		try {
//			Scanner	vInput = new Scanner(v);
//			while(vInput.hasNext()) {
//				Vertex vertex = new Vertex(vInput.next());
//				verts.add(vertex);
//			}
//			vInput.close();
//		}
//		catch(FileNotFoundException e) {
//			System.out.println(vfile + " Problem");
//			System.exit(1);
//		}
//
//		adjmat = new double[vfile.length()][vfile.length()];
//		edges = new ArrayList<Edge>();
//
//		File e = new File(efile);
//
//		try {
//			Scanner	eInput = new Scanner(e);
//			for(int i = 0; i < vfile.length(); i++){
//				for(int j = 0; j < vfile.length(); j++) {
//					adjmat[i][j] = eInput.nextDouble();
//
//					if(adjmat[i][j] != 0) {
//						Edge ed = new Edge(verts.get(i), verts.get(j), adjmat[i][j]);
//
//						edges.add(ed);
//
//						verts.get(i).addAdj(verts.get(j),adjmat[i][j]);
//					}
//				}	
//			}
//			eInput.close();
//		}
//		catch(FileNotFoundException f) {
//			System.out.println(efile + " Problem");
//			System.exit(1);
//		}
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
		return mapLabel.get(label);
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
		return getEdge(vsrc.label, vdst.label);
//		for(Edge e : edges) {
//			if(e.source.equals(vsrc) && e.dest.equals(vdst));
//			return e;
//		}
//		return null;
	}
	/**
	 * returns the edge from src to dst. Returns null if no edge exists from src to dst
	 * @param src to dst
	 * @param dst that is being returned from src
	 * @return the edge
	 */
	public Edge getEdge(String src, String dst) {
		Vertex vs = mapLabel.get(src);
		if(vs != null) {
			return vs.getAdj().get(dst);
		}
		return null;
			
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
	/**
	 * returns the number of edges in this graph
	 * @return the number of edges in this graph
	 */
	public int nEdges() {
		return edges.size();
	}
	/**
	 * EdgeComp.java
	 * comparator for edge weights first then edge labels
	 */

	private class EdgeComp implements Comparator<Edge> {
	    public int compare(Edge e1, Edge e2) {
	        int diff = (int) (Math.round((e1.weight - e2.weight) * 100)) / 100;

	        // if weights of the two edges are the same, compare the edge labels
	        if (diff == 0) {
	            String eLabel1 = e1.source.label + e1.dest.label;
	            String eLabel2 = e2.source.label + e2.dest.label;

	            return eLabel1.compareTo(eLabel2);
	        }
	        else {
	            return diff;
	        }
	    }
	}

	/**
	 * (to be used for Kruskal's algorithm)
	 * internally convert directed graph to undirected graph 
	 * and return the edges sorted by weight and label in increasing order
	 *
	 * @return list of edges sorted by weight first then by edge label in increasing order
	 */
	public ArrayList<Edge> getEdgesUndirected() {
	    ArrayList<Edge> und = new ArrayList<Edge>();
	    EdgeComp edgeComp = new EdgeComp();

	    for (Edge e : edges) {
	        Vertex src = e.source;
	        Vertex dst = e.dest;
	        double w   = e.weight;

	        // each edge's src vertex is < dst vertex in 
			// undirected graph for easier comparison
	        String srcLabel = src.label;
	        String dstLabel = dst.label;

	        if (srcLabel.compareTo(dstLabel) > 0) {
	            Vertex tmp = src;
	            src = dst;
	            dst = tmp;
	        }

	        Edge ne = new Edge(src, dst, w);

	        // similar to one iteration of the insertion sort algorithm
	        // add the newly created edge to a correct location according 
			// to the sort order
	        int i = 0, eCount = und.size();
	        while (i < eCount && edgeComp.compare(ne, und.get(i)) > 0) {
	            ++i;
	        }
	        und.add(i, ne);
	    }

	    return und;
	}

	/**
	 * (to be used before applying Prim's algorithm)
	 * for each directed edge (v->u) add edge (u->v) if it doesn't exist
	 * if it does, update edge (u->v)'s weight to the weight of edge (v->u)
	 * update adjacency matrix accordingly
	 */
	public void toUndirected() {
		for (Vertex v : verts) {
			int vID = v.id;
			TreeMap v_adj = v.getAdj();

			// for each edge (v->u)
			for (Edge e : v.adj.values()) {
				Vertex u = e.dest;
				double w = e.weight;
				int uID  = u.id;

				// add v as neighbor to dst if it isn't already a neighbor
				// edge (u->v)
				if (u.getAdj(v.label) == null) {
					Edge newE = new Edge(u, v, w);
					u.addAdj(newE);
					edges.add(newE);
				}
				// if edge (u->v) already exists, need to update it with the same weight
				else {
					Edge oldE = u.getAdj(v.label);
					oldE.weight = w;
				}

				// update the adjacency matrix accordingly
				adjmat[uID][vID] = w;
			}
		}
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
