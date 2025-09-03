// 1004. Max Consecutive Ones III
// https://leetcode.com/problems/max-consecutive-ones-iii/
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, maxFrequency = 0, maxLength = 0;
        while(right < nums.length){
            if(nums[right] == 1)
                maxFrequency++;
            if((right-left+1) - maxFrequency <= k){
                 maxLength = Math.max(maxLength, (right-left+1));
            }else{
                if(nums[left] == 1)
                    maxFrequency--;
                left++;
            }
            right++;
        }
        return maxLength;
    }
}