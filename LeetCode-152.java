// 152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/
public class Solution {
    public int maxProduct(int[] nums){
        int res = nums[0];

        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            res = Math.max(res, cur);
            for(int j = i + 1; j < nums.length; j++){
                cur *= nums[j];
                res = Math.max(res, cur);
            }
        }

        return res;
    }
}