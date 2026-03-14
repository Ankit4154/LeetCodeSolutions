// 741. Cherry Pickup
// https://leetcode.com/problems/cherry-pickup/
class Solution {
	int n;
    Integer[][][] memo;
    int[][] grid;
    public int cherryPickup(int[][] grid) {
		n = grid.length;
        memo = new Integer[n][n][n];
        this.grid = grid;
        int ans = dfs(0, 0, 0);
        return Math.max(0, ans);
    }
    int dfs(int r1, int c1, int r2) {

        int c2 = r1 + c1 - r2;

        // out of bounds
        if(r1 >= n || c1 >= n || r2 >= n || c2 >= n)
            return Integer.MIN_VALUE;

        // thorn
        if(grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;

        // reached end
        if(r1 == n-1 && c1 == n-1)
            return grid[r1][c1];

        if(memo[r1][c1][r2] != null)
            return memo[r1][c1][r2];

        int cherries = grid[r1][c1];

        if (r1 != r2)   // avoid double count
            cherries += grid[r2][c2];

        int best = Math.max(
                Math.max(dfs(r1+1, c1, r2+1), dfs(r1, c1+1, r2)),
                Math.max(dfs(r1+1, c1, r2), dfs(r1, c1+1, r2+1))
        );

        cherries += best;

        memo[r1][c1][r2] = cherries;
        return cherries;
    }
}