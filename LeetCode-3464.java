// 3464. Maximize the Distance Between Points on a Square
// https://leetcode.com/problems/maximize-the-distance-between-points-on-a-square
class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long per = 4L * side;

        // map to 1D
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) arr[i] = x;
            else if (x == side) arr[i] = side + y;
            else if (y == side) arr[i] = 3L * side - x;
            else arr[i] = 4L * side - y;
        }

        Arrays.sort(arr);

        // duplicate
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = arr[i];
            ext[i + n] = arr[i] + per;
        }

        long lo = 0, hi = per, ans = 0;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            if (can(ext, n, k, mid, per)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean can(long[] ext, int n, int k, long d, long per) {
        int m = 2 * n;

        // Step 1: build next[] in O(n)
        int[] next = new int[m];
        int j = 0;

        for (int i = 0; i < m; i++) {
            while (j < m && ext[j] - ext[i] < d) j++;
            next[i] = j;
        }

        // Step 2: try each start
        for (int start = 0; start < n; start++) {
            int curr = start;

            for (int step = 1; step < k; step++) {
                curr = next[curr];
                if (curr >= start + n) break;
            }

            if (curr < start + n) {
                long first = ext[start];
                long last = ext[curr];

                if (per - (last - first) >= d) {
                    return true;
                }
            }
        }

        return false;
    }
}