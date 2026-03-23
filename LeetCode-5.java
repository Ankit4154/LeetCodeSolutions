// 5. Longest Palindromic Substring
// https://leetcode.com/problems/longest-palindromic-substring
class Solution {
    int maxLen = 0;
    public String longestPalindrome(String s) {
        String ans = "";
        String res = "";
        for(int i=0;i<s.length();i++){
            res = getPalin(i, i, s);
            if(res.length() >= maxLen){
                ans = res;
            }
            res = getPalin(i, i+1, s);
            if(res.length() >= maxLen){
                ans = res;
            }
        }
        return ans;
    }
    String getPalin(int left, int right, String s){
        String res = "";
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            if((right - left + 1) >= maxLen){
                maxLen = right - left + 1;
                res = s.substring(left, right+1);
            }
            left--;
            right++;
        }
        return res;
    }
}