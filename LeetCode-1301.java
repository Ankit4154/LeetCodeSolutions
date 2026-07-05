// 1301. Number of Paths with Max Score
// https://leetcode.com/problems/number-of-paths-with-max-score
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
		int n = board.size();
        int MOD = 1_000_000_007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        // -1 means unreachable
        for(int i=0;i<n;i++) {
            Arrays.fill(score[i], -1);
        }

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for(int i=n-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {

                char ch = board.get(i).charAt(j);

                if(ch == 'X')
                    continue;

                if(i == n - 1 && j == n - 1)
                    continue;

                int best = -1;
                long cnt = 0;

                // down
                if(i + 1 < n && score[i + 1][j] != -1) {
                    if(score[i + 1][j] > best) {
                        best = score[i + 1][j];
                        cnt = ways[i + 1][j];
                    }else if(score[i + 1][j] == best) {
                        cnt += ways[i + 1][j];
                    }
                }

                // right
                if(j + 1 < n && score[i][j + 1] != -1) {
                    if(score[i][j + 1] > best) {
                        best = score[i][j + 1];
                        cnt = ways[i][j + 1];
                    }else if(score[i][j + 1] == best) {
                        cnt += ways[i][j + 1];
                    }
                }

                // diagonal
                if(i + 1 < n && j + 1 < n && score[i + 1][j + 1] != -1) {
                    if(score[i + 1][j + 1] > best) {
                        best = score[i + 1][j + 1];
                        cnt = ways[i + 1][j + 1];
                    } else if(score[i + 1][j + 1] == best) {
                        cnt += ways[i + 1][j + 1];
                    }
                }

                if(best == -1)
                    continue;

                int val = (ch == 'E') ? 0 : ch - '0';

                score[i][j] = best + val;
                ways[i][j] = (int) (cnt % MOD);
            }
        }

        if(ways[0][0] == 0)
            return new int[] {0, 0};

        return new int[] {score[0][0], ways[0][0]};
		
		
    }
}