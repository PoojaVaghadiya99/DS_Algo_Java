package DynamecProgramming;

import java.util.Scanner;

// Recursion
// Time - O(2^N)
// Space - O(1)

public class _1_Fibonacci_Series_Recursion {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();
        int result = fibonacci(n);
        System.out.println("Fibonacci Value Of " + n + " Position Is : " + result);
    }

    // Function Of Fibonacci Series
    public static int fibonacci(int n) {
        // Base Case
        if(n == 0 || n == 1) {
            return n;
        }
        // Recursive Case
        int ans;
        ans = fibonacci(n-1) + fibonacci(n-2);
        return ans;
    }
}
