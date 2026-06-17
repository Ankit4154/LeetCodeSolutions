// 3614. Process String with Special Operations II
// https://leetcode.com/problems/process-string-with-special-operations-ii
class Solution {
    public char processStr(String s, long k) {
        int n = s.length();

        long[] len = new long[n + 1];

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c >= 'a' && c <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if(c == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            } else if(c == '#') {
                len[i + 1] = len[i] * 2;
            } else { // %
                len[i + 1] = len[i];
            }
        }

        if(k >= len[n])
            return '.';

        long idx = k;

        for(int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if(c >= 'a' && c <= 'z') {
                if(idx == len[i])
                    return c;
            }
            else if(c == '#') {
                if(len[i] > 0)
                    idx %= len[i];
            }
            else if(c == '%') {
                idx = len[i] - 1 - idx;
            }
        }

        return '.';
    }
}