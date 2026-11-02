// 462. Minimum Moves to Equal Array Elements II
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii
class Solution {
    public int minMoves2(int[] nums) {
        if(nums.length == 1)
            return 0;
        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int moves = 0;

        for(int n : nums){
            moves += Math.abs(n - median);
        }

        return moves;
    }
}