// 76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, count = 0, minLength = 1000000, startIndex = -1;
        // Store all the character values from t in a map with respective frequencies
        for(Character ch : t.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0)+1);
        while(right < s.length()){
            char ch = s.charAt(right);
            // process only the characters which are present in t / map
            if(map.containsKey(ch)){
                // if right pointer elements are present and greater than 0
                // increase the count to reach t.length
                if(map.get(ch) > 0)
                    count++;
                // as right pointer moves ahead, keep decreasing the value of map elements
                map.put(ch, map.get(ch)-1);
            }
            if(count == t.length()){
                while(count >= t.length()){
                    char l = s.charAt(left);
                    if(map.containsKey(l)){
                        // as left pointer moves ahead, keep increasing the value of map elements
                        map.put(l, map.get(l)+1);
                        // if the value becomes greater than 0 it means we are standing at a valid character
                        // which contributes to the count == t.length()
                        // Capture the minLength and startIndex at this point
                        if(map.get(l) > 0){
                            // ensure to record startIndex only when we find valid minLength
                            // avoid Math.min as we can't simultaneously validate startIndex value using that method
                            if(minLength > (right - left + 1) ){
                                minLength = right - left + 1;
                                startIndex = left;
                            }
                            // Reduce the count by 1 as we are abuot to move the left pointer ahead
                            count--;
                        }
                    }
                    // if count becomes less than t's length increment left pointer and break
                    if(count < t.length()){
                        left++;
                        break;
                    }
                    if(minLength > (right - left + 1) ){
                        minLength = right - left + 1;
                        startIndex = left;
                    }
                    left++;
                }
            }
            right++;
        }
        // if no starting index was found return empty string
        if(startIndex < 0)
            return "";
        return s.substring(startIndex,startIndex + minLength);
    }
}