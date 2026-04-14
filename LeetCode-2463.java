// 2463. Minimum Total Distance Traveled
// https://leetcode.com/problems/minimum-total-distance-traveled
class Solution {
    Long[][] dp;
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
		// sort by factory positions;
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        List<Integer> slots = new ArrayList<>();
        for(int[] f : factory){
            for(int k = 0; k < f[1]; k++){
                slots.add(f[0]);
            }
        }

        dp = new Long[robot.size()][slots.size()];
        return solve(0, 0, robot, slots);
    }

    long solve(int i, int j, List<Integer> robot, List<Integer> slots) {
        if(i == robot.size()) 
            return 0;
        if(j == slots.size()) 
            return (long) 1e15;

        if(dp[i][j] != null) 
            return dp[i][j];

        // skip
        long skip = solve(i, j + 1, robot, slots);

        // take
        long take = Math.abs(robot.get(i) - slots.get(j)) +
                    solve(i + 1, j + 1, robot, slots);

        return dp[i][j] = Math.min(skip, take);
    }
}