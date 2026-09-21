// 3524. Find X Value of Array I
// https://leetcode.com/problems/find-x-value-of-array-i
class Solution {
    public long[] resultArray(int[] nums, int k) {
		int n = nums.length;

        long[] out = new long[k];
        long[] prev = new long[k];

        for(int num : nums){

            long[] curr = new long[k];

            // Start a new subarray with just this number
            curr[num % k]++;

            // Extend all subarrays ending at previous index
            for(int r = 0; r < k; r++){
                if(prev[r] > 0){
                    int newRemainder = (r * (num % k)) % k;
                    curr[newRemainder] += prev[r];
                }
            }

            // All subarrays ending here contribute to answer
            for(int r = 0; r < k; r++){
                out[r] += curr[r];
            }

            prev = curr;
        }

        return out;
    }
	
}

// naive, errored
class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
		int left = 0, right = 0;
		long[] out = new long[k];
		while(right < n){
			int num = nums[right];
			out[num % k]++;
			right++;
		}
		int prod = nums[0];
		right = 1;
		while(right < n){
			int num = nums[right];
			prod *= num;
			out[prod % k]++;
			right++;
		}
		while(left < n-2){
			int num = nums[left];
			prod /= num;
			out[prod % k]++;
			left++;
		}
		return out;
    }
	
}