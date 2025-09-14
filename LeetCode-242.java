// 242. Valid Anagram
// https://leetcode.com/problems/valid-anagram/


class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        
        int[] map = new int[26];
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map[ch - 'a']++;
        }
        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            map[ch - 'a']--;
        }
        for(int x : map){
            if(x!=0)
                return false;
        }
        return true;
    }
}

// Hashmap operations are slow when compared to arrays for alphabets(26), known/fixed size data structures. Always try using array approach first than hashmap
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            if(map.containsKey(ch)){
                if(map.get(ch) > 1)
                    map.put(ch, map.get(ch)-1);
                else
                    map.remove(ch);
            }
        }
        if(map.size()==0)
            return true;
        else
            return false;
    }
}

