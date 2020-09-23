package DynamecProgramming;

// Minimum Number Of Coin
// Bottom Up Approach
// Time - O(TN) : T - Number Of Coins
// Space - O(N)

import java.util.Scanner;

public class _8_Minimum_Coin_Change_Bottom_Up {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Value : ");
        int n= sc.nextInt();

        int coins[] = {1,7,10};
        int c = coins.length;
        int result = minCoins(n,coins,c);

        System.out.print("Coins - ");
        for(int i=0;i<c;i++) {
            System.out.print(coins[i] + " ");
        }
        System.out.println();
        System.out.println("Minimum Number Of Coins Is : " + result);
    }

    public static int minCoins(int n,int coins[],int t) {
        int dp[] = new int[n+1];
        // Iterate Over All States 1 ... N
        for(int i=1;i<=n;i++) {
            // Initialize the Current Answer As Integer Max Value
            dp[i] = Integer.MAX_VALUE;
            for(int j=0;j<t;j++){
                if(i-coins[j] >= 0) {
                    int sunProbleam = dp[i-coins[j]];
                    dp[i] = Math.min(dp[i],sunProbleam+1);
                }
            }
        }
        return dp[n];
    }
}
