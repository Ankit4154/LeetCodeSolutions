// 189. Rotate Array
// https://leetcode.com/problems/rotate-array/
// rotate array in place without using extra space
// 1 approach is to go via cyclic dependency loop incrementing by k untill you reach the starting index again.
// another approach is to reverse entire array, then reverse first k elements and then reverse remaining nums.length - k elements
// always mod k if k > nums.length or if i+k > nums.length
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if(k == 0 || nums.length == 1)
            return;
        for(int i=0,j=nums.length-1; i<j; i++,j--){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        for(int i=0,j=k-1; i<j; i++,j--){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        for(int i=k,j=nums.length-1; i<j; i++,j--){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}