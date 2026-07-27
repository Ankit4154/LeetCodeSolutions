// 1464. Maximum Product of Two Elements in an Array
// https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array
class Solution {
    public int maxProduct(int[] nums) {
        int firstMax = 0;
		int secondMax = 0;
		for(int i=0;i<nums.length;i++){
			if(firstMax < nums[i]){
				secondMax = firstMax;
				firstMax = nums[i];
			}else if(secondMax < nums[i]){
				secondMax = nums[i];
			}
		}		
		return (firstMax-1)*(secondMax-1);
    }
}