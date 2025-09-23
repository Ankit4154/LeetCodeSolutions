// 11. Container With Most Water
// https://leetcode.com/problems/container-with-most-water/
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1, area = 0, maxArea = 0;
        while(left < right){
            area = (right - left) * Math.min(height[right], height[left]);
            maxArea = Math.max(maxArea, area);
            if(height[right] < height[left]){
                right--;
            }else if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }
}