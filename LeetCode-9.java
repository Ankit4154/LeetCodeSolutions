// 9. Palindrome Number
// https://leetcode.com/problems/palindrome-number/
class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers are not palindrome
        if (x < 0) 
            return false;
        
        // Numbers ending with 0 (but not 0 itself) cannot be palindrome
        if (x % 10 == 0 && x != 0) 
            return false;

        int reversedHalf = 0;

        // Reverse half of the number
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // For even digits: x == reversedHalf
        // For odd digits: x == reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
