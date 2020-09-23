package DynamecProgramming;

import java.util.Scanner;

// Top Down Approach
// Recursion + Memoization
// Time - O(N)
// Space - O(N)

public class _2_Fibonacci_Series_Recursion_Top_Down {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();
        int dp[] = new int[n+1];
        int result = fibonacci(n,dp);
        System.out.println("Fibonacci Value Of " + n + " Position Is : " + result);
    }

    // Function Of Fibonacci Series
    public static int fibonacci(int n,int dp[]) {
        // Base Case
        if(n == 0 || n == 1) {
            return n;
        }
        // Recursive Case
        if(dp[n] != 0) {
            return dp[n];
        }
        int ans;
        ans = fibonacci(n-1,dp) + fibonacci(n-2,dp);
        return dp[n] = ans;
    }
}
