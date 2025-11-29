// 1092. Shortest Common Supersequence 
// https://leetcode.com/problems/shortest-common-supersequence/
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int[][] dp = new int[s1.length+1][s2.length+1];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i], -1);
		//int lcs = longestCommonSubsequence(s1, s2, s1.length, s2.length);
		//int lcs = longestCommonSubsequence(s1, s2, s1.length, s2.length, dp);
		// Build LCS table (BOTTOM UP)
        for(int i = 1; i <= s1.length; i++){
            for(int j = 1; j <= s2.length; j++){
                if(s1[i-1] == s2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
		return printLCS(s1, s2, s1.length, s2.length, dp);
    }
	
	public String printLCS(char[] s1, char[] s2, int m, int n, int[][] dp){
		StringBuilder sb = new StringBuilder();
		while(m > 0 && n > 0){
			if(s1[m-1] == s2[n-1]){
				sb.append(s1[m-1]);
				m--;
				n--;
			}else{
				if(dp[m-1][n] > dp[m][n-1]){
                    sb.append(s1[m-1]);
					m--;
				}else{
                    sb.append(s2[n-1]);
					n--;
				}
			}
		}
        while(m > 0){
             sb.append(s1[m-1]);
             m--;
        }
        while(n > 0){
             sb.append(s2[n-1]);
             n--;
        }
		return sb.reverse().toString();
	}
	
	public int longestCommonSubsequence(char[] s1, char[] s2, int m, int n) {
        // base case
		if(m == 0 || n == 0)
			return 0;
		int ans = 0;
		if(s1[m-1] == s2[n-1]){
			ans = 1 + longestCommonSubsequence(s1, s2, m-1, n-1);
		}else{
			ans = Math.max(longestCommonSubsequence(s1, s2, m-1, n),
			longestCommonSubsequence(s1, s2, m, n-1));
		}
		return ans;
    }
	
	public int longestCommonSubsequence(char[] s1, char[] s2, int m, int n, int[][] dp) {
        // base case
		if(m == 0 || n == 0)
			return 0;
		if(dp[m][n] != -1)
			return dp[m][n];
		int ans = 0;
		if(s1[m-1] == s2[n-1]){
			ans = 1 + longestCommonSubsequence(s1, s2, m-1, n-1, dp);
		}else{
			ans = Math.max(longestCommonSubsequence(s1, s2, m-1, n, dp),
			longestCommonSubsequence(s1, s2, m, n-1, dp));
		}
		return dp[m][n] = ans;
    }
}