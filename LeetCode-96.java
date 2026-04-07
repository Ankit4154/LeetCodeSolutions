// 96. Unique Binary Search Trees
// https://leetcode.com/problems/unique-binary-search-trees/
class Solution {
	Integer[] dp;
    public int numTrees(int n) {
        dp = new Integer[n+1];
		return solve(n);
    }
	int solve(int n){
		if(n == 1 || n == 0)
			return 1;
		if(dp[n] != null)
			return dp[n];
		// try each number as root node
		int total = 0;
		for(int root = 1; root <= n ; root++){
			int left = solve(root - 1); // left subtree
			int right = solve(n - root); // right subtree
			
			total += left * right;
		}
		return dp[n] = total;
	}
}