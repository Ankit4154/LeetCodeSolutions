// 1658. Minimum Operations to Reduce X to Zero
// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/submissions/1766399385/
class Solution {
    public int minOperations(int[] nums, int x) {
        int maxLength = -1, left = 0, right = 0, sum = 0, target = 0;
        for(int i=0;i<nums.length;i++){
            sum+= nums[i];
        }
        target = sum - x;
        sum = 0;
        while(right < nums.length){
            sum += nums[right];
            while(sum > target && left<=right){
                sum -= nums[left];
                left++;
            }
            if(sum == target){
                maxLength = Math.max(maxLength,right - left + 1);
            }
            right++;
        }
        if(maxLength != -1)
            return nums.length - maxLength;
        else
            return -1;
    }
}