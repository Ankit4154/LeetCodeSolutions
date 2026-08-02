// 877. Stone Game
// https://leetcode.com/problems/stone-game
// optim
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}

class Solution {
    Integer[][] dp;
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new Integer[n][n];
        return solve(piles, 0, n - 1) >= 0;
    }
    private int solve(int[] nums, int i, int j) {
        if(i == j)
            return nums[i];

        if(dp[i][j] != null)
            return dp[i][j];

        int left = nums[i] - solve(nums, i + 1, j);
        int right = nums[j] - solve(nums, i, j - 1);

        return dp[i][j] = Math.max(left, right);
    }
}