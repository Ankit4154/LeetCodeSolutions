// 3739. Count Subarrays With Majority Element II
// https://leetcode.com/problems/count-subarrays-with-majority-element-ii
class Solution {
    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        void add(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int offset = n + 2;
        Fenwick ft = new Fenwick(2 * n + 5);

        long ans = 0;
        int pref = 0;

        ft.add(offset, 1); // prefix sum = 0

        for (int x : nums) {
            if (x == target)
                pref++;
            else
                pref--;

            ans += ft.query(pref + offset - 1);
            ft.add(pref + offset, 1);
        }

        return ans;
    }
}