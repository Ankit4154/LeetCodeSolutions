// 3789. Minimum Cost to Acquire Required Items
// https://leetcode.com/problems/minimum-cost-to-acquire-required-items/
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long costN1 = (long)need1 * cost1;
        long costN2 = (long)need2 * cost2;
        long totalCostN = (long)costN1 + costN2;
        long totalCostB = need1 > need2 ? ((long)need1 * costBoth) : ((long)need2 * costBoth);
        long totalCostB2 = Long.MAX_VALUE;
        if(costBoth < (cost1 + cost2)){
            if(need1 < need2){
                totalCostB2 = (long)need1 * costBoth;
                totalCostB2 += (long)cost2 * (need2 - need1);
            }else if(need2 < need1){
                totalCostB2 = (long)need2 * costBoth;
                totalCostB2 += (long)cost1 * (need1 - need2);
            }
        }
        return Math.min(totalCostN, Math.min(totalCostB, totalCostB2));
    }
}