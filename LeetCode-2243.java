// 2243. Calculate Digit Sum of a String
// https://leetcode.com/problems/calculate-digit-sum-of-a-string
class Solution {
    public String digitSum(String s, int k) {
         while(s.length() > k) {
            StringBuilder sb = new StringBuilder();
            int sum = 0;

            for(int i = 0; i < s.length(); i++) {
                sum += s.charAt(i) - '0';

                if((i+1) % k == 0 || i == s.length()-1) {
                    sb.append(sum);
                    sum = 0;
                }
            }

            s = sb.toString();
        }

        return s;
    }
}
// naive
class Solution {
    public String digitSum(String s, int k) {
        int n = s.length();
		while(n > k){
			int sum = 0;
			StringBuilder sub = new StringBuilder();
			for(int i=0;i<n;i++){
				char c = s.charAt(i);
				sum += Integer.parseInt(c+"");
				if((i+1) % k == 0){
					sub.append(sum);
                    sum = 0;
				}
			}
			if(n % k != 0)
                sub.append(sum);
			s = sub.toString();
			n = sub.length();
		}
		return s;
    }
}