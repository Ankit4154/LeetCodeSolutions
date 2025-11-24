// 1143. Longest Common Subsequence
// https://leetcode.com/problems/longest-common-subsequence/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // Input: text1 = "abcde", text2 = "ace" 
		// output : 3 (ace)
		char[] s1 = text1.toCharArray();
		char[] s2 = text2.toCharArray();
		int[][] dp = new int[s1.length+1][s2.length+1];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i], -1);
		//return longestCommonSubsequence(s1, s2, s1.length, s2.length);
		return longestCommonSubsequence(s1, s2, s1.length, s2.length, dp);
    }
	//with dp
	public int longestCommonSubsequence(char[] s1, char[] s2, int m, int n, int[][] dp){
		if(m == 0 || n == 0)
			return 0;
		if(dp[m][n] != -1)
			return dp[m][n];
		int ans = 0;
		if(s1[m-1] == s2[n-1])
			ans = 1+ longestCommonSubsequence(s1, s2, m-1,n-1, dp);
		else{
			ans = Math.max(longestCommonSubsequence(s1, s2, m,n-1, dp),
			longestCommonSubsequence(s1, s2, m-1,n, dp));
		}
		return dp[m][n] = ans;
	}
	// without dp
	public int longestCommonSubsequence(char[] s1, char[] s2, int m, int n){
		if(m == 0 || n == 0)
			return 0;
		int ans = 0;
		if(s1[m-1] == s2[n-1])
			ans = 1+ longestCommonSubsequence(s1, s2, m-1,n-1);
		else{
			ans = Math.max(longestCommonSubsequence(s1, s2, m,n-1),
			longestCommonSubsequence(s1, s2, m-1,n));
		}
		return ans;
	}
}