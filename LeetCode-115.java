// 115. Distinct Subsequences
// https://leetcode.com/problems/distinct-subsequences
class Solution {
	int dp[][];
    public int numDistinct(String s, String t) {
		dp = new int[s.length()][t.length()];
		for(int[] d : dp)
			Arrays.fill(d, -1);
		return solve(0, 0, s, t);
    }
	
	int solve(int ind, int tInd, String s, String t){
		if(tInd == t.length()){
			return 1;
		}
		if(ind == s.length())
			return 0;
		
		if(dp[ind][tInd] != -1)
			return dp[ind][tInd];
		
		char c = s.charAt(ind);
		char targetChar = t.charAt(tInd);
		if(c != targetChar){
            // skip
			dp[ind][tInd] = solve(ind+1, tInd, s, t);
		}else{
			dp[ind][tInd] = solve(ind+1, tInd+1, s, t)  // use
            + solve(ind+1, tInd, s, t); // skip
		}
		
        return dp[ind][tInd];
	}
}
// TLE
class Solution {
	int globalCount = 0;
    public int numDistinct(String s, String t) {
		solve(0, 0, 0, s, t);
		return globalCount;
    }
	
	void solve(int ind, int count, int tInd, String s, String t){
		if(count == t.length()){
			globalCount++;
			return;
		}
		if(tInd == t.length())
			return;
		
		for(int i=ind;i<s.length();i++){
			char c = s.charAt(i);
			char targetChar = t.charAt(tInd);
			if(c != targetChar){
				continue;
			}
			solve(i+1, count+1, tInd+1, s, t);
		}
        return;
	}
}