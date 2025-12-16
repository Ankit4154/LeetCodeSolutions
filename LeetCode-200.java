// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands
class Solution {
    public int numIslands(char[][] grid) {
		int count = 0;
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				if(grid[i][j] == '1'){
					count++;
                    grid[i][j] = '0';
					bfs(i, j, grid);                    
				}
			}
		}
		return count;
    }
	
	public void bfs(int i, int j, char[][] grid){
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i, j));
		while(!q.isEmpty()){
            Pair p = q.poll();
            int row = p.row;
            int col = p.col;
            int[] dRow = {-1, 1, 0, 0};
            int[] dCol = {0, 0, -1, 1};
            for(int d=0;d<4;d++){
                int currRow = row + dRow[d];
                int currCol = col + dCol[d];
                if(currRow >= 0 && currRow < grid.length 
                    && currCol >=0 && currCol < grid[0].length 
                    && grid[currRow][currCol] == '1'
                    ){
                        grid[currRow][currCol] = '0';
                        q.add(new Pair(currRow, currCol));
                }
                
            }
		}
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