// 72. Edit Distance
// https://leetcode.com/problems/edit-distance
// optim
class Solution {
	Integer dp[][];
    public int minDistance(String word1, String word2) {
		dp = new Integer[word1.length()][word2.length()];
        return solve(word1.length()-1, word2.length()-1, word1, word2);
    }
	int solve(int n, int m, String word1, String word2){
		if(n < 0 || m < 0){
			if(n < 0){
				// add to compensate remaining characters in word2
				return m+1;
			}else if(m < 0){
				// delete to compensate with empty/all matched word2
				return n+1;
			}
		}
		if(dp[n][m] != null){
			return dp[n][m];
		}
		int min = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		// if characters match, no distance count
		if(word1.charAt(n) == word2.charAt(m)){
			min = solve(n-1, m-1, word1, word2);
		}else{
			// remove character
			int remove = 1 + solve(n-1, m, word1, word2);
			// replace character
			int replace = 1 + solve(n-1, m-1, word1, word2);
            // add character
            //word1 = word1+""+word2.charAt(m);
            int add = 1 + solve(n, m-1, word1, word2);
			min2 = Math.min(remove, replace);
            min2 = Math.min(min2, add);
		}
		return dp[n][m] = Math.min(min, min2);
	}
}
// TLE
class Solution {
    public int minDistance(String word1, String word2) {
        return solve(word1.length()-1, word2.length()-1, word1, word2);
    }
	int solve(int n, int m, String word1, String word2){
		if(n < 0 || m < 0){
			if(n < 0){
				// add to compensate remaining characters in word2
				return m+1;
			}else if(m < 0){
				// delete to compensate with empty/all matched word2
				return n+1;
			}
		}
		int min = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		// if characters match, no distance count
		if(word1.charAt(n) == word2.charAt(m)){
			min = solve(n-1, m-1, word1, word2);
		}else{
			// remove character
			int remove = 1 + solve(n-1, m, word1, word2);
			// replace character
			int replace = 1 + solve(n-1, m-1, word1, word2);
            // add character
            //word1 = word1+""+word2.charAt(m);
            int add = 1 + solve(n, m-1, word1, word2);
			min2 = Math.min(remove, replace);
            min2 = Math.min(min2, add);
		}
		return Math.min(min, min2);
	}
}