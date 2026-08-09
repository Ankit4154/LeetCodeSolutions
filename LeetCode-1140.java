// 1140. Stone Game II
// https://leetcode.com/problems/stone-game-ii/
class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = sum of piles[i...n-1]
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        Integer[][] dp = new Integer[n][n + 1];

        return solve(piles, 0, 1, suffix, dp);
    }

    private int solve(int[] piles, int i, int M,
                      int[] suffix, Integer[][] dp) {

        // All piles have already been taken
        if(i >= piles.length){
            return 0;
        }

        if(dp[i][M] != null){
            return dp[i][M];
        }

        int n = piles.length;

        // Can take all remaining piles
        if(2 * M >= n - i){
            return dp[i][M] = suffix[i];
        }

        int best = 0;

        // Try taking X piles
        for(int X = 1; X <= 2 * M && i + X <= n; X++){

            int nextM = Math.max(M, X);

            // Current player gets:
            // total remaining - maximum opponent can get
            int current = suffix[i]
                    - solve(piles, i + X, nextM, suffix, dp);

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}