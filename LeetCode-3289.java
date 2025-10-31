// 3289. The Two Sneaky Numbers of Digitville
// https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] ans = new int[2];
        int idx = 0;

        for (int num : nums) {
            count[num]++;
            if (count[num] == 2) {
                ans[idx++] = num;
            }
        }

        return ans;
    }
}