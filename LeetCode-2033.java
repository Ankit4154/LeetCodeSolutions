// 2033. Minimum Operations to Make a Uni-Value Grid
// https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid
class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if((grid[i][j] - grid[0][0]) % x != 0)
                    return -1;
                list.add(grid[i][j]);
            }
        }
        Collections.sort(list);
        int median = list.get(list.size()/2);
        int ops = 0;
        for(int val : list){
            ops += Math.abs(median - val) / x;
        }
        return ops;
    }
}