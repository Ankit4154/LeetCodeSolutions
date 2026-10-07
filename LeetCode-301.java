// 301. Remove Invalid Parentheses
// https://leetcode.com/problems/remove-invalid-parentheses
class Solution {
	Set<String> out = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        int n = s.length();
		int open = 0, close = 0;
		// count additional open and closed paranthesis
		for(int i=0;i<n;i++){
			char c = s.charAt(i);
			if(c == '('){
				open++;
			}else if(c == ')'){
				if(open > 0)
					open--;
				else
					close++;
			}
		}
		solve(s, 0, open, close, 0, new StringBuilder());
        if(out.isEmpty())
            out.add("");
		return new ArrayList<>(out);
    }
	
	private void solve(String s, int ind, int open, int close, int balance, StringBuilder sb){
		
		
		// valid string check at the end of string
		if(ind == s.length()){
			if(open == 0 && close == 0 && balance == 0){
				out.add(sb.toString());
			}
			return;
		}
		
		char current = s.charAt(ind);
		
		// Remove
		// reduce open or close to compensate for the additional paranthesis
		if(current == '(' && open > 0){
			solve(s, ind + 1, open - 1, close, balance, sb);
		}else if(current == ')' && close > 0){
            solve(s, ind + 1, open, close - 1, balance, sb);
		}
		
		// Keep
		if(current == '('){
			sb.append(current);
			solve(s, ind + 1, open, close, balance + 1, sb);
			// backtrack
			sb.deleteCharAt(sb.length()-1);
		}else if(current == ')'){
            // We can only keep ')' if
            // there is an unmatched '('
			if(balance > 0){
				sb.append(current);
				solve(s, ind + 1, open, close, balance - 1, sb);
				// backtrack
				sb.deleteCharAt(sb.length()-1);
			}
		}else{
			sb.append(current);
			solve(s, ind + 1, open, close, balance, sb);
            sb.deleteCharAt(sb.length() - 1);
		}		
	}
}