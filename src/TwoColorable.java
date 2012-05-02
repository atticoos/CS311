import java.util.LinkedList;

/**
 * Performs a depth first search and divides the graph into two colorable partitions
 * If the graph is not colorable, a substructure of the graph leading up to the colorable conflict is created
 * 
 * @author ajwhite
 */
public class TwoColorable {
	private boolean colorable = true;
	private boolean[] visited;	 // list of visited nodes
	private boolean[] partition; // colorable partition
	private Integer[] parent;    // list of visited nodes' parent
	//private Integer[] depth; 	 // nodes current depth
	private Cycle cycle;
	private Graph G;
	

	/**
	 * The cycle of the graph causing it to be uncolorable
	 * Created when u,v are in the same partition, generates the substructure
	 */
	class Cycle {
		private int u;
		private int v;
		private LinkedList<Integer> ancestry;
		
		Cycle (int u, int v){
			this.u = u; 
			this.v = v;
			build();
		}
		
		/**
		 * Finds the common path that u and v share and construct the ancestry selection
		 */
		public LinkedList<Integer> build(){
			Integer t = u;
			ancestry = new LinkedList<Integer>();
			ancestry.push(t);
			// builds the selected nodes within u's ancestry
			while((t=parent[t]) != null){
				ancestry.push(t);
			}
			
			
			// scan through v's ancestry
			// if u,v have a common ancestor, we have reached our substructure
			// remove v's uncommon ancestors to leave a remaining substructure
			t = v;
			while((t=parent[t]) != null){
				for (int i=0; i<ancestry.size(); i++){
					if (t == ancestry.get(t)){
						for (int j=0; j<i; j++){
							ancestry.pop();
						}
					}
				}
			}
			return ancestry;
		}
		
		public LinkedList<Integer> substructure(){ return ancestry; }
	}
	
	
	
	/**
	 * Initializes the meta information about the graph and performs
	 */
	public TwoColorable(Graph G){
		this.visited   = new boolean[G.V()];   
		this.partition = new boolean[G.V()];
		this.parent    = new Integer[G.V()];   	     
		//this.depth	   = new Integer[G.V()];
		this.G 		   = G;
		
		// intialize meta data
		for (int i=0; i<G.V(); i++){
			visited[i]   = false;
			partition[i] = false;
			parent[i]    = null;
			//depth[i]	 = 0;
		}
	}
	
	
	/**
	 * The DFS routine scans the graph visiting each unvisited vertex v adjacent to vertex u
	 * Per visit, the parent, visited flag, partition, and depth are updated
	 * 
	 */
	public void dfs(Graph G, int u){
		// current node is visited
		visited[u] = true;
		if (!colorable) return; // hard stop
		
		// visit adjacent vertices
		for(int v : G.adj(u)){
			if (!colorable) return; // hard stop
			
			if (!visited[v]){
				// track which node lead to v
				parent[v] = u;
				// mark v as visited
				visited[v] = true;
				// add v to the opposite partition of its parent (u)
				partition[v] = !partition[u];
				// one deeper than u
				// depth[v] = depth[u] + 1;
				
				//System.out.printf("[%s]-> %s  (c:%s, d:%s)\n", u+1, v+1, getColor(partition[v]), depth[v]);
				dfs(G, v);
			}
			
			// not colorable, u and v are within the same partition
			if(partition[u] == partition[v]){
				colorable = false;
				// creates the substructure
				cycle = new Cycle(u, v);
				return;
			}
		}
	}
	
	
	/**
	 * Performs the dfs across the graph
	 */
	public void analyze(){
		for (int i=0; i<G.V(); i++){
			if (!visited[i] || i==0){
				dfs(G, i);
			}
		}
	}
	
	
	/**
	 * Translates the graph into output
	 */
	public String toString(){
		StringBuilder output = new StringBuilder();
		// outputs the determination of the problem
		output.append(String.format("%s\n", colorable ? "Yes":"No"));

		// graph is colorable
		if (colorable){
			// outputs the results of the two partitions
			for (int i=0; i<G.V(); i++){
				output.append(String.format("%s, %s\n", i+1, getColor(partition[i])));
			}
		} 
		
		// graph is not colorable
		else {
			// output the substructure
			for(Integer n : cycle.substructure()){
				output.append(String.format("%s,%s\n", n+1, getColor(partition[n])));
			}
		}
		return output.toString();
	}
	

	/**
	 * Performs a verification on the results of the search
	 * This scans through each entry in the adjacency list and detects
	 * if any same-partition conflicts occur for each vertex u and its adjacent verticies
	 */
	public boolean verifyGraph(){
		boolean verified = true;
		for(int u=0; u<G.V(); u++){
			for(int v : G.adj(u)){
				if (partition[u] == partition[v]){
					verified = (colorable==false);
				}
			}
		}
		if (verified){
			System.out.println("Graph verification: GRAPH IS VALID - colorable verification has succeeded");
		} else {
			System.out.println("Graph verification: GRAPH IS INVALID - colorable verification has failed");
		}
		return verified;
	}
	

	/**
	 * Returns the readable partition color
	 */
	private String getColor(boolean color){
		return color ? "B" : "R";
	}
	
}
