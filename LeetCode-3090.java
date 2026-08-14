// 3090. Maximum Length Substring With Two Occurrences
// https://leetcode.com/problems/maximum-length-substring-with-two-occurrences
// dynamic space optim, removed hashmap
class Solution {
    public int maximumLengthSubstring(String s) {
        int[] alpha = new int[26];
        int left = 0, right = 0;
        int max = 1;
        while(right < s.length()){
            int rightLetter = s.charAt(right) - 'a';
            alpha[rightLetter]++;
            while(alpha[rightLetter] > 2){
                int leftLetter = s.charAt(left) - 'a';
                alpha[leftLetter]--;
                left++;
            }
            max = Math.max(max, right-left+1);
            right++;
        }
        return max;
    }
}
// init
class Solution {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int max = 1;
        while(right < s.length()){
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0)+1);
            while(map.get(ch) > 2){
                char l = s.charAt(left);
                map.put(l, map.get(l)-1);
                left++;
            }
            max = Math.max(max, right-left+1);
            right++;
        }
        return max;
    }
}