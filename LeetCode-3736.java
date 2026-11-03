// 3736. Minimum Moves to Equal Array Elements III
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii
class Solution {
    public int minMoves(int[] nums) {
        if(nums.length == 1)
			return 0;
		int max = 0;
		int sum = 0;
		for(int x : nums){
			max = Math.max(max, x);
		}
		for(int x : nums){
			sum += max - x;
		}
		return sum;
    }
}