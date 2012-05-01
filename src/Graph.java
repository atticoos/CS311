import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple class to represent an undirected graph G(V,E) 
 * with an adjacency list of edges
 * 
 * @author ajwhite
 */
public class Graph {
	private int 		V,E; // Vertices, Edges
	private boolean[][] adj; // adjacancy matrix of edges
	public int E() { return E; }
	public int V() { return V; }
	public void V(int V){ this.V = V; }
	
	
	/**
	 * Create a new graph with V verticies
	 * Initializes the adjacency matrix for all V
	 */
	public Graph (int V){
		this.V = V;
		this.E = 0;
		this.adj = new boolean[V][V];
		// initialize the adjacency list for each vertex to store an arraylist of the nodes it connects to
		for (int i=0; i<V; i++){
			for (int j=0; j<V; j++){
				adj[i][j] = false;
			}
		}
	}
	
	/**
	 * Returns the nodes (v) connected to the node (u)
	 */
	public List<Integer> adj(int u){
		List<Integer> adjList = new ArrayList<Integer>();
		for (int i=0; i<V; i++){
			if (adj[u][i]){
				adjList.add(i);
			}
		}
		return adjList;
	}

	/**
	 * Adds an edge between node u and v by adding v to the ArrayList mapped by u
	 * Increase the number of edges
	 */
	public void addEdge(int u, int v){
		adj[u][v] = true;
		adj[v][u] = true;
		E++;
	}
	
	/**
	 * Utility
	 */
	public void printAdjacencyList(){
		for (int u=0; u<V; u++){
			System.out.printf("%s-> [ ", u+1);
			for (int v : adj(u)){
				System.out.print(v+1 + " ");
			}
			System.out.print("]\n");
		}
	}
	
	
}
