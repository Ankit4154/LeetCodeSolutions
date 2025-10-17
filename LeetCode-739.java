// 739. Daily Temperatures
// https://leetcode.com/problems/daily-temperatures/description/
class Solution {
    // start with index 1
    // check if t[i] > t[i-1], if yes
    // then store t[i-1] = 1, as we found the required warmer temp for i-1
    // check if stack is not empty, while it is not
    // validate if the current temp at index i can also be a solution
    // for any previously stored temp indexes from stack
    // if t[i] > t[stack.peek] or t[stack.top]
    // then pop out the index at stack top and populate the value as i - ind
    // once all previous values are populated based on the i > stack.top condition
    // push the current largest value to the stack
    // if t[i] < t[i-1], the else part
    // check if i-1 is already present on stack top or not
    // if not present, push i-1 to the stack
    // set the current value for i-1 index as default 0,
    // until we find any furture warm day to reset this value.
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] out = new int[temperatures.length];
        for(int i=1;i<temperatures.length;i++){
            if(temperatures[i] > temperatures[i-1]){
                out[i-1] = 1;
                while(!stack.isEmpty()){
                    if(temperatures[i] > temperatures[stack.peek()]){
                        int ind = stack.pop();
                        out[ind] = i - ind;
                    }else{
                        break;
                    }
                }
                stack.push(i);
            }else{
                if(stack.peek() != i-1)
                    stack.push(i-1);
                out[i-1] = 0; // mark zero at current for now
            }
        }
        return out;
    }
}