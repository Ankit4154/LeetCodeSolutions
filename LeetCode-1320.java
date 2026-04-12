// 1320. Minimum Distance to Type a Word Using Two Fingers
// https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers
class Solution {
	Integer[][] memo;
    String word;

    public int minimumDistance(String word) {
        this.word = word;
        int n = word.length();
        
        // 27 = 0-25 letters + 26 means "unused"
        memo = new Integer[n][27];
        
        return dfs(1, 26); // start from index 1, other finger unused
    }

    private int dfs(int i, int other) {
        if(i == word.length())
            return 0;

        if(memo[i][other] != null)
            return memo[i][other];

        int curr = word.charAt(i) - 'A';
        int prev = word.charAt(i - 1) - 'A';

        // Choice 1: same finger
        int useSame = dist(prev, curr) + dfs(i + 1, other);

        //Choice 2: use other finger
        int cost = (other == 26) ? 0 : dist(other, curr);
        int useOther = cost + dfs(i + 1, prev);

        return memo[i][other] = Math.min(useSame, useOther);
    }
    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}