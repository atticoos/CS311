import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch {
	private Graph G;
	private Queue<Integer> Q;
	private boolean[] visited;
	private int[] d;
	private int count;
	
	public BreadthFirstSearch(Graph G, int s){
		this.visited = new boolean[G.V()];
		this.count = 0;
		this.d = new int[G.V()];
		this.Q = new LinkedList<Integer>();
		this.G = G;
		visited[s] = true;
		d[s] = 0;
		Q.add(s);
		bfs(G);
	}
	
	public void bfs(Graph G){
		while(!Q.isEmpty()){
			int u = Q.poll();
			count++;
			for (Integer v : G.adj(u)){
				if(!visited[v]){
					visited[v] = true;
					d[v] = d[u] + 1;
					System.out.println("["+(u+1)+"] -> " + (v+1) + " (d:"+d[v]+")");
					Q.add(v);
				}
			}
		}
	}

	public boolean isConnected(){
		return count == G.V();
	}
	
	public void printSearch(){
		String output = "";
		for (int u=0; u<G.V(); u++){
			if (visited[u]){
				output += (u+1) + " ";
			}
		}
		System.out.println("BFS:");
		System.out.println(output);
		System.out.println(isConnected() ? "Fully Connected Graph" : "Incomplete Graph");
	}
}
