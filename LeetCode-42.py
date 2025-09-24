# 42. Trapping Rain Water
# https://leetcode.com/problems/trapping-rain-water/
class Solution:
    def trap(self, height: List[int]) -> int:

        if height == 0:
            return 0

        left, right = 0, len(height)-1
        maxLeft, maxRight = height[left], height[right]
        maxSum = 0

        while left < right:
            if maxLeft < maxRight:
                left += 1
                maxLeft = max(maxLeft, height[left])
                maxSum += maxLeft - height[left]
            else:
                right -= 1
                maxRight = max(maxRight, height[right])
                maxSum += maxRight - height[right]
        
        return maxSum
