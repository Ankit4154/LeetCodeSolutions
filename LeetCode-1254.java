// 1254. Number of Closed Islands
// https://leetcode.com/problems/number-of-closed-islands
class Solution {
	int m, n;
	boolean[][] visited;
    public int closedIsland(int[][] grid) {
        m = grid.length;
		n = grid[0].length;
		visited = new boolean[m][n];
		int count = 0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(grid[i][j] == 0 && !visited[i][j]){
					if(dfs(i,j,grid)){
						count++;
					}
				}
			}
		}
		return count;
		
    }
	
	private boolean dfs(int r, int c, int[][] grid){
		// if boundary is reached return false
		if(r < 0 || r >= m || c < 0 || c >= n)
			return false;
        // Water does not lead outside through land
		if(grid[r][c] == 1)
			return true;
        // if already visited
		if(visited[r][c])
			return true;
		// mark cell visited
		if(grid[r][c] == 0)
			visited[r][c] = true;
		// explore neighbours
		boolean down = dfs(r + 1, c, grid);
		boolean right = dfs(r, c + 1, grid);
		boolean up = dfs(r - 1, c, grid);
		boolean left = dfs(r, c - 1, grid);
		
        // All directions must remain inside the grid
		return down && right && up && left;
	}
}