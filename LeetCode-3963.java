// 3963. Create Grid With Exactly One Path
// https://leetcode.com/problems/create-grid-with-exactly-one-path/
// optim, Bonus : Arrays.fill is faster than 2 for loops
class Solution {
    public String[] createGrid(int m, int n) {
		char[][] grid = new char[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        for(int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }

        for(int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
		
    }
}

// naive
class Solution {
    public String[] createGrid(int m, int n) {
		String[] out = new String[m];
		for(int i=0;i<m;i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<n;j++){
				if(i == 0 || j == n-1){
					sb.append(".");
				}else{
					sb.append("#");
				}
			}
			out[i] = sb.toString();
		}
		return out;
    }
}