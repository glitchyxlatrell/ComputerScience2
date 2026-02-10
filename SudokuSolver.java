/*  Latrell Kong
    Mystic Sudoku
    COP3503 Computer Science 2
    SudokuSolver.java
*/

public class SudokuSolver
{
    private int solCounter = 0;
    private boolean firstSolution = false;
    private int[][] onlySolution = new int[9][9];

    public int solve(int[][] board, int[][] forbiddenPairs)
    {
        solCounter = 0;
        firstSolution = false;
        
        backtrackSolve(board, forbiddenPairs);

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

    private void backtrackSolve(int[][] board, int[][] forbiddenPairs)
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(board[i][j] == 0)
                {
                    for(int x = 1; x < 10; x++)
                    {
                        if(positionOk(i, j, x, board, forbiddenPairs))
                        {
                            board[i][j] = x;

                            backtrackSolve(board, forbiddenPairs);
                        }
                        board[i][j] = 0;
                    }

                    return;
                }
            }
        }

        solCounter++;

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

    boolean positionOk(int xCor, int yCor, int number, int[][] board, int[][] forbiddenPairs)
    {   
        // checking for row and column rule
        for(int i = 0; i < 9; i++)
        {
            if(board[i][yCor] == number || board[xCor][i] == number)
            {
                return false;
            }
        }

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

        

        // checking for knight rule (with wrap-around)
        int[] offsetKnightX = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] offsetKnightY = {1, -1, 1, -1, 2, -2, 2, -2};
        int knightX = 0;
        int knightY = 0;

        for(int i = 0; i < 8; i++)
        {
            knightX = (xCor + offsetKnightX[i] + 9) % 9;
            knightY = (yCor + offsetKnightY[i] + 9) % 9;

            if(board[knightX][knightY] == number)
            {
                return false;
            }
        }

        // checking for orthogonally adjacent forbidden pair (with wrap-around)
        int[] offsetX = {1, -1, 0, 0};
        int[] offsetY = {0, 0, -1, 1};
        int orthX = 0;
        int orthY = 0;

        for(int i = 0; i < 4; i++)
        {
            orthX = (xCor + offsetX[i] + 9) % 9;
            orthY = (yCor + offsetY[i] + 9) % 9;

            for(int j = 0; j < forbiddenPairs.length; j++)
            {
                if(number == forbiddenPairs[j][0] && board[orthX][orthY] == forbiddenPairs[j][1])
                {
                    return false;
                }
                else if(number == forbiddenPairs[j][1] && board[orthX][orthY] == forbiddenPairs[j][0])
                {
                    return false;
                }
            }
        }

        return true;
    }

}
