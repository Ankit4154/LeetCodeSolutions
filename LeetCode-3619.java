// 3619. Count Islands With Total Value Divisible by K
// https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k
// optim, removed visited array, O(n) space
class Solution {
	int m,n;
    public int countIslands(int[][] grid, int k) {
        m = grid.length;
		n = grid[0].length;
		int count = 0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(grid[i][j] > 0){
					long sum = dfs(i, j, grid);
						if(sum % k == 0)
							count++;
				}
			}
		}
		return count;
    }
	private long dfs(int r, int c, int[][] grid){
		if(r < 0 || r >= m || c < 0 || c >= n)
			return 0;
		if(grid[r][c] == 0)
			return 0;
		long sum = grid[r][c];
		grid[r][c] = 0;
		sum += dfs(r + 1, c, grid);
		sum += dfs(r - 1, c, grid);
		sum += dfs(r, c - 1, grid);
		sum += dfs(r, c + 1, grid);
		
		return sum;
	}
}

// naive
class Solution {
	int m,n;
	boolean[][] visited;
    public int countIslands(int[][] grid, int k) {
        m = grid.length;
		n = grid[0].length;
		visited = new boolean[m][n];
		int count = 0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(grid[i][j] > 0 && !visited[i][j]){
					long sum = dfs(i, j, grid);
						if(sum % k == 0)
							count++;
				}
			}
		}
		return count;
    }
	private long dfs(int r, int c, int[][] grid){
		if(r < 0 || r >= m || c < 0 || c >= n)
			return 0;
		if(grid[r][c] == 0)
			return 0;
		if(visited[r][c])
			return 0;
		visited[r][c] = true;
		long down = dfs(r + 1, c, grid);
		long up = dfs(r - 1, c, grid);
		long left = dfs(r, c - 1, grid);
		long right = dfs(r, c + 1, grid);
		long sum = grid[r][c] + (down + up + left + right);
		return sum;
	}
}