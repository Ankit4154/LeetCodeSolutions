// 1784. Check if Binary String Has at Most One Segment of Ones
// https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones
class Solution {
    public boolean checkOnesSegment(String s) {
        boolean seenOne = false;
        boolean zeroAfterOne = false;

        for(char c : s.toCharArray()) {
            if(c == '1') {
                if(zeroAfterOne) return false;
                seenOne = true;
            } else { // c == '0'
                if(seenOne) zeroAfterOne = true;
            }
        }

        return true;
    }
}