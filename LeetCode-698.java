// 698. Partition to K Equal Sum Subsets
// https://leetcode.com/problems/partition-to-k-equal-sum-subsets
// optimized
// Time : O(k*2^n), Space : O(n)
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
		for(int x : nums)
			sum += x;
		if(sum%k != 0)
			return false;
        Arrays.sort(nums);
        reverse(nums);
        int target = sum/k;
        if(nums[0] > target)
            return false;
		boolean[] used = new boolean[nums.length];
        return solve(0, nums, 0, target, used, k);
    }
	
    private boolean solve(int start, int[] nums, int currSum, int target, boolean[] used, int k){
		if(k == 1)
			return true;
		if(currSum == target){
			return solve(0, nums, 0, target, used, k-1);
		}
		for(int i=start;i<nums.length;i++){
            if(used[i])
                continue;
            if(nums[i]+currSum > target)
                continue;
            // skip duplicates
            if(i > start && nums[i] == nums[i - 1] && !used[i - 1])
                continue;
            used[i] = true;
            if(solve(i+1, nums, nums[i]+currSum, target, used, k))
                return true;
            used[i] = false;
            // pruning
            // If currSum == 0 and placing nums[i] fails, 
            // don’t try other numbers at this level.
            if(currSum == 0)
                return false;
            //After adding an element that exactly 
            // reaches target but fails deeper:
            if(currSum + nums[i] == target)
                return false;
		}
		return false;
	}
    private void reverse(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            l++; r--;
        }
    }
}
// init , Time : O(k*2^n), Space : O(n)
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
		for(int x : nums)
			sum += x;
		if(sum%k != 0)
			return false;
		boolean[] used = new boolean[nums.length];
        return solve(0, nums, 0, sum/k, used, k);
    }
	
    private boolean solve(int start, int[] nums, int currSum, int target, boolean[] used, int k){
		if(k == 1)
			return true;
		if(currSum == target){
			return solve(0, nums, 0, target, used, k-1);
		}
		for(int i=start;i<nums.length;i++){
            if(used[i])
                continue;
            if(nums[i]+currSum > target)
                continue;
            used[i] = true;
            if(solve(i+1, nums, nums[i]+currSum, target, used, k))
                return true;
            used[i] = false;
		}
		return false;
	}
}