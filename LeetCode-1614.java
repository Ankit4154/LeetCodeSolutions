// 1614. Maximum Nesting Depth of the Parentheses
// https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses
// optim
class Solution {
    public int maxDepth(String s) {
        int depth = 0;
		int max = 0;
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '('){
				depth++;
				max = Math.max(max, depth);
			}else if(c == ')')
				depth--;
		}
		return max;
    }
}

// init
class Solution {
    public int maxDepth(String s) {
        int n = s.length();
		Deque<Character> stack = new ArrayDeque<>();
		int max = 0;
		for(int i=0;i<n;i++){
			char c = s.charAt(i);
			if(c == '('){
				stack.push('(');
				max = Math.max(max, stack.size());
			}else if(c == ')')
				stack.pop();
		}
		return max;
    }
}