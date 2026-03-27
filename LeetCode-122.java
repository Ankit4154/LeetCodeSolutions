// 122. Best Time to Buy and Sell Stock II
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
// iterative, Space : O(1) , Time : O(n)
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int aheadBuy = 0;   // dp[ind+1][1]
        int aheadSell = 0;  // dp[ind+1][0]

        for(int ind = n-1; ind >= 0; ind--){
            int currBuy = Math.max(-prices[ind] + aheadSell, aheadBuy);
            int currSell = Math.max(prices[ind] + aheadBuy, aheadSell);

            aheadBuy = currBuy;
            aheadSell = currSell;
        }

        return aheadBuy; // starting state: canBuy = 1
    }
}

// memoized, Space : O(n) , Time : O(n)
class Solution {
	int[][] dp;
    public int maxProfit(int[] prices) {
		// prices = [7,1,5,3,6,4] // o/p : 7 , (5-1)+(6-3)=7
        dp = new int[prices.length][2];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i], -1);
		return solve(0, prices, 1);
    }
	int solve(int ind, int[] prices, int canBuy){
		if(ind == prices.length){
			return 0;
		}
		if(dp[ind][canBuy] != -1)
			return dp[ind][canBuy];
		int take = 0;
		int skip = 0;
		if(canBuy == 1){
			take = -prices[ind] + solve(ind+1, prices, 0); //buy
			skip = solve(ind+1, prices, 1); //skip
		}else{
			take = prices[ind] + solve(ind+1, prices, 1); //sell
			skip = solve(ind+1, prices, 0); //hold
		}
		return dp[ind][canBuy] = Math.max(take, skip);
		
	}
}

// init , TLE
class Solution {
	int globalMax = -2;
    public int maxProfit(int[] prices) {
		// prices = [7,1,5,3,6,4] // o/p : 7 , (5-1)+(6-3)=7
        solve(0, prices, 0);
		return globalMax;
    }
	void solve(int ind, int[] prices, int max){
		if(ind == prices.length-1){
			globalMax = Math.max(max, globalMax);
			return;
		}
		for(int i=ind+1;i<prices.length;i++){
			if(prices[ind] < prices[i]){
				int diff = prices[i] - prices[ind];
				solve(i, prices, diff + max);
			}else{
				solve(i, prices, max);
			}
		}
	}
}
