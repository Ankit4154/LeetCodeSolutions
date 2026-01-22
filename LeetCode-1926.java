// 1926. Nearest Exit from Entrance in Maze
// https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
// 
class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;
        int er = entrance[0];
        int ec = entrance[1];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{er, ec, 0});
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};
		boolean[][] visited = new boolean[n][m];
        visited[er][ec] = true;
		while(!q.isEmpty()){
			int[] p = q.poll();
			int r = p[0];
			int c = p[1];
			int path = p[2];
			if((r != er || c != ec) && (r == 0 || r == n-1
				|| c == 0 || c == m-1
			)){
				return path;
			}
			for(int d=0;d<4;d++){
				int cRow = r + dRow[d];
				int cCol = c + dCol[d];
				if(cRow < 0 || cRow >= n
					|| cCol < 0 || cCol >= m
				)
					continue;
				if(maze[cRow][cCol] == '.' 
                    && !visited[cRow][cCol]){
                    visited[cRow][cCol] = true;
					q.add(new int[]{cRow, cCol, path+1});
				}
			}
		}
		return -1;
	}
}