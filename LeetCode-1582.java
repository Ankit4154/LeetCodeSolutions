// 1582. Special Positions in a Binary Matrix
// https://leetcode.com/problems/special-positions-in-a-binary-matrix/
class Solution {
    public int numSpecial(int[][] mat) {
        int count = 0;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j] == 1){
                    if(!foundOneCol(mat, i, j+1) && !foundOneRow(mat, 0,j,i))
                        count++;
                    break;
                }
            }
        }
        return count;
    }
    public boolean foundOneCol(int[][] mat, int row, int col){
        boolean foundOne = false;
        while(col<mat[0].length){
            if(mat[row][col] == 1){
                foundOne = true;
                break;
            }
            col++;
        }
        return foundOne;
    }
    public boolean foundOneRow(int[][] mat, int row, int col, int r){
        boolean foundOne = false;
        while(row<mat.length){
            if(row != r && mat[row][col] == 1){
                foundOne = true;
                break;
            }
            row++;
        }
        return foundOne;
    }
}