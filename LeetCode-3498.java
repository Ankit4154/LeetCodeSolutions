// 3498. Reverse Degree of a String
// https://leetcode.com/problems/reverse-degree-of-a-string
class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int ind = (int) 'z'- c +1;
            sum += (i+1) * ind;
        }
        return sum;
    }
}