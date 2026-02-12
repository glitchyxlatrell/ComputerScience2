/*
COP3503C | Spring 2026 | Section 0201
Name: Latrell Kong
UCF ID: 5624427
*/

// Disjoint Set (Union-Find) lab solution in Java
// Elements: 1..5
// Operations: Union(1,2), Union(3,4), Union(2,3)
// Queries: rep(1), rep(4), sameSet(1,5)

public class disjointSetStudent {

    static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        // We’ll use 1-based indexing for simplicity (ignore index 0)
        DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            makeSet(n);
        }

        // Make-Set for elements 1..n
        private void makeSet(int n) {
            // creating n-number of disjoint sets
            for(int i = 1; i <= n; i++)
            {
                parent[i] = i;
                rank[i] = 0;
            }

        }

        // Find-Set with path compression
        int find(int x) {
            // returning if representative
           if(parent[x] == x)
           {
            return x;
           }
           
           // recursive call if not representative
           parent[x] = find(parent[x]);
           return parent[x];

        }

        // Union by rank
        void union(int x, int y) {
            
            // finding representatives of each int
            int firstRoot = find(x);
            int secondRoot = find(y);
            
            // if already in union, nothing happens
            if(firstRoot == secondRoot){
                return;
            }
            
            // cases for union based on rank
            if(rank[firstRoot] > rank[secondRoot])
            {
                parent[secondRoot] = firstRoot;
            }
            else if(rank[firstRoot] < rank[secondRoot])
            {
                parent[firstRoot] = secondRoot;
            }
            else
            {
                parent[secondRoot] = firstRoot;
                rank[firstRoot]++;
            }
            return;
        }

        boolean sameSet(int a, int b) {
            // looking for same representative
            if(find(a) == find(b))
            {
                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(5); // elements 1..5

        // Perform the required unions
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(2, 3);

        // Queries
        int rep1 = ds.find(1);
        int rep4 = ds.find(4);
        boolean same = ds.sameSet(1, 5);

        // Output (example format)
        System.out.println("Representative of 1: " + rep1);
        System.out.println("Representative of 4: " + rep4);
        System.out.println("Are 1 and 5 in the same set? " + (same ? "Yes" : "No"));
    }
}
