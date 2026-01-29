/*  Latrell Kong
    SensorRange
    COP3503 Computer Science 2
    SensorRange.java
*/

// importing built-in java array methods
import java.util.Arrays;

public class SensorRange
{
    // brute force method to check queries
    public static int[] rangeCountBF(int[] readings, int[][] queries)
    {   
        // initializing return array
        int returnArray[] = new int[queries.length];
        
        // for loop to parse through all queries
        for(int i = 0; i < queries.length; i++)
        {   
            // initializing left/right bound and counter for how much values are between range
            int queryCounter = 0;
            int leftBound = queries[i][0];
            int rightBound = queries[i][1];

            // returning 0 for i-th query if left bound > right bound
            if(leftBound > rightBound)
            {
                returnArray[i] = 0;
                continue;
            }

            // parsing through every sensor reading for each query
            for(int j = 0; j < readings.length; j++)
            {   
                // incrementing queryCounter if data is in range
                if(readings[j] >= leftBound && readings[j] <= rightBound)
                    {
                    queryCounter++;
                }
            }

            // putting counter data in returnArray
            returnArray[i] = queryCounter;
        }

        return returnArray;
    }

    // optimized solution to check queries
    public static int[] rangeCountFast(int[] readings, int[][] queries)
    {   
        // initializing return array and sorting readings in ascending order
        int returnArray[] = new int[queries.length];
        Arrays.sort(readings);

        // for loop to parse through each query
        for(int i = 0; i < queries.length; i++)
        { 
            int leftBound = queries[i][0];
            int rightBound = queries[i][1];

            // returning 0 for i-th query if left bound > right bound
            if(leftBound > rightBound)
            {
                returnArray[i] = 0;
                continue;
            }

            // finding indexes of first value >= left bound and > right bound
            int leftIndex = findLeft(readings, leftBound);
            int rightIndex = findRight(readings, rightBound);

            // number of readings in range is equal to the difference of right and left index
            returnArray[i] = rightIndex - leftIndex;
        }

        return returnArray;
    }
    
    // helper function to find left index
    public static int findLeft(int[] readings, int bound)
    {
        // initializing values for left, right, and mid of readings
        int left = 0;
        int right = readings.length;
        int mid = 0;

        // loop that splits search in half every iteration
        while(left < right)
        {
            // calculating mid
            mid = (left + right) / 2;

            // looking for first index with value greater than or equal to left bound
            if(bound <= readings[mid])
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }

        return left;
    }

    // helper function to find right index
    public static int findRight(int[] readings, int bound)
    {
        // initializing values for left, right, and mid of readings
        int left = 0;
        int right = readings.length;
        int mid = 0;

        // loop that splits search in half every iteration
        while(left < right)
        {
            mid = (left + right) / 2;
            // looking for first index with value greater than rightBound
            if(bound < readings[mid])
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }

        return left;
    }

}
