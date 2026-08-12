// 2958. Length of Longest Subarray With at Most K Frequency
// https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
		int left = 0, right = 0;
		int max = 1;
		while(right < nums.length){
			int key = nums[right];
			map.put(key, map.getOrDefault(key, 0) + 1);
			while(map.get(key) > k){
				int leftKey = nums[left];
				map.put(leftKey, map.get(leftKey) - 1);
				left++;
			}
			max = Math.max(max, right-left+1);
			right++;
		}
		return max;
    }
}