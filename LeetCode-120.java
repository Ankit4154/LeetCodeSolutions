// 120. Triangle
// https://leetcode.com/problems/triangle
class Solution {
    Integer[][] dp;

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        dp = new Integer[n][n];
        return solve(0, 0, triangle);
    }

    int solve(int i, int j, List<List<Integer>> triangle) {
        // base case: last row
        if(i == triangle.size() - 1){
            return triangle.get(i).get(j);
        }

        // if already computed
        if(dp[i][j] != null) {
            return dp[i][j];
        }

        // recursive calls
        int down = solve(i + 1, j, triangle);
        int diag = solve(i + 1, j + 1, triangle);

        // store and return
        return dp[i][j] = triangle.get(i).get(j) + Math.min(down, diag);
    }
}