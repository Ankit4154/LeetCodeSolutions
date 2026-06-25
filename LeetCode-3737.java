// 3737. Count Subarrays With Majority Element I
// https://leetcode.com/problems/count-subarrays-with-majority-element-i
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        boolean present = false;
		for(int i=0;i<nums.length;i++){
            if(nums[i] == target){
                present = true;
                break;
            }
		}
        if(!present)
            return 0;
		int subLength = 0;
		int majority = 0;
		int count = 0;
		for(int i=0;i<nums.length;i++){
            subLength = 0;
            majority = 0;
			for(int j=i; j<nums.length;j++){
				if(nums[j] == target){
					majority++;
				}
				subLength++;
				int subHalf = subLength/2 + 1;
				if(majority >= subHalf){
					count++;
				}
			}
		}
		return count;
    }
}
