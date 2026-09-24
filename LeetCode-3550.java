// 3550. Smallest Index With Digit Sum Equal to Index
// https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index
class Solution {
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
			if(getDigitsSum(nums[i]) == i)
				return i;
		}
		return -1;
    }
	private int getDigitsSum(int n){
		int sum = 0;
		while(n > 0){
			int rem = n % 10;
			sum += rem;
			n /= 10;
		}
		return sum;
	}
}