// 1524. Number of Sub-arrays With Odd Sum
// https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum
class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        long even = 1, odd = 0;
        long sum = 0, ans = 0;

        for(int num : arr) {
            sum += num;

            if(sum % 2 == 0) {
                ans += odd;
                even++;
            } else {
                ans += even;
                odd++;
            }

            ans %= mod;
        }

        return (int)ans;
    }
}
