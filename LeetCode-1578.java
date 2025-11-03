// 1578. Minimum Time to Make Rope Colorful
// https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
// Optimized O(n)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int total = 0;
        for(int i = 1; i < colors.length(); i++) {
            // If two adjacent colors are the same, remove one
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                // Remove the one with smaller time
                total += Math.min(neededTime[i], neededTime[i - 1]);
                
                // Keep the larger one as "previous" for future comparisons
                neededTime[i] = Math.max(neededTime[i], neededTime[i - 1]);
            }
        }
        return total;
    }
}

// O(n)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        if(neededTime.length == 1)
            return 0;
        int left = 0, right = 1, sum = 0;
        while(right < colors.length()){
            int max = neededTime[left];
            int leftSum = neededTime[left];
            boolean sameVal = false;
            while(right < colors.length() && colors.charAt(left) == colors.charAt(right)){
                max = Math.max(max, neededTime[right]);
                leftSum += neededTime[right];
                right++;
            }
            sum += leftSum - max;
            left = right;
            right = left + 1;
        }
        return sum;
    }
}