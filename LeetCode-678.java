// 678. Valid Parenthesis String
// https://leetcode.com/problems/valid-parenthesis-string
class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == '('){
                low++;
                high++;
            }else if(c == ')'){
                low--;
                high--;
            }else{
                // '*' can be ')', empty or '('
                low--;
                high++;
            }

            // No possible interpretation can make it valid
            if(high < 0)
                return false;

            // Minimum balance cannot be negative
            low = Math.max(0, low);
        }

        return low == 0;
    }
}