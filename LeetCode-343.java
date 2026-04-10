// 343. Integer Break
// https://leetcode.com/problems/integer-break
class Solution {
    Integer[] dp;
    public int integerBreak(int n) {
        dp = new Integer[n + 1];
        return solve(n);
    }

    int solve(int n){
        // base case
        if(n == 1) 
            return 1;

        // memoization
        if(dp[n] != null) 
            return dp[n];

        int max = 0;

        // try all possible first cuts
        for(int i = 1; i < n; i++){
            // option 1: don't break further
            int noBreak = i * (n - i);

            // option 2: break further
            int furtherBreak = i * solve(n - i);

            max = Math.max(max, Math.max(noBreak, furtherBreak));
        }

        return dp[n] = max;
    }
}