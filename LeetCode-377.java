// 377. Combination Sum IV
// https://leetcode.com/problems/combination-sum-iv
class Solution {
	Integer[] dp;
    public int combinationSum4(int[] nums, int target) {
        dp = new Integer[target+1];
		return solve(nums, target);
    }
	int solve(int[] nums, int target){
		if(target == 0){
			return 1;
		}
		if(target < 0)
			return 0;
		
		if(dp[target] != null)
			return dp[target];
		
		int ways = 0;
		for(int n : nums){
			ways += solve(nums, target - n);
		}
        return dp[target] = ways;
	}
}
// TLE
class Solution {
	int count = 0;
    public int combinationSum4(int[] nums, int target) {
        solve(nums, target);
		return count;
    }
	void solve(int[] nums, int target){
		
		if(target == 0){
			count++;
			return;
		}
		if(target < 0)
			return;
		
		for(int n : nums){
			solve(nums, target - n);
		}
        return;
	}
}