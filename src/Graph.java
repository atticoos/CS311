import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to represent a graph G(V,E) 
 * with an adjacency list of edges
 * 
 * @author ajwhite
 */
public class Graph {
	private int V, E;
	private List<Integer>[] adj;
	
	public Graph (int V){
		this.V = V;
		this.E = 0;
		this.adj = new ArrayList[V];
		
		// initialize the adjacency list for each vertex to store an arraylist of the nodes it connects to
		for (int i=0; i<V; i++){
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	public int V() { return V; }
	public int E() { return E; }
	
	/**
	 * Returns the nodes (v) connected to the node (u)
	 */
	public List<Integer> adj(int u){
		return adj[u];
	}

	
	public void addEdge(int u, int v){
		adj[u].add(v);
		adj[v].add(u); // undirected graph
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
