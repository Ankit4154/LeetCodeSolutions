// 55. Jump Game
// https://leetcode.com/problems/jump-game
// Greedy , O(n)
class Solution {
    public boolean canJump(int[] nums) {
       int n = nums.length;
	   int maxJumps = 0;
	   for(int i=0;i<n;i++){
			if(i > maxJumps)
				return false;
			maxJumps = Math.max(maxJumps, i+nums[i]);
			if(maxJumps >= n-1)
				return true;
	   }
	   return true;
    }
}

// slight optimum, still O(n^2)
class Solution {
	Boolean[] dp;
    public boolean canJump(int[] nums) {
        dp = new Boolean[nums.length];
		return solve(0, nums);
    }
	boolean solve(int ind, int[] nums){
		// if jump goes beyond , invalid jump
		if(ind >= nums.length)
			return false;
		
		// if jump lands on last index
		if(ind == nums.length-1)
			return true;
		// if already visited/stored
		if(dp[ind]!=null)
			return dp[ind];
        
		int maxJumps = nums[ind];
		// try bigger jumps first
		for(int jump=maxJumps;jump >= 1;jump--){
			int next = ind + jump;
			
			// direct reach check
			if(next >= nums.length-1)
				return dp[ind] = true;
			
			// skip if already known false
			if(dp[next]!=null && dp[next] == false)
				continue;
			
			if(solve(next, nums)){
				return dp[ind] = true;
			}
		}
		return dp[ind] = false;
	}
}

// init
class Solution {
	Boolean[] dp;
    public boolean canJump(int[] nums) {
        dp = new Boolean[nums.length];
		return solve(0, nums);
    }
	boolean solve(int ind, int[] nums){
		// if jump goes beyond , invalid jump
		if(ind >= nums.length)
			return false;
		
		// if jump lands on last index
		if(ind == nums.length-1)
			return true;
		// if already visited/stored
		if(dp[ind]!=null)
			return dp[ind];
		int maxJumps = nums[ind];
		for(int jump=1;jump <= maxJumps;jump++){
			if(solve(ind+jump, nums)){
				return dp[ind] = true;
			}
		}
		return dp[ind] = false;
	}
}