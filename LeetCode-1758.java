// 1758. Minimum Changes To Make Alternating Binary String
// https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string
class Solution {
    public int minOperations(String s) {
        String finalString1 = "0";
        String finalString2 = "1";
        for(int i=1;i<s.length();i++){
            finalString1 += i%2;
        }
        for(int i=0;i<s.length()-1;i++){
            finalString2 += i%2;
        }
        int minA = 0, minB = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!= finalString1.charAt(i))
                minA++;
            if(s.charAt(i)!= finalString2.charAt(i))
                minB++;
        }
        return Math.min(minA, minB);
        // int count = 0;
		// "10010100"
		// char[] carr = s.toCharArray();
        // for(int i=1;i<carr.length;i++){
        //     if(carr[i-1] == carr[i]){
        //         carr[i] = (carr[i] == '1') ? '0' : '1';
        //         count++;
        //     }
        // }
        // return count;
    }
}