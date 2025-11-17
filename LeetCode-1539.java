// 1539. Kth Missing Positive Number
// https://leetcode.com/problems/kth-missing-positive-number/
class Solution {
	// since the array is sorted and elements will be starting with 1
	// we can know at any given index value, how may elements are missing.
	// Using this information we need to find range where the missing 
	// k value is present.
	// applying binary search on the elements, the high will point to the
	// min no. where missing_numbers < k 
	// and low will point to the max no. where missing_numbers >= k
	// thus the answer can be arr[high] + k - missing_numbers
	// resolving missing_numbers further it becomes
	// arr[high] + k - (arr[high] - high - 1)
	// arr[high] + k - arr[high] + high + 1
	// k + high + 1, or can also be written as k + low since low = high + 1
	// Notes : returning value of present at index high or low can
	// result in index out of bounds error. To avoid that, in this 
	// question, we were able to derive the result as an equation
	// and cancel out 2 opposite(+/-) arr[high].
    public int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
		int miss = 0;
		while(low <= high){
			int mid = low + (high-low)/2;
            miss = arr[mid] - (mid + 1);
            if(miss < k){
                low = mid + 1;                
            }else{
                high = mid - 1;
            }
		}
		return k + high + 1;
    }
}