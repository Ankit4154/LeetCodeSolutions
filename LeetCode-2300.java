// 2300. Successful Pairs of Spells and Potions
// https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] ans = new int[spells.length];
        Arrays.sort(potions);
        int n = potions.length;

        for (int i = 0; i < spells.length; i++) {
            int spell = spells[i];
            int idx = getLowestPotion(spell, potions, success);
            if (idx == n) ans[i] = 0; // no successful potion
            else ans[i] = n - idx;    // count of successful potions
        }
        return ans;
    }
    public int getLowestPotion(int spell, int[] potions, long success){
        int low = 0, high = potions.length - 1, ans = potions.length;

        while (low <= high) {
            int mid = (low + high) / 2;
            long product = (long) spell * potions[mid];

            if (product >= success) {
                ans = mid;      // this potion works, but maybe there’s a smaller one
                high = mid - 1; // look left
            } else {
                low = mid + 1;  // need a stronger potion
            }
        }
        return ans;
    }
}