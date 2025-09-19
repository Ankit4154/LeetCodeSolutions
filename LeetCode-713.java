// 713. Subarray Product Less Than K
// https://leetcode.com/problems/subarray-product-less-than-k/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k == 0)
            return 0;
        int left = 0, right = 0, count = 0, product = 1;
        while(right < nums.length){
            product *= nums[right];
            while(left <= right && product >= k){
                product /= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }
}