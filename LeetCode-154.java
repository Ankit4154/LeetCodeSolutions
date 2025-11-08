// 154. Find Minimum in Rotated Sorted Array II
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
class Solution {
	// in given array, when you point to mid, there will always be
    // one part of the array sorted and other part will be not sorted.
    // your rotation point will always lie in the part which is not sorted.
    // store the current min value from sorted part and then pivot to the other part.
	// if we can't determine the sorted part due to duplicates
	// shrink the window
    public int findMin(int[] nums) {
		int low = 0, high = nums.length - 1;
		int ans = Integer.MAX_VALUE;
		while(low <= high){
			int mid = low + (high-low)/2;
			// shrink the window
			if(nums[low] == nums[mid] && nums[high] == nums[mid]){
				ans = Math.min(ans, nums[mid]);
				low++;
				high--;
				continue;
			}
			if(nums[low] <= nums[mid]){
				ans = Math.min(ans, nums[low]);
				low = mid + 1; // search other half
			}else {
				ans = Math.min(ans, nums[mid]);
				high = mid - 1; // search other half
			}
		}
		return ans;
    }
}