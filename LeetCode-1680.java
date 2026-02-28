// 1680. Concatenation of Consecutive Binary Numbers
// https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers
class Solution {
    public int concatenatedBinary(int n) {
        long mod = 1_000_000_007;
        long res = 0;
        int bitLength = 0;

        for(int i = 1; i <= n; i++){
            // if i is power of 2, increase bit length
            if((i & (i - 1)) == 0){
                bitLength++;
            }
            res = ((res << bitLength) + i) % mod;
        }

        return (int) res;
    }
}