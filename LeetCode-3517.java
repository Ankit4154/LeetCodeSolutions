// 3517. Smallest Palindromic Rearrangement I
// https://leetcode.com/problems/smallest-palindromic-rearrangement-i
class Solution {
    public String smallestPalindrome(String s) {
        int[] alpha = new int[26];
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			alpha[c - 'a']++;
		}
		StringBuilder left = new StringBuilder();
        StringBuilder mid = new StringBuilder();
		for(int i=0;i<26;i++){
			char c = (char) (i + 'a');
            for(int k=0;k<alpha[i]/2;k++){
                left.append(c);
            }
            if(alpha[i]%2!=0)
               mid.append(c);
        }
        StringBuilder out = new StringBuilder();
        out.append(left);
        out.append(mid);
        out.append(left.reverse());
		return out.toString();
    }
}