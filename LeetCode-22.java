// 22. Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/
class Solution {
    // only add open paranthesis if openN < N
    // only add close paranthesis if closedN < openN
    // String is valid if openN == closedN == n
	// O(4^N / N^1/2)
    List<String> list = new ArrayList<>();
    String s = "";
    public List<String> generateParenthesis(int n) {
        return backTrack(0, 0, n);
    }

    public List<String> backTrack(int openN, int closedN, int n){
        if(openN == n && closedN == n){
            list.add(s);
            return list;
        }
        if(openN < n){
            s = s+"(";
            backTrack(openN+1, closedN, n);
            s = s.substring(0,s.length()-1);
        }
        if(closedN < openN){
            s = s+")";
            backTrack(openN, closedN+1, n);
            s = s.substring(0,s.length()-1);
        }
        return list;
    }
}