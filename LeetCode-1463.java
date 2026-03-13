// 1463. Cherry Pickup II
// https://leetcode.com/problems/cherry-pickup-ii/
// dfs + memoization
class Solution {
    int[][][] dp;
    int n, m;
    public int cherryPickup(int[][] grid) {
        n = grid.length;
		m = grid[0].length;
        dp = new int[n][m][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                Arrays.fill(dp[i][j], -1);
        return dfs(0,0,m-1,grid);
    }
    int dfs(int r, int c1, int c2, int[][] grid){
        if(c1 < 0 || c1 >= m || c2 < 0 || c2 >= m)
            return 0;
        if(dp[r][c1][c2] != -1)
            return dp[r][c1][c2];
        int result = grid[r][c1];
        if(c1 != c2)
            result += grid[r][c2];
        if(r != n-1){
            int max = 0;
            for(int d1=-1; d1<=1; d1++){
                for(int d2=-1; d2<=1; d2++){
                    max = Math.max(max,
                        dfs(r+1, c1+d1, c2+d2, grid));
                }
            }
            result += max;
        }
        return dp[r][c1][c2] = result;
    }
}

// Memory limit exceeded
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
		int m = grid[0].length;
		int max = Integer.MIN_VALUE;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{0,0,m-1,grid[0][0] + grid[0][m-1]});
		int[] d = {-1,0,1};
		while(!q.isEmpty()){
				int[] curr = q.poll();
                int r = curr[0];
                int c1 = curr[1];
                int c2 = curr[2];
                int sum = curr[3];
                if(r == n-1){
                    max = Math.max(max, sum);
                    continue;
                }
				for(int d1=0;d1<d.length;d1++){
                    for(int d2=0;d2<d.length;d2++){
                        int newr = r + 1;
                        int newc1 = c1 + d[d1];
					    int newc2 = c2 + d[d2];

                        if(newc1 < 0 || newc1 >= m || newc2 < 0 || newc2 >= m)
                            continue;
                        int currentVal = grid[newr][newc1];
                        if(newc1 != newc2){
                            currentVal += grid[newr][newc2];
                        }
                        q.add(new int[]{newr, newc1,newc2, sum+currentVal});
                    }
				}
		}
		return max;
    }
}