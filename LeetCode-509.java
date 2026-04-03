// 509. Fibonacci Number
// https://leetcode.com/problems/fibonacci-number
class Solution {
    Integer dp[];
    public int fib(int n) {
        dp = new Integer[n+1];
        return solve(n);
    }
    int solve(int n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        if(dp[n] != null)
            return dp[n];
        return dp[n] = solve(n-1) + solve(n-2);
    }
}