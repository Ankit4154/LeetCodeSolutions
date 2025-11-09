// 540. Single Element in a Sorted Array
// https://leetcode.com/problems/single-element-in-a-sorted-array/description
class Solution {
	// Observation: before the single element
	// all values are following even,odd index pattern
	// and after single element all values are following 
	// odd,even index pattern
	// Example : [1,1,2,2,3,4,4]
	// (even,odd) pairs before single element 3 : (1,1)(2,2)
	// (odd,even) pairs after single element 3 : (4,4)
	// To find answer, check if any previous value and next value
	// to the mid are not equal to mid value. if found return.
	// else if the mid is before single element, determine the pattern
	// if mid is at odd index and the value before mid 
	// matches with mid value, then pivot searching the other half
	// Perform opposite operation if mid is at even index.
	//
	// To avoid index out of bounds exception with mid-1 and mid+1 checks,
	// pre-compute for nums[0] and nums[n-1] values and return if
	// single element is found already.
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length, ans = 0;
        if(nums.length == 1)
            return nums[0];
		while(low <= high){
			if(nums[0] != nums[1])
				return nums[0];
			if(nums[nums.length-1] != nums[nums.length-2])
				return nums[nums.length-1];
			int mid = low + (high-low)/2;
			if(nums[mid-1] != nums[mid] && nums[mid+1] != nums[mid]){
				return nums[mid];
			}else{
				if(mid % 2 != 0){
					if(nums[mid-1] == nums[mid])
						low = mid + 1;
					else
						high = mid - 1;
				}else{
					if(nums[mid-1] == nums[mid])
						high = mid - 1;
					else
						low = mid + 1;
				}
			}
		}
		return -1;
    }
}