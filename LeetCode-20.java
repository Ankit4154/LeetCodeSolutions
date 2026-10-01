// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/
// another
class Solution {
    public boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '(' || c == '{' || c == '[')
				stack.push(c);
			else{
				if(stack.isEmpty())
					return false;
				if(c == ')' && !(stack.peek() == '('))
					return false;
				else if(c == '}' && !(stack.peek() == '{'))
					return false;
				else if(c == ']' && !(stack.peek() == '['))
					return false;
                stack.pop();
			}
		}
		if(stack.isEmpty())
			return true;
		return false;
    }
}

class Solution {
    public boolean isValid(String s) {
        if(s.length() == 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{')
                stack.add(ch);
            else{
                if(stack.isEmpty()){
                    stack.add(ch);
                    continue;
                }
                char c = stack.peek();
                if(c == '(' && ch == ')'){
                    stack.pop();
                }else if(c == '[' && ch == ']'){
                    stack.pop();
                }else if(c == '{' && ch == '}'){
                    stack.pop();
                }else
                    stack.add(ch);
            }
        }
        if(!stack.isEmpty())
            return false;
        return true;
    }
}
