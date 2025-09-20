// 238. Product of Array Except Self
// https://leetcode.com/problems/product-of-array-except-self/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        int product = 1;
        output[0] = nums[0];
        // store all prefix / left elements product for each nums[i]
        for(int i=1;i<nums.length;i++){
            product *= nums[i-1];
            output[i] = product;
        }
        product = 1;
        int j = nums.length-1;
        // multiply all postfix / right elements product for each nums[i]
        for(int i=nums.length-2;i>=0;i--){
            product *= nums[j--];
            output[i] *= product;
            if(i == 0)
                output[0] = product;
        }
        return output;
    }
}