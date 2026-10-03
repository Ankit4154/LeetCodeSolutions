// 32. Longest Valid Parentheses
// https://leetcode.com/problems/longest-valid-parentheses/?envType=daily-question&envId=2026-10-03
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int ans = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);

            if(c == '('){
                stack.push(i);
            }else{
                stack.pop();

                if(stack.isEmpty()){
                    // Unmatched ')' becomes the new boundary
                    stack.push(i);
                }else{
                    // Current valid substring starts after stack.peek()
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }

        return ans;
    }
}
