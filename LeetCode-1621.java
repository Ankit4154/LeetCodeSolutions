// 1621. Number of Sets of K Non-Overlapping Line Segments
// https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments
class Solution {
    final int MOD = 1_000_000_007;
    public int numberOfSets(int n, int k) {
        int[][] dp = new int[n + k][2 * k + 1];

        for(int i = 0; i <= n + k - 1; i++){
            dp[i][0] = 1;

            for(int j = 1; j <= Math.min(i, 2 * k); j++){
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[n + k - 1][2 * k];
    }
}