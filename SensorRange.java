/*  Latrell Kong
    Assignment 1 - SensorRange
    COP3503 Computer Science 2
    SensorRange.java
*/

public class SensorRange
{
    public static int[] rangeCountBF(int[] readings, int[][] queries)
    {
        int queryLength = queries.length;
        int returnArray[] = new int[queryLength];
        int leftBound = 0;
        int rightBound = 0;
        
        for(int i = 0; i < queryLength; i++)
        {   
            int queryCounter = 0;
            leftBound = queries[i][0];
            rightBound = queries[i][1];

            if(leftBound > rightBound)
            {
                returnArray[i] = 0;
                continue;
            }

            for(int j = 0; j < readings.length; j++)
            {
                if(readings[j] >= leftBound && readings[j] <= rightBound)
                    {
                    queryCounter++;
                }
            }

            returnArray[i] = queryCounter;
        }

        return returnArray;
    }

    public static int[] rangeCountFast(int[] readings, int[][] queries)
    {
        
    }


}
