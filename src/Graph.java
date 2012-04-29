import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to represent a graph G(V,E) 
 * with an adjacency list of edges
 * 
 * @author ajwhite
 */
public class Graph {
	private int V, E; //Vertices, Edges
	private List<Integer>[] adj; // adjacency list - [u][v1, v2,...,vn] (u has an edge to to [v...vn])
	
	public Graph (int V){
		this.V = V;
		this.E = 0;
		this.adj = new ArrayList[V];
		
		// initialize the adjacency list for each vertex to store an arraylist of the nodes it connects to
		for (int i=0; i<V; i++){
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	// number of nodes
	public int V() { return V; }
	// number of edges
	public int E() { return E; }
	
	/**
	 * Returns the nodes (v) connected to the node (u)
	 */
	public List<Integer> adj(int u){
		return adj[u];
	}

	
	/**
	 * Adds an edge between node u and v by adding v to the ArrayList mapped by u
	 * Increase the number of edges
	 */
	public void addEdge(int u, int v){
		adj[u].add(v);
		E++;
	}
	
	public void printGraph(){
		System.out.println(V + " verticies, " + E+ " edges");
		for (int u=0; u<V; u++){
			String output = (u+1) + ": ";
			for (Integer v : adj(u)){
				output += (v+1) + " ";
			}
			System.out.println(output);
		}
	}
}
