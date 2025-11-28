// 409. Longest Palindrome
// https://leetcode.com/problems/longest-palindrome/
class Solution {
    public int longestPalindrome(String s) {
        int[] freq = new int[128]; // ASCII supports both lowercase & uppercase

        for(char c : s.toCharArray()) {
            freq[c]++;
        }

        int length = 0;
        boolean hasOdd = false;

        for(int count : freq){
            if (count % 2 == 0){
                length += count;
            }else{
                length += count - 1; // use the even part
                hasOdd = true;
            }
        }

        // If we had any odd count, we can add exactly one character in the center
        if(hasOdd){
            length++;
        }

        return length;
    }
}