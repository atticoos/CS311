
public class DepthFirstSearch {
	private boolean[] visited;
	private int[] d;
	private int count;
	private Graph G;
	
	public DepthFirstSearch(Graph G, int s){
		this.G = G;
		this.visited = new boolean[G.V()];
		this.d = new int[G.V()];
		this.count = 0;
		d[s] = 0;
		dfs(G, s);
	}
	
	public void dfs(Graph G, int u){
		count++;
		visited[u] = true;
		
		//System.out.println("[" + (u+1) + "]");
		for (Integer v : G.adj(u)){
			if(!visited[v]){
				d[v] = d[u] + 1;
				//System.out.println("["+(u+1)+"] -> " + (v+1));
				System.out.println("["+(u+1)+"] -> " + (v+1) + " (d:"+d[v]+")");
				dfs(G, v);
			}
		}
	}
	
	public boolean visited(int u){
		return visited[u];
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
		System.out.println("DFS:");
		System.out.println(output);
		System.out.println(isConnected() ? "Fully Connected Graph" : "Incomplete Graph");
	}
	
}
