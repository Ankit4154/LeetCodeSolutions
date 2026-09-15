// 2472. Maximum Number of Non-overlapping Palindrome Substrings
// https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings
class Solution {
    int[] dp;
    boolean[][] isPalin;
    int n;

    public int maxPalindromes(String s, int k) {

        n = s.length();

        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // Precompute whether s[i...j] is palindrome
        isPalin = new boolean[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {

                if(s.charAt(i) == s.charAt(j) &&
                   (j - i <= 2 || isPalin[i + 1][j - 1])) {

                    isPalin[i][j] = true;
                }
            }
        }

        return solve(0, s, k);
    }

    private int solve(int i, String s, int k) {

        if(i >= n)
            return 0;

        if(dp[i] != -1)
            return dp[i];

        // Option 1: skip this character
        int ans = solve(i + 1, s, k);

        // Option 2: take a palindrome starting at i
        for(int j = i + k - 1; j < n; j++) {

            if(isPalin[i][j]) {

                ans = Math.max(
                    ans,
                    1 + solve(j + 1, s, k)
                );
            }
        }

        return dp[i] = ans;
    }
}