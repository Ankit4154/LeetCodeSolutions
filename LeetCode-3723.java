// 3723. Maximize Sum of Squares of Digits
// https://leetcode.com/problems/maximize-sum-of-squares-of-digits
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // maximum we can achieve is 9 at every digit place
		// if sum is greater than that, then not possible
		if(sum > 9 * num)
			return ""; 
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=num;i++){
			int digit = Math.min(9, sum);
            sb.append(digit);
            sum -= digit;
		}
		return sb.toString();
		
    }
}