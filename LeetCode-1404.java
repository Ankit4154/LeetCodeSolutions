// 1404. Number of Steps to Reduce a Number in Binary Representation to One
// https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        // Traverse from right to left (ignore MSB at index 0)
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';
            
            if (bit + carry == 1) {
                // odd -> add 1 + divide
                steps += 2;
                carry = 1;
            } else {
                // even -> divide only
                steps += 1;
            }
        }
        
        // If carry remains at MSB
        return steps + carry;
    }
}