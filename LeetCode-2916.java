// 2916. Subarrays Distinct Element Sum of Squares II
// https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/
class Solution {
    static final int MOD = 1000000007;

    class SegmentTree {
        long[] sum, sq;
        int[] lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new int[4 * n];
        }

        void apply(int node, int l, int r) {
            long len = r - l + 1;

            sq[node] = (sq[node] + 2 * sum[node] + len) % MOD;
            sum[node] = (sum[node] + len) % MOD;
            lazy[node]++;
        }

        void push(int node, int l, int r) {
            if(lazy[node] == 0 || l == r)
                return;

            int mid = (l + r) / 2;

            applyLazy(node * 2, l, mid, lazy[node]);
            applyLazy(node * 2 + 1, mid + 1, r, lazy[node]);

            lazy[node] = 0;
        }

        void applyLazy(int node, int l, int r, int v) {
            long len = r - l + 1;

            sq[node] = (sq[node] + 2L * v * sum[node]
                    + (long) v * v % MOD * len) % MOD;

            sum[node] = (sum[node] + (long) v * len) % MOD;
            lazy[node] += v;
        }

        void update(int node, int l, int r, int ql, int qr) {
            if(ql > r || qr < l)
                return;

            if(ql <= l && r <= qr) {
                apply(node, l, r);
                return;
            }

            push(node, l, r);

            int mid = (l + r) / 2;
            update(node * 2, l, mid, ql, qr);
            update(node * 2 + 1, mid + 1, r, ql, qr);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }

        void applyLazy(int node, int l, int r, int v, boolean unused) {
            applyLazy(node, l, r, v);
        }

        void update(int l, int r) {
            if(l <= r)
                update(1, 0, n - 1, l, r);
        }

        long getSquareSum() {
            return sq[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        int[] last = new int[100001];
        Arrays.fill(last, -1);

        SegmentTree st = new SegmentTree(n);
        long ans = 0;

        for(int r = 0; r < n; r++) {
            int p = last[nums[r]];

            st.update(p + 1, r);

            last[nums[r]] = r;
            ans = (ans + st.getSquareSum()) % MOD;
        }

        return (int) ans;
    }
}