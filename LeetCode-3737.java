// 3737. Count Subarrays With Majority Element I
// https://leetcode.com/problems/count-subarrays-with-majority-element-i
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<nums.length;i++){
            // store all the frequencies of each element
			map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
		}
        if(!map.containsKey(target))
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