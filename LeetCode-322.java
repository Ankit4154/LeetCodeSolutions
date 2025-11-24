// 322. Coin Change
// https://leetcode.com/problems/coin-change/
class Solution {
    public int coinChange(int[] coins, int amount) {
		int[][] dp = new int[coins.length][amount + 1];
        // initialize dp with -1 meaning "not computed"
        for (int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        //int ans = partitionSum(coins, 0, amount);
		int ans = partitionSum(coins, 0, amount, dp);
        return ans >= 100000000 ? -1 : ans; 
    }

	// with dp/memoization
	public int partitionSum(int[] nums, int i, int sum, int[][] dp){
        // base cases
        if (sum == 0)
            return 0;

        if (i == nums.length)
            return Integer.MAX_VALUE-1;  // impossible

        if (dp[i][sum] != -1)
            return dp[i][sum];

        int ans;

        if (nums[i] <= sum) {
            int take = 1 + partitionSum(nums, i, sum - nums[i], dp);
            int skip = partitionSum(nums, i + 1, sum, dp);
            ans = Math.min(take, skip);
        } else {
            ans = partitionSum(nums, i + 1, sum, dp);
        }

        dp[i][sum] = ans;
        return ans;
    }

	// without dp/memoization
	public int partitionSum(int[] nums, int i, int sum){
		// base case
        if(sum == 0)
            return 0;
		if(i == nums.length){
            return Integer.MAX_VALUE-1;
        }
        int ans = 0;
		if(nums[i] <= sum){
			int take = 1+ partitionSum(nums, i, sum - nums[i]);
            int skip = partitionSum(nums, i+1, sum);
            ans = Math.min(take, skip);
		}else{
			ans = partitionSum(nums, i+1, sum);
		}
		return ans;
	}
}	