// 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // count the number of cells during bfs or dfs
		// instead of having a visited array, mark visited 
		// nodes as 0 while/after visiting
		int maxArea = 0, area = 0;
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				if(grid[i][j] == 1){
					grid[i][j] = 0;
					area = bfs(i, j, grid, ++area);
					maxArea = Math.max(maxArea, area);
					area = 0;
				}
			}
		}
		return maxArea;
    }
	
	private int bfs(int i, int j, int[][] grid, int area){
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i, j));
		while(!q.isEmpty()){
			Pair p = q.poll();
			int row = p.row;
			int col = p.col;
			int[] dRow = {-1,1,0,0};
			int[] dCol = {0,0,-1,1};
			for(int d=0;d<4;d++){
				int currRow = row + dRow[d];
				int currCol = col + dCol[d];
				if(currRow >=0 && currRow < grid.length
					&& currCol >= 0 && currCol < grid[0].length 
					&& grid[currRow][currCol] == 1
				){
					grid[currRow][currCol] = 0;
					area++;
					q.add(new Pair(currRow, currCol));
				}
			}
		}
		return area;
	}
	private class Pair{
		int row;
		int col;
		Pair(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}