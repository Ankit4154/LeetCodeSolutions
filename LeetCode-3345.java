// 3345. Smallest Divisible Digit Product I
// https://leetcode.com/problems/smallest-divisible-digit-product-i
class Solution {
    public int smallestNumber(int n, int t) {
        for(int i=n;i<101;i++){
			int num = i;
			int prod = 1;
			while(num > 0){
				int rem = num % 10;
				prod *= rem;
				num = num / 10;
			}
			if(prod % t == 0)
				return i;
		}
		
		return n;
    }
}