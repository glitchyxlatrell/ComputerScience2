/*  Latrell Kong
    Mystic Sudoku
    COP3503 Computer Science 2
    SudokuSolver.java
*/

public class SudokuSolver
{

    // global variables 
    private int solCounter = 0;
    private boolean firstSolution = false;
    private int[][] onlySolution = new int[9][9];

    // solve function
    public int solve(int[][] board, int[][] forbiddenPairs)
    {   
        // resetting values for each puzzle board
        solCounter = 0;
        firstSolution = false;
        
        // creating a 2-d boolean array to track all forbidden pairs
        boolean[][] forbid = new boolean[10][10];
        for(int i = 0; i < forbiddenPairs.length; i++)
        {
            int firstNum = forbiddenPairs[i][0];
            int secNum = forbiddenPairs[i][1];
            forbid[firstNum][secNum] = true;
            forbid[secNum][firstNum] = true;
        }

        // calling recursive function
        backtrackSolve(board, forbiddenPairs, forbid);

        // getting the board values if only one solution
        if(solCounter == 1)
        {
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    board[i][j] = onlySolution[i][j];
                }
            }
        }

        return solCounter;
    }

    // recursive function 
    private void backtrackSolve(int[][] board, int[][] forbiddenPairs, boolean[][] forbid)
    {   
        // parsing through board to find first 0
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(board[i][j] == 0)
                {   
                    // trying every digit for position
                    for(int x = 1; x < 10; x++)
                    {   
                        // testing if position is valid for digit
                        if(positionOk(i, j, x, board, forbiddenPairs, forbid))
                        {
                            // inserting value and recursively calling function
                            board[i][j] = x;
                            backtrackSolve(board, forbiddenPairs, forbid);
                        }
                        // undoing to backtrack
                        board[i][j] = 0;
                    }

                    // returning after trying all digits
                    return;
                }
            }
        }

        // adding to solCounter if there is no value that is 0
        solCounter++;

        // checking if first solution, and saving board if it is
        if(!firstSolution)
        {
            firstSolution = true;
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    onlySolution[i][j] = board[i][j];
                }
            }
        }
    }

    // function to check if position is valid for value
    boolean positionOk(int xCor, int yCor, int number, int[][] board, int[][] forbiddenPairs, boolean[][] forbid)
    {   
        // checking for row and column rule
        for(int i = 0; i < 9; i++)
        {
            if(board[i][yCor] == number || board[xCor][i] == number)
            {
                return false;
            }
        }

        // math to find what grid position is in
        int rowGrid = (xCor / 3) * 3;
        int colGrid = (yCor / 3) * 3;

        // checking for grid rule
        for(int i = rowGrid; i < rowGrid + 3; i++)
        {
            for(int j = colGrid; j < colGrid + 3; j++)
            {
                if(board[i][j] == number)
                {
                    return false;
                }
            }
        }

        
        // arrays for each knight move
        int[] offsetKnightX = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] offsetKnightY = {1, -1, 1, -1, 2, -2, 2, -2};
        int knightX = 0;
        int knightY = 0;

        // checking for knight rule (with wrap-around)
        for(int i = 0; i < 8; i++)
        {   
            // math to account for wrap-around
            knightX = (xCor + offsetKnightX[i] + 9) % 9;
            knightY = (yCor + offsetKnightY[i] + 9) % 9;

            if(board[knightX][knightY] == number)
            {
                return false;
            }
        }

        // array for each orthogonal move
        int[] offsetX = {1, -1, 0, 0};
        int[] offsetY = {0, 0, -1, 1};
        int orthX = 0;
        int orthY = 0;

        // checking for orthogonally adjacent forbidden pair (with wrap-around)
        for(int i = 0; i < 4; i++)
        {   
            // math for wrap-around
            orthX = (xCor + offsetX[i] + 9) % 9;
            orthY = (yCor + offsetY[i] + 9) % 9;

            // checking each forbidden pair
            if(forbid[number][board[orthX][orthY]])
            {
                return false;
            }
        }

        return true;
    }

}
