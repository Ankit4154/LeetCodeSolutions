// 1872. Stone Game VIII
// https://leetcode.com/problems/stone-game-viii/
class Solution {
    public int stoneGameVIII(int[] stoneValue) {
        int n = stoneValue.length;

        int[] prefix = new int[n];
        prefix[0] = stoneValue[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stoneValue[i];
        }

        int best = prefix[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            best = Math.max(best, prefix[i] - best);
        }

        return best;
    }
}
