// 3411. Maximum Subarray With Equal Products
// https://leetcode.com/problems/maximum-subarray-with-equal-products
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int left = 0; left < n; left++) {
            int prod = 1;
            int g = 0;
            int l = 1;

            for(int right = left; right < n; right++) {
                int num = nums[right];

                prod *= num;
                g = gcd(g, num);
                l = (l / gcd(l, num)) * num;

                if(prod == g * l) {
                    max = Math.max(max, right-left+1);
                }
            }
        }
        return max;
    }

    int gcd(int a, int b){
        while(b!=0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}