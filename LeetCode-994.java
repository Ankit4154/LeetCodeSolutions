// 994. Rotting Oranges
// https://leetcode.com/problems/rotting-oranges
class Solution {
    public int orangesRotting(int[][] grid) {
        // count each rotten orange and 
		// loop over each of them and count a minute, 
        // each minute rotten the neighbouring oranges 
		// when no more rotten oranges are left return minute count.
		int min = 0, fresh = 0;
        Queue<Pair> q = new ArrayDeque<>();
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				if(grid[i][j] == 2){
                    q.add(new Pair(i, j));
				}else if(grid[i][j] == 1){
                    fresh++;
                }
			}
		}
        // No fresh oranges
        if(fresh == 0)
            return 0;
        if (q.isEmpty())
            return -1;
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while(!q.isEmpty()){
            int size = q.size();
            boolean rottenThisMinute = false;
            for(int k=0;k<size;k++){
                Pair p = q.poll();
                int row = p.row;
                int col = p.col;
                for(int d=0;d<4;d++){
                    int cRow = row + dRow[d];
                    int cCol = col + dCol[d];
                    if(cRow >=0 && cRow < grid.length
                        && cCol >=0 && cCol < grid[0].length
                        && grid[cRow][cCol] == 1
                    ){
                        grid[cRow][cCol] = 2;
                        rottenThisMinute = true;
                        q.add(new Pair(cRow, cCol));
                        fresh--;
                    }
                }
            }
            if(rottenThisMinute)
                min++;
        }
        return fresh == 0 ? min : -1;
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
