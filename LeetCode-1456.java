// 1456. Maximum Number of Vowels in a Substring of Given Length
// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length
class Solution {
    public int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, count = 0, maxLength = 0;
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        while(right < s.length()){
            char ch = s.charAt(right);
            if(set.contains(ch))
                count++;
            if(k == 1 && count > 0)
                return 1;
            maxLength = Math.max(maxLength,count);
            if(k != 1 && right - left + 1 == k){
                char c = s.charAt(left);
                if(set.contains(c))
                    count--;
                left++;
            }
            right++;
        }
        if(k == 1 && maxLength == 0)
            return 0;
        return maxLength;
    }
}