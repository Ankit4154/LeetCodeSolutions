// 64. Minimum Path Sum
// https://leetcode.com/problems/minimum-path-sum/
// optimized
class Solution {
	int n,m;
	int[][] dp;
    public int minPathSum(int[][] grid) {
        n = grid.length;
		m = grid[0].length;
		dp = new int[n][m];
		for(int i=0;i<n;i++)
            Arrays.fill(dp[i], -1);
		return dfs(0,0,grid, 0);
    }
	int dfs(int r, int c, int[][] grid, int sum){
		if(r < 0 || r >= n || c < 0 || c >= m)
			return Integer.MAX_VALUE;
		if(dp[r][c] != -1)
			return dp[r][c];
		if(r == n-1 && c == m-1)
            return grid[r][c];
        int down = dfs(r + 1, c, grid, sum);
		int right = dfs(r, c + 1, grid, sum);
		return dp[r][c] = grid[r][c] + Math.min(down, right);
	}
}



// Time Limit Exceeded (TLE)
class Solution {
	int n,m;
	int min = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
        n = grid.length;
		m = grid[0].length;
		dfs(0,0,grid, 0);
		return min;
    }
	void dfs(int r, int c, int[][] grid, int sum){
		if(r < 0 || r >= n || c < 0 || c >= m)
			return;
        sum += grid[r][c];
		if(r == n-1 && c == m-1){
			min = Math.min(min, sum);
		}
		dfs(r + 1, c, grid, sum);
		dfs(r, c + 1, grid, sum);
		return;
	}
}


// Memory Limit Exceeded (MLE)
class Solution {
	int n,m;
    public int minPathSum(int[][] grid) {
        n = grid.length;
		m = grid[0].length;
		return bfs(0,0,grid);
    }
	int bfs(int r, int c, int[][] grid){
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{0,0,grid[0][0]});
		int min = Integer.MAX_VALUE;
		int[] dRow = {0, 1};
		int[] dCol = {1, 0};
		while(!q.isEmpty()){
			int[] cur = q.poll();
            int sum = cur[2];
			if(cur[0] == n-1 && cur[1] == m-1){
				min = Math.min(min, sum);
				continue;
			}
			for(int d=0;d<2;d++){
				int cRow = cur[0] + dRow[d];
				int cCol = cur[1] + dCol[d];
				if(cRow < 0 || cRow >= n ||  cCol < 0 || cCol >= m)
					continue;
				q.add(new int[]{cRow, cCol, grid[cRow][cCol]+sum});
			}
		}
		return min;
	}
}