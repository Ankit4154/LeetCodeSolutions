// 980. Unique Paths III
// https://leetcode.com/problems/unique-paths-iii/
class Solution {
	int n,m;
	int[][] grid;
    int empty = 0;
    public int uniquePathsIII(int[][] grid) {
        n = grid.length;
		m = grid[0].length;
		this.grid = grid;
		int[] start = new int[2];
		int[] end = new int[2];
		// find start and end position
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(grid[i][j] == 1){
					start[0] = i;
					start[1] = j;
				}
				if(grid[i][j] != -1)
					empty++;
			}
		}
		return solve(start[0],start[1]);
    }
	int solve(int r, int c){
		if(r < 0 || r >=n || c < 0 || c >= m || grid[r][c] == -1){
			return 0;
		}
		if(grid[r][c] == 2){
            if(empty == 1)
                return 1;
            return 0;
		}
        int original = grid[r][c];
        grid[r][c] = -1; //mark visited
        empty--;
		int paths = solve(r, c-1) 
                    + solve(r, c+1)
                    + solve(r-1, c)
                    + solve(r+1, c);
		grid[r][c] = original; //backtrack or restore
        empty++;
		return paths;
	}
}