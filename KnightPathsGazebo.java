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

    // method that returns an array list with the solution path
    public ArrayList<Integer> computePath(Graph list, int[] distances, int[] predecessors)
    {
        // initializing variables to track best station, gazebo, and distance
        int bestGazebo = -1;
        int bestStation = -1;
        int bestDistance = Integer.MAX_VALUE;

        // initializing variable and arrays to track a path total, and distances/predecessors from a gazebo to a station
        int total = Integer.MAX_VALUE;
        int [] stationDistances = new int[list.numVertices + 1];
		int [] stationPredecessors = new int[list.numVertices + 1];

        // parsing through all vertices
        for(int i = 1; i <= list.numVertices; i++)
        {
            // if vertex is a gazebo, and reachable from start vertex
            if(list.gazeboList[i] && distances[i] < Integer.MAX_VALUE)
            {
                // computing bfs from gazebo to a station, to get distance and predecessors
                bfs(list, i, stationDistances, stationPredecessors);

                // parsing through all possible points again
                for(int j = 1; j <= list.numVertices; j++)
                {
                    // if vertex is a station, and reachable from gazebo
                    if(list.stationList[j] && stationDistances[j] < Integer.MAX_VALUE)
                    {
                        // tracking total distance through distance arrays
                        total = distances[i] + stationDistances[j];

                        // if current path has best distance
                        if(total < bestDistance)
                        {   
                            // updating all values
                            bestDistance = total;
                            bestStation = j;
                            bestGazebo = i;
                        }
                        // if current distance is equal
                        else if(total == bestDistance)
                        {
                            // checking if current station has lowest ID, updating if so
                            if(j < bestStation)
                            {
                                bestStation = j;
                            }
                            // if current station is best station already
                            else if(j == bestStation)
                            {
                                // checking if gazebo has lowest ID, updating if so
                                if(i < bestGazebo)
                                {
                                    bestGazebo = i;
                                }
                            }
                        }
                    }
                }
            }
        }

        // initializing array lists to keep track of paths 
        ArrayList<Integer> finalPath = new ArrayList<Integer>();
        ArrayList<Integer> firstPath = new ArrayList<Integer>();
        ArrayList<Integer> secondPath = new ArrayList<Integer>();

        // if could not find a solution path, returns an empty array list
        if(bestStation == -1)
        {
            return finalPath;
        }

        // temp variable to parse predecessors
        int tempGazebo = bestGazebo;

        // going until temp reaches start vertex
        while(tempGazebo != list.s)
        {   
            // adding vertices from gazebo to start to first path
            firstPath.add(tempGazebo);
            tempGazebo = predecessors[tempGazebo];
        }

        // adding start vertex and reversing array list 
        firstPath.add(tempGazebo);
        Collections.reverse(firstPath);

        // temp variable to parse station predecessors
        int tempStation = bestStation;

        // going until temp reaches best gazebo
        while(tempStation != bestGazebo)
        {
            // adding vertices from station to gazebo to second path
            secondPath.add(tempStation);
            tempStation = stationPredecessors[tempStation];
        }

        // adding gazebo and reversing array list
        secondPath.add(tempStation);
        Collections.reverse(secondPath);

        // adding first path into final
        for(int i = 0; i < firstPath.size(); i++)
        {
            finalPath.add(firstPath.get(i));
        }

        // adding second path into final
        for(int i = 1; i < secondPath.size(); i++)
        {
            finalPath.add(secondPath.get(i));
        }

        // returning final path
        return finalPath;
    }
}