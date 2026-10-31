// 3665. Twisted Mirror Path Count
// https://leetcode.com/problems/twisted-mirror-path-count/
class Solution {
    int MOD = 1_000_000_007;
    int m, n;
    int[][][] dp;
    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n][2];
        for(int[][] v : dp){
            for(int[] k : v){
                Arrays.fill(k, -1);
            }
        }
        return solve(0, 0, grid, 0);
    }
    int solve(int r, int c, int[][] grid, int prevPos){
        
        if(r >= m || c >= n) {
            return 0;
        }
        
        if(r == m-1 && c == n-1){
            return dp[r][c][prevPos] = 1;
        }
        if(dp[r][c][prevPos] != -1)
           return dp[r][c][prevPos];
        
        int cell = grid[r][c];
        int unique = 0;
        if(cell == 1){
            // previous direction was from left
           if(prevPos == 0){
                unique = solve(r + 1, c, grid, 1);
            }else{
                // previous direction was from up
               unique = solve(r, c + 1, grid, 0);
            }
            
        }else{
          
          int down =  solve(r + 1, c, grid, 1);
          int right = solve(r, c + 1, grid, 0);
           
            unique = (down + right) % MOD;
        }
        
        return dp[r][c][prevPos] = unique % MOD;
    }
}
