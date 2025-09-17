// 2760. Longest Even Odd Subarray With Threshold
// https://leetcode.com/problems/longest-even-odd-subarray-with-threshold/

class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxLength = 0;
        int currentLength = 0;
        boolean expectEven = false;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > threshold){
                currentLength = 0;
            }else if(currentLength == 0){
                if(nums[i]%2 == 0){
                    currentLength = 1;
                    expectEven = false;
                }
            }else{
                if(!expectEven && nums[i]%2 != 0 || expectEven && nums[i]%2 == 0){
                    currentLength++;
                    expectEven = !expectEven;
                }else{
                    if(nums[i]%2 == 0){
                        currentLength = 1;
                        expectEven = false;
                    }else{
                        currentLength = 0;
                    }
                }
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }
}