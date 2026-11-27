// 3946. Maximum Number of Items From Sale I
// https://leetcode.com/problems/maximum-number-of-items-from-sale-i
class Solution {
	int n;
    int minPrice;
    int[][] dp;

    public int maximumSaleItems(int[][] items, int budget) {
        n = items.length;
        minPrice = Integer.MAX_VALUE;

        int[] free = new int[n];

        // Calculate free items for each item type
        for(int i=0;i<n;i++){
            minPrice = Math.min(minPrice, items[i][1]);

            for(int j=0;j<n;j++){
                if(i != j && items[j][0] % items[i][0] == 0){
                    free[i]++;
                }
            }
        }

        dp = new int[n][budget+1];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }

        return solve(0, budget, items, free);
    }

    int solve(int ind, int budget, int[][] items, int[] free){

        if(ind == n){
            // Purchase additional copies using remaining budget
            return budget/minPrice;
        }

        if(dp[ind][budget] != -1){
            return dp[ind][budget];
        }

        // Skip current item type
        int skip = solve(ind+1, budget, items, free);

        int take = 0;
        
        int price = items[ind][1];

        // Take current item type once
        if(budget >= price){
            take = 1 + free[ind] +
                   solve(ind+1, budget-price, items, free);
        }

        return dp[ind][budget] = Math.max(take, skip);
    }
}