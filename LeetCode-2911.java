// 2911. Minimum Changes to Make K Semi-palindromes
// https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes
class Solution {
    int[][] cost;
    int[][] dp;
    int n;

    public int minimumChanges(String s, int k) {
        n = s.length();

        cost = new int[n][n];
        dp = new int[n][k + 1];

        for(int[] row : dp)
            Arrays.fill(row, -1);

        // Precompute semi-palindrome conversion costs
        for(int len = 2; len <= n; len++) {
            for(int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                cost[l][r] = Integer.MAX_VALUE;

                for(int d = 1; d < len; d++) {
                    if(len % d != 0)
                        continue;

                    int changes = 0;

                    for(int i = 0; i < d; i++) {
                        int left = l + i;
                        int right = l + i + (len / d - 1) * d;

                        while(left < right) {
                            if(s.charAt(left) != s.charAt(right))
                                changes++;

                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        return solve(0, k);
    }

    int solve(int ind, int parts) {
        if(parts == 0)
            return ind == n ? 0 : 1000000;

        if(n - ind < 2 * parts)
            return 1000000;

        if(dp[ind][parts] != -1)
            return dp[ind][parts];

        int ans = 1000000;

        // Leave at least 2 characters for every remaining part
        for(int j = ind + 1; j <= n - 2 * (parts - 1) - 1; j++) {
            int curr = cost[ind][j] + solve(j + 1, parts - 1);
            ans = Math.min(ans, curr);
        }

        return dp[ind][parts] = ans;
    }
}