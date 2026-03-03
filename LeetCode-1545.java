// 1545. Find Kth Bit in Nth Binary String
// https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
class Solution {
    public char findKthBit(int n, int k) {
        if(n == 1)
            return '0';
        int mid = 1 << (n - 1);  // 2^(n-1)
        if(k == mid)
            return '1';
        if(k < mid){
            return findKthBit(n - 1, k);
        }
        // Mirror position
        char bit = findKthBit(n - 1, (1 << n) - k);

        // Invert the bit
        return bit == '0' ? '1' : '0';
    }
}