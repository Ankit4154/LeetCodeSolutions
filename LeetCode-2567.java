// 2567. Minimum Score by Changing Two Elements
// https://leetcode.com/problems/minimum-score-by-changing-two-elements
class Solution {
    public int minimizeSum(int[] nums) {
       int n = nums.length;

        if(n <= 3)
            return 0;

        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;

        ans = Math.min(ans, nums[n-1] - nums[2]);
        ans = Math.min(ans, nums[n-3] - nums[0]);
        ans = Math.min(ans, nums[n-2] - nums[1]);

        return ans;  
    }
}