// Q1. Minimum Absolute Difference Between Two Values
// https://leetcode.com/contest/biweekly-contest-179
class Solution {
    public int minAbsoluteDifference(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if((nums[i] == 1 && nums[j] == 2) || (nums[i] == 2 && nums[j] == 1)){
                    min = Math.min(min, Math.abs(i-j));
                }
            }
        }
        if(min == Integer.MAX_VALUE)
            return -1;
        return min;
    }
}©leetcode