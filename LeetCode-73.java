// 73. Set Matrix Zeroes
// https://leetcode.com/problems/set-matrix-zeroes/
class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
		int m = matrix[0].length;
		boolean rowZero = false;

		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(matrix[i][j] == 0){
					matrix[0][j] = 0;
					if(i > 0)
						matrix[i][0] = 0;
					else
						rowZero = true;
				}
			}
		}
		for(int i=1;i<n;i++){
			for(int j=1;j<m;j++){
				if(matrix[i][0] == 0 || matrix[0][j] == 0){
					matrix[i][j] = 0;
				}
			}
		}
        // zero out first column
        if(matrix[0][0] == 0) {
            for(int r = 0; r < n; r++){
                matrix[r][0] = 0;
            }
        }
        // zero out first row
		if(rowZero){
			for(int c=0;c<m;c++){
				matrix[0][c] = 0; 
			}
		}
		
    }
}

// O(n+m) space
class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
		int m = matrix[0].length;
		boolean[] row = new boolean[n];
		boolean[] col = new boolean[m];
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(matrix[i][j] == 0){
					row[i] = true;
					col[j] = true;
				}
			}
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(row[i] || col[j]){
					matrix[i][j] = 0;
				}
			}
		}
    }
}