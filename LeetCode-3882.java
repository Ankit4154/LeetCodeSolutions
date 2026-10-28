// 3882. Minimum XOR Path in a Grid
// https://leetcode.com/problems/minimum-xor-path-in-a-grid
class Solution {
	int min = Integer.MAX_VALUE;
	int m, n;
    boolean[][][] visited;
    public int minCost(int[][] grid) {
		m = grid.length;
		n = grid[0].length;
        visited = new boolean[m][n][1024];
		solve(0, 0, grid, 0);
		return (min == Integer.MAX_VALUE) ? 0 : min;
    }
	
	private void solve(int r, int c, int[][] grid, int xor){
		if(r >= m || c >= n)
			return;
        xor ^= grid[r][c];
        if(visited[r][c][xor])
            return;
        visited[r][c][xor] = true;
		if(r == m-1 && c == n-1){
			min = Math.min(min, xor);
			return;
		}
		solve(r + 1, c, grid, xor);
		solve(r, c + 1, grid, xor);
		return;
	}
}