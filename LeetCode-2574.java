// 2574. Left and Right Sum Differences
// https://leetcode.com/problems/left-and-right-sum-differences
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        int sum = nums[0];
        for(int i=1;i<n;i++){
            leftSum[i] = sum;
            sum += nums[i];
        }

        sum = nums[n-1];
        for(int i=n-2;i >= 0;i--){
            rightSum[i] = sum;
            sum += nums[i];
        }

        for(int i=0;i<n;i++){
            nums[i] = Math.abs(leftSum[i] - rightSum[i]);
        }
        return nums;
    }
}