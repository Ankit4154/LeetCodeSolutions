// 1631. Path With Minimum Effort
// https://leetcode.com/problems/path-with-minimum-effort/
// optimized O(n*m log(n*m))
class Solution {
    public int minimumEffortPath(int[][] heights) {
		int n = heights.length;
		int m = heights[0].length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a.maxDiff));
		minHeap.add(new Node(0, 0, 0));
        int[][] dist = new int[n][m];
        for(int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		while(!minHeap.isEmpty()){
			Node p = minHeap.poll();
			int r = p.r;
			int c = p.c;
            if(p.maxDiff > dist[r][c]) 
                continue;
			if(r == n-1 && c == m-1)
				return p.maxDiff;
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow < 0 || cRow >= n || cCol < 0 || cCol >= m)
                    continue;
                
                int newDiff = Math.max(
                        p.maxDiff,
                        Math.abs(heights[r][c] - heights[cRow][cCol])
                );

                if(newDiff < dist[cRow][cCol]) {
                    dist[cRow][cCol] = newDiff;
                    minHeap.add(new Node(newDiff, cRow, cCol));
                }
			}
		}
        return 0;
    }
	private static class Node{
		int maxDiff;
		int r;
		int c;
		Node(int maxDiff, int r, int c){
			this.maxDiff = maxDiff;
			this.r = r;
			this.c = c;
		}
	}
}
// initial solution
class Solution {
    public int minimumEffortPath(int[][] heights) {
		int n = heights.length;
		int m = heights[0].length;
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(Pair::getHeight));
		minHeap.add(new Pair(0, new int[]{0,0}));
		boolean[][] visited = new boolean[n][m];
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		while(!minHeap.isEmpty()){
			Pair p = minHeap.poll();
			int r = p.node[0];
			int c = p.node[1];
			if(visited[r][c])
				continue;
			visited[r][c] = true;
			if(r == n-1 && c == m-1){
				return p.height;
			}
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow >=0 && cRow < n 
					&& cCol >=0 && cCol < m
					&& !visited[cRow][cCol]){
					minHeap.add(
						new Pair(Math.max(p.height, Math.abs(heights[r][c] - heights[cRow][cCol])) 
						, new int[]{cRow, cCol})
						);
				}
			}
			
		}
        return 0;
    }
	private class Pair{
		int height;
		int[] node;
		Pair(int height, int[] node){
			this.height = height;
			this.node = node;
		}
		public int getHeight(){
			return this.height;
		}
	}
}