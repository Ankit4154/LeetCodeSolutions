// 3622. Check Divisibility by Digit Sum and Product
// https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product
class Solution {
    public boolean checkDivisibility(int n) {
        int k = n;
        int prod = 1, sum = 0;
        while(n > 0){
            int rem = n%10;
            sum += rem;
            prod *= rem;
            n = n / 10;
        }
        if(k%(sum+prod)==0){
            return true;
        }
        return false;
    }
}
