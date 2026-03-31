// 639. Decode Ways II
// https://leetcode.com/problems/decode-ways-ii/
class Solution {
    int MOD = 1_000_000_007;
    Integer[] dp;
    public int numDecodings(String s) {
        dp = new Integer[s.length()];
        return solve(0, s);
    }
    int solve(int ind, String s){
        if(ind >= s.length())
            return 1;
        if(dp[ind] != null)
            return dp[ind];
        long count = 0;
        // take one digit
        String sub = s.substring(ind, ind+1);
        int ways1 = decode(sub);
        if(ways1 > 0){
            count += (long)ways1 * solve(ind+1, s);
        }
        // take two digit
        if(ind + 1 < s.length()){
            sub = s.substring(ind, ind+2);
            int ways2 = decode(sub);
            if(ways2 > 0){
                count += (long)ways2 * solve(ind+2, s);
            }
        }
        return dp[ind] = (int)(count % MOD);
    }
    int decode(String s){
        if(s.isEmpty())
            return 0;
        if(s.startsWith("0"))
            return 0;
        if(s.length()==1){
            char c = s.charAt(0);
            if(c == '*')
                return 9;
            return 1;
        }
        
        char c1 = s.charAt(0);
        char c2 = s.charAt(1);
        // if both *
        if(c1 == '*' && c2 == '*'){
            return 15; //(11 to 19 + 21-26)
        }
        // if first * 
        if(c1 == '*'){
            if(c2 >= '0' && c2 <= '6') // 1x or 2x
                return 2;
            else
                return 1; // only 1x
        }
        // second *
        if(c2 == '*'){
            if(c1 == '1')
                return 9;
            if(c1 == '2')
                return 6;
            return 0;
        }
        // both digits
        if(c1 == '0')
            return 0;
        int num = (c1 - '0') * 10 + (c2 - '0');
        if(num >= 10 && num <= 26)
            return 1;

        return 0;
    }
}