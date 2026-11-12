// 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
// https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
		if(n == 1)
			return n;
		int left = 0, right = 1;
		int max = 1, count = 0;
		while(right < n){
			int l = nums[left];
			while(right < n && l < nums[right]){
				count = right - left + 1;
				l = nums[right];
				right++;
			}
			max = Math.max(max,count);
			left = right;
			right++;
		}
		left = 0;
        right = 1;
		while(right < n){
			int l = nums[left];
			while(right < n && l > nums[right]){
				count = right - left + 1;
				l = nums[right];
				right++;
			}
			max = Math.max(max,count);
			left = right;
			right++;
		}
		return max;
    }
}