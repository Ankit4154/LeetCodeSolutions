// 3870. Count Commas in Range
// https://leetcode.com/problems/count-commas-in-range
class Solution {
    public int countCommas(int n) {
        if(n<=999)
           return 0;
        return n-999;
    }
}