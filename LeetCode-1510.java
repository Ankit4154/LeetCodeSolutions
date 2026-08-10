// 1510. Stone Game IV
// https://leetcode.com/problems/stone-game-iv
class Solution {
    public boolean winnerSquareGame(int n) {
         boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> current player loses

        for(int i = 1; i <= n; i++){

            for(int j = 1; j * j <= i; j++){

                int remaining = i - j * j;

                // If we can leave the opponent
                // in a losing position, we win.
                if(!dp[remaining]){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}