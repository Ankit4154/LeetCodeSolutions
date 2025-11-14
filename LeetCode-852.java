// 852. Peak Index in a Mountain Array
// https://leetcode.com/problems/peak-index-in-a-mountain-array
class Solution {
    // When on mid, check whether peak will be on left or on the right
	// and increment low / decrement high accordingly.
	// since we are comparing mid-1 and mid+1, 
	// we may get out of bounds exception if the answer is close towards
	// end of the array elements, to avoid that
	// handle those edge cases prior and return the peak if exists,
	// and then start binary search from 2nd most left and right elements.
	public int peakIndexInMountainArray(int[] arr) {
        int low = 1, high = arr.length - 2;
		if(arr[low] > arr[low+1])
			return low;
		if(arr[high] > arr[high-1])
			return high;
		while(low <= high){
			int mid = low + (high-low)/2;
			if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]){
				return mid;
			}else if(arr[mid] < arr[mid-1] ){
				high = mid - 1;
			}else{
				low = mid + 1;
			}
		}
		return -1;
    }
}