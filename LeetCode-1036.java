// 1036. Escape a Large Maze
// https://leetcode.com/problems/escape-a-large-maze/
class Solution {
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // use bfs to explore all possible unblocked cell and reach target.
		// BFS might result in memory limit exceed or TLE error as there are nearly 1 million cells.
		// Even when we have to find only 1 path and return true, in worst case, BFS has to
		// store all the 1 million nodes in the queue.
		// If we check the maximum blocked array length, it is 200. It is nearly impossible to block / 
		// surround all the 1 million cells covering source and target with 200.
		// Thus, we can try BFS/DFS traversal 
		// and return true if we reach target.
		// and additionally traverse till these 200 blocks from both sides, first source and
		// then target. If there's a path from both source and target we return true.
		if(blocked.length == 0)
			return true;
		Set<String> blockedCells = new HashSet<>();
		for(int i=0;i<blocked.length;i++){
			blockedCells.add(blocked[i][0] + "," + blocked[i][1]);
		}
		int exhaustedTarget = solve(blockedCells, source, target);
		if(exhaustedTarget == 1)
			return true;
		int exhaustedSource = solve(blockedCells, target, source);
		if(exhaustedSource == 1)
			return true;
		if(exhaustedTarget == 0 && exhaustedSource == 0)
			return true;
		return  false;
    }

	private int solve(Set<String> blockedCells, int[] source, int[] target){
		int n = 1_000_000;
        int m = 1_000_000;
        int MAX = blockedCells.size() * (blockedCells.size() - 1) / 2;
		int er = source[0];
        int ec = source[1];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{er, ec});
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		Set<String> visited = new HashSet<>();
        visited.add(er+","+ec);
		while(!q.isEmpty()){
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			if(r == target[0] && c == target[1]){
				return 1;
			}
			if(visited.size() > MAX)
				return 0;
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow < 0 || cRow > n-1
					|| cCol < 0 || cCol > m-1
				)
					continue;
				String currentCell = cRow+","+cCol;
				if(!visited.contains(currentCell) && !blockedCells.contains(currentCell)){
                    visited.add(currentCell);
					q.add(new int[]{cRow, cCol});
				}
			}
		}
		return -1;
	}
    
}