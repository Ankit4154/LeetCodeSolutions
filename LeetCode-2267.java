// 2267.  Check if There Is a Valid Parentheses String Path
// https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path
class Solution {
	int n = 0, m = 0;
    Boolean[][][] dp;
    public boolean hasValidPath(char[][] grid) {
        n = grid.length;
		m = grid[0].length;
		// A valid path has even length
        if((n + m - 1) % 2 != 0)
            return false;

        dp = new Boolean[n][m][n + m];

        return solve(0, 0, grid, 0);
    }
	
	private boolean solve(int r, int c, char[][] grid, int balance){
		if(r >= n || c >= m)
            return false;

        if(grid[r][c] == '(')
            balance++;
        else
            balance--;

        // More ')' than '('
        if(balance < 0)
            return false;

        // Remaining cells cannot close all open '('
        int remaining = (n - 1 - r) + (m - 1 - c);
        if(balance > remaining)
            return false;

        if(r == n - 1 && c == m - 1)
            return balance == 0;

        if(dp[r][c][balance] != null)
            return dp[r][c][balance];

        boolean down = solve(r + 1, c, grid, balance);
        boolean right = solve(r, c + 1, grid, balance);

        return dp[r][c][balance] = down || right;	
	}
}