package DynamecProgramming;

import java.util.Scanner;

// Bottom Up Approach With Space Optimization
// Time - O(N)
// Space - O(1)

public class _4_Fibonacci_Series_Recursion_Bottom_Up_SpaceUtilization {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();
        int result = fibonacci(n);
        System.out.println("Fibonacci Value Of " + n + " Position Is : " + result);
    }

    // Function Of Fibonacci Series
    public static int fibonacci(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        int c = 0;

        for(int i=2;i<=n;i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
