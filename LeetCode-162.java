// 162. Find Peak Element
// https://leetcode.com/problems/find-peak-element/
class Solution {
	// if mid-1 < mid value > mid+1 holds true then 
	// that's the peak element, return mid index
	// if not found, check whether we are on increasing curve
	// or decreasing curve. if mid value > mid-1 value
	// our mid is at increasing curve moving towards peak
	// so increment low to mid + 1
	// similarly if mid value < mid - 1 or mid+1 < mid value
	// our mid is at decreasing slope, pivot high to mid - 1
	// to move towards the slope behind or can also pivot forward
	// to reach out to the next or end peak element.
    public int findPeakElement(int[] nums) {
		if(nums.length == 1)
			return 0;
        if(nums[0] > nums[1])
            return 0;
        if(nums[nums.length-1] > nums[nums.length-2])
            return nums.length-1;
		int low = 1, high = nums.length - 2, peak = 0;
		while(low <= high){
			int mid = low + (high-low)/2;
			if(nums[mid-1] < nums[mid] && nums[mid] > nums[mid+1]){
				return mid;
			}else{
				if(nums[mid] >= nums[mid-1]){
					low = mid + 1;
				}else {
					high = mid - 1;
				}
			}
		}
		return peak;
    }
}