// 647. Palindromic Substrings
// https://leetcode.com/problems/palindromic-substrings/
class Solution {
	int count = 0;
    public int countSubstrings(String s) {
		for(int i=0;i<s.length();i++){
			getPalin(i, i, s); //odd
			getPalin(i, i+1, s); // even
		}
		return count;
    }
	void getPalin(int l, int r, String s){
		while(l >=0 && r < s.length() && s.charAt(l) == s.charAt(r)){
			count++;
			l--;
			r++;
		}
	}
}