package DynamecProgramming;

import java.util.Scanner;

// Bottom Up Approach
// Time - O(N)
// Space - O(N)

public class _3_Fibonacci_Series_Recursion_Bottom_Up {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();
        int result = fibonacci(n);
        System.out.println("Fibonacci Value Of " + n + " Position Is : " + result);
    }

    // Function Of Fibonacci Series
    public static int fibonacci(int n) {
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
