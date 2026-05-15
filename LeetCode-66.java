// 66. Plus One
// https://leetcode.com/problems/plus-one
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        if(digits[n-1] < 9){
            digits[n-1]++;
            return digits;
        }
        
        int i=n-1;
        for(;i>=0;i--){
            if(digits[i] == 9){
                digits[i] = 0;
            }else{
                digits[i]++;
                break;
            }
        }
        if(i<0){
            int[] newDigits = new int[n+1];
            for(int k=0;k<n;k++){
                newDigits[k+1]=digits[k];
            }
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }
}