//395. Longest Substring with At Least K Repeating Characters
//https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
class Solution {
    public int longestSubstring(String s, int k) {
        int maxLength = findMaxSubString(s, 0, s.length(),k);
        return maxLength;
    }
    public int findMaxSubString(String s, int start, int end, int k){
        if(end-start < k) 
            return 0;
        Map<Character, Integer> alphaCount = new HashMap<>();
        for(int i=start;i<end;i++){
            char ch = s.charAt(i);
            alphaCount.put(ch, alphaCount.getOrDefault(ch,0)+1);
        }
		// optimization to early exit, no difference in time complexity, remains O(n^2)
        // boolean isPresent = false;
        // for(Map.Entry<Character, Integer> entry : alphaCount.entrySet()){
        //     if(entry.getValue() >= k){
        //         isPresent = true;
        //         break;
        //     }
        // }
        // if(!isPresent)
        //     return 0;
        for(int i=start;i<end;i++){
            char ch = s.charAt(i);
            if(alphaCount.get(ch) < k){
                int j = i+1;
                while(j<end && alphaCount.get(s.charAt(j)) < k)
                    j++;
                return Math.max(findMaxSubString(s,start,i,k), findMaxSubString(s,j,end,k));
            }
        }
        return end-start;
    }
}