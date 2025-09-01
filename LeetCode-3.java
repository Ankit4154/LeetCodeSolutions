// 3. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(right<s.length()){
            char ch = s.charAt(right);
            if(!map.containsKey(ch)){
                map.put(ch, 1);
                maxLength = Math.max(maxLength, map.size());
            }else{
                map.put(ch, map.get(ch)+1);
                char start = s.charAt(left);
                while(start != ch){
                    if(map.containsKey(start)){
                        if(map.get(start) > 1)
                            map.put(start, map.get(start)-1);
                        else
                            map.remove(start);
                    }
                    left++;
                    start = s.charAt(left);
                }
                start = s.charAt(left);
                if(map.containsKey(start)){
                    if(map.get(start) > 1)
                        map.put(start, map.get(start)-1);
                    else
                        map.remove(start);
                }
                left++;
            }
            right++;
        }
        return maxLength;
    }
}