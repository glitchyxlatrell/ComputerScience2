import java.util.*;

public class BFS {

    static final int WHITE = 0;
    static final int GRAY = 1;
    static final int BLACK = 2;

    static class Graph {
        int[][] adjMatrix;
        int numVertices;
        int[] color;      // WHITE, GRAY, BLACK
        int[] distance;   // Distance from source
        int[] parent;     // Predecessor in BFS tree

        Graph(int[][] matrix) {
            this.adjMatrix = matrix;
            this.numVertices = matrix.length;
            this.color = new int[numVertices];
            this.distance = new int[numVertices];
            this.parent = new int[numVertices];
        }

        // Perform BFS from source vertex s
        void bfs(int s) {
            // Step 1: Initialize all nodes
            for (int u = 0; u < numVertices; u++) {
                if (u != s) {
                    color[u] = WHITE;
                    distance[u] = Integer.MAX_VALUE;
                    parent[u] = -1;
                }
            }

            // Step 2: Initialize source
            color[s] = GRAY;
            distance[s] = 0;
            parent[s] = -1;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(s);

            // Step 3: Standard BFS loop
            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (int v = 0; v < numVertices; v++) {
                    if (adjMatrix[u][v] == 1 && color[v] == WHITE) {
                        color[v] = GRAY;
                        distance[v] = distance[u] + 1;
                        parent[v] = u;
                        queue.offer(v);
                    }
                }

                color[u] = BLACK;
            }
        }

        // Utility to print BFS tree info
        void printBFSInfo() {
            System.out.println("Node | Distance | Parent");
            for (int i = 0; i < numVertices; i++) {
                System.out.printf("  %d  |    %2d     |   %s\n",
                        i,
                        distance[i] == Integer.MAX_VALUE ? -1 : distance[i],
                        parent[i] == -1 ? "None" : parent[i]);
            }
        }
    }

    public static void main(String[] args) {
        // int[][] matrix = {
        //     { 0, 1, 0, 1 },
        //     { 1, 0, 1, 1 },
        //     { 0, 1, 0, 1 },
        //     { 1, 1, 1, 0 }
        // };
        int[][] matrix = {
        {0, 1, 1, 0}, // Node 0 is connected to 1 and 2
        {1, 0, 0, 0}, // Node 1 is connected to 0
        {1, 0, 0, 1}, // Node 2 is connected to 0 and 3
        {0, 0, 1, 0}  // Node 3 is connected to 2
        };
        // 0 - 1
        // |
        // 2 - 3

        Graph graph = new Graph(matrix);
        int source = 2;

        System.out.println("Running BFS from source node: " + source);
        graph.bfs(source);
        graph.printBFSInfo();
    }
}
