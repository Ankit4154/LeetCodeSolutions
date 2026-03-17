// 2435. Paths in Matrix Whose Sum Is Divisible by K
// https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
class Solution {
	int n,m;
	int[][][] dp;
	int k;
    final int MOD = 1_000_000_007;
    public int numberOfPaths(int[][] grid, int k) {
        n = grid.length;
		m = grid[0].length;
		this.k = k;
		dp = new int[n][m][k];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
		return dfs(0,0,grid, 0);
    }
	int dfs(int r, int c, int[][] grid, int rem){
		if(r >= n || c >= m)
			return 0;
        rem = (rem + grid[r][c]) % k;
		
		if(r == n-1 && c == m-1){
			return rem == 0 ? 1 : 0;
		}
        if(dp[r][c][rem] != -1)
            return dp[r][c][rem];
		int pathRight = dfs(r, c+1, grid, rem);
		int pathDown = dfs(r+1, c, grid, rem);
		
		return dp[r][c][rem] = (pathRight + pathDown) % MOD;
	}
}

// TLE without DP
class Solution {
	int n,m;
	int[][] dp;
	int k;
	int count = 0;
    public int numberOfPaths(int[][] grid, int k) {
        n = grid.length;
		m = grid[0].length;
		this.k = k;
		dp = new int[n][m];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i], -1);
		dfs(0,0,grid, 0);
		return count;
    }
	void dfs(int r, int c, int[][] grid, int sum){
		if(r < 0 || r >= n || c < 0 || c >= m)
			return;
		
		if(r == n-1 && c == m-1){
			sum = sum + grid[r][c];
			if(sum % k == 0){
				count++;
			}
			return;
		}
		dfs(r, c+1, grid, sum + grid[r][c]);
		dfs(r+1, c, grid, sum + grid[r][c]);
		
		return;
	}
}