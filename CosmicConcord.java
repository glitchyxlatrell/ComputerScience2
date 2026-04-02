/*  Latrell Kong
    Cosmic Concordance
    COP3503 Computer Science 2
    CosmicConcord.java
*/

public class CosmicConcord {

    // top-down memoization solution with recursion
    public static int solveRecMemo(int[] A, int[] B, int D, int G, int K, int prevA, int prevB, int[][][] memo) 
    {
        // returning if already solved for this pair of points
        if(memo[prevA][prevB][K] != -1)
        {
            return memo[prevA][prevB][K];
        }


        // initializing to keep track of most optimal pair
        int optimalSol = 0;

        // parsing through all possible next set of points
        for(int nextA = prevA + 1; nextA < A.length; nextA++)
        {
            for(int nextB = prevB + 1; nextB < B.length; nextB++)
            {
                // checking if pair is inside appropriate tolerance
                if(Math.abs(A[nextA] - B[nextB]) <= D)
                {   
                    // checking if points are considered rising
                    if((A[nextA] - A[prevA] >= G) && (B[nextB] - B[prevB] >= G))
                    {   
                        // recursively calling with next set of points, and keeping most optimal pair
                        optimalSol = Math.max(optimalSol, 1 + solveRecMemo(A, B, D, G, K, nextA, nextB, memo));
                    }
                    // if pair is considered dip, recursive call if dips still allowed
                    else if(K > 0)
                    {
                        // recursively calling with next set of points and 1 less dip allowed, and keeping most optimal pair 
                        optimalSol = Math.max(optimalSol, 1 + solveRecMemo(A, B, D, G, K - 1, nextA, nextB, memo));
                    }
                }
            }
        }

        // putting best solution into current pair
        memo[prevA][prevB][K] = optimalSol;
        return memo[prevA][prevB][K];
    }

    // bottom-up iterative tabulation approach 
    public static int solveTab(int[] A, int[] B, int N, int M, int D, int G, int K) 
    {
        // creating 3-d array to keep track of best solutions
        int[][][] tab = new int[N + 1][M + 1][K + 1];

        // 3-level nested for loop to initialize values
        for (int i = 0; i < N + 1; i++)
        {
            for (int j = 0; j < M + 1; j++) 
            {
                for (int k = 0; k < K + 1; k++) 
                {
                    // initializing to 0
                    tab[i][j][k] = 0;
                }
            }
        }

        // initializing best variable to keep highest number
        int best = 0;

        // three-level nested for loop to check all pairs and with certain amount of K values
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++) 
            {
                for(int k = 0; k <= K; k++)
                {

                    // checking if pair is within D tolerance range
                    if(Math.abs(A[i] - B[j]) <= D)
                    {

                        // initializing all first pairs,
                        tab[i][j][k] = 1;

                        // checking best in case of 1-pair high edge case
                        best = Math.max(best,1);

                        // double nested for loop to check for highest possible value in previous pairs
                        for(int c = 0; c < i; c++)
                        {
                            for(int v = 0; v < j; v++)
                            {
                                // checking if it is a rising step next value in A and B
                                if((A[i] - A[c] >= G) && (B[j] - B[v] >= G))
                                {   
                                    // updating current index to highest possible value
                                    tab[i][j][k] = Math.max(tab[i][j][k], 1 + tab[c][v][k]);
                                }
                                // if dip step
                                else if(k > 0)
                                {   
                                    // updating current index to highest possible value with 1 less dip remaining
                                    tab[i][j][k] = Math.max(tab[i][j][k], 1 + tab[c][v][k - 1]);
                                }
                                // putting highest value in best
                                if(tab[i][j][k] > best)
                                {
                                    best = tab[i][j][k];
                                }
                            }
                        }
                    }
                }
            }
        }
        return best;
    }

    /*
        N and M are lengths of sequences A and B
        D is tolerance for appropriate matching
        G is minimum required increase for rising
        K is maximum number of dip steps
    */ 
    public static void solve(int[] A, int[] B, int N, int M, int D, int G, int K) 
    {
        // creating memo 3-d array that keeps track of max for index N and M with K dips left
        int[][][] memo = new int[N + 1][M + 1][K + 1];
        for (int i = 0; i < N + 1; i++)
        {
            for (int j = 0; j < M + 1; j++) 
            {
                for (int k = 0; k < K + 1; k++) 
                {
                    // initializing to -1 
                    memo[i][j][k] = -1;
                }
            }
        }

        // initializing to keep track of best solution
        int bestSolution = 0;

        // parsing through all possible first pairs
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < M; j++)
            {
                // checking if pair is in required D tolerance range
                if(Math.abs(A[i] - B[j]) <= D)
                {   
                    // recursive call, and keeping track of best solution
                    bestSolution = Math.max(bestSolution, 1 + solveRecMemo(A, B, D, G, K, i, j, memo));
                }
            }
        }

        // printing out results of solving with memoization and tabulation
        System.out.println("Maximum length using Recursion (with Memoization): " + bestSolution);
        System.out.println("Maximum length using Tabulation: " + solveTab(A, B, N, M, D, G, K));
    }
}