// 198. House Robber
// https://leetcode.com/problems/house-robber/
public class Solution {
    private int[] memo;

    public int rob(int[] nums){
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs(nums, 0);
    }

    private int dfs(int[] nums, int i){
        if(i >= nums.length){
            return 0;
        }
        if(memo[i] != -1){
            return memo[i];
        }
        memo[i] = Math.max(dfs(nums, i + 1),
                         nums[i] + dfs(nums, i + 2));
        return memo[i];
    }
}