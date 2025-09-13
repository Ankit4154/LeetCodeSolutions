// 2981. Find Longest Special Substring That Occurs Thrice I
// https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i	
class Solution {
    public int maximumLength(String s) {
        int maxLength = -1;
        StringBuilder currString = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i < s.length(); i++){
            char left = s.charAt(i);
            currString.setLength(0);
            for(int j=i; j < s.length(); j++){
                char right = s.charAt(j);
                if(right != left)
                    break;
                currString.append(right);
                map.put(currString.toString(), map.getOrDefault(currString.toString(), 0)+1);
            }
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() >= 3 )
                maxLength = Math.max(maxLength, entry.getKey().length());
        }
        return maxLength;
    }
}