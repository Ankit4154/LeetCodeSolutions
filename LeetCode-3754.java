// 3754. Concatenate Non-Zero Digits and Multiply by Sum I
// https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i
// optim
class Solution {
    public long sumAndMultiply(int n) {
		long mul = 1, num = 0;
        int sum = 0;
        while(n > 0){
			int last = n % 10;
			if(last != 0){
				sum += last;
				num += last * mul;
				mul = mul*10;
			}
			n = n/10;
		}        
		return (long) sum * num;
		
    }
}
// init
class Solution {
    public long sumAndMultiply(int n) {
        String s = new String(n+"");
        String num = "";
        int sum = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c!='0'){
                num += c;
                sum += c-'0';
            }
        }
        if(num.isEmpty())
            return 0;
        long nu = Long.parseLong(num);
        return (long)sum * nu;
    }
}