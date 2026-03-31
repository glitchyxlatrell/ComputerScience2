//COP3503 Computer Science 2
//LCS.java using Dynamic Programming

import java.io.*; 
import java.util.*; 

public class LCS
{ 
	public static int lcs(String X, String Y, int m, int n) 
	{ 
		if (m == 0 || n == 0) 
			return 0; 
		if (X.charAt(m - 1) == Y.charAt(n - 1)) 
			return 1 + lcs(X, Y, m - 1, n - 1); 
		else
			return max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n)); 
	} 
	
	
	public static int lcsMemoization(String X, String Y, int m, int n, int[][] memo) 
    { 
        if (m == 0 || n == 0) 
            return 0; 
  
        if (memo[m][n] != -1) 
            return memo[m][n]; 
  
        if (X.charAt(m - 1) == Y.charAt(n - 1)) 
		{ 
            memo[m][n] = 1 + lcs(X, Y, m - 1, n - 1); 
            return memo[m][n]; 
        } 
  
        memo[m][n] = Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n)); 
        return memo[m][n]; 
    }
	
	public static int lcsTabluation(String X, String Y, int m, int n) 
    { 
        int c[][] = new int[m + 1][n + 1]; 
		char b[][] = new char[m + 1][n + 1]; 
		
		for(int i = 0; i <= m; ++i)
			c[i][0] = 0;
		
		for(int j = 0; j <= n; ++j)
			c[0][j] = 0;
		
		
        for (int i = 1; i <= m; i++) 
		{ 
            for (int j = 1; j <= n; j++) 
			{ 
                if (X.charAt(i - 1) == Y.charAt(j - 1)) 
				{
                    c[i][j] = c[i - 1][j - 1] + 1; 
					b[i][j] = 'd';
				}
                else if(c[i - 1][j] >= c[i][j - 1])
				{
                    c[i][j] = c[i - 1][j];
					b[i][j] = 'v';
				}
				else
				{
					c[i][j] = c[i][j - 1];
					b[i][j] = 'h';
				}
            } 
        } 
		
        
		StringBuilder sb=new StringBuilder(printLCS(b, X, m, n));   
		System.out.println(sb);
		
		for(int row = 1; row <= m; ++row)
		{
			for(int col = 1; col <= n; ++col)
			{
				System.out.print(c[row][col] + "   ");
			}
			System.out.println();
		}
		
		return c[m][n]; 
    } 
	
	public static String printLCS(char [][] b, String X, int i, int j)
	{
		if(i == 0 || j == 0)
            return "";

		if (b[i][j] == 'd')
            return printLCS(b, X, i - 1, j - 1) + X.charAt(i - 1);
        else if(b[i][j] == 'v')	
            return printLCS(b, X, i - 1, j) + "";
        else			
            return printLCS(b, X, i, j - 1) + "";
	}


	public static int max(int a, int b) 
	{ 
		if(a > b)
			return a;
		else
			return b;
	} 

	// Driver code 
	public static void main(String[] args) 
	{ 	
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first sequence: ");
        String s1 = scanner.nextLine();

        System.out.print("Enter second sequence: ");
        String s2 = scanner.nextLine();
		
		int m = s1.length(); 
		int n = s2.length(); 
		
		
		int[][] memo = new int[m + 1][n + 1]; 
        for (int i = 0; i < m + 1; i++) 
            for (int j = 0; j < n + 1; j++) 
                memo[i][j] = -1; 
			
		System.out.println("Length of LCS using regular recursion is" + " " + lcs(s1, s2, m, n)); 
		System.out.println("Length of LCS using regular recursion with memoization is" + " " + lcsMemoization(s1, s2, m, n, memo)); 
		System.out.println("Length of LCS using tabulation is" + " " + lcsTabluation(s1, s2, m, n)); 
	} 
} 
