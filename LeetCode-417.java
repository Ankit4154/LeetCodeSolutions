// 417. Pacific Atlantic Water Flow
// https://leetcode.com/problems/pacific-atlantic-water-flow/
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights){		
		int row = heights.length;
		int col = heights[0].length;
		boolean[][] pacific = new boolean[row][col];
		boolean[][] atlantic = new boolean[row][col];

		// traverse first and last row
		for(int i=0;i<col;i++){
			bfs(0, i, pacific, heights);
			bfs(row-1, i, atlantic, heights);
		}
        // traverse first and last col
		for(int i=0;i<row;i++){
			bfs(i, 0, pacific, heights);
			bfs(i, col-1, atlantic, heights);
		}
		List<List<Integer>> out = new ArrayList<>();
        for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(pacific[i][j] && atlantic[i][j]){
					out.add(Arrays.asList(i,j));
				}
			}
		}
		
		return out;
    }
	
	public void bfs(int i, int j, boolean[][] ocean, int[][] heights){
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{i,j});
        ocean[i][j] = true;
		int[] dRow = {-1,1,0,0};
		int[] dCol = {0,0,-1,1};
		while(!q.isEmpty()){
			int[] p = q.poll();
			int row = p[0];
			int col = p[1];
			for(int d=0;d<4;d++){
				int cRow = row + dRow[d];
				int cCol = col + dCol[d];
				if(cRow >= 0 && cRow < heights.length
				&& cCol >= 0 && cCol < heights[0].length
				&& heights[cRow][cCol] >= heights[row][col]
				&& !ocean[cRow][cCol]){
					q.add(new int[]{cRow, cCol});
					ocean[cRow][cCol] = true;
				}
			}
		}
	}
}