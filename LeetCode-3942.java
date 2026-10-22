// 3942. Minimum Operations to Sort a Permutation
// https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Case 1: nums is a rotation of sorted array
        boolean normal = true;
        for(int i = 1; i < n; i++) {
            if(nums[i] != (nums[0] + i) % n) {
                normal = false;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if(normal) {
            int k = (n - nums[0]) % n;

            // Rotate left k times, or reverse twice
            ans = Math.min(k, n - k + 2);
        }

        // Case 2: reversed nums is a rotation of sorted array
        boolean reverse = true;
        for(int i = 0; i < n; i++) {
            int val = nums[n - 1 - i];

            if(val != (nums[n - 1] + i) % n) {
                reverse = false;
                break;
            }
        }

        if(reverse) {
            int k = (n - nums[n - 1]) % n;

            // Reverse then rotate, or rotate then reverse
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}