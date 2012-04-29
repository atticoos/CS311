import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class App {
	
	private Graph generateGraph(String file){
		Graph graph;
		List<int[]> edges = new ArrayList<int[]>();
		int size = 0;
		try {
		    String str;
		    boolean firstLine = true;		    
		    BufferedReader in = new BufferedReader(new FileReader(file));
		    while ((str = in.readLine()) != null) {
		    	if (firstLine){ 
		    		size = Integer.valueOf(str);
		    		firstLine = false;
		    		continue;
		    	}
		    	String[] values = str.split(",");
		    	edges.add(new int[]{Integer.valueOf(values[0]), Integer.valueOf(values[1])});
		    }
		    in.close();
		} catch (IOException e) { }

		graph = new Graph(size);
		
	    
	    for (int[] edge : edges){
	    	int v1 = edge[0]-1;
	    	int v2 = edge[1]-1;
	    	graph.addEdge(v1, v2);
	    }
	    //graph.printGraph();
	    System.out.println();
	    DepthFirstSearch DFS = new DepthFirstSearch(graph, 0);
	    DFS.printSearch();
	    
	    System.out.println("BFS");
	    BreadthFirstSearch BFS = new BreadthFirstSearch(graph, 0);
	    BFS.printSearch();
	    
	    TwoColorable BP = new TwoColorable(graph);
	    return graph; 
	}

	public static void main(String[] args) {
		String[] files = {"smallgraph.txt", "largegraph1.txt", "largegraph2.txt"};
		App app = new App();
		app.generateGraph(files[2]);
	}
	
}
