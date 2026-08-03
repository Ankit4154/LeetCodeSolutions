// 1406. Stone Game III
// https://leetcode.com/problems/stone-game-iii
class Solution {
    Integer[] dp;
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        dp = new Integer[n];
        int diff = solve(stoneValue, 0);
        if(diff > 0)
            return "Alice";
        else if(diff < 0)
            return "Bob";
        return "Tie";
    }
    private int solve(int[] nums, int i) {
        if(i >= nums.length)
            return 0;

        if(dp[i] != null)
            return dp[i];

        int sum = 0, ans = Integer.MIN_VALUE;
        for(int k=0;k<3 && i+k<nums.length;k++){
            sum += nums[i+k];
            ans = Math.max(ans, sum - solve(nums, i+k+1));
        }
        return dp[i] = ans;
    }
}