// 930. Binary Subarrays With Sum
// https://leetcode.com/problems/binary-subarrays-with-sum
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int maxA = getSubArrayCount(nums, goal);
        int maxB = getSubArrayCount(nums, goal-1);
        return maxA-maxB;
    }

    public int getSubArrayCount(int[] nums, int goal) {
        if(goal < 0)
            return 0;
        int left = 0, right = 0, sum = 0, count = 0;
        while(right < nums.length){
            sum += nums[right];
            while(sum > goal){
                sum -= nums[left];
                left++;
            }
            count += right-left+1;
            right++;
        }
        return count;
    }
	
	//public int numSubarraysWithSumBrute(int[] nums, int goal) {
        // brute force
		// int left = 0, sum = 0, count = 0;
        // for(int i=left;i<nums.length;i++){
        //     sum = 0;
        //     for(int j=i;j<nums.length;j++){
        //         sum += nums[j];
        //         if(sum == goal){
        //             count++;
        //         }
        //     }
        // }
		// return count;
    //}
	

}
