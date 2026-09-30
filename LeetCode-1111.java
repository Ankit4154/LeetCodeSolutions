// 1111. Maximum Nesting Depth of Two Valid Parentheses Strings
// https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] out = new int[n];

        int depth = 0;

        for(int i=0;i<n;i++){
            char c = seq.charAt(i);

            if(c == '('){
                depth++;
                out[i] = depth % 2;
            }else{
                out[i] = depth % 2;
                depth--;
            }
        }

        return out;
    }
}