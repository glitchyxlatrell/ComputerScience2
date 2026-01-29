/*  Latrell Kong
    Sensor Range Queries
    COP3503 Computer Science 2
    SensorRangeDriver.java
    Compile: javac SensorRangeDriver.java
    Run: java SensorRangeDriver [CASE]
*/

import java.util.Arrays;

public class SensorRangeDriver
{
    public static void main(String args[])
    {
        int testCase = Integer.parseInt(args[0]);
        
        switch(testCase)
        {
            case 1:
            {
                int[] readings = buildReadings(10);
                int[][] queries = {{-5, 5}, {0, 3}, {4, 20}, {10, 1}};
                runAndPrint("Case 1: n=10, q=4", readings, queries);
                break;
            }

            case 2:
            {
                int[] readings = buildReadings(100);
                int[][] queries = {{-10, 10}, {-5, 0}, {1, 5}, {6, 20}, {-100, 100}, {50, 70}, 
                                    {30, 29}, {-20, -1}, {0, 0}, {99, 200}};
                runAndPrint("Case 2: n=100, q=10", readings, queries);
                break;
            }

            case 3:
            {
                int[] readings = buildReadings(1000);
                int[][] queries = buildQueries(20);

                runAndPrint("Case 3: n=1000, q=20", readings, queries);
                break;
            }

            case 4:
            {
                int[] readings = buildReadings(10000);
                int[][] queries = buildQueries(50);

                runAndPrint("Case 4: n=10000, q=50", readings, queries);
                break;
            }

            case 5:
            {
                // Compare execution times only. Note that the runtime may vary between runs
                // This test case is intended to help you observe the performance difference
                // between the two approaches; you will not be graded on matching specific timing values
                int n = 50000;
                int q = 10000;

                int[] readings = buildReadings(n);
                int[][] queries = buildQueries(q);

                long startFast = System.nanoTime();
                int[] fast = SensorRange.rangeCountFast(readings, queries);
                long endFast = System.nanoTime();

                long startBF = System.nanoTime();
                int[] bf = SensorRange.rangeCountBF(readings, queries);
                long endBF = System.nanoTime();

                System.out.println("Case 5: Timing Only");
                System.out.println("Readings size: " + readings.length);
                System.out.println("Queries size : " + queries.length);
                System.out.println("FAST time (ms): " + (endFast - startFast) / 1000000.0);
                System.out.println("BF time (ms): " + (endBF - startBF) / 1000000.0);

                break;
            }

            default:
                System.out.println("Invalid Test Case...");
        }
    }

    // Helper Methods to generate random numbers and quires

    private static int[] buildReadings(int n)
    {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = (i % 100) - 50;
        return a;
    }

    private static int[][] buildQueries(int q)
    {
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) 
        {
            queries[i][0] = (i % 20) - 10;
            queries[i][1] = (i % 20);
        }
        return queries;
    }

    private static void runAndPrint(String label, int[] readings, int[][] queries)
    {
        int[] bf = SensorRange.rangeCountBF(readings, queries);
        int[] fast = SensorRange.rangeCountFast(readings, queries);

        System.out.println(label);

        // System.out.println("Readings:");
        // System.out.println(Arrays.toString(readings));

        // System.out.println("Queries (L, R):");
        // for (int i = 0; i < queries.length; i++) 
        //     System.out.println("Query " + i + ": [" + queries[i][0] + ", " + queries[i][1] + "]");

        System.out.println("BF Results:");
        System.out.println(Arrays.toString(bf));

        System.out.println("FAST Results:");
        System.out.println(Arrays.toString(fast));
    }
}
