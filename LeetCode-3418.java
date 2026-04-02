// 3418. Maximum Amount of Money Robot Can Earn
// https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn
class Solution{
    int m,n;
    Integer[][][] dp;

    public int maximumAmount(int[][] coins){
        m=coins.length;
        n=coins[0].length;
        dp=new Integer[m][n][3];
        return solve(0,0,2,coins);
    }

    int solve(int i,int j,int k,int[][] coins){
        // out of bounds
        if(i>=m||j>=n)
            return Integer.MIN_VALUE;

        // final cell
        if(i==m-1&&j==n-1){
            if(coins[i][j]>=0)
                return coins[i][j];

            // if negative: either take loss or neutralize
            if(k>0)
                return Math.max(coins[i][j],0);
            return coins[i][j];
        }

        if(dp[i][j][k]!=null)
            return dp[i][j][k];

        int val=coins[i][j];

        // move right and down
        int right=solve(i,j+1,k,coins);
        int down=solve(i+1,j,k,coins);

        int best=Integer.MIN_VALUE;

        if(val>=0){
            // if current cell is positive, just add it
            int next=Math.max(right,down);
            if(next!=Integer.MIN_VALUE)
                best=val+next;
        }else{
            // option 1: take the loss
            int next=Math.max(right,down);
            if(next!=Integer.MIN_VALUE)
                best=val+next;

            // option 2: neutralize the robber (if available)
            if(k>0){
                int right2=solve(i,j+1,k-1,coins);
                int down2=solve(i+1,j,k-1,coins);
                int next2=Math.max(right2,down2);

                if(next2!=Integer.MIN_VALUE)
                    best=Math.max(best,next2);
            }
        }

        return dp[i][j][k]=best;
    }
}