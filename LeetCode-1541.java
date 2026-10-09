// 1541. Minimum Insertions to Balance a Parentheses String
// https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
// optim, O(1) space
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
		int open = 0, close = 0;
		int depth = 0;
		for(int i=0;i<n;i++){
			char c = s.charAt(i);
			if(c == '('){
				depth++;
				continue;
			}
			if(depth == 0){
				if(i+1 < n){
					char next = s.charAt(i+1);
					if(next == ')'){
						i++;
					}else{
						close++;
					}
					open++;
				}else{
					close += 2;
				}
				continue;
			}
			
			if(depth > 0){
				if(i+1 < n){
					char next = s.charAt(i+1);
					if(next == ')'){
						i++;
					}else{
						close++;
					}
				}else{
					close++;
				}
				depth--;
			}
		}
		if(depth > 0){
			close += 2 * depth;
		}
		return close + open;
    }
}

// init, O(n) space
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
		int open = 0, close = 0;
		Deque<Character> stack = new ArrayDeque<>();
		for(int i=0;i<n;i++){
			char c = s.charAt(i);
			if(c == '('){
				stack.push(c);
				continue;
			}
			if(stack.isEmpty()){
				if(i+1 < n){
					char next = s.charAt(i+1);
					if(next == ')'){
						i++;
					}else{
						close++;
					}
					open++;
				}else{
					close += 2;
				}
				continue;
			}
			
			if(stack.peek() == '('){
				if(i+1 < n){
					char next = s.charAt(i+1);
					if(next == ')'){
						i++;
					}else{
						close++;
					}
				}else{
					close++;
				}
				stack.pop();
			}
		}
		if(!stack.isEmpty()){
			close += 2 * stack.size();
		}
		return close + open;
    }
}