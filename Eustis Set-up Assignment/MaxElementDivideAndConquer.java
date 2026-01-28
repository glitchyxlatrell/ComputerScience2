import java.util.Scanner;

public class MaxElementDivideAndConquer{

    // divide and conquer function
    public static int findMax(int [] arr, int left, int right){
        // returning max once there is only one value left in divided "half"
        if(left == right){
            return arr[left];
        }
        // getting midpoint
        int middle = (left + right) / 2;
        
        // recursively calling function to "divide and conquer"
        int maxLeft = findMax(arr, left, middle);
        int maxRight = findMax(arr, middle + 1, right);

        // comparing and returning max value
        if(maxLeft >= maxRight){
            return maxLeft;
        }else{
            return maxRight;
        }
        
    }

    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scan.nextInt();

        if(n < 1)
            return;
        

        int [] arr = new int[n];

        System.out.println("Enter the elements: ");

        for(int i = 0; i < n; ++i)
        arr[i] = scan.nextInt();

        int maxElement = findMax(arr, 0, n - 1);

        System.out.println("Maximum element: " + maxElement);

        scan.close();
        }
}