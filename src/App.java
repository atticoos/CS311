import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class App {
	private final String path;
	private final String filename;
	
	/**
	 * Initializes and runs the program
	 * - Generates the graph
	 * - Performs the TwoColorable search
	 * - Verifies the results of the search
	 * - Serializes and outputs the results
	 * 
	 */
	public App(String path, String filename, boolean verify){
		this.path = path;
		this.filename = filename;
		Graph G = generateGraph();
		TwoColorable colorable = new TwoColorable(G);
		colorable.analyze();
		System.out.println(colorable);
		writeGraph(colorable);
		
		if (verify){
			colorable.verifyGraph();
		}
	}
	
	/**
	 * Writes the serialized result from TwoColorable
	 */
	public void writeGraph(TwoColorable colorable){
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output_" + filename));
			writer.write(colorable.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the serialized graph into a graph with vertices and edges
	 */
	private Graph generateGraph(){
		Graph graph = null;

		try {
		    String str;
		    BufferedReader in = new BufferedReader(new FileReader(path + filename));
		   
		    // the number of vertices
		    int size = Integer.valueOf(in.readLine());
		    // initializes a graph with V the length of size
		    graph = new Graph(size);
		    
		    // reads the serialized edges
		    while ((str = in.readLine()) != null) {
		    	// format: vertex1,vertex2
		    	String[] values = str.split(","); 
		    	// adds an edge to the graph
		    	graph.addEdge(Integer.valueOf(values[0])-1, Integer.valueOf(values[1])-1);
		    }
		    in.close();

		} catch (IOException e) { }

		if (graph == null){
			System.err.println("Error occured when building graph");
		}
		return graph;
	}

	public static void main(String[] args) {
		String path;
		String filename;
		boolean verify = false;
		
		if (args.length == 0){
			filename = "largegraph3.txt";
			path = "/Users/ajwhite/Desktop/";
			//System.err.println("Please enter a graph file");
			//return;
		} else {
			path = "/courses/cs300/cs311/cs311/311_ProG1_INPUT/";
			filename = args[0];
			if (args.length > 1 && args[1] == "verify"){
				verify = true;
			}
		}
		
		App app = new App(path, filename, verify);
	}
	
}
