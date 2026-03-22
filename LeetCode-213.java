// 213. House Robber II
// https://leetcode.com/problems/house-robber-ii
class Solution {
    public int rob(int[] nums) {
		int n = nums.length;
		if(n == 1)
			return nums[0];
		int[] dp = new int[n];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp, -1);
		int skipFirst = solve(1,nums,dp);
		nums[n-1] = 0;
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp, -1);
		int skipLast = solve(0,nums,dp);
        return Math.max(skipFirst, skipLast);
    }
	int solve(int ind, int[] nums, int[] dp){
		if(ind >= nums.length)
			return 0;
		if(dp[ind] != -1)
			return dp[ind];
		int pick = nums[ind] + solve(ind+2, nums, dp);
		int skip = solve(ind+1, nums, dp);
		return dp[ind] = Math.max(pick, skip);
	}
}