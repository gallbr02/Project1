
public class GraphTest {

	 public static void main(String[] args) {

		 Graph g1 = new Graph("g1_labels", "g1_w");
		 
		 System.out.println(g1.getVerts());
		 
		 for(int i = 0; i < g1.adjMatrix().length; i++ ) {
			 for(int j = 0; j < g1.adjMatrix().length; j++) {
				 System.out.print(g1.adjMatrix()[i][j] + " ");
			 }
			 System.out.println();
		 }
		 
		 System.out.println();
		 System.out.println(g1.getVertex(0));
		 System.out.println(g1.getEdge(g1.getVertex(0), g1.getVertex(1)));
		
	    }
}
