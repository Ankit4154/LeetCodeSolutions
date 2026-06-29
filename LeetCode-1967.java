// 1967. Number of Strings That Appear as Substrings in Word
// https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word
class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for(String s : patterns){
            if(word.contains(s))
                count++;
        }
        return count;
    }
}