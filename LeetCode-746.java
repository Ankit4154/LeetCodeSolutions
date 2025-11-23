// 746. Min Cost Climbing Stairs
// https://leetcode.com/problems/min-cost-climbing-stairs
class Solution {
	// Approach : consume cost at index 0 and move 
	// 1 step ahead and 2 step ahead.
	// calculate the cost at the index 
	// and store it in min if it is mininum min = Math.min(cost[i], min)
	// move to the next index adding up the cost to sum
	// base case 1) when i > cost.length-1
	// optimization if sum > min return 0;
	// else return sum
    // optimized
    public int minCostClimbingStairs(int[] cost) {
        for (int i = cost.length - 3; i >= 0; i--) {
                cost[i] += Math.min(cost[i + 1], cost[i + 2]);
            }
        return Math.min(cost[0], cost[1]);
        //return int minCostClimbingStairs2(cost);
    }
    
    public int minCostClimbingStairs2(int[] cost) {
        //Input: cost = [1,100,1,1,1,100,1,1,100,1]
		//Output: 6
		int[] dp = new int[cost.length+1];
		for(int i=0;i<dp.length;i++)
			dp[i] = -1;
		return Math.min(getCost(cost, 0, dp), getCost(cost, 1, dp));
    }
	public int getCost(int[] cost, int i, int[] dp){
		// base case
		if(i >= cost.length)
			return 0;
		if(dp[i] != -1)
			return dp[i];
		dp[i] = cost[i] + Math.min(getCost(cost, i + 1, dp),
                                  getCost(cost, i + 2, dp));
		return dp[i];
	}
    
}