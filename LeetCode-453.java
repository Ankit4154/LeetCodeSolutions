// 453. Minimum Moves to Equal Array Elements
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
// optim
class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int n : nums){
            min = Math.min(min, n);
        }
        int diff = 0;
        for(int n : nums){
            diff += n - min;
        }
        return diff;
    }
}
// init, naive
class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int diff = 0;
        for(int i=nums.length-1;i>=0;i--){
            diff += nums[i]-nums[0];
        }
        return diff;
    }
}