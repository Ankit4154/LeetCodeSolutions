// 80. Remove Duplicates from Sorted Array II
// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
class Solution {
    public int removeDuplicates(int[] nums) {
        int left = 0;
        if(nums.length == 1)
            return 1;
        for (int right = 0; right < nums.length; right++) {
            // keep all numbers if less than 2 have been written
            if (left < 2 || nums[right] != nums[left - 2]) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }
}