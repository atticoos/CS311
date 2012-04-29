import java.util.LinkedList;
import java.util.Queue;


public class Bipartite {
	private boolean[] visited;
	private int[] partition;
	private int[] d;
	private Queue<Integer> Q;
	
	public Bipartite(Graph G){
		this.visited = new boolean[G.V()];
		this.partition = new int[G.V()];
		this.d = new int[G.V()];
		this.Q = new LinkedList<Integer>();
		
		for (int i=1; i<G.V(); i++){
			visited[i] = false;
			d[i] = 0;
			partition[i] = 0;
		}
		int s = 0;
		
		visited[s] = true;
		partition[s] = 1;
		d[s]  = 0;
		Q.add(s);
		System.out.println(s+1 + " " + getColor(partition[s]));
		run(G);
		
	}
	
	public String getColor(int i){
		return i==1 ? "B" : "R";
	}
	
	public boolean run(Graph G){

		while(!Q.isEmpty()){
			Integer u = Q.peek();
			for (Integer v : G.adj(u)){
				
				if (partition[u] == partition[v]){
					System.out.println("NOT BIPARTITE");
					return false;
				} else {
					if (!visited[v]){
						visited[v] = true;
						partition[v] = 3-partition[u];
						System.out.println((v+1) + " " + getColor(partition[v]));
						//System.out.println("["+(u+1)+"] -> " + (v+1) + " p:" + partition[v]);
						Q.add(v);
					}
				}
			}
			Q.poll();
		}
		return true;
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