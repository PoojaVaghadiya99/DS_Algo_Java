package DynamecProgramming;

import java.util.Scanner;

// Minimum Number Of Step to Move N to 1
// Top Down Approach
// N ... N/3 , N/2 , N-1
// Time - O(N)
// Space - O(N)

public class _5_Minimum_Step_N_To_1_Top_Down {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();
        int dp[] = new int[n+1];
        int result = minStep(n,dp);
        System.out.println("Minimum Steps Of " + n + " To 1 Is : " + result);
    }

    public static int minStep(int n,int dp[]) {
        // Base Case
        if(n == 1) {
            return 0;
        }
        // If Already Computed then Return Value
        if(dp[n] != 0) {
            return dp[n];
        }
        // Recursive Case
        // Compute If dp[n] is Not Know
        int o1,o2,o3;
        o1 = o2 = o3 = Integer.MAX_VALUE;

        if(n%3 == 0) {
            o1 = minStep(n/3,dp) + 1;
        }
        if(n%2 == 0) {
            o2 = minStep(n/2,dp) + 1;
        }
        o3 = minStep(n-1,dp) + 1;

        int ans = Math.min(Math.min(o1,o2),o3);
        return ans;
    }
}
