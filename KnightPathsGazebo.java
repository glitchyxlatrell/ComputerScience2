/*  Latrell Kong
    Shade Before Hydration: Guiding Knights Across Campus
    COP3503 Computer Science 2
    KnightPathsGazebo.java
*/

public class KnightPathsGazebo
{
    // nested class to implement graph in adjacency list representation
    public class Graph
    {
        // storing number of vertices and an array list of linked lists to store where they point to
        int numVertices;
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();

        // boolean lists to store which vertices are station, gazebos, or both
        boolean[] gazeboList;
        boolean[] stationList;

        // int to store start vertex
        int startVertex = -1;
        
        // constuctor for adj list
        public Graph(int n) {
            
            // storing num of vertices and creating boolean lists
            this.numVertices = n;
            gazeboList = new boolean[n + 1];
            stationList = new boolean[n + 1];

            // creating linked lists for every vertex
            for (int i = 0; i <= n; i++) 
            {
                adjacencyList.add(new LinkedList<Integer>());
            }
        }
    }

    // method to perform standard breadth-first search from starting vertex
    public void bfs(Graph list, int s, int[] distances, int[] predecessors)
    {
        // creating boolean list to track visited vertexes
        boolean[] visitVertex = new boolean[list.numVertices + 1];

        // for every vertex, defaulting distance to max, predecessor to -1, and visted to false
        for (int i = 1; i <= list.numVertices; i++) 
        {
            if (i != s) {
                distances[i] = Integer.MAX_VALUE;
                predecessors[i] = -1;
                visitVertex[i] = false;
            }
        }

        // setting all starting vertex values
        visitVertex[s] = true;
        distances[s] = 0;
        predecessors[s] = -1;

        // creating queue and inserting starting vertex
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);

        // while queue not empty
        while (!queue.isEmpty()) 
        {
            // getting first vertex of edge from queue
            int u = queue.poll();

            // creating linked list of all neighbors of vertex u
            LinkedList<Integer> possibleNext = list.adjacencyList.get(u);

            // parsing through all possible neighbors
            for(int i = 0; i < possibleNext.size(); i++)
            {
                // getting every second vertex of edge
                int v = possibleNext.get(i);

                // if not visited
                if(!visitVertex[v])
                {
                    // setting visited to true, updating distance, updating predecessor, and inserting second vertex into queue
                    visitVertex[v] = true;
                    distances[v] = distances[u] + 1;
                    predecessors[v] = u;
                    queue.offer(v);
                }
            }
        }
    }

    // method to construct graph from input file
    public Graph buildGraph(String input) throws FileNotFoundException
    {
        // scanning in input from file
        Scanner sc = new Scanner(new File(input));

        // taking in num of vertex and calling Graph constructor
        int n = sc.nextInt();
        Graph mapList = new Graph(n);

        // getting num of edges
        int m = sc.nextInt();
        int u = -1;
        int v = -1;

        // parsing through all edge lines and inputting them into adjacency list
        for(int i = 0; i < m; i++)
        {
            u = sc.nextInt();
            v = sc.nextInt();
            mapList.adjacencyList.get(u).add(v);
            mapList.adjacencyList.get(v).add(u);
        }

        // getting num of gazebos and their IDs
        int r = sc.nextInt();
        int gazeboID = -1;

        // inputting each gazeboID into list
        for(int i = 0; i < r; i++)
        {
            gazeboID = sc.nextInt();
            mapList.gazeboList[gazeboID] = true;
        }

        // getting num of stations and their IDs
        int k = sc.nextInt();
        int stationID = -1;

        // inputting each stationID into list
        for(int i = 0; i < k; i++)
        {
            stationID = sc.nextInt();
            mapList.stationList[stationID] = true;
        }

        // getting and storing starting vertex
        int s = sc.nextInt();
        mapList.startVertex = s;

        // closing file and returning graph
        sc.close();
        return mapList;
    }

}