// 424. Longest Repeating Character Replacement
// https://leetcode.com/problems/longest-repeating-character-replacement
class Solution {
    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int maxFrequency = 0;
        int length = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(right<s.length()){
            char r = s.charAt(right);
            map.put(r, map.getOrDefault(r, 0)+1);
            length++;
            maxFrequency = Math.max(maxFrequency,map.get(r));
            if(length-maxFrequency <= k){
                maxLength = Math.max(maxLength, length);
            }else{
                char l = s.charAt(left);
                if(map.get(l) > 1)
                    map.put(l, map.get(l)-1);
                else
                    map.remove(l);
                maxFrequency--;
                length--;
                left++;
            }
            right++;
        }
        return maxLength;
    }
}