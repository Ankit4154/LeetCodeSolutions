// 1848. Minimum Distance to the Target Element
// https://leetcode.com/problems/minimum-distance-to-the-target-element
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int left = start;
		int right = start;
		while(left >= 0 && right < nums.length){
			if(left == right && nums[left] == target){
				return Math.abs(left - start);
			}else if(nums[left] == target){
				return Math.abs(left - start);
			}else if(nums[right] == target){
				return Math.abs(right - start);
			}
			left--;
			right++;
		}
		while(left >= 0){
			if(nums[left] == target){
				return Math.abs(left - start);
			}
			left--;
		}
		while(right < nums.length){
			if(nums[right] == target){
				return Math.abs(right - start);
			}
			right++;
		}
        return 0;
    }
}