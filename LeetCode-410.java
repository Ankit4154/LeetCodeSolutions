// 410. Split Array Largest Sum
// https://leetcode.com/problems/split-array-largest-sum
class Solution {
	// First we identify the possible values / range for 
	// the required answer, which is max element in the array
	// and the sum of all elements
	// [7,2,5,10,8], k = 2
	// 7		 	2+5+10+8=25
	// 7+2=9   		5+10+8=23
	// 7+2+5=14  	10+8=18
	// 7+2+5+10=24  8
	// output : 18
	// [1,2,3,4,5], k = 2
	// 1	       2+3+4+5=14
	// 1+2=3       3+4+5=12
	// 1+2+3=6     4+5=9
	// 1+2+3+4=10  5
	// output : 9
	// In above examples we have calculated max element 10 and 5, because
	// if k = nums.length(k=5), then the larget sum (max element will be the answer)
	// else if k = 1, then the sum of all contiguous elements will be the larget sum / answer
	// and the max sum of all elements in above are 32 and 15
	// so our answer for perfect partitioning will lie between these range
	// 10-32 and 5-15 for above example
	// In method canSplit, we will try to assign/split the partition 
	// against the picked number n from the range.
	// if the split partition is exactly equals to k, 
	// that's the answer in linear search time.
	// For binary search, if the split partition size < k
	// search on the left half of mid as we need more split partitions
	// if the split partition size > k
	// search on right half of mid as we need less split partition, 
	// when low and high cross each other, and loop terminates
	// the low will be pointing to the required answer; 
	
    public int splitArray(int[] nums, int k) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
        if(k > nums.length)
            return 0;
		for(int x: nums){
			sum += x;
			max = Math.max(max, x);
		}
		// Linear search, trying each possible value
		// for(int i=max;i<=sum;i++){
		// 	int partitions = canSplit(nums, i);
		// 	if(partitions == k)
		// 		return i;
		// }
        int low = max, high = sum;
        while(low <= high){
			int mid = low + (high-low)/2;
			int partitions = canSplit(nums,mid);
			if(partitions > k){
				low = mid + 1;
			}else {
				high = mid - 1;
			}
		}
		return low;
    }
	
	public int canSplit(int[] nums, int n){
		int partition = 1;
		int extra = 0;
		for(int i=0;i<nums.length;i++){
			if(nums[i]+extra > n){
				partition++;
				extra = nums[i];
			}else{
				extra += nums[i];
			}
		}
		return partition;
	}
}