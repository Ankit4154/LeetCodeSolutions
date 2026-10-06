// 921. Minimum Add to Make Parentheses Valid
// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid
// optim, O(1) space
class Solution {
    public int minAddToMakeValid(String s) {
		int count = 0, depth = 0;
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '('){
				depth++;
			}else{
				if(depth == 0){
					count++;
					continue;
				}
				if(depth > 0)
					depth--;
			}
		}
		if(depth != 0)
			return count + Math.abs(depth);
        return count;
    }
}

// init, O(n) space
class Solution {
    public int minAddToMakeValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		int count = 0;
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '('){
				stack.push(c);
			}else{
				if(stack.isEmpty()){
					count++;
					continue;
				}
				if(stack.peek() == '(')
					stack.pop();
			}
		}
		if(!stack.isEmpty())
			return count + stack.size();
        return count;
    }
}