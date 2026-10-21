// 3863. Minimum Operations to Sort a String
// https://leetcode.com/problems/minimum-operations-to-sort-a-string
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        if(n == 1)
            return 0;

        boolean sorted = true;
        char min = 'z', max = 'a';
        int minCount = 0, maxCount = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(i > 0 && s.charAt(i) < s.charAt(i - 1))
                sorted = false;

            min = (char)Math.min(min, c);
            max = (char)Math.max(max, c);
        }

        if(sorted)
            return 0;

        if(n == 2)
            return -1;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == min)
                minCount++;
            if(s.charAt(i) == max)
                maxCount++;
        }

        if(s.charAt(0) == min || s.charAt(n - 1) == max)
            return 1;

        if(s.charAt(0) == max && s.charAt(n - 1) == min
            && maxCount == 1 && minCount == 1)
            return 3;

        return 2;
    }
}