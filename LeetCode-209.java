// 209. Minimum Size Subarray Sum
// https://leetcode.com/problems/minimum-size-subarray-sum/
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, minLength = 1000000, sum = 0;
        while(right < nums.length){
            sum += nums[right];
            while(sum >= target){
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        if(minLength == 1000000)
            return 0;
        return minLength;
    }
}