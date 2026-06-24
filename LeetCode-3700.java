// 3700. Number of ZigZag Arrays II
// https://leetcode.com/problems/number-of-zigzag-arrays-ii
class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        int size = 2 * m;

        // state for length = 2
        long[] state = new long[size];

        for (int v = 0; v < m; v++) {
            state[v] = v;               // up[v]
            state[m + v] = m - 1 - v;   // down[v]
        }

        if (n == 2) {
            long ans = 0;
            for (long x : state) ans = (ans + x) % MOD;
            return (int) ans;
        }

        long[][] T = new long[size][size];

        for (int v = 0; v < m; v++) {

            // newUp[v] = sum down[u], u < v
            for (int u = 0; u < v; u++) {
                T[v][m + u] = 1;
            }

            // newDown[v] = sum up[u], u > v
            for (int u = v + 1; u < m; u++) {
                T[m + v][u] = 1;
            }
        }

        long[][] P = matrixPower(T, n - 2);

        long[] finalState = multiply(P, state);

        long ans = 0;
        for (long x : finalState) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;

            for (int j = 0; j < n; j++) {
                sum = (sum + A[i][j] * v[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }

    private long[][] matrixPower(long[][] base, long exp) {
        int n = base.length;

        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base);
            }

            base = multiply(base, base);
            exp >>= 1;
        }

        return result;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0) continue;

                long a = A[i][k];

                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    res[i][j] = (res[i][j] + a * B[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}