// 300. Longest Increasing Subsequence
// https://leetcode.com/problems/longest-increasing-subsequence/
// Time : O(n^2), Space : O(n^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n+1][n+1];
		for(int ind=n-1;ind >= 0;ind--){
			for(int prev=ind-1;prev >= -1;prev--){
				int len = 0;
				int take = 0;
				int skip = dp[ind+1][prev+1];
				// if increasing sequence or 
				// first index element then take
				if(prev == -1 || nums[ind] > nums[prev])
					take = 1 + dp[ind+1][ind+1];
				len = Math.max(take, skip);
				dp[ind][prev+1] = len;
			}
		}
		return dp[0][-1+1];
    }
}

// DFS + memo, Time : O(n*n) = O(n^2), Space : O(n*n) dp + O(n) recursion stack = O(n^2)
class Solution {
	Integer[][] dp;
	int n;
    public int lengthOfLIS(int[] nums) {
        n = nums.length;
		// store the states for vars used in the dfs i.e. ind & prev
		// for ind range goes from 0 to n-1, thus dp[i] = nums.length
		// for prev range goes from -1 to n-1, so we can use co-ordinate shift
		// by 1 place to adjust -1 at 0th index, 0 at 1st index and so on..
		// thus, we can take 1 extra size for dp[i][j] = nums.length+1
        dp = new Integer[n][n+1];
		return dfs(0, -1, nums);
    }
	int dfs(int ind, int prev, int[] nums){
		if(ind == n)
			return 0;
        if(dp[ind][prev+1] != null){
            return dp[ind][prev+1];
        }
		int len = 0;
        int take = 0;
		int skip = dfs(ind+1, prev, nums);
        // if increasing sequence or 
        // first index element then take
		if(prev == -1 || nums[ind] > nums[prev])
		    take = 1 + dfs(ind+1, ind, nums);
		len = Math.max(take, skip);
		return dp[ind][prev+1] = len;
	}
}

// init, TLE, Time : O(2^n), Max Constraint : 1 <= nums.length <= 2500
class Solution {
	int n;
    public int lengthOfLIS(int[] nums) {
        n = nums.length;
		return dfs(0, -1, nums);
    }
	int dfs(int ind, int prev, int[] nums){
		if(ind == n)
			return 0;
		int len = 0;
        int take = 0;
		int skip = dfs(ind+1, prev, nums);
        // if increasing sequence or 
        // first index element then take
		if(prev == -1 || nums[ind] > nums[prev])
		    take = 1 + dfs(ind+1, ind, nums);
		len = Math.max(take, skip);
		return len;
	}
}