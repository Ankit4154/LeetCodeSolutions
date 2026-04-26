// 1559. Detect Cycles in 2D Grid
// https://leetcode.com/problems/detect-cycles-in-2d-grid
// optim
class Solution {
	boolean[][] visited;
    public boolean containsCycle(char[][] grid) {
		visited = new boolean[grid.length][grid[0].length];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
                if(!visited[i][j]){
                    if(solve(i, j, -1,-1, grid)){
					    return true;
				    }
                }
			}
		}
        return false;
    }
	boolean solve(int r, int c, int pr, int pc, char[][] grid){
		int n = grid.length, m = grid[0].length;

		if(r < 0 || r >= n || c < 0 || c >= m)
			return false;
        
        // cycle detected
        if(visited[r][c])
			return true;

		visited[r][c] = true;
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] d : dir){
            int nr = r + d[0];
            int nc = c + d[1];
            // skip parent
            if(nr == pr && nc == pc)
                continue;

            // only go to same character
            if(nr >= 0 && nr < n && nc >= 0 && nc < m &&
               grid[nr][nc] == grid[r][c]) {

                if(solve(nr, nc, r, c, grid))
                    return true;
            }
        }
		return false;
	}
}

class Solution {
	boolean[][] visited;
    public boolean containsCycle(char[][] grid) {
		visited = new boolean[grid.length][grid[0].length];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
                if(!visited[i][j]){
                    if(solve(i, j, -1,-1, grid, grid[i][j])){
					    return true;
				    }
                }
			}
		}
        return false;
    }
	boolean solve(int r, int c, int pr, int pc, char[][] grid, char prev){
		
		if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
			return false;
        
        if(grid[r][c] != prev)
			return false;
        
        // cycle detected
        if(visited[r][c])
			return true;

		prev = grid[r][c];
		visited[r][c] = true;
        
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int[] d : dir){
            int nr = r + d[0];
            int nc = c + d[1];
            // skip parent
            if(nr == pr && nc == pc)
                continue;
            if(solve(nr, nc, r, c, grid, prev))
                return true;
        }
		return false;
	}
}

// init, errored
class Solution {
	boolean[][] visited;
    public boolean containsCycle(char[][] grid) {
		visited = new boolean[grid.length][grid[0].length];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
                if(!visited[i][j]){
                    if(solve(i, j, -1,-1, grid, grid[i][j])){
					    return true;
				    }
                }
			}
		}
        return false;
    }
	boolean solve(int r, int c, int pr, int pc, char[][] grid, char prev){
		
		if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
			return false;
        
        if(r == pr && c == pc)
            return false;
        
        if(grid[r][c] != prev)
			return false;
        
        // cycle detected
        if(visited[r][c])
			return true;

		prev = grid[r][c];
		visited[r][c] = true;
        
		// right
		if(solve(r, c+1, r, c, grid, prev))
            return true;
		// left
		if(solve(r, c-1, r, c, grid, prev))
            return true;
		// down
		if(solve(r+1, c, r, c, grid, prev))
            return true;
		// top
		if(solve(r-1, c, r, c, grid, prev))
            return true;
		
		return false;
	}
}