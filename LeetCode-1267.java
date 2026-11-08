// 1267. Count Servers that Communicate
// https://leetcode.com/problems/count-servers-that-communicate
// optim
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
		int n = grid[0].length;
		
		int[] rowCount = new int[m];
		int[] colCount = new int[n];
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(grid[i][j] == 1){
					rowCount[i]++;
					colCount[j]++;
				}
			}
		}
		int count = 0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(grid[i][j] == 1 && 
                (rowCount[i] > 1 || colCount[j] > 1))
					count++;
			}
		}
		return count;
		
    }
}

// naive, init
class Solution {
	int m,n;
    public int countServers(int[][] grid) {
        m = grid.length;
		n = grid[0].length;
		int total = 0, count = 0;;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(grid[i][j] == 1){
					total++;
				}
				if(grid[i][j] == 1 && isNotPair(i, j, grid)){
					count++;
				}
			}
		}
		return total - count;
    }
	
	private boolean isNotPair(int r, int c, int[][] grid){
		int count = 0;
		for(int i=0;i<n;i++){
			if(grid[r][i] == 1){
				count++;
			}
            if(count > 1)
				return false;
		}
		count = 0;
		for(int i=0;i<m;i++){
			if(grid[i][c] == 1){
				count++;
			}
            if(count > 1)
				return false;
		}
		
		return true;
		
	}
}