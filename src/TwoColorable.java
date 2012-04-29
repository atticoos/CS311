import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class TwoColorable {
	private boolean[] visited;	 // track node visits
	private boolean[] partition; // true = left, false = right (red, blue)
	private int[] parent;		 // track which node lead to the current one (node's pi value)
	private Stack<Integer> oddCycle;
	private boolean twoColorable = true;
	
	public TwoColorable(Graph G){
		this.visited = new boolean[G.V()];   
		this.partition = new boolean[G.V()];
		this.parent = new int[G.V()];   	     
		this.oddCycle = new Stack<Integer>();// odd cycle substructure
		
		// intialize meta data
		for (int i=1; i<G.V(); i++){
			visited[i] = false;
			partition[i] = false;
		}
		
		// perform search
		for (int i=0; i<G.V(); i++){
			if (!visited[i] || i==0){
				dfs(G, i);
			}
		}
		
		
		printTree(G);
	}
	
	public void dfs(Graph G, int u){
		// current node is visited
		visited[u] = true;
		// loop over connected nodes
		for(int v : G.adj(u)){
			if (!twoColorable) return;
			System.out.println("["+(u+1)+"] -> " + (v+1));
			// unvisited node connected to u
			if (!visited[v]){
				// track which node lead to v
				parent[v] = u;
				// mark v as visited
				visited[v] = true;
				// add v to the opposite partition of its parent (u)
				partition[v] = !partition[u];
				dfs(G, v);
				//System.out.println((v+1) + " " + getColor(partition[v]) + "(" + partition[v] + ")");
				System.out.println("["+(u+1)+"] -> " + (v+1) + " (color: " + getColor(partition[v]) + ")");
			} 
			// non 2-colorable detection, parent and child nodes are in the same partition (color)
			else if(partition[u] == partition[v]){
				twoColorable = false;
				int n = u;
				// find all nodes connecting to the culprit
				oddCycle.push(v);
				while(n != v && n != 0){
					n = parent[n];
					oddCycle.push(n);
					System.out.println("ODD CYCLE: " + n);
				}
				return;
			}
		}
	}
	
	public String getColor(boolean color){
		return color ? "B" : "R";
	}
	
	public void printTree(Graph G){
		if (twoColorable){
			System.out.println("-----");
			for (int i=0; i<G.V(); i++){
				System.out.println((i+1) + " -> " + getColor(partition[i]));
				
			}
		} else {
			System.out.println("Odd-number cycle:");
			while(!oddCycle.empty()){
				System.out.println(oddCycle.pop());
			}
		}
	}
	
}

/*
 ALGORITHM: BIPARTITE (G, S)

For each vertex u in V[G] − {s}
   do color[u] ← WHITE
        d[u] ← ∞
        partition[u] ← 0
color[s] ← GRAY
partition[s] ← 1
d[s] ← 0
Q ← [s]
while Queue 'Q' is non-empty
      do u ← head [Q]
         for each v in Adj[u] do
                if partition [u] ← partition [v]
                   then   return 0
                 else
                       if color[v] ← WHITE then
                            then color[v] ← gray
                                   d[v] = d[u] + 1
                                   partition[v] ← 3 − partition[u]
                                   ENQUEUE (Q, v)
           DEQUEUE (Q)
Color[u] ← BLACK
Return 1

*/