// 3904. Smallest Stable Index II
// https://leetcode.com/problems/smallest-stable-index-ii
// optim
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
		int[] min = new int[n];
		min[n-1] = nums[n-1];
		int maxVal = nums[0];
        for(int i=n-2;i>=0;i--){
            min[i] = Math.min(min[i+1], nums[i]);
		}
        for(int i=0;i<n;i++){
            maxVal = Math.max(maxVal, nums[i]);
			if(maxVal - min[i] <= k)
				return i;
		}
		return -1;
    }
}

// init
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