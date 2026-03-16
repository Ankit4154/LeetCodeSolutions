// 174. Dungeon Game
// https://leetcode.com/problems/dungeon-game/
class Solution {
	int n, m;
	int[][] dp;
    public int calculateMinimumHP(int[][] dungeon) {
        n = dungeon.length;
		m = dungeon[0].length;
		dp = new int[n][m];
		for(int i=0;i<dungeon.length;i++)
			Arrays.fill(dp[i], -1);
		int total = dfs(0,0,dungeon);
		return total;
    }
	int dfs(int r, int c, int[][] dungeon){
		if(r < 0 || r >= n || c < 0 || c >= m)
			return Integer.MAX_VALUE;
		if(dp[r][c] != -1)
			return dp[r][c];
		if(r == n-1 && c == m-1){
            int res = dungeon[r][c];
            if(res < 0)
                return Math.abs(res) + 1;
            else
                return 1;
        }
		int pathRight = dfs(r, c + 1, dungeon);
		int pathDown = dfs(r + 1, c, dungeon);
		int healthNeeded = Math.min(pathRight, pathDown) - dungeon[r][c];
		return dp[r][c] = Math.max(1,healthNeeded);
	}
}