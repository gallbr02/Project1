import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author Brandon gallagher
 * Representation of a graph
 */
public class GraphUtil {
	/**
	 * lists the vertices visited by the breadth first search traversal alg on the graph g from the src vertex
	 * @param g the graph of traversal algorithm
	 * @param src the vertex 
	 */
	public static void bfs(Graph g, String src) {
		Queue<Vertex> q = new LinkedList<Vertex>();
		//Vertex svrc = new Vertex(src);
		Vertex vsrc = g.getVertex(src);
		//Vertex u = q.poll();
		q.add(vsrc);
		vsrc.mark();
		String str = "bfs" + "(" + vsrc.label + ")" + vsrc.label + " ";
		while(!q.isEmpty()) {
			Vertex u = q.poll();
			LinkedList<Vertex> travers = new LinkedList<Vertex>();
			travers.add(u);
			Set<String> neighbors = u.adj.keySet();
			for(String s : neighbors) {
				Vertex n = g.getVertex(s);
				
				if(!n.visited) {
					str += s + "-";
					n.mark();
					q.add(n);
				}
			}
		}
		System.out.println(str + "done");
		g.resetVerts();
	}
	/**
	 *lists the vertices visited by the depth first search traversal alg on the graph g from the src vertex
	 * @param g the graph of traversal algorith
	 * @param src the vertex
	 */
	public static void dfs(Graph g, String src) {
		g.resetVerts();
		System.out.println("dfs(" + src + ")");
		Vertex vsrc = g.getVertex(src);
		dfs2(vsrc);
	}

	/**
	 * Helper to original dfs mehthod
	 * @param src source vertex
	 */
	private static void dfs2(Vertex src) {
		if(!src.isMarked()) {
			System.out.println(src.label + "-");
			src.mark();
			for(Edge w : src.adj.values()) {
				if(!w.dest.isMarked()) {
					dfs2(w.dest);
				}
			}
		}
	}
	/**
	 * applies the dijkstra shortest path alg to the graph g from the src vertex
	 * prints the paths from src to each vertex in the graph g using printPath
	 * @param g the graph 
	 * @param src the vertex
	 */
	public static void dijkstra(Graph g, String src) {
		g.resetVerts();
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		String str = "dijkstra's single source shortest path from " + src;
		list.addAll(g.verts);
		Vertex vsrc = g.getVertex(src);
		vsrc.cost = 0;
		while (!list.isEmpty()) {
			Vertex vert = removeMin(list);
			vert.mark();
			Set<String> neighbors = vert.adj.keySet();
			System.out.println(neighbors);
			for (String s : neighbors) {
				Vertex v = g.getVertex(s);
				double weight = g.getEdge(vert, v).weight;
				if (!v.isMarked() && v.cost > vert.cost + weight) {
					v.cost = vert.cost + weight;
					v.pred = vert;
				}
			}
		}
		System.out.println(str);
	}
	
	/**
	 * Removes the minimum value
	 * @param list list of vertecies
	 * @return the removed vertex
	 */
	public static Vertex removeMin(ArrayList<Vertex> list) {
		double min;
		Vertex start = list.get(0);
		min = start.cost;
		int i = 0;
		int hold = i;
		for (Vertex v : list) {
			if (v.cost < min) {
				min = v.cost;
				hold = i;
			}
			++i;
		}
		return list.remove(hold);
	}
	/**
	 * Prints the path from src to dst assuming a path algorithm had been applied and vertices have proper information
	 * @param g the graph
	 * @param src the vertex
	 * @param dst the path
	 */
	public static void printPath(Graph g, String src, String dst) {
		Vertex vsrc = g.getVertex(src);
		Vertex vdst = g.getVertex(dst);
		String str = src + "-->" + dst + ": ";
		double total = 0.0;
		total += vdst.cost;
		str += " (" + total + ") ";
		Vertex dest = vdst;
		Stack<String> stack = new Stack<String>();
		str += src + "-";
		while (dest != vsrc && dest != null) {
			stack.push(dest.label);
			dest = dest.pred;
		}
		while (!stack.isEmpty()) {
			str += stack.pop() + "-";
		}
		if (dest != null && total != Double.POSITIVE_INFINITY && vsrc != vdst) {
			System.out.println(str + "(done)");
		}
		else if (vsrc != vdst) {
			System.out.println(src + "-->" + dst + ": no path");
		}
		
	}
	
	
	public static void main (String[] args) {
		Graph g1 = new Graph("g1_labels", "g1_w");
		bfs(g1,"A");
		bfs(g1,"B");
		bfs(g1,"C");
		bfs(g1,"D");
		bfs(g1,"E");
		bfs(g1,"F");
		bfs(g1,"G");
		bfs(g1,"H");
		bfs(g1,"I");
		System.out.println("bfs" +g1);
		
		dfs(g1,"A");
		dfs(g1,"B");
		dfs(g1,"C");
		dfs(g1,"D");
		dfs(g1,"E");
		dfs(g1,"F");
		dfs(g1,"G");
		dfs(g1,"H");
		dfs(g1,"I");
		System.out.println("dfs" + g1);

	}



}
