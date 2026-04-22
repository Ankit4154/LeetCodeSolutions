// 1025. Divisor Game
// https://leetcode.com/problems/divisor-game
// optim
class Solution {
    public boolean divisorGame(int n) {
        return (n % 2 == 0);
    }
}

// init
class Solution {
    Boolean[] dp;
    public boolean divisorGame(int n) {
        dp = new Boolean[n+1];
        return solve(n);
    }
    boolean solve(int n){
        if(n == 1)
            return false;
        if(dp[n] != null)
            return dp[n];
        for(int x=1;x<n;x++){
            if(n % x == 0){
                if(!solve(n-x)){
                    return dp[n] = true;
                }
            }
        }
        return dp[n] = false;
    }
}