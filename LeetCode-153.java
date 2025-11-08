// 153. Find Minimum in Rotated Sorted Array
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

public class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length -1;
		int ans = Integer.MAX_VALUE;
        // in given array, when you point to mid, there will always be
        // one part of the array sorted and other part will be not sorted.
        // your rotation point will always lie in the part which is not sorted.
        // store the current min value from sorted part and then pivot to the other part.
		while(low <= high){
            // when the search space is already sorted then
			// nums[low] will always be the minimum
            if (nums[low] < nums[high]) {
                ans = Math.min(ans, nums[low]);
                break;
            }
			int mid = low + (high-low)/2;
			// if left half is sorted
			if(nums[low] <= nums[mid]){
				ans = Math.min(ans, nums[low]);
				low = mid + 1; // pivot to other un-sorted/sorted half
			}else{ // if right half is sorted
				ans = Math.min(ans, nums[mid]);
				high = mid - 1; // pivot to other un-sorted/sorted half
			}
		}
        return ans;
    }
}


// public class Solution {
//     public int findMin(int[] nums) {
//         int l = 0;
//         int r = nums.length - 1;
//         int res = nums[0];

//         while (l <= r) {
//             if (nums[l] < nums[r]) {
//                 res = Math.min(res, nums[l]);
//                 break;
//             }

//             int m = l + (r - l) / 2;
//             res = Math.min(res, nums[m]);
//             if (nums[m] >= nums[l]) {
//                 l = m + 1;
//             } else {
//                 r = m - 1;
//             }
//         }
//         return res;
//     }

// }

