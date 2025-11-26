// 516. Longest Palindromic Subsequence
// https://leetcode.com/problems/longest-palindromic-subsequence/description/
class Solution {
	// Input: s = "bbbab"
	// output : 4
	// bbbab 
	// babbb
	// lcs : bbbb, bbb, bb, b, a,5 possible LCS
	// length of max, bbbb = 4
    public int longestPalindromeSubseq(String s) {
        StringBuilder rev = new StringBuilder(s);
		rev.reverse();
		char[] s1 = s.toCharArray();
		char[] s2 = rev.toString().toCharArray();
		//return longestPalindromeSubseq(s1, s2, s1.length, s2.length);
		int[][] dp = new int[s1.length+1][s2.length+1];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i], -1);
		return longestPalindromeSubseq(s1, s2, s1.length, s2.length, dp);
    }
	// with dp/memoization
	public int longestPalindromeSubseq(char[] s1, char[] s2, int m, int n, int[][] dp){
		// base case
		if(m == 0 || n == 0)
			return 0;
		int ans = 0;
		if(dp[m][n] != -1)
			return dp[m][n];
		if(s1[m-1] == s2[n-1]){
			ans = 1 + longestPalindromeSubseq(s1, s2, m-1, n-1, dp);
		}else{
			ans = Math.max(longestPalindromeSubseq(s1, s2, m-1, n, dp), 
            longestPalindromeSubseq(s1, s2, m, n-1, dp));
		}
		return dp[m][n] = ans;
	}
	// without dp
	public int longestPalindromeSubseq(char[] s1, char[] s2, int m, int n){
		// base case
		if(m == 0 || n == 0)
			return 0;
		int ans = 0;
		if(s1[m-1] == s2[n-1]){
			ans = 1 + longestPalindromeSubseq(s1, s2, m-1, n-1);
		}else{
			ans = Math.max(longestPalindromeSubseq(s1, s2, m-1, n), 
            longestPalindromeSubseq(s1, s2, m, n-1));
		}
		return ans;
	}
}