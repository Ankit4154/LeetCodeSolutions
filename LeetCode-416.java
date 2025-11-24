// 416. Partition Equal Subset Sum
// https://leetcode.com/problems/partition-equal-subset-sum/
class Solution {
    public boolean canPartition(int[] nums) {
        // Input: nums = [1,5,11,5]
		// base case if sum is odd then not possible
		int sum = 0;
		for(int x : nums)
			sum+=x;
		if(sum%2 != 0)
			return false;
		// find out the max possible answer and min possible answer
		// to prove : s1 + s2 = sum
		// We know that sum will always be an even number
		// and both of our sum s1 and s2 will be equal, should be equal
		// which means s1 + s1 = sum, 2s1 = sum, s1 = sum/2
		// So we need to find a partition where s1 = sum/2 = 22/2 = 11
		// Initialize dp
        int target = sum / 2;
		Boolean[][] dp = new Boolean[nums.length+1][target+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                if(i==0)
                    dp[i][j] = false;
                if(j==0)
                    dp[i][j] = true;
            }
        }
		for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[i].length;j++){
                boolean skip = dp[i-1][j];
                boolean take = false;
                if(nums[i-1] <= j)
                    take = dp[i-1][j-nums[i-1]];
                dp[i][j] = take || skip;
            }
        }
        return dp[nums.length][target];
		//return partitionSum(nums, 0, target, dp);
    }
	public boolean partitionSum(int[] nums, int i, int sum, Boolean[][] dp){
		if(i == nums.length)
			return false;
		if(sum == 0)
			return true;
        if(dp[i][sum] != null)
            return dp[i][sum];
        boolean take = false;
        if(nums[i] <= sum){
            take = partitionSum(nums, i+1, sum - nums[i], dp);
        }        
        boolean skip = partitionSum(nums, i+1, sum, dp);
		return dp[i][sum] = take || skip;
	}
}