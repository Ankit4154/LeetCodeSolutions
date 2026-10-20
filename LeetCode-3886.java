// 3886. Sum of Sortable Integers
// https://leetcode.com/problems/sum-of-sortable-integers
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for(int k = 1; k <= n; k++) {
            if(n % k != 0)
                continue;

            boolean valid = true;

            for(int start = 0; start < n; start += k) {
                if(!isRotation(nums, sorted, start, k)) {
                    valid = false;
                    break;
                }
            }

            if(valid)
                ans += k;
        }

        return ans;
    }

    private boolean isRotation(int[] nums, int[] sorted, int start, int k) {
        // Check whether nums[start..start+k-1] is a
        // cyclic rotation of sorted[start..start+k-1].

        for(int shift = 0; shift < k; shift++) {
            boolean match = true;

            for(int j = 0; j < k; j++) {
                if(nums[start + j] != sorted[start + (j + shift) % k]) {
                    match = false;
                    break;
                }
            }

            if(match)
                return true;
        }

        return false;
    }
}