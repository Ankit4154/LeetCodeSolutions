// 1190. Reverse Substrings Between Each Pair of Parentheses
// https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses
class Solution {
    public String reverseParentheses(String s) {
		int n = s.length();

        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Find matching parentheses
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);

            if(c == '('){
                stack.push(i);
            }else if(c == ')'){
                int open = stack.pop();
                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder out = new StringBuilder();

        int i = 0;
        int direction = 1;

        while(i >= 0 && i < n){

            char c = s.charAt(i);

            if(c == '(' || c == ')'){
                i = pair[i];
                direction = -direction;
            }else{
                out.append(c);
            }

            i += direction;
        }

        return out.toString();
    
    }
}
// init
class Solution {
    public String reverseParentheses(String s) {
		int n = s.length();
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<n;i++){
			char c = s.charAt(i);
            int ind = i;
			if(c == ')'){
                StringBuilder sb = new StringBuilder();
				// Pop until we find '('
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }

                // Remove '('
                if(!stack.isEmpty())
                    stack.pop();

                // Put reversed characters back
                for(char c1 : sb.toString().toCharArray()){
                    stack.push(c1);
                }
			}else{
				stack.push(c);
			}
		}
		StringBuilder out = new StringBuilder();
		for(Character k : stack){
			out.append(k);
		}
		return out.toString();
    }
}