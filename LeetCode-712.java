// 712. Minimum ASCII Delete Sum for Two Strings
// https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings
class Solution {
	Integer[][] dp;
    public int minimumDeleteSum(String s1, String s2) {
		dp = new Integer[s1.length()][s2.length()];
		return solve(s1.length()-1, s2.length()-1, s1, s2);
    }
	int solve(int n, int m, String s1, String s2){
		if(n < 0){
			int min = 0;
			while(m >=0){
				int asc = (int)s2.charAt(m);
				min += asc;
				m--;
			}
			return min;
		}
		if(m < 0){
			int min = 0;
			while(n >=0){
				int asc = (int)s1.charAt(n);
				min += asc;
				n--;
			}
			return min;
		}
		if(dp[n][m] != null)
			return dp[n][m];
		int min = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		int c1 = (int)s1.charAt(n);
		int c2 = (int)s2.charAt(m);
		if(c1 == c2){
			min = solve(n-1, m-1, s1, s2);
		}else{
			// delete char of s1
			int delC1 = c1 + solve(n-1, m, s1, s2);
			// delete char of s2
			int delC2 = c2 + solve(n, m-1, s1, s2);
			min2 = Math.min(delC1, delC2);
		}
		return dp[n][m] = Math.min(min, min2);
	}
}