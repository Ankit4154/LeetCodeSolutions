// 43. Multiply Strings
// https://leetcode.com/problems/multiply-strings/
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) 
            return "0";
        int n1 = num1.length();
        int n2 = num2.length();
        int[] res = new int[n1+n2];
        String prod = "";
        for(int i=n1-1;i>=0;i--){  
            for(int j=n2-1;j>=0;j--){
                int k = (int)(num1.charAt(i) - '0');
                int l = (int)(num2.charAt(j) - '0');
                int mul = k*l;
                int sum = mul + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;             
            }
        }
        int i = 0;
        // skip leading zeros
        while(i < res.length && res[i] == 0)
            i++;
        while(i < res.length)
            prod+=res[i++];
        return prod;
    }
}