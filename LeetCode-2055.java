// 2055. Plates Between Candles
// https://leetcode.com/problems/plates-between-candles/
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
		int n = s.length();
        int[] prefix = new int[n];
        int[] left = new int[n];   // nearest candle to the left (or self)
        int[] right = new int[n];  // nearest candle to the right (or self)

        // Prefix count of plates(*)
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') count++;
            prefix[i] = count;
        }

        // Nearest left candle
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') prev = i;
            left[i] = prev;
        }

        // Nearest right candle
        int next = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') next = i;
            right[i] = next;
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int start = right[l]; // first candle to right of l
            int end = left[r];   // last candle to left of r
            if (start == -1 || end == -1 || start >= end)
                res[i] = 0;
            else
                res[i] = prefix[end] - prefix[start];
        }

        return res;
    }
}