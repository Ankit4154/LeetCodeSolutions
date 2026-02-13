// 3714. Longest Balanced Substring II
// https://leetcode.com/problems/longest-balanced-substring-ii/
class Solution {

    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;

        // ---- Case 1: single character runs ----
        int run = 1;
        maxLen = 1;

        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == s.charAt(i - 1)){
                run++;
            }else{
                run = 1;
            }
            maxLen = Math.max(maxLen, run);
        }

        // ---- Case 2: equal pairs ----
        maxLen = Math.max(maxLen, checkPair(s, 'a', 'b'));
        maxLen = Math.max(maxLen, checkPair(s, 'a', 'c'));
        maxLen = Math.max(maxLen, checkPair(s, 'b', 'c'));

        // ---- Case 3: a = b = c ----
        maxLen = Math.max(maxLen, checkAllThree(s));

        return maxLen;
    }

    // Equal counts for two characters only
    private int checkPair(String s, char x, char y) {
        Map<Integer, Integer> map = new HashMap<>();
        int countX = 0, countY = 0;
        int maxLen = 0;

        map.put(0, -1);

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch != x && ch != y) {
                // restart segment safely (O(1))
                map = new HashMap<>();
                map.put(0, i);
                countX = 0;
                countY = 0;
                continue;
            }

            if(ch == x) 
                countX++;
            else 
                countY++;

            int diff = countX - countY;

            if (map.containsKey(diff)) {
                maxLen = Math.max(maxLen, i - map.get(diff));
            } else {
                map.put(diff, i);
            }
        }

        return maxLen;
    }

    // Equal counts for a, b, c
    private int checkAllThree(String s) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        // initial state: diffAB = 0, diffAC = 0 at index -1
        map.computeIfAbsent(0, k -> new HashMap<>()).put(0, -1);

        int a = 0, b = 0, c = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == 'a') 
                a++;
            else if(ch == 'b')
                b++;
            else
                c++;

            int diffAB = a - b;
            int diffAC = a - c;

            map.putIfAbsent(diffAB, new HashMap<>());
            Map<Integer, Integer> inner = map.get(diffAB);

            if(inner.containsKey(diffAC)){
                maxLen = Math.max(maxLen, i - inner.get(diffAC));
            } else {
                inner.put(diffAC, i);
            }
        }
        return maxLen;
    }
}

