// 778. Swim in Rising Water
// https://leetcode.com/problems/swim-in-rising-water/
// optimized version O(n^2logn)
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
		int m = grid[0].length;
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(Pair::getMaxTime));
		minHeap.add(new Pair(grid[0][0], new int[]{0,0}));
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
			if(r == n-1 && c == m-1)
				return p.maxTime;
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow >=0 && cRow < n
					&& cCol >=0 && cCol < m
					&& !visited[cRow][cCol]
				){
					int neiVal = grid[cRow][cCol];
					minHeap.add(new Pair(Math.max(p.maxTime, neiVal), new int[]{cRow,cCol}));
				}
			}
		}
		return -1;
    }
	private class Pair{
		int maxTime;
		int[] node;
		Pair(int maxTime, int[] node){
			this.maxTime = maxTime;
			this.node = node;
		}
		public int getMaxTime(){
			return this.maxTime;
		}
	}
}



// initial, O(n^2logn), used HashSet instead of boolean[][]
class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
		int m = grid[0].length;
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparing(Pair::getMaxTime));
		minHeap.add(new Pair(grid[0][0], new int[]{0,0}));
		Set<Integer> visited = new HashSet<>();
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		int minTime = 0;
		while(visited.size() < n*m){
			Pair p = minHeap.poll();
			int[] node = p.node;
			if(node[0] == n-1 && node[1] == m-1)
				return p.maxTime;
            int currVal = grid[node[0]][node[1]];
			if(visited.contains(currVal))
				continue;
			visited.add(currVal);
			for(int d=0;d<4;d++){
				int cRow = node[0] + dRow[d];
				int cCol = node[1] + dCol[d];
				if(cRow >=0 && cRow < n
					&& cCol >=0 && cCol < m
					&& !visited.contains(grid[cRow][cCol])
				){
					int neiVal = grid[cRow][cCol];
					minHeap.add(new Pair(Math.max(p.maxTime, neiVal), new int[]{cRow,cCol}));
				}
			}
		}
		
		return minTime;
    }
	private class Pair{
		int maxTime;
		int[] node;
		Pair(int maxTime, int[] node){
			this.maxTime = maxTime;
			this.node = node;
		}
		public int getMaxTime(){
			return this.maxTime;
		}
	}
}
