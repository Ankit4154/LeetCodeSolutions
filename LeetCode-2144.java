// 2144. Minimum Cost of Buying Candies With Discount
// https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount
class Solution {
    public int minimumCost(int[] cost) {
        int[] costD = new int[101];
        for(int n : cost)
            costD[n]++;
        int totalCost = 0;
        int candyCount = 0;
        for(int i=100;i>0;i--){
            while(costD[i] > 0){
                if(candyCount == 2){
                    costD[i]--;
                    candyCount = 0;
                    continue;
                }
                totalCost += i;
                costD[i]--;
                candyCount++;
            }
        }
        return totalCost;
    }
}