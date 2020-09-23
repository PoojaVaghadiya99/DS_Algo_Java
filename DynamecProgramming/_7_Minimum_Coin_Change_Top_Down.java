package DynamecProgramming;

import java.util.Scanner;

// Minimum Number Of Coin
// Top Down Approach
// Time - O(TN) : T - Number Of Coins
// Space - O(N)

public class _7_Minimum_Coin_Change_Top_Down {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();

        int coins[] = {1,7,10};
        int dp[] = new int[n+1];
        int c = coins.length;
        int result = minCoins(n,coins,c,dp);

        System.out.print("Coins - ");
        for(int i=0;i<c;i++) {
            System.out.print(coins[i] + " ");
        }
        System.out.println();
        System.out.println("Minimum Number Of Coins Is : " + result);
    }

    public static int minCoins(int n,int coins[],int t,int dp[]) {
        // Base Case
        if(n == 0) {
            return 0;
        }
        // LookUp
        if(dp[n] != 0) {
            return dp[n];
        }
        // Recursive Case
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<t;i++) {
            if(n-coins[i] >= 0) {
                int subProbleam = minCoins(n-coins[i],coins,t,dp);
                ans = Math.min(ans,subProbleam+1);
            }
        }
        dp[n] = ans;
        return dp[n];
    }
}
