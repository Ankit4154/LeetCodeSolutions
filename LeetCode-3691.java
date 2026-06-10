// 3691. Maximum Total Subarray Value II
// https://leetcode.com/problems/maximum-total-subarray-value-ii
class Solution {
    class Node {
        long val;
        int l, r;

        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }

    int[][] maxSt;
    int[][] minSt;
    int[] log;

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        buildSparseTables(nums);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));

        for (int l = 0; l < n; l++) {
            long val = getValue(l, n - 1);
            pq.offer(new Node(val, l, n - 1));
        }

        long ans = 0;

        while (k-- > 0) {
            Node cur = pq.poll();

            ans += cur.val;

            if (cur.r > cur.l) {
                int newR = cur.r - 1;
                long newVal = getValue(cur.l, newR);
                pq.offer(new Node(newVal, cur.l, newR));
            }
        }

        return ans;
    }

    private void buildSparseTables(int[] nums) {
        int n = nums.length;

        log = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int K = log[n] + 1;

        maxSt = new int[K][n];
        minSt = new int[K][n];

        for (int i = 0; i < n; i++) {
            maxSt[0][i] = nums[i];
            minSt[0][i] = nums[i];
        }

        for (int j = 1; j < K; j++) {
            int len = 1 << j;

            for (int i = 0; i + len <= n; i++) {
                maxSt[j][i] = Math.max(
                    maxSt[j - 1][i],
                    maxSt[j - 1][i + (len >> 1)]
                );

                minSt[j][i] = Math.min(
                    minSt[j - 1][i],
                    minSt[j - 1][i + (len >> 1)]
                );
            }
        }
    }

    private long getValue(int l, int r) {
        int len = r - l + 1;
        int p = log[len];

        int mx = Math.max(
            maxSt[p][l],
            maxSt[p][r - (1 << p) + 1]
        );

        int mn = Math.min(
            minSt[p][l],
            minSt[p][r - (1 << p) + 1]
        );

        return (long) mx - mn;
    }
}