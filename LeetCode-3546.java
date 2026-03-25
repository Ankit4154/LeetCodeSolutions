// 3546. Equal Sum Grid Partition I
// https://leetcode.com/problems/equal-sum-grid-partition-i/
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for(int[] row : grid) {
            for(int val : row) {
                total += val;
            }
        }

        // If total is odd → impossible
        if(total % 2 != 0) 
            return false;

        // Try horizontal cuts
        long curr = 0;
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n; j++) {
                curr += grid[i][j];
            }
            if(curr * 2 == total) 
                return true;
        }

        // Try vertical cuts
        curr = 0;
        for (int j = 0; j < n - 1; j++) {
            for(int i = 0; i < m; i++) {
                curr += grid[i][j];
            }
            if(curr * 2 == total)
                return true;
        }

        return false;
    }
}