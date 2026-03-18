// 63. Unique Paths II
// https://leetcode.com/problems/unique-paths-ii/
class Solution {
	int n,m;
	int[][] dp;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        n = obstacleGrid.length;
		m = obstacleGrid[0].length;
		dp = new int[n][m];
		for(int i=0;i<n;i++)
			Arrays.fill(dp[i], -1);
		return solve(0,0,obstacleGrid);
    }
	int solve(int r, int c, int[][] grid){
		if(r >= n || c >= m)
			return 0;
		if(r == n-1 && c == m-1){
            if(grid[r][c] == 1)
			    return 0;
			return 1;
		}
		if(grid[r][c] == 1)
			return 0;
		if(dp[r][c]!=-1)
			return dp[r][c];
		int pathRight = solve(r, c+1, grid);
		int pathDown = solve(r+1, c, grid);
		return dp[r][c] = pathRight + pathDown;
	}
}