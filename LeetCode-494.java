// 494. Target Sum
// https://leetcode.com/problems/target-sum/
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // nums = [1,1,1,1,1], target = 3
		// Output: 5
		// for each element there will be either 2 options + or -
		// if we divide elements into 2 partition s1 and s2
		// both combinedly can form s1 + s2 = sum
		// let's say all my s1 elements are positive 
		// and all s2 elements are negative and we have to form target
		// s1(e1 + e2) + s2(-e3 + -e4) = target
		// s1(e1 + e2) - s2(e3 + e4) = target
		// s1 - s2 = target
		// and from above we also have 1 more equation as s1 + s2 = sum
		// s1 - s2 = target ...1
		// s1 + s2 = sum ...2
		// 2s1 = target + sum
		// s1 = (target + sum) /2
		// so it means if we find count of partition from s1 we can have our required answer
		//
		int sum = 0;
		for(int x : nums)
			sum += x;
		if(Math.abs(target) > sum)
            return 0;
        // if both partition sums is an odd number, it can't be divided equally
        // into 2 partitions
        if((target + sum) % 2 != 0)
            return 0;
        int reqSum = (target + sum)/2; //(3+5)/2=4
        int[][] dp = new int[nums.length+1][reqSum+1];
		//return partitionSum(nums,0, reqSum);
		return partitionSum(nums,0, reqSum, dp);
    }
	//nums = [1,1,1,1,1], target = 3
	// with memoization
	public int partitionSum(int[] nums, int i, int sum, int[][] dp){
		// base case
		if(i == nums.length){
            if(sum == 0)   
                return 1;
            else 
                return 0;
        }
		if(dp[i][sum] != 0)
			return dp[i][sum];
		if(nums[i] <= sum){
			dp[i][sum] = partitionSum(nums, i+1, sum - nums[i], dp) + partitionSum(nums, i+1, sum, dp);
		}else{
			dp[i][sum] = partitionSum(nums, i+1, sum, dp);
		}
		return dp[i][sum];
	}
	// without dp/memoization
	public int partitionSum(int[] nums, int i, int sum){
		int ans = 0;
		// base case
		if(i == nums.length){
            if(sum == 0)   
                return 1;
            else 
                return 0;
        }
		if(nums[i] <= sum){
			ans = partitionSum(nums, i+1, sum - nums[i]) + partitionSum(nums, i+1, sum);
		}else{
			ans = partitionSum(nums, i+1, sum);
		}
		return ans;
	}
}