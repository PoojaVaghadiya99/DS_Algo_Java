package DynamecProgramming;

import java.util.Scanner;

// Minimum Number Of Step to Move N to 1
// Bottom Up Approach
// N ... N/3 , N/2 , N-1
// Time - O(1)
// Space - O(N)

public class _6_Minimum_Step_N_To_1_Bottom_Up {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();
        int result = minStep(n);
        System.out.println("Minimum Steps Of " + n + " To 1 Is : " + result);
    }

    public static int minStep(int n) {
        int dp[] = new int[n+1];
        dp[1] = 0;
        for(int i=2;i<=n;i++) {
            int o1,o2,o3;
            o1 = o2 = o3 = Integer.MAX_VALUE;

            if(i%3 == 0) {
                o1 = dp[i/3];
            }
            if(i%2 == 0) {
                o2 = dp[i/2];
            }
            o3 = dp[i-1];
            dp[i] = Math.min(Math.min(o1,o2),o3) + 1;
        }
        return dp[n];
    }
}
