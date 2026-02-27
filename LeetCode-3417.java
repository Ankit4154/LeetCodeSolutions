// 3417. Zigzag Grid Traversal With Skip
// https://leetcode.com/problems/zigzag-grid-traversal-with-skip/
class Solution {
    public List<Integer> zigzagTraversal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        List<Integer> result = new ArrayList<>();
        boolean take = true;  // start by taking first cell
        
        for (int i = 0; i < m; i++) {
            
            if (i % 2 == 0) {
                // left to right
                for (int j = 0; j < n; j++) {
                    if (take) {
                        result.add(grid[i][j]);
                    }
                    take = !take;   // flip after each cell
                }
            } else {
                // right to left
                for (int j = n - 1; j >= 0; j--) {
                    if (take) {
                        result.add(grid[i][j]);
                    }
                    take = !take;
                }
            }
        }
        
        
        
        
        return result;
    }
}
