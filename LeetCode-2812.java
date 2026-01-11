// 2812. Find the Safest Path in a Grid
// https://leetcode.com/problems/find-the-safest-path-in-a-grid/
// multi-source BFS to populate safeness factor/distance 
// of all cells from thief cells
// And then apply Djkshtra on distance array for 
// finding path with maximum safeness factor
class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
		int n = grid.size();
		int m = grid.get(0).size();
        if(grid.get(0).get(0) == 1 || grid.get(n-1).get(m-1) == 1)
            return 0;
		Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][m];
		for(int row[] : dist)
			Arrays.fill(row, -1);
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(grid.get(i).get(j) == 1){
					dist[i][j] = 0;
					q.add(new int[]{i,j});
				}
			}
		}
        
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		while(!q.isEmpty()){
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow < 0 || cRow >= n || cCol < 0 || cCol >= m)
					continue;
				if(dist[cRow][cCol] == -1){
					dist[cRow][cCol] = dist[r][c] + 1;
					q.add(new int[]{cRow, cCol});
				}
			}
		}
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
			(a,b) -> b[2] - a[2]  // descreasing order, max first
		);
		maxHeap.add(new int[]{0,0,dist[0][0]});
		boolean[][] visited = new boolean[n][m];
		while(!maxHeap.isEmpty()){
			int[] p = maxHeap.poll();
			int r = p[0];
			int c = p[1];
			int safe = p[2];
			if(visited[r][c])
				continue;
			visited[r][c] = true;
			if(r == n-1 && c == m-1)
				return safe;
			
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow < 0 || cRow >= n || cCol < 0 || cCol >= m)
					continue;
                if(!visited[cRow][cCol]){
                    maxHeap.add(new int[]{cRow, cCol, Math.min(safe, dist[cRow][cCol])});
                }
			}
		}
		return 0;
    }

}
