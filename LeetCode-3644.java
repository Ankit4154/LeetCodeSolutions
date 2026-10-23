// 3644. Maximum K to Sort a Permutation
// https://leetcode.com/problems/maximum-k-to-sort-a-permutation
class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;

        boolean sorted = true;
        for(int i=0;i<n;i++){
            if(nums[i] != i){
                sorted = false;
                break;
            }
        }

        if(sorted)
            return 0;

        int ans = -1;

        for(int i=0;i<n;i++){
            if(nums[i] != i)
                ans &= nums[i];
        }

        return ans;
    }
}