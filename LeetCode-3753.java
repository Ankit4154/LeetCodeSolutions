// 3753. Total Waviness of Numbers in Range II
// https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii
class Solution {

    class Pair {
        long cnt;
        long sum;

        Pair(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    char[] arr;
    Pair[][][][][] dp;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    long solve(long n) {

        if(n <= 0)
            return 0;

        arr = String.valueOf(n).toCharArray();

        int m = arr.length;

        dp = new Pair[m][11][11][17][2];

        return dfs(0, 10, 10, 0, 1).sum;
    }

    Pair dfs(int pos, int prev2, int prev1, int len, int tight) {

        if(pos == arr.length) {
            return new Pair(1, 0);
        }

        if(dp[pos][prev2][prev1][len][tight] != null) {
            return dp[pos][prev2][prev1][len][tight];
        }

        int limit = tight == 1 ? arr[pos] - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for(int d = 0; d <= limit; d++) {

            int nextTight = (tight == 1 && d == limit) ? 1 : 0;

            if(len == 0 && d == 0) {

                Pair next = dfs(pos + 1,10,10,0,nextTight);

                totalCnt += next.cnt;
                totalSum += next.sum;

            } else {

                int add = 0;

                if(len >= 2) {

                    if((prev1 > prev2 && prev1 > d)
                        || (prev1 < prev2 && prev1 < d)) {

                        add = 1;
                    }
                }

                int newPrev2;
                int newPrev1;

                if(len == 1) {
                    newPrev2 = prev1;
                    newPrev1 = d;
                } else if(len >= 2) {
                    newPrev2 = prev1;
                    newPrev1 = d;
                } else {
                    newPrev2 = 10;
                    newPrev1 = d;
                }

                Pair next = dfs(pos + 1,newPrev2,newPrev1,len + 1,nextTight
                );

                totalCnt += next.cnt;
                totalSum += next.sum + (long)add * next.cnt;
            }
        }

        return dp[pos][prev2][prev1][len][tight]
            = new Pair(totalCnt, totalSum);
    }
}