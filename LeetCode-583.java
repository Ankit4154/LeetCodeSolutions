// 583. Delete Operation for Two Strings
// https://leetcode.com/problems/delete-operation-for-two-strings/
class Solution {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
		char[] w2 = word2.toCharArray();
		int[][] dp = new int[w1.length+1][w2.length+1];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i], -1);
		//int commonLength = longestCommonSubsequenceCount(w1, w2, w1.length, w2.length);
		int commonLength = longestCommonSubsequenceCount(w1, w2, w1.length, w2.length, dp);
		int changesForWord1ToCommon = w1.length - commonLength;
		int changesForCommonToWord2 = w2.length - commonLength;
		return changesForWord1ToCommon + changesForCommonToWord2;
    }
	// with dp
	public int longestCommonSubsequenceCount(char[] w1, char[] w2, int m, int n, int[][] dp){
		// base case if length is 0
		if(m == 0 || n == 0)
			return 0;
		if(dp[m][n] != -1)
			return dp[m][n];
		int ans = 0;
		if(w1[m-1] == w2[n-1]){
			ans = 1 + longestCommonSubsequenceCount(w1, w2, m-1, n-1, dp);
		}else{
			ans = Math.max(longestCommonSubsequenceCount(w1, w2, m-1, n, dp), 
			longestCommonSubsequenceCount(w1, w2, m, n-1, dp));
		}
		return dp[m][n] = ans;
	}
	
	// without dp
	public int longestCommonSubsequenceCount(char[] w1, char[] w2, int m, int n){
		// base case if length is 0
		if(m == 0 || n == 0)
			return 0;
		int ans = 0;
		if(w1[m-1] == w2[n-1]){
			ans = 1 + longestCommonSubsequenceCount(w1, w2, m-1, n-1);
		}else{
			ans = Math.max(longestCommonSubsequenceCount(w1, w2, m-1, n), 
			longestCommonSubsequenceCount(w1, w2, m, n-1));
		}
		return ans;
	}
}