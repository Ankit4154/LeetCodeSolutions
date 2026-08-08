// 3302. Find the Lexicographically Smallest Valid Sequence
// https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence
import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] ans = new int[m];

        /*
         * last[j] = position in word1 used to match
         * word2[j] when matching word2 from RIGHT to LEFT.
         *
         * This tells us:
         *
         * "If I use a mismatch at position i for word2[j],
         * can I still match word2[j+1...]?"
         */
        int[] last = new int[m];
        Arrays.fill(last, -1);

        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }

            i--;
        }

        /*
         * Greedily construct lexicographically smallest answer.
         */
        boolean usedMismatch = false;

        j = 0;

        for (i = 0; i < n && j < m; i++) {

            /*
             * Exact match.
             *
             * Always take it because we're scanning from
             * left to right and want the smallest index.
             */
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;

            }

            /*
             * Mismatch.
             *
             * We can use the one allowed mismatch here only if:
             *
             * 1. We haven't used it yet.
             * 2. There is enough room to match word2[j+1...].
             */
            else if (!usedMismatch &&
                     (j == m - 1 || i < last[j + 1])) {

                ans[j] = i;
                j++;
                usedMismatch = true;
            }
        }

        /*
         * Could not construct the complete sequence.
         */
        if (j != m) {
            return new int[]{};
        }

        return ans;
    }
}