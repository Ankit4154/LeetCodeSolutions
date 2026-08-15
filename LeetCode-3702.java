// 3702. Longest Subsequence With Non-Zero Bitwise XOR
// https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor
class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;

        for(int num : nums){
            xor = xor ^ num;
        }

        if(xor != 0)
            return n;

        for(int num : nums){
            if(num != 0)
                return n - 1;
        }

        return 0;
    }
}