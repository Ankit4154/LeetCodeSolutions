// 3903. Smallest Stable Index I
// https://leetcode.com/problems/smallest-stable-index-i
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
		int[] min = new int[n];
		int[] max = new int[n];
		int minVal = nums[n-1];
		int maxVal = nums[0];
        for(int i=n-1;i>=0;i--){
            minVal = Math.min(minVal, nums[i]);
			min[i] = minVal;
		}
        for(int i=0;i<n;i++){
            maxVal = Math.max(maxVal, nums[i]);
			max[i] = maxVal;
		}
		for(int i=0;i<n;i++){
			// if stable
			if((max[i] - min[i]) <= k)
                return i;
		}
		return -1;
    }
}