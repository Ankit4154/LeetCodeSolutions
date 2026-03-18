// 62. Unique Paths
// https://leetcode.com/problems/unique-paths/
class Solution {
	int dp[][];
	int m,n;
    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
		this.m = m;
		this.n = n;
		for(int i=0;i<m;i++)
			Arrays.fill(dp[i], -1);
		return dfs(0,0);
    }
	int dfs(int r, int c){
		if(r >= m || c >= n)
			return 0;
		
        if(r == m-1 && c == n-1)
            return 1;
        
		if(dp[r][c] != -1)
			return dp[r][c];
		int pathRight = dfs(r, c+1);
		int pathDown = dfs(r+1, c);
		return dp[r][c] = pathRight + pathDown;
	}
}