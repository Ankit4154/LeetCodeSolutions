// 3699. Number of ZigZag Arrays I
// https://leetcode.com/problems/number-of-zigzag-arrays-i
class Solution {
        private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        // length = 2 states
        long[] up = new long[m];
        long[] down = new long[m];

        for(int v = 0; v < m; v++) {
            up[v] = m - 1 - v; // choose larger previous value
            down[v] = v;       // choose smaller previous value
        }
        
        if(n == 2) {
            long ans = 0;
            for (int i = 0; i < m; i++) {
                ans = (ans + up[i] + down[i]) % MOD;
            }
            return (int) ans;
        }

        for(int len = 3; len <= n; len++) {

            long[] prefixUp = new long[m];
            long[] prefixDown = new long[m];

            prefixUp[0] = up[0];
            prefixDown[0] = down[0];

            for(int i = 1; i < m; i++) {
                prefixUp[i] = (prefixUp[i - 1] + up[i]) % MOD;
                prefixDown[i] = (prefixDown[i - 1] + down[i]) % MOD;
            }

            long totalUp = prefixUp[m - 1];
            long totalDown = prefixDown[m - 1];

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            for(int v = 0; v < m; v++){

                newUp[v] =
                    (totalDown - prefixDown[v] + MOD) % MOD;

                newDown[v] =
                    (v == 0) ? 0 : prefixUp[v - 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for(int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}