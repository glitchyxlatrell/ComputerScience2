/*  Latrell Kong
    SensorRange
    COP3503 Computer Science 2
    SensorRange.java
*/

import java.util.Arrays;

public class SensorRange
{
    public static int[] rangeCountBF(int[] readings, int[][] queries)
    {
        int returnArray[] = new int[queries.length];
        
        for(int i = 0; i < queries.length; i++)
        {   
            int queryCounter = 0;
            int leftBound = queries[i][0];
            int rightBound = queries[i][1];

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
        int returnArray[] = new int[queries.length];
        Arrays.sort(readings);

        for(int i = 0; i < queries.length; i++)
        { 
            int leftBound = queries[i][0];
            int rightBound = queries[i][1];

            int leftIndex = findLeft(readings, leftBound);
            int rightIndex = findRight(readings, rightBound);

            returnArray[i] = rightIndex - leftIndex;
        }


        return returnArray;
    }
    
    public static int findLeft(int[] readings, int bound)
    {
        int left = 0;
        int right = readings.length;
        int mid = 0;
        while(left < right)
        {
            mid = (left + right) / 2;
            if(bound <= readings[mid])
            {
                right = mid;
            }else
            {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int findRight(int[] readings, int bound)
    {
        int left = 0;
        int right = readings.length;
        int mid = 0;
        while(left < right)
        {
            mid = (left + right) / 2;
            if(bound < readings[mid])
            {
                right = mid;
            }else
            {
                left = mid + 1;
            }
        }

        return left;
    }

}
