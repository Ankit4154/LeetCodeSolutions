// 150. Evaluate Reverse Polish Notation
// https://leetcode.com/problems/evaluate-reverse-polish-notation/
// similar approach as below but modularized and clean
class Solution {
    public int evalRPN(String[] tokens) {
// postfix to infix
// if operand push to stack
// if operator, pop two elements
// put operator in between
// evaluate expression and put back in stack.
// O(n) time to read and O(n) space to store elements in stack
// adding and removing from stack can make non amortized execution time O(2N)
        Stack<Integer> stack = new Stack<>();
        for(String x : tokens){
            if("+-*/".contains(x)){
                int second = stack.pop();
                int first = stack.pop();
                stack.push(performOp(first, second, x));
            }else
                stack.push(Integer.parseInt(x));
        }
        return stack.pop();
    }

    public int performOp(int f, int s, String operator){
        switch(operator){
            case "*":
                return f * s;
            case "+":
                return f + s;
            case "-":
                return f - s;
            case "/":
                return f / s;
        }
        return 0;
    }
}
// without try catch solution
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String x : tokens){
            switch(x){
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case "-":
                    int s = stack.pop();
                    int f = stack.pop();
                    stack.push(f-s);
                    break;
                case "/":
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.push(first/second);
                    break;
                default: 
                    stack.push(Integer.parseInt(x));
            }
        }
        return stack.pop();
    }
}

// Solution with try catch, shouldn't be used as it increases 
// execution time with unwinding multiple internal call stacks 
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String x : tokens){
            try {
                Integer.parseInt(x);
                stack.push(Integer.parseInt(x));
            } catch (NumberFormatException e) {
                int second = stack.pop();
                int first = stack.pop();
                int result = performOp(first, second, x); 
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public int performOp(int f, int s, String operator){
        switch(operator){
            case "*":
                return f * s;
            case "+":
                return f + s;
            case "-":
                return f - s;
            case "/":
                return f / s;
        }
        return 0;
    }
}