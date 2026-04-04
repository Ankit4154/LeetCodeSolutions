// 45. Jump Game II
// https://leetcode.com/problems/jump-game-ii
// Greedy, O(1)
class Solution {
	Integer[] dp;
	int n;
    public int jump(int[] nums) {
        n = nums.length;
		dp = new Integer[n];
		return solve(0, nums);
    }
	int solve(int ind, int[] nums){
		// if jump goes beyond, invalid jump
		if(ind >= n)
			return Integer.MAX_VALUE;
		if(ind == n-1)
			return 0;
		if(dp[ind]!= null)
			return dp[ind];
		int maxJump = nums[ind];
        int min = Integer.MAX_VALUE;
		for(int jump = maxJump; jump >= 1; jump--){
			int next = ind + jump;
			if(next == n-1)
				return dp[ind] = 1;
            if(next >= n)
                continue;
			// skip if already known or maximum
			if(dp[next]!=null && dp[next] == Integer.MAX_VALUE)
				continue;
			int val = solve(next, nums);
			if(val == Integer.MAX_VALUE)
				continue;
			min = Math.min(min, 1+val);
		}
		return dp[ind] = min;
	}
}
//init, dp, O(n^2)
class Solution {
	Integer[] dp;
	int n;
    public int jump(int[] nums) {
        n = nums.length;
		dp = new Integer[n];
		return solve(0, nums);
    }
	int solve(int ind, int[] nums){
		// if jump goes beyond, invalid jump
		if(ind >= n)
			return Integer.MAX_VALUE;
		if(ind == n-1)
			return 0;
		if(dp[ind]!= null)
			return dp[ind];
		int maxJump = nums[ind];
        int min = Integer.MAX_VALUE;
		for(int jump = maxJump; jump >= 1; jump--){
			int next = ind + jump;
			if(next == n-1)
				return dp[ind] = 1;
            if(next >= n)
                continue;
			// skip if already known or maximum
			if(dp[next]!=null && dp[next] == Integer.MAX_VALUE)
				continue;
			int val = solve(next, nums);
			if(val == Integer.MAX_VALUE)
				continue;
			min = Math.min(min, 1+val);
		}
		return dp[ind] = min;
	}
}