// 2125. Number of Laser Beams in a Bank
// https://leetcode.com/problems/number-of-laser-beams-in-a-bank
class Solution {
    public int numberOfBeams(String[] bank) {
        int sum = 0;
        if(bank.length == 1)
            return 0;
        int count = 0;
        int match = 0;
        for(int i=1;i<bank.length;i++){
            String s1 = bank[i-1];
            String s = bank[i];
            for(int j=0;j<s1.length();j++){
                if(s1.charAt(j) == '1')
                    count++;
                if(s.charAt(j) == '1')
                    match++;
            }
            if(match != 0){
                sum += count * match;
                count = 0;
                match = 0;
            }
        }
        return sum;
    }
}