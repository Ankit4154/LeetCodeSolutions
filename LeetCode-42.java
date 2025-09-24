// 42. Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/
class Solution {
    public int trap(int[] height) {
        if(height.length == 0)
            return 0;
        int left = 0, right = height.length -1;
        int maxLeft = height[left], maxRight = height[right], maxSum = 0;
        while(left < right){
            if(maxLeft < maxRight){
                left++;
                maxLeft = Math.max(maxLeft, height[left]);
                maxSum += maxLeft - height[left];
            }else{
                right--;
                maxRight = Math.max(maxRight, height[right]);
                maxSum += maxRight - height[right];
            }
        }
        return maxSum;
    }
}