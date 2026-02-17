// 31. Next Permutation
// https://leetcode.com/problems/next-permutation/
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 1. Find first decreasing element
        while(i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if(i >= 0) {
            int j = n - 1;

            // 2. Find just larger element
            while(nums[j] <= nums[i]) {
                j--;
            }

            // 3. Swap
            swap(nums, i, j);
        }

        // 4. Reverse right part
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while(left < right) {
            swap(nums, left++, right--);
        }
    }
}