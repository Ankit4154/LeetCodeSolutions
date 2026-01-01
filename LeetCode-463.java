// 463. Island Perimeter
// https://leetcode.com/problems/island-perimeter
class Solution {
	// optimized
	public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
		int row = grid.length;
		int col = grid[0].length;
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				if(grid[i][j] == 1){
					perimeter += 4;
					if(i+1 < row && grid[i+1][j] == 1){
						perimeter -= 2;
					}
					if(j+1 < col && grid[i][j+1] == 1){
						perimeter -= 2;
					}
				}
			}
		}
        return perimeter;
    }
    // public int islandPerimeter(int[][] grid) {
	// 	int[] dRow = {-1,1,0,0};
	// 	int[] dCol = {0,0,-1,1};
    //     int ans = 0;
	// 	for(int i=0;i<grid.length;i++){
	// 		for(int j=0;j<grid[i].length;j++){
	// 			if(grid[i][j] == 1){
    //                 int side = 0;
	// 				for(int d=0;d<4;d++){
	// 					int cRow = i + dRow[d];
	// 					int cCol = j + dCol[d];
	// 					if(cRow >=0 && cRow < grid.length
	// 						&& cCol >=0 && cCol < grid[0].length
	// 						&& grid[cRow][cCol] == 1
	// 					){
	// 						side++;
	// 					}
	// 				}
	// 				ans += 4-side;
	// 			}
	// 		}
	// 	}
    //     return ans;
    // }
}