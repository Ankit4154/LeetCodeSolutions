// 542. 01 Matrix
// https://leetcode.com/problems/01-matrix
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // skip 0's , for 1's add up the min
		// neighbour value to the current cell
		// store in output / answer array
		int r = mat.length;
		int c = mat[0].length;
		int[][] out = new int[r][c];
		Queue<int[]> q = new ArrayDeque<>();
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				if(mat[i][j] == 0){
					out[i][j] = 0;
					q.add(new int[]{i,j});
				}else{
					out[i][j] = -1; // unvisited
				}
			}
		}
		int[] dRow = new int[]{-1,1,0,0};
		int[] dCol = new int[]{0,0,-1,1};
		while(!q.isEmpty()){
			int[] p = q.poll();
			int min = 0;
			for(int d=0;d<4;d++){
				int cRow = p[0] + dRow[d];
				int cCol = p[1] + dCol[d];
				if(cRow >=0 && cRow < r
					&& cCol >=0 && cCol < c
					&& out[cRow][cCol] == -1
				){
					out[cRow][cCol] = out[p[0]][p[1]] + 1;
					q.add(new int[]{cRow,cCol});
				}
			}
		}
		return out;
    }
}