// 1391. Check if There is a Valid Path in a Grid
// https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid
class Solution {
    boolean[][] visited;
    int n, m;
    int[][][] streetDirs = {
        {},
        {{0,-1},{0,1}},   // 1
        {{-1,0},{1,0}},   // 2
        {{0,-1},{1,0}},   // 3
        {{0,1},{1,0}},    // 4
        {{0,-1},{-1,0}},  // 5
        {{0,1},{-1,0}}    // 6
    };
    public boolean hasValidPath(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        return dfs(0, 0, grid);
    }
    boolean dfs(int r, int c, int[][] grid){
        if(r == n-1 && c == m-1)
            return true;
        visited[r][c] = true;
        for(int[] d : streetDirs[grid[r][c]]) {
            int nr = r + d[0];
            int nc = c + d[1];

            if(nr < 0 || nr >= n || nc < 0 || nc >= m)
                continue;
            if(visited[nr][nc])
                continue;
            // check if neighbor connects back
            for(int[] back : streetDirs[grid[nr][nc]]) {
                if(nr + back[0] == r && nc + back[1] == c) {
                    if(dfs(nr, nc, grid)) return true;
                }
            }
        }
        return false;
    }
}