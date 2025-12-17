// 994. Rotting Oranges
// https://leetcode.com/problems/rotting-oranges
class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        // Step 1: Count fresh oranges & push all rotten oranges into queue
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // If no fresh oranges exist
        if(fresh == 0) 
			return 0;

        int minutes = 0;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        // Step 2: BFS level order traversal
        while(!q.isEmpty()) {
            int size = q.size();
            boolean rottedThisMinute = false;

            for(int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for(int d = 0; d < 4; d++) {
                    int nr = r + dRow[d];
                    int nc = c + dCol[d];

                    if(nr >= 0 && nr < m && nc >= 0 && nc < n 
                        && grid[nr][nc] == 1) {

                        grid[nr][nc] = 2;   // make it rotten
                        fresh--;
                        q.offer(new int[]{nr, nc});
                        rottedThisMinute = true;
                    }
                }
            }

            if(rottedThisMinute) 
				minutes++;
        }

        // Step 3: Check if fresh oranges remain
        return fresh == 0 ? minutes : -1;
    }
}