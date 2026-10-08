// 1021. Remove Outermost Parentheses
// https://leetcode.com/problems/remove-outermost-parentheses
class Solution {
    public String removeOuterParentheses(String s) {
        int depth = 0;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '('){
				if(depth != 0){
					sb.append(c);
				}
				depth++;
			}else{
				depth--;
				if(depth != 0){
					sb.append(c);
				}
			}
		}
		return sb.toString();
    }
}