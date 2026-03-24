// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/
class Solution {
    Integer[] dp;
    public int numDecodings(String s){
        dp = new Integer[s.length()];
        return solve(0, s);
    }
	int solve(int ind, String s){
		if(ind >= s.length())
			return 1;
        if(dp[ind] != null)
            return dp[ind];
        int count = 0;
		for(int i = ind; i < Math.min(ind + 2, s.length()); i++){
            String partA = s.substring(ind, i + 1);
            if(decode(partA)){
                count = count + solve(i + 1, s);
            }
        }
        // Another approach
        // take 1 digit
        // if(decode(s.substring(ind, ind + 1))){
        //     count += solve(ind + 1, s);
        // }

        // take 2 digits (only if possible)
        // if(ind + 1 < s.length() && decode(s.substring(ind, ind + 2))){
        //     count += solve(ind + 2, s);
        // }
        return dp[ind] = count;
	}
	boolean decode(String s){
		if(s.isEmpty())
			return false;
		if(s.startsWith("0"))
			return false;
		int c = Integer.parseInt(s);
		return (c >= 1 && c <= 26);
	}
}