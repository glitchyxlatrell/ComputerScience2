//COP3503 Computer Science 2
//Graph Adjacency List Implementation

import java.util.LinkedList;
import java.util.ArrayList;

// Helper Class representing an edge in the graph
class Edge {
	int from;    // Source vertex
	int to;      // Destination vertex
	int weight;    // Weight of the edge
	
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}

// Class representing the graph using adjacency list
public class GraphAdjacencyList {
	int numVertices; // Number of vertices in the graph
	ArrayList<LinkedList<Edge>> adjacencyList = new ArrayList<LinkedList<Edge>>(); // Adjacency list
	
	// Constructor: initializes the graph with the given number of vertices
	public GraphAdjacencyList(int numVertices) {
	    // Store the total number of vertices in the graph
	    this.numVertices = numVertices;

	    // For each vertex, we need to create an empty list to store its outgoing edges
	    // Think of it as creating a "bucket" (linked list) for each vertex to hold its neighbors
	    for (int i = 0; i < numVertices; ++i) {
	        // Add an empty LinkedList to the adjacency list for this vertex
	        // This prepares the graph to hold connections from this vertex
	        adjacencyList.add(new LinkedList<Edge>());
	    }
	}

	
	// Method to add a directed, weighted edge to the graph
	public void addEdge(int from, int to, int weight) {
	    // Step 1: Create a new Edge object that represents a connection
	    // from the 'from' node to the 'to' node with the given 'weight'
	    Edge edge = new Edge(from, to, weight);

	    // Step 2: Get the list of edges (adjacent nodes) for the 'from' node.
	    // This is like grabbing the "bucket" where we store all neighbors of this node.
	    LinkedList<Edge> edges = adjacencyList.get(from);

	    // Step 3: Add the new edge to the beginning of the list.
	    // This means we’re inserting it right at the front for quick access.
	    edges.addFirst(edge);
}

	// Method to display the graph
	public void display() {
		for (int i = 0; i < numVertices; ++i) {
			System.out.print("Node " + i + " connects to: ");
			LinkedList<Edge> edges = adjacencyList.get(i);
			if (!edges.isEmpty()) {
				for (Edge edge : edges) {
					System.out.print(toString(edge) + " ---> ");
				}
			}
			System.out.println("NULL");
			System.out.println();	
		} 	
	}
	
	// Helper method to return string representation of an edge's destination
	public String toString(Edge edge) {
		return String.valueOf(edge.to);
	}
	
	// Method to check if there is a connection between two vertices
	public boolean isConnected(int from, int to) {
		// Travers the linked list - we need a temp pointer (edges) to get to the head
		// of the linked list.
		LinkedList<Edge> edges = adjacencyList.get(from);
		for (Edge edge : edges) {
			if (edge.to == to)
				return true; // Found a connection
		}
		return false; // No connection found
	}
	
	// Main method to test the graph
	public static void main(String[] args) {
		GraphAdjacencyList myGraph = new GraphAdjacencyList(6);
		
		// Add some edges to the graph
		myGraph.addEdge(0, 2, 3);
		myGraph.addEdge(0, 5, 8);
		myGraph.addEdge(2, 3, 4);
		myGraph.addEdge(3, 1, 5);
		myGraph.addEdge(4, 5, 6);
		
		// Display the graph
		myGraph.display();
		
		// Check if vertices 0 and 2 are connected
		if (myGraph.isConnected(0, 2)) {
			System.out.println("Node 0 is directly connected to node 2.");
		} else {
			System.out.println("Node 0 is NOT directly connected to node 2.");
		}
		
		// Check if vertices 2 and 5 are connected
		if (myGraph.isConnected(2, 5)) {
			System.out.println("Node 2 is directly connected to node 5.");
		} else {
			System.out.println("Node 2 is NOT directly connected to node 5.");
		}
	}
}
