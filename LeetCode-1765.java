// 1765. Map of Highest Peak
// https://leetcode.com/problems/map-of-highest-peak/
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        int[][] out = new int[n][m];
        for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(isWater[i][j] == 1){
                    out[i][j] = 0;
					q.add(new int[]{i,j});
				}else{
                    out[i][j] = -1;
                }
			}
		}
		int[] dRow = {-1,1,0,0};
		int[] dCol = {0,0,-1,1};
		while(!q.isEmpty()){
			int[] p = q.poll();
			for(int d=0;d<4;d++){
				int cRow = p[0] + dRow[d];
				int cCol = p[1] + dCol[d];
				if(cRow >= 0 && cRow < n
					&& cCol >= 0 && cCol < m
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